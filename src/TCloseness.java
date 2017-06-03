/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 */
//import static LDiversity.lValue;

public class TCloseness {

    static String sensitive_attribute = "`CRIME_TYPE`";
    static String quasi_attribute = "`RESIDENCE`";

    public static String[][] t_closeness(int kAnon, float tvalue) {
        for (int i = 0; i < 9; i++) {

        }
        return null;
    }

    public static void setTclosenessEquivalenceClass() {
        // get a DBManager
        DBManager dbManager = new DBManager();

        // create sql to get all Ids
        String sql = "SELECT " + sensitive_attribute + " " + quasi_attribute
                + "FROM Crime";
        String[] query_result = dbManager.runQuery(sql);
        System.out.println("tcloseness query_result  " + query_result.length);
        prepareForTCloseness(dbManager);
    }

    // select crime_type, anonymized_residence as anon_res, count(anonymized_residence), count(anonymized_residence)/(select count(anonymized_residence) from Phase3.EquivalenceClassTable where anonymized_residence = anon_res group by anonymized_residence) as EqClass_saDist from Phase3.EquivalenceClassTable group by crime_type, anonymized_residence;
    // SELECT DISTINCT CRIME_TYPE, Anonymized_residence, COUNT(CRIME_TYPE), COUNT(CRIME_TYPE)/(SELECT COUNT(CRIME_TYPE) FROM Phase3.EquivalenceClassTable) as sens_att_dist FROM Phase3.EquivalenceClassTable GROUP BY CRIME_TYPE, anonymized_residence;
    private static void prepareForTCloseness(DBManager dBManager) {

        String[] result = null;

        // get a DBManager
        DBManager dbManager = new DBManager();

        try {

            String sql = "SELECT " + sensitive_attribute + "FROM Crime";
            // String anon_res;
            //String sqlEqvClass = "SELECT anonymized_residence FROM EquivalenceClassTable where anonymized_residece = " + anon_res;
            String[] query_result = dbManager.runQuery(sql);
            //String sql_query = "SELECT DISTINCT CRIME_TYPE, COUNT(DISTINCT CRIME_TYPE), COUNT(DISTINCT CRIME_TYPE)/" + query_result.length + "FROM EquivalenceClassTable GROUP BY CRIME_TYPE";
            String sql_query = "SELECT DISTINCT CRIME_TYPE, COUNT(CRIME_TYPE), COUNT(CRIME_TYPE)/(SELECT COUNT(CRIME_TYPE) FROM EquivalenceClassTable) FROM EquivalenceClassTable GROUP BY CRIME_TYPE";
            //String sql_query2 = "SELECT DISTINCT(COUNT(CRIME_TYPE)), ANONYMIZED_RESIDENCE FROM EquivalenceClassTable GROUP BY ANONYMIZED_RESIDENCE";

            result = dBManager.runQuery(sql_query);
        } catch (Exception e) {

            e.printStackTrace();
        }

        String[][] bufferSenDis = new String[result.length / 3][3];

        int value = -1;
        for (int i = 0; i < bufferSenDis.length; i++) {
            System.out.print("Eventual result is ");
            for (int j = 0; j < bufferSenDis[i].length; j++) {
                value++;
                bufferSenDis[i][j] = result[value];
                System.out.print(bufferSenDis[i][j] + ", ");
            }
            System.out.println();
        }

        /*  for (int i = 0; i < size; i++) {
                waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
                System.out.println("The length is "+ waiting_time.length + " i.e "+ waiting_time.length +" instances of "+ splitsuppressedRecArray[i]+" found.");
                for(String time:waiting_time){
                 System.out.println("The waiting time for suppressed record " + splitsuppressedRecArray[i] + " is " +time);
               }
         }*/
        // confirmLDiversity(forTCloseness,lValue,Phase3GUI.kAnon);
    }

    private static void confirmTCloseness(String[][] forTCloseness, int kValue, long t) {

        int eqClassSatLDiv = 0;
        int eqClassSuppRecords = 0;
        int eqClassNotSatLDiv = 0;
        for (int j = 0; j < forTCloseness.length; j++) {
            int k = 0;
            int countOfCrimeType = Integer.parseInt(forTCloseness[j][k]);
            int equivalenceClassCount = Integer.parseInt(forTCloseness[j][++k]);
            if (countOfCrimeType >= t && equivalenceClassCount >= kValue) {
                eqClassSatLDiv += 1;
            } else {
                eqClassSuppRecords += equivalenceClassCount;
                eqClassNotSatLDiv += 1;

            }

        }
        if (eqClassNotSatLDiv > 0) {

        }

    }

}
