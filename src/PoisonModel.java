/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 *
 */
import static java.lang.Math.pow;
import java.sql.*;
import java.util.ArrayList;
//import static java.lang.Math;

public class PoisonModel {

    //int[] times;
    public int K, k;
    public static long RemainingTime;
    // public static String[] waiting_time;// = new String(); //new String();
    public static String[] waiting_time;
    // = new String[GeneralizationTable.stringList.size()]; 
    String wt;
    public static double prob;
    public int loop = 1;
    // public static String[] age;
    public static String[] residence;
    public static String[] poi_residence;
    public static String residence_temp;
    // public static String age_temp;
    public static double lamda;
    public static double infoloss_counter = 0.0;
    public static double suppressed_counter = 0.0;
    public static int supp_poison = 0;
    public static int supp_no_poison = 0;
    public static String[] expired_rows;
    public static String[] expired_row;
    private String[][] Supp_table = new String[GeneralizationTable.stringList.size()][4]; //why four? four because i am considering 4 columns
    long former_existence_time;

    InfoLoss a = new InfoLoss();
    String[] waiting_times;
    String[] arrival_time;
    public static String[] number_of_rows;
    String inclause;
    String residence_inclause;
    Statement stmt;
    DBManager dbMngr = new DBManager();
    int maximum_waiting;
    int k_counter = 0;
    public static String[] supp_rec;
    // public static String[] supp_rec2;

    private static void BubbleSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            for (int x = 1; x < num.length - i; x++) {
                if (num[x - 1] > num[x]) {
                    int temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;

                }
            }
        }
    }

    public int maximum(int[] times) {
        int max = times[0];
        for (int i = 1; i < times.length; i++) {
            if (max < times[i]) {
                max = times[i];
            }
        }
        return max;
    }

    public int minimum(int[] time) {
        int min = time[0];
        for (int i = 1; i < time.length; i++) {
            if (min > time[i]) {
                min = time[i];
            }
        }
        return min;
    }

    public void delete_unsupp() {

        GeneralizationTable.stringList.stream().forEach((listItem) -> {
            System.out.println("String list include: " + listItem);
        });
        //GeneralizationTable.stringList - Represents the suppressed records;
        int size = GeneralizationTable.stringList.size();
        String[] suppressedRecArray = new String[size];
        String[] splitsuppressedRecArray = new String[size];
        String[] returnedArray = GeneralizationTable.stringList.toArray(suppressedRecArray);

        inclause = "";
        residence_inclause = "";
        String time_inclause = "";
        number_of_rows = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Phase3.Crime"));
        System.out.println("The number of rows/tuples in this buffer is " + number_of_rows.length);

        //am not understanding...ok, i grab
        for (int i = 0; i < size; i++) {

            splitsuppressedRecArray[i] = returnedArray[i];
            if (i == size - 1) {
                inclause = inclause.concat(splitsuppressedRecArray[i]);
            } else {
                inclause = inclause.concat(splitsuppressedRecArray[i]).concat(",");
            }
        }
          for(int i=0;i<number_of_rows.length;i++)
            {
                System.out.println("Here is a what we have  "+ number_of_rows[i]);
            }
        

        if (Phase3GUI.anonymised_length != a.noOfTuplesInBuffer) { //okay, a.noOfTuplesInBuffer can actually be number_of_rows.length

            // supp_rec = (GeneralizationTable.suppressedRecord).split(",");
           int[] waitingTimeOfSuppRecords = new int[size];
            
            for(int i=0;i<splitsuppressedRecArray.length;i++){
                System.out.println("Here is a suppressed record "+ splitsuppressedRecArray[i]);
            }

            ArrayList<String> read = new ArrayList<>();
            int waitingTimeIndexPointer = 0;  //helps to point to the proper record waiting time to read

            for (int i = 0; i < size; i++) {
                waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
                System.out.println("The length is "+ waiting_time.length + " i.e "+ waiting_time.length +" instances of "+ splitsuppressedRecArray[i]+" found.");
                for(String time:waiting_time){
                 System.out.println("The waiting time for suppressed record " + splitsuppressedRecArray[i] + " is " +time);
               }
               

//                if (read.contains(splitsuppressedRecArray[i])) {
//                    waitingTimeIndexPointer += 1;
//                    System.out.println("The waiting time for suppressed record " + splitsuppressedRecArray[i] + " is " + waiting_time[waitingTimeIndexPointer]);
//                    waitingTimeOfSuppRecords[i] = Integer.parseInt(waiting_time[waitingTimeIndexPointer]);
//
//                    if (i == size - 1) {
//                        time_inclause = time_inclause.concat(waiting_time[waitingTimeIndexPointer]);
//                    } else {
//                        time_inclause = time_inclause.concat(waiting_time[waitingTimeIndexPointer]).concat(",");
//                    }
//                } else {
                    
                    waitingTimeOfSuppRecords[i] = Integer.parseInt(waiting_time[0]);

                    if (i == size - 1) {
                        time_inclause = time_inclause.concat(waiting_time[0]);
                    } else {
                        time_inclause = time_inclause.concat(waiting_time[0]).concat(",");
                    }
                //read.add(splitsuppressedRecArray[i]);
            }

            //time_in_clause holds the waiting time of the suppressed records as being comma seperated
            BubbleSort(waitingTimeOfSuppRecords);
            String Gen;

            //for each of the suppresed record, i need the residence, generation of that residence and waiting time in a tabular form
            System.out.println("Analysis based on the suppresed records");

            for (int i = size - 1; i < size; i--) // for (int i = 0; i <size; i++)
            //come to think of it, this stupid for loop method is being used because the waiting time has bee sorted
            {
                if (i == -1) {
                    break;
                }
                for (int j = 0; j <= 3; j++) {  //why 3?

                    if (j == 0) {

                        residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Crime WHERE ExpectedWaitingTime = (%s) ", waitingTimeOfSuppRecords[i]));
                        Supp_table[i][j] = residence[0];
                        System.out.println("Residence is  " + Supp_table[i][j] + " and its waiting time is " + waitingTimeOfSuppRecords[i]);

                    }
                    if (j == 1) {

                        Supp_table[i][j] = Generalizer.generalizeRESIDENCE(residence[0], Phase3GUI.GenStep, QuasiID.RESIDENCE);
                        System.out.println("Residence Generalization is  " + Supp_table[i][j]);

                    }
                    if (j == 2) {

                        Supp_table[i][j] = String.valueOf(waitingTimeOfSuppRecords[i]);
                        System.out.println("Waiting Time is  " + Supp_table[i][j]);

                    }
                    //may need remove but hold on
                    if (j == 3) {

                        Supp_table[i][j] = "";
                    }
                }

            }
           
             //from here is really doing nothing, ejor e help mi
            int counter = 0;
            String temp = null;  //appears to be useful
            if (size - 1 != -1) {
                temp = Supp_table[size - 1][1];
            }
            
            for (int i = size - 1; i <= size; i--) {
                if (i == -1) {
                    break;
                }
                for (int j = 0; j <= 3; j++) {

                    if (temp.equals(Supp_table[i][j])) {
                        //  k_counter++;
                        // System.out.println("temp counter is " + k_counter);
                    }

                    //    System.out.println(Supp_table[i][j]);
                    // for (int i = 0; i <size; i++)
                }
            }
            
            //to here

            for (int i = 0; i < size; i++) {
                System.out.println("Waiting time of suppressed records in sorted order");
                System.out.println(waitingTimeOfSuppRecords[i]);

            }
            
            former_existence_time = Phase3GUI.existence_time;

            for (int i = size - 1; i < size; i--) {

                if (i == -1) {
                    break;
                }
                // if ((5000 - int_waitingtime[i] ) < 2000 + Phase3GUI.anon_time)
                if ((Phase3GUI.existence_time - waitingTimeOfSuppRecords[i]) < 2000 + Phase3GUI.anon_time) //if ((Phase3GUI.existence_time - (System.currentTimeMillis() - long_waitingtime[i]) ) < 2000 + Phase3GUI.temp_total_time)
                {

                    //  dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[i]));
                    // prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
                    prob = 0.0;
                    lamda = 0.0;
                    Supp_table[i][1] = " ";
                    // System.out.println("Didnt go thru poisson " + Phase3GUI.anon_time);
                } else {

                    temp = Supp_table[i][1];
                    System.out.println("temp is " + temp + " which i think is generalised form of "+ Supp_table[i][0]);
                   //dont know what this is doing, really
                    for (int ii = size - 1; ii <= size; ii--) {
                        if (ii == -1) {
                            break;
                        }
                        for (int j = 0; j <= 3; j++) {
                            if (temp.equals(Supp_table[ii][j]) && temp != (Supp_table[ii][0])) //   if (temp.equals(Supp_table[ii][j]))
                            //if (temp.equals(Supp_table[ii][1]) && "".equals(Supp_table[ii][3]))    
                            {
                                k_counter++;
                                //  Supp_table[ii][3] = "****";
                                System.out.println("temp counter is " + k_counter);
                            }
                        }

                    }
                    System.out.println("Anon time is " + Phase3GUI.anon_time);
                    //Phase3GUI.existence_time - (System.currentTimeMillis() - long_waitingtime[i])
                    prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(waitingTimeOfSuppRecords[i]) - Phase3GUI.anon_time, Phase3GUI.kAnon, Phase3GUI.existence_time);
                    //prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
                    //prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
                }
                //System.out.println(waiting_time[i] + int_waitingtime[i]);
                if (prob >= 0.3) //  if (prob >= 0.5 || lamda>= 0.5)
                {
                        //deleting the unsupressed records here, the time_inclause contains the suppressed records
                    dbMngr.execUpdate(String.format("DELETE FROM Crime WHERE ExpectedWaitingTime NOT IN (%s) ", time_inclause));
                    //  poi_residence = dbMngr.runQuery(("SELECT RESIDENCE FROM Crime")); 
                    //System.out.println("Poi Res is  " + poi_residence.length);
                    // supp_poison = supp_poison + poi_residence.length;
                    // System.out.println("supp_poison is  " + supp_poison);

                    Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(waitingTimeOfSuppRecords[i]) - Phase3GUI.anon_time;

                    for (int j = 0; j < size; j++) {

                        String sql = "UPDATE Crime " + " SET ExpectedWaitingTime = '" + Long.valueOf(Phase3GUI.existence_time + waitingTimeOfSuppRecords[j] + Phase3GUI.anon_time) + "'" + "WHERE ExpectedWaitingTime = '" + waitingTimeOfSuppRecords[j] + "'";
                        // String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[j] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[j] + "'" ; 
                        dbMngr.execUpdate(sql);

                    }
                    break;
                } else {

                    Supp_table[i][1] = " ";
                    residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Phase3.Crime WHERE ExpectedWaitingTime = (%s) ", waitingTimeOfSuppRecords[i]));
                    //return generalizeRESIDENCE(RESIDENCE, numGeneralizations, QuasiID.RESIDENCE);
                    // System.out.println("Value is  " + residence);
                    residence_temp = residence[0];
                    System.out.println("Residence to watch out for is  " + residence_temp);
                    //String Gen;
                    Gen = Generalizer.generalizeRESIDENCE(residence_temp, Phase3GUI.GenStep, QuasiID.RESIDENCE);
                    System.out.println("Generalization of Residence is  " + Gen);
                    //age_temp = age[0];
                    ReusableCluster.reusbale();
                    String reuse = ReusableCluster.search(ReusableCluster.reusable, residence_temp);
                    if (reuse == null || reuse.isEmpty()) {
                        suppressed_counter++;
                        if (prob == 0.0) {
                            supp_no_poison++;
                            //System.out.println("Supp Rec without Poisson  " + supp_no_poison++);
                        }

                    } else {

                        infoloss_counter++;

                    }

                    ReusableCluster.reuse_return = "";
                    reuse = null;
                    dbMngr.execUpdate(String.format("DELETE FROM Crime WHERE ExpectedWaitingTime IN (%s)", waitingTimeOfSuppRecords[i]));
                    if (i == 0 && prob < 0.6) //   if(i == 0 && prob < 0.9 && lamda < 0.9)
                    {

                        dbMngr.execUpdate("DELETE " + " FROM Crime");
                        // prob = 0.00;
                        // lamda = 0.00;
                        RemainingTime = 0;
                        dbMngr.clearTableData();
                        Phase3GUI.existence_time = 5000;
                        break;

                    } else {
                        continue;
                    }
                }
            }

            System.out.println("prob is  " + prob);

        } else {

            ReusableCluster.reusbale();
            prob = 0.00;
            RemainingTime = 0;
            dbMngr.clearTableData();
            Phase3GUI.existence_time = 5000;

        }

    }

    public double PoisonModel(long times, int K_value, long SW_AcTime) {
        // double lamda;
        int x;
        double prb = 0.00000;
        //k = 1;
        //  i think what i am trying to do here is as folows:
        // (1) to find the remaining number of records required to satisfy the minimal criteria
        //     of k-value needed for anonymization to take place. k is the remaing value
        k = K_value - k_counter;
        System.out.println(" small k is " + k);
        lamda = (double) (((double) k_counter / (double) SW_AcTime) * (double) (times));
        // double lamda = (double) (k/SW_AcTime * RemainingTime);
        x = K_value - k;

        System.out.println("Arrival rate prediction of records that will incur less information loss " + "in the next round is " + lamda);

        for (int i = 0; i < k; i++) //for (int i = 0; i <= (x-1); i++)
        //int i = 0;
        {
            double temp = (double) (pow(lamda, (double) i) * Math.exp(-lamda)) / (double) (factorial(i));
            System.out.println(temp);
            prb = prb + temp;
            System.out.println(prb);
        }

        prb = 1 - prb;
        k_counter = 0;

        return prb;

    }

    public int factorial(int number) {
        int factor = 1;

        for (int i = 1; i <= number; i++) {
            factor = factor * i;
        }

        return factor;

    }

}
