
import java.util.Vector;
import java.util.*;

public class GeneralizationTable {
    //public GeneralizationTable() {}

    public GeneralizationTable(int kAnon, QuasiID... list) {
        this();
        selectedIds = list;
        this.kAnon = kAnon;

        // set up the table if k anon is greater than 1,
        // otherwise if k is 1, then we're just returning 
        // all data untouched (or ungeneralized)		
        if (this.kAnon > 1) {
            setClusterList();
            setKTupleList();

            if (!kTupleListIsEmpty) {
                setAttributeLists();
                fillGenTable();
            }
        }
    }

    public GeneralizationTable() {
        this.clusterList = null;
        this.kTupleList = null;
        this.selectedIds = null;
        this.genTable = null;
        this.clusterListAttributes = null;
        this.kAnon = 0;
        this.kTupleListIsEmpty = true;
        this.clusterListSizes = null;
    }

    public boolean testSolution(GeneralizationSteps solution, int maxSuppression) {
        int suppressionCount = 0;
        if (!kTupleListIsEmpty && (this.kAnon > 1)) {
            for (int i = 0; i < kTupleList.length; i++) {
                int numHits = genTable[i].testSolution(solution, i);
                if (numHits < kAnon) {
                    suppressionCount++;
                    if (suppressionCount > maxSuppression) {
                        return false; // we hit the suppression limit, stop checking solution
                    }

                    // If the remaining rows to check are less than the suppression
                    // limit, there's no need to check them...
                    int suppressionAvailable = maxSuppression - suppressionCount;
                    int remainingRows = genTable.length - i;
                    if (suppressionAvailable >= remainingRows) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public String[] getAttributesToGeneralize(GeneralizationSteps solution) {
        ArrayList<String> output = new ArrayList<String>();

        if (!kTupleListIsEmpty && (this.kAnon > 1)) {
            // load output with ALL attributes and number of appearances of 
            // these attributes in the data.
            // For example one row of this output array might look like:
            // "3,13,9,1980"
            // This means "13,9,1980" appears 3 times in our data
            for (int i = 0; i < this.clusterListAttributes.length; i++) {
                String line = this.clusterListSizes[i] + ",";
                for (String s : this.clusterListAttributes[i]) {
                    line += s + ",";
                 //   System.out.println("same data is " + line);
                }
                output.add(line);
            }

            // zero out all suppressed attributes
            int end = kTupleList.length - 1;
            //length_suppres = new int(2);
            for (int i = end; i > -1; i--) {
                int numHits = genTable[i].testSolution(solution, i);
                if (numHits < kAnon) {
                    //   int iter = 0;
                    // System.out.println("Number of hits is " + numHits);
                    //  System.out.println("suppressed attribute is " + kTupleList[i]);
                    // This tuple must be suppressed.
                    suppressedRecord = output.remove(kTupleList[i]);
                   // System.out.println("supp rec " + suppressedRecord);
                   stringList.add(suppressedRecord.substring(2, (suppressedRecord.length()-1)));
                   length_suppres.add(suppressedRecord.length()-1);
                    //str.substring(2, str.length())
                   //stringListCrime.add(suppressedRecord.substring(5, 6));
                    System.out.println(" suppressedRecord is " + suppressedRecord);
                   // System.out.println("crime is " + stringListCrime);
                  //  System.out.println("age is " + stringList);
                  //  System.out.println("supp rec length is " + length_suppres);
                   // System.out.println("age 1 is " + suppressedRecord.substring(2, suppressedRecord.length()));
                    //stringListCrime.remove(",");
                    // System.out.println(stringList);
                    // output.remove(kTupleList[i]);
                                   /* if (iter == 0) {
                    suppressedRecord=output.remove(kTupleList[i]);
                    System.out.println("1st Supp is " + suppressedRecord);}
                    else
                    {
                    
                    suppressedRecord1=output.remove(kTupleList[i]);
                    System.out.println("2nd Supp is " + suppressedRecord1);
                    }*/
                    //  iter++;
                    // System.out.println("suppressedRecord[0] " + suppressedRecord[0]);
                }
            }
        }

        String[] data = new String[output.size()];
        for (int i = 0; i < output.size(); i++) {
            data[i] = output.get(i);
           // System.out.println("Number of hits is " + data[i]);
        }

        return data;
    }

    public String toString() {

        if (kTupleListIsEmpty) {
            return "Table is empty";
        } else {
            String newLine = System.getProperty("line.separator");
            String output = "\t\t";
            // get cluster names
            for (String s : clusterList) {
                output += s + "  ";
            }
            output += newLine;
            // get row data
            for (int i = 0; i < kTupleList.length; i++) {
                output += kTupleList[i] + "  " + genTable[i];
                output += newLine;
            }
            return output;
        }
    }

    private void fillGenTable() {
        genTable = new GeneralizationRow[kTupleList.length];

        for (int i = 0; i < kTupleList.length; i++) {
            genTable[i] = new GeneralizationRow(i);
        }
    }

    // Assuming that there are more than 0 actual product ids
    private void setClusterList() {
        // get a DBManager
        DBManager dbManager = new DBManager();

        String allQuasiIds = getCommaSeparatedQuasiIdList();
        String alreadyMatched = "----";
        String clusters = "";

        // create sql to get all Ids
        String sql = "SELECT " + QuasiID.ID_NUMBER.attributeName + " "
                + "FROM Crime";


        // place all Ids into string array
        String[] allIds = dbManager.runQuery(sql);
        //System.out.println(allIds.length);

        // test each id in ProductIds array to find matches.
        // i may need this
        int IdPtr = 0;
        while (IdPtr < allIds.length) {

            // get the next productId to find samples for
            String sampleId = allIds[IdPtr];
            allIds[IdPtr] = alreadyMatched; // set to "----"

            // get the quasiId values for the sample
            //selecting the residence...thats the only thing in the qausi identifier
            sql = "SELECT " + allQuasiIds + " "
                    + "FROM Crime "
                    + "WHERE " + QuasiID.ID_NUMBER.getAttributeName() + "='" + sampleId + "'";
            String[] sampleIdQuasiIdValues = dbManager.runQuery(sql);

            // get the product id(s) for everything that matches the values
            // found above
            sql = "SELECT " + QuasiID.ID_NUMBER.getAttributeName() + " "
                    + "FROM Crime "
                    + "WHERE " + getQuasiIdValuePairs(sampleIdQuasiIdValues);
            
            //getting the ID_NUMBER of all rows with a given residence 
            String[] IDs = dbManager.runQuery(sql);
            /*if (IDs.length <= 1 ){System.out.println(IDs.length);}
            else
            {
            System.out.println(IDs.length);
            System.out.println(IDs[0]);
            System.out.println(IDs[1]);}*/

            // set matching productIds in allProductIds array to "----"
            // we don't want to get duplicate data and re-match again
            if (IDs.length > 1) {
                for (String s : IDs) {
                    for (int i = 0; i < allIds.length; i++) {
                        if (allIds[i].equals(s)) {
                            allIds[i] = alreadyMatched;
                            //i = allIds.length; // found it, break the loop
                        }
                    }
                }
            }

            String subCluster = "";
            for (String s : IDs) {
               // System.out.println("subcluster is  " + s);
                subCluster += "," + s; 					// add matches to sub-cluster string...
                //System.out.println("subcluster is  " + s);
            }
            System.out.println("Here is the subcluster  " + subCluster.substring(1));
            clusters += subCluster.substring(1) + ";"; 	// ... and end this round with a ";"
            System.out.println("cluster is  " + clusters);
            IdPtr++;

            // iterate to the next index that is not "----" and be sure
            // to stay within length of allProductIds array
            while ((IdPtr < allIds.length)
                    && (allIds[IdPtr].equals(alreadyMatched))) {
                IdPtr++; // advance the pointer, index is matched already
            }
        }

        // close that db connection...
        dbManager.closeConnection(false);

       
        this.clusterList = clusters.split(";");
        //this is shaky
       // System.out.println("cluster is  " + clusterList[0] + clusterList[1]);
        ///
        
        this.setClusterListSizes();
    }

    private String getQuasiIdValuePairs(String[] values) {
        String output = "";
        for (int i = 0; i < selectedIds.length; i++) {
            output += " AND " + selectedIds[i].getAttributeName() + "='" + values[i] + "'";
        }
        //System.out.println("output is  " + output);
        output = output.substring(5);
        //System.out.println("output is  " + output);
        return output;

    }

    private String getCommaSeparatedQuasiIdList() {
        String allQuasiIds = "";
        for (QuasiID id : this.selectedIds) {
            // System.out.println(selectedIds[0]);
            // System.out.println(selectedIds[1]);
            allQuasiIds += "," + id.getAttributeName();
        }
        allQuasiIds = allQuasiIds.substring(1); // remove first comma
        return allQuasiIds;
    }

    private void setKTupleList() {
        Vector<Integer> kTuplePtrs = new Vector<>();
        Vector<Integer> MetkTuplePtrs = new Vector<>();  //Useless
        Integer knotmet;
        for (int i = 0; i < clusterList.length; i++) {
            if (clusterList[i].split(",").length < this.kAnon) {
                kTuplePtrs.add(i);
                //System.out.println("kTuplePtrs is  " + kTuplePtrs.get(i));
            } else {
                MetkTuplePtrs.add(i);
                // System.out.println("kTuplePtrs is  " + MetkTuplePtrs.get(i));
                //System.out.println("output is  " + output);
            }
        }

        if (kTuplePtrs.isEmpty()) {
            kTupleListIsEmpty = true;
        } else {
            kTupleList = new int[kTuplePtrs.size()];
            for (int i = 0; i < kTuplePtrs.size(); i++) {
                kTupleList[i] = kTuplePtrs.get(i);
               // System.out.println("K tuple is " + kTupleList[i]);
            }
            kTupleListIsEmpty = false;
        }
    }

    private void setAttributeLists() {
        //just one seleceted id though
        clusterListAttributes = new String[clusterList.length][selectedIds.length];

        DBManager dbManager = new DBManager();

        String quasiIds = "";
        for (QuasiID id : selectedIds) {
            quasiIds += "," + id.getAttributeName();
        }
        quasiIds = quasiIds.substring(1);

        for (int i = 0; i < clusterList.length; i++) {
            String clusterName = clusterList[i];
            if (clusterName.contains(",")) {
                clusterName = clusterList[i].substring(0, clusterList[i].indexOf(","));
            }
            System.out.println("Cluster name is " +clusterName);
            String[] attributes = dbManager.runQuery("SELECT " + quasiIds + " "
                    + "FROM Crime "
                    + "WHERE " + QuasiID.ID_NUMBER.getAttributeName()
                    + "='" + clusterName + "'");
            for (int j = 0; j < selectedIds.length; j++) {
                clusterListAttributes[i][j] = attributes[j];
                System.out.println("clusterListAttributes[i][j] is  " + attributes[j]);
            }
        }

        dbManager.closeConnection(false);
    }

    private void setClusterListSizes() {
        //A cluster in the long run is basically all rows with the same residence in a buffer
        clusterListSizes = new int[clusterList.length];
        for (int i = 0; i < clusterList.length; i++) {
            clusterListSizes[i] = clusterList[i].split(",").length;
            System.out.println("cluster list is " + clusterList[i]);
            System.out.println("cluster size is " + clusterListSizes[i]);
        }
    }
    private String[] clusterList;
    public static String suppressedRecord;
    // public  static String suppressedRecord1;
    public static ArrayList<String> stringList = new ArrayList<>();
    public static ArrayList<String> stringListCrime = new ArrayList<>();
    private int[] clusterListSizes;
    private String[][] clusterListAttributes;
    private int[] kTupleList;				// pointer to kTuples within clusterList
    private QuasiID[] selectedIds;
    private GeneralizationRow[] genTable;
    private int kAnon;
    private boolean kTupleListIsEmpty;
    public static ArrayList<Integer> length_suppres = new ArrayList<Integer>();
    //public int[] length_suppres;

    private class GeneralizationRow {

        private GeneralizationSteps[] genStepsRow;

        public GeneralizationRow(int singleTupleIndex) {
            genStepsRow = new GeneralizationSteps[clusterList.length];
        }

        private void setup(int kTupleIndex, int genStepIndex) {
            String kTuple = clusterList[kTupleList[kTupleIndex]];
            genStepsRow[genStepIndex] = new GeneralizationSteps();
            if (kTuple.equals(clusterList[genStepIndex])) {
                int steps = 0;
                for (int j = 0; j < selectedIds.length; j++) {
                    genStepsRow[genStepIndex].setGenSteps(selectedIds[j], steps);
                }
            } else {
                for (int j = 0; j < selectedIds.length; j++) {
                    int steps = 0;
                    String attribute1 = clusterListAttributes[kTupleList[kTupleIndex]][j];
                    String attribute2 = clusterListAttributes[genStepIndex][j];
                    // System.out.println(attribute1);
                    // System.out.println(attribute2);
                    steps = Generalizer.getNumGeneralization(attribute1, attribute2, selectedIds[j]);
                    genStepsRow[genStepIndex].setGenSteps(selectedIds[j], steps);
                    // System.out.println("Set gen steps  " +  steps);
                }
            }
        }

        public int testSolution(GeneralizationSteps solution, int kTuplePosition) {
            int numSolutions = 0;

            for (int i = 0; i < genStepsRow.length; i++) {

                if (genStepsRow[i] == null) {
                    setup(kTuplePosition, i);
                }

                if (solution.dominates(genStepsRow[i].getAllInfoLossLevels())) {
                    numSolutions += clusterListSizes[i];	// cluster could be more than one
                }

                // If the number of solutions found is greater than or equal
                // to the desired k anon, stop testing solutions, we're good
                // to go.
                if (numSolutions >= kAnon) {
                    i = genStepsRow.length;
                }
                //  System.out.println("Number of solns is   " + numSolutions);
            }

            return numSolutions;
        }

        public String toString() {
            String output = "";
            for (int i = 0; i < genStepsRow.length; i++) {
                if (genStepsRow[i] == null) {
                    output += "[null]\t";
                } else {
                    output += genStepsRow[i].toString() + "\t";
                }
            }
            return output;
        }
    }
}