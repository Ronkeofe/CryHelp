

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asakpere
 */
//import static PoisonModel.residence;
//import static PoisonModel.residence;
//import static PoisonModel.residence;
import java.io.File;
import jxl.write.Number;
import java.sql.*;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class LDiversity {

    public static long lDivTime;
    static long startTime;
    static long endTime;
    public static int lValue = 3; //this is the l diversity value
    static String sensitive_attribute = "`CRIME_TYPE`";
    static String quasi_attribute = "`RESIDENCE`";
    //static String[] query_result;
    static String[][] GeneralizedDataForAnonymization;
    static int EC_Length;  // EC_Length stores the total number of records in the buffer. 
    // i need EC_Length for function populateDatabase_EquivalenceClass
    //static String [][] GeneralizedDataForAnonymization = new String[query_result.length/2][3];
    //String[][] result = new String[query_result.length/2][noOfColumns];

    public static String[][] distinct_ldiverse(int kAnon, int ldiverse) {
        String[][] dbInfo;
        // dbInfo = RetrieveDatabaseInfo();

        //i may need this:  System.out.println("Gen Step is "  + GenStep);
        //the main thing now is the conversion
        for (int i = 0; i < 9; i++) {

            //I MAY NEED THIS
            //  Supp_table[i][j]= Generalizer.generalizeRESIDENCE(residence[0], Phase3GUI.GenStep, QuasiID.RESIDENCE); 
        }
        return null;
    }

    public static String[][] RetrieveDatabaseInfo() {
        // get a DBManager
        DBManager dbManager = new DBManager();

        // create sql to get sensitive and quasi values
        String sql = "SELECT " + quasi_attribute + " ," + sensitive_attribute
                + "FROM Crime";
        String[] query_result = dbManager.runQuery(sql);
        int noOfColumns = 2;
        String[][] result = new String[query_result.length / 2][noOfColumns];

        // output of sql is placed into a two-dimensional array that can now be manipulated for k-anonymity and l-diversity
        int value = -1;
        for (int i = 0; i < result.length; i++) {
            System.out.print("content of query is ");
            for (int j = 0; j < result[i].length; j++) {
                value++;
                result[i][j] = query_result[value];
                System.out.print(result[i][j] + ", ");
            }
            System.out.println();
        }

        return result;

        //System.out.println("length of l-diversity query_result is " + query_result.length);
    }

    public static String[][] AnonymizedInfo() {
        // get a DBManager
       
        DBManager dbManager = new DBManager();

        // create sql to get sensitive and quasi values
        String sql = "SELECT " + quasi_attribute + " ," + sensitive_attribute
                + "FROM Crime";
        String[] query_result = dbManager.runQuery(sql);
        int noOfColumns = 2;
        String[][] result = new String[query_result.length / 2][noOfColumns]; // divided by 2 because the two columns are combined together
        // EC_Length stores the total number of records in the buffer
        EC_Length = result.length;

        // output of sql is placed into a two-dimensional array that can now be manipulated for k-anonymity and l-diversity
        int value = -1;
        for (int i = 0; i < result.length; i++) {
            System.out.print("content of query is ");
            for (int j = 0; j < result[i].length; j++) {
                value++;
                result[i][j] = query_result[value];
                System.out.print(result[i][j] + ", ");
            }
            System.out.println();
        }
        
        GeneralizedDataForAnonymization = new String[result.length][3];
        startTime = System.currentTimeMillis();
        System.out.println("L div start time is  " + startTime);

        for (int i = 0; i < result.length; i++) // for (int i = 0; i <size; i++)
        //come to think of it, this stupid for loop method is being used because the waiting time has bee sorted
        {
            for (int j = 0; j < 3; j++) //3 or result[i+1].length because i am considering 3 columns
            // for(int j =0;j<result[i].length;j++)  //3 because i am considering 3 columns
            {
                if (j == 2) {  // 2 because i am considering the third column which is the generalized cell
                    //if (j == 1) {  // 1 because i am considering the second column which is the generalized cell

                    GeneralizedDataForAnonymization[i][j] = Generalizer.generalizeRESIDENCE(result[i][j - 2], Phase3GUI.GenStep, QuasiID.RESIDENCE);
                    // j- 1 because i need to find the generalized value of the immediate past cell
                    // j- 2 because i need to find the generalized value of two immediate past cell
                    System.out.println("Residence Generalization of GeneralizedDataForAnonymization is  " + GeneralizedDataForAnonymization[i][j]);

                } // if (j == 0 || j == 2 )
                else {
                    GeneralizedDataForAnonymization[i][j] = result[i][j];
                }
            }

        }

        //return result; 
        return GeneralizedDataForAnonymization;
    }

    public static void populateDatabase_EquivalenceClass() //public static void populateDatabase_EquivalenceClass( Statement statement, String filename )
    {

        Statement statement = null;
        DBManager dbManager = new DBManager();
        Connection connection;

        String USER = "root";
        String MYSQL_URL = "jdbc:mysql://localhost/mysql";
        try {
            connection = DriverManager.getConnection(MYSQL_URL, USER, null);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // DatabaseCreator.createEquivalenceClassTable();
        //DatabaseCreator.createTableAnonymization();
        for (int i = 0; i < EC_Length; i++) { // for (int i = 0; i <size; i++)
            //come to think of it, this stupid for loop method is being used because the waiting time has bee sorted

            int j = 0;
            // for(int j =0;j<3;j++)  //3 or result[i+1].length because i am considering 3 columns
            // for(int j =0;j<result[i].length;j++)  //3 because i am considering 3 columns

            // INSERT INTO createEquivalenceClassTable(RESIDENCE, CRIME_TYPE, ANONYMIZED_RESIDENCE) VALUES (GeneralizedDataForAnonymization[i][j], GeneralizedDataForAnonymization[i][j++], GeneralizedDataForAnonymization[i][j++]);
            String first = GeneralizedDataForAnonymization[i][j];
            String second = GeneralizedDataForAnonymization[i][++j];
            String third = GeneralizedDataForAnonymization[i][++j];

            try {
                // create sql to get sensitive and quasi values
                String sql_insert_stmt = "INSERT INTO Phase3.EquivalenceClassTable "
                        + "VALUES ('" + first + "','" + second + "','" + third + "')";
                //statement.runQuery(sql_insert_stmt );
                if (statement != null) {
                    statement.execute(sql_insert_stmt);
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

            //DBManager insert_stmt = new DBManager();
            // create sql to get sensitive and quasi values
            //String sql = "SELECT * FROM Crime";
            // insert_stmt.runQuery(sql);
        }

        String[] result = null;
        try {

//              Count(Distinct user_id) As countUsers
//   , Count(site_id) As countVisits
//   , site_id As site
            String sql_query = "SELECT * FROM EquivalenceClassTable";

            //String sql_query2 = "SELECT DISTINCT(COUNT(CRIME_TYPE)), ANONYMIZED_RESIDENCE FROM EquivalenceClassTable GROUP BY ANONYMIZED_RESIDENCE";
            result = dbManager.runQuery(sql_query);

        } catch (Exception e) {

            e.printStackTrace();
        }

//        for(int k =0;k<result.length;k++){
//            System.out.println("Result "+result[k]);
//        }
        prepareForLDiversity(dbManager);

    }

    private static void prepareForLDiversity(DBManager dBManager) {

        String[] result = null;
        try {
           /* startTime = System.currentTimeMillis();
            System.out.println("L div start time is  " + startTime);*/
            String sql_query = "SELECT COUNT(DISTINCT CRIME_TYPE), COUNT(ANONYMIZED_RESIDENCE),ANONYMIZED_RESIDENCE FROM EquivalenceClassTable GROUP BY ANONYMIZED_RESIDENCE";

            //String sql_query2 = "SELECT DISTINCT(COUNT(CRIME_TYPE)), ANONYMIZED_RESIDENCE FROM EquivalenceClassTable GROUP BY ANONYMIZED_RESIDENCE";
            result = dBManager.runQuery(sql_query);
        } catch (Exception e) {

            e.printStackTrace();
        }

//        //print out the result...lets see
//        for(int i = 0;i<result.length;i++){
//            System.out.println("Values are "+ result[i]);
//        
//        }
        String[][] forLDiversity = new String[result.length / 3][3];

        int value = -1;
        for (int i = 0; i < forLDiversity.length; i++) {
            System.out.print("Eventual result is ");
            for (int j = 0; j < forLDiversity[i].length; j++) {
                value++;
                forLDiversity[i][j] = result[value];
                System.out.print(forLDiversity[i][j] + ", ");
            }
            System.out.println();
        }

        confirmLDiversity(forLDiversity, lValue, Phase3GUI.kAnon);

    }

    private static void confirmLDiversity(String[][] forLDiversity, int lValue, int kValue) {
        
        int eqClassSatLDiv = 0;
        int eqClassSuppRecords = 0;
        int eqClassNotSatLDiv = 0;
        int totalNoOfRecords = 0;

        for (int j = 0; j < forLDiversity.length; j++) {
            int k = 0;
            int countOfCrimeType = Integer.parseInt(forLDiversity[j][k]);
            int equivalenceClassCount = Integer.parseInt(forLDiversity[j][++k]);

            totalNoOfRecords += equivalenceClassCount;
            if (countOfCrimeType >= lValue && equivalenceClassCount >= kValue) {
                eqClassSatLDiv += 1; //l-diversity is satisified for that equivalence class

            } else {
                eqClassSuppRecords += equivalenceClassCount;
                eqClassNotSatLDiv += 1; //l-diversity is not satisified for that equivalence class
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("L div end time is  " + endTime);
        lDivTime = endTime - startTime;
        System.out.println("L div Time is  " + lDivTime);
        String[][] lDivData = new String[eqClassSatLDiv][2]; //two dim array to keep anonymized residence against the count of anonymize res.
        
         int x =0;
        
        for (int m = 0; m < forLDiversity.length; m++) {
            int k = 0;
            int countOfCrimeType = Integer.parseInt(forLDiversity[m][k]);
            int equivalenceClassCount = Integer.parseInt(forLDiversity[m][++k]);
            if (countOfCrimeType >= lValue && equivalenceClassCount >= kValue) {
               int y=0;
               lDivData[x][y] = forLDiversity[m][2];
               lDivData[x][++y] = forLDiversity[m][1];
               x++;
              
            }
        }
        int noOfEqClass = forLDiversity.length;

        //call info_loss with lDivData
        InfoLoss infoLoss = new InfoLoss();
        infoLoss.infoLossForLDiv(lDivData);
         int j = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File("/home/asakpere/Desktop/ldiversity.xls"));
            WritableWorkbook copy = Workbook.createWorkbook(new File("/home/asakpere/Desktop/ldiversity.xls"), workbook);
            WritableSheet sheet = copy.getSheet(0);
           
            Number rround = new Number(j, Phase3GUI.round, Phase3GUI.round);
            sheet.addCell(rround);
            j++;
            Number totalNoOfRec = new Number(j, Phase3GUI.round, totalNoOfRecords);
            sheet.addCell(totalNoOfRec);
            j++;
            Number numberOfEqClass = new Number(j, Phase3GUI.round, noOfEqClass);
            sheet.addCell(numberOfEqClass);
            j++;
            Number eqClassSatisfied = new Number(j, Phase3GUI.round, eqClassSatLDiv);
            sheet.addCell(eqClassSatisfied);
            j++;
            Number eqClassNotSatisfied = new Number(j, Phase3GUI.round, eqClassNotSatLDiv);
            sheet.addCell(eqClassNotSatisfied);
            j++;

            Number eqClassSuppRec = new Number(j, Phase3GUI.round, eqClassSuppRecords);
            sheet.addCell(eqClassSuppRec);
            j++;
            Number lDivT = new Number(j, Phase3GUI.round, lDivTime);
            sheet.addCell(lDivT);
            j++;
            Number lDivInfoLoss = new Number(j, Phase3GUI.round, InfoLoss.roundloss);
            sheet.addCell(lDivInfoLoss);
            j++;
            Number bufferLife = new Number(j, Phase3GUI.round, Phase3GUI.existence_time);
            sheet.addCell(bufferLife);
             copy.write();
            copy.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (eqClassNotSatLDiv > 0) {

        }

    }

//    private static void writeLDivResult(int totalNoOfRecords, int noOfEqClass,
//            int eqClassSatLDiv, int eqClassNotSatLDiv) {
//        try {
//            Workbook workbook = Workbook.getWorkbook(new File("/home/asakpere/Desktop/ldiv.xls"));
//            WritableWorkbook copy = Workbook.createWorkbook(new File("/home/asakpere/Desktop/ldiv.xls"), workbook);
//            WritableSheet sheet = copy.getSheet(0);
//            int j = 0;
//            Number rround = new Number(j, Phase3GUI.round, Phase3GUI.round);
//            sheet.addCell(rround);
//            j++;
//            Number totalNoOfRec = new Number(j, Phase3GUI.round, totalNoOfRecords);
//            sheet.addCell(totalNoOfRec);
//            j++;
//            Number numberOfEqClass = new Number(j, Phase3GUI.round, noOfEqClass);
//            sheet.addCell(numberOfEqClass);
//            j++;
//            Number eqClassSatisfied = new Number(j, Phase3GUI.round, eqClassSatLDiv);
//            sheet.addCell(eqClassSatisfied);
//            j++;
//            Number eqClassNotSatisfied = new Number(j, Phase3GUI.round, eqClassNotSatLDiv);
//            sheet.addCell(eqClassNotSatisfied);
//            j++;
//
//            Number eqClassSuppRec = new Number(j, Phase3GUI.round, eqClassSuppRecords);
//            sheet.addCell(eqClassSuppRec);
//            j++;
//
//        } catch (Exception e) {
//
//        }
//    }
    //convertdb_info converts a two dimensional query result into one dimensional 
    // well-understood now
    public static hold_info[] convertDbInfo(String[][] queryResult) {
        hold_info[] result = new hold_info[queryResult.length];

        for (int i = 0; i < queryResult.length; i++) {
            String[] d = queryResult[i];
            hold_info t = new hold_info(d);
            result[i] = t;
        }
        return result;
    }

    public static void print() {
        hold_info temp;

        // dbInfo = RetrieveDatabaseInfo();
        //i may need this:  System.out.println("Gen Step is "  + GenStep);
        //the main thing now is the conversion
        for (int i = 0; i < 9; i++) {

        }

    }

//holds the values of our query result: residence and crime type     
// using gen steps try to  find anonymization          
}
