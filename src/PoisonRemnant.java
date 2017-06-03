/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 * 
 */
/*public class PoisonModel {

    
     //int[] times;
    public int K,k;
    public static long RemainingTime;
   // public static String[] waiting_time;// = new String(); //new String();
    public static String[] waiting_time = new String[GeneralizationTable.stringList.size()]; 
    public static double prob;
    public int loop = 1;
    InfoLoss a = new InfoLoss();
    String[]  waiting_times;
    String inclause;
    Statement stmt;
    DBManager dbMngr = new DBManager();
    public static String[] supp_rec;
    public static String[] supp_rec2;
    
    public PoisonModel() {}
    
  
 /* public void bubblesort(int[] sort_time)
  {
  
      for (int i = 0; i < sort_time.length; i++)
    {
       for(int j = 0; j < (sort_time.length-i-1); j++)
       {
                if(sort_time[i] < sort_time[j + 1])
                {
                            int tempVar = sort_time [j + 1];
                            sort_time[j + 1]= sort_time [i];
                            sort_time [i] = tempVar;
                }
       }
}
  
  }*/
  
 /* private static void BubbleSort(int[] num) {
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
     
  public int maximum(int[] times)
  {
          int max = times[0];
    for (int i = 1; i < times.length; i++)
    {
    if(max < times[i])
    {
    max = times[i];
    }
    }
      return max;
  }
     
  public int minimum(int[] time)
  {
          int min = time[0];
    for (int i = 1; i < time.length; i++)
    {
    if(min > time[i])
    {
    min = time[i];
    }
    }
      return min;
  }
  
  
  public void delete_unsupp()
  
  {
       
   String[] suppressedRecArray = new String[GeneralizationTable.stringList.size()];
   String[] splitsuppressedRecArray = new String[GeneralizationTable.stringList.size()];
   // String[] splitsuppressedRecArray = new String[GeneralizationTable.stringList.size()*4];
   String[] returnedArray = GeneralizationTable.stringList.toArray(suppressedRecArray);
   int size = GeneralizationTable.stringList.size();
   inclause = "";
   for (int i = 0; i <size; i++)
   {
       
    splitsuppressedRecArray [i] = returnedArray[i].substring (0,2);
    if (i==size-1){inclause = inclause.concat(splitsuppressedRecArray [i]);}
    else
   {
    
    inclause = inclause.concat(splitsuppressedRecArray [i]).concat(",");
    System.out.println(splitsuppressedRecArray[i]);}
   }
   
   System.out.println(inclause );
   //int size = GeneralizationTable.stringList.size();
      
      //System.out.println("suppressed record is " + GeneralizationTable.suppressedRecord);
      //System.out.println("db length is " + Phase3GUI.anonymised_length);
      //System.out.println("ano length is " + a.database_length);
      //if (AfterRound1.database_length  !=  AfterRound1.anonymised_length)
      if (Phase3GUI.anonymised_length  != a.database_length)
      {
          // stringList.add(suppressedRecord);
          //supp_rec = (GeneralizationTable.suppressedRecord).split(",");
           supp_rec = (GeneralizationTable.suppressedRecord).split(",");
         //  String[] suppressedRecArray = new String[GeneralizationTable.stringList.size()];
         //  String[] returnedArray = GeneralizationTable.stringList.toArray(suppressedRecArray);
          // int size = GeneralizationTable.stringList.size();
          // supp_rec2 = (returnedArray).split(",");
          //  System.out.println("Suppressed record 1 is " + returnedArray[0]);
          //  System.out.println("Suppressed record 2 is " + returnedArray[1]);
          //  System.out.println("Suppressed record 3 is " + returnedArray[2]);
           //String[] suppressedRecArray = new String[GeneralizationTable.stringList.size()];
          // suppressedRecArray.toArray(suppressedRecArray);
          // String[] returnedArray = GeneralizationTable.stringList.toArray(suppressedRecArray);
          // String [] assetTradingArray = new String[assetTradingList.size()];
          // assetTradingArray.toArray(assetTradingArray);
           System.out.println("Suppressed record/unproperly anonymised record is " + GeneralizationTable.suppressedRecord);
           //dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE AGE in (%s) and CRIME_TYPE in (%s)", supp_rec[1], supp_rec[2]));
          /*
            * sql = "SELECT " + ExpectedWaitingTime + " " +
				  "FROM Student " +
				  "WHERE " + AGE + "IN " + returnedArray + "'"; 
             			String[]  waiting_time = dbManager.runQuery(sql);
            * getQuasiIdValuePairs(sampleIdQuasiIdValues);
            QuasiID.ID.getDBName() + "='" + sampleId + "'";
			
           */
        /*  String ExpectedWaitingTime = "ExpectedWaitingTime"; 
           String AGE = "Age";
         String  sql = "SELECT " + ExpectedWaitingTime + " " +
				  "FROM Student " +
				  "WHERE " + AGE + "IN " + returnedArray + "'"; 
             			  waiting_times = dbMngr.runQuery(sql);*/
           //waiting_time[] =  dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE AGE in (%s)", returnedArray[0], returnedArray[1], returnedArray[2]));
//           System.out.println(waiting_time + waiting_time[0] +  waiting_time[1]);
           //System.out.println(waiting_time + waiting_time[0]+waiting_time[1]);
           //waiting_time =  dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE AGE in (%s)", GeneralizationTable.stringList));
           // DBManager dbMngr = new DBManager();
         /* waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE AGE IN (%s) ", inclause));
          // waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE AGE = (%s) and CRIME_TYPE = (%s)", supp_rec[1], supp_rec[2]));
         // System.out.println("Waiting time is " + waiting_time[0]);
          //waiting_time = dbMngr.runQuery( "SELECT " + "ExpectedWaitingTime" + " FROM Student" + "where deptID = '" + supp_rec[1] + "' and weight = '" + supp_rec[2] + "'");
           //waiting_time = dbMngr.runQuery( "SELECT " + "ExpectedWaitingTime" + " FROM Student"  );
          // System.out.println("Supp 1 is  " + supp_rec[1]);
         //  System.out.println("Waiting Time is  " + waiting_time);
        
          int int_waitingtime[] = new int[size];
          for (int i = 0; i <size; i++)
           {
               //prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[0]),2,Phase3GUI.existence_time);
              int_waitingtime[i] = Integer.parseInt(waiting_time[i]);
              System.out.println(waiting_time[i] + int_waitingtime[i]);
              // int x = minimum (Integer.parseInt(waiting_time));
           }
          
          for (int i = 0; i <size; i++)
           {
               //prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[0]),2,Phase3GUI.existence_time);
               // if (int_waitingtime[i] > Phase3GUI.existence_time ||int_waitingtime[i] <0 ){dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime in (%s)", int_waitingtime[i]));}
               //if (int_waitingtime[i] > Phase3GUI.existence_time ||int_waitingtime[i] <0 ){dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime in (%s)", int_waitingtime[i]));}
               System.out.println(waiting_time[i] + int_waitingtime[i]);
              // int x = minimum (Integer.parseInt(waiting_time));
           }
          BubbleSort(int_waitingtime);
        //  bubblesort(int_waitingtime);
           for (int i = 0; i <size; i++)
           {System.out.println(int_waitingtime[i]);}
          int minimum_waiting = minimum(int_waitingtime);
          int maximum_waiting = int_waitingtime[size-1];
          //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime <= (%s)", Phase3GUI.existence_time));
          //int maximum_waiting = int_waitingtime[size-1];
          //  int maximum_waiting = maximum(int_waitingtime);
          //sort in ascending order
         // dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime <= (%s)", int_waitingtime[0]));
          
         /* if ((Phase3GUI.existence_time-maximum_waiting) < 1000 )
          {
              dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[size-1]));
              maximum_waiting = int_waitingtime[size-2];
              prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(maximum_waiting),2,Phase3GUI.existence_time);
          if (maximum_waiting <= 1000 )
          {   
              
              dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[size-2]));
              maximum_waiting = int_waitingtime[size-3];}
             // if (maximum_waiting.length < 3)
          }*/
          //  if (int_waitingtime[1] < 1000)
          // dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[0]));
          //prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[1]),2,Phase3GUI.existence_time);}
          //else{
      /*    System.out.println("max waiting is  " + maximum_waiting + (Phase3GUI.existence_time - Long.valueOf(maximum_waiting)));
           prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(maximum_waiting),2,Phase3GUI.existence_time);
           
           // prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[0]),2,Phase3GUI.existence_time);
           System.out.println("prob is  " + prob);
          // prob = 0.8;
           if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", inclause));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", splitsuppressedRecArray[0]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
              // Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
              long former_existence_time = Phase3GUI.existence_time;
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(maximum_waiting);
              // for (int i = 0; i <size; i++)
             /*  {
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE EntryTime < (%s)", Phase3GUI.existence_time));
               }*/
             /*   dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", 0));
               for (int i = 0; i <size; i++)
               {
                   //sql = "SELECT " + ExpectedWaitingTime + " " +
                   //if (int_waitingtime[i] > ){}
                  // String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(int_waitingtime[i] + Phase3GUI.existence_time) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
                  String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[i] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
                  // String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
             	   dbMngr.execUpdate(sql);
           // * getQuasiIdValuePairs(sampleIdQuasiIdValues);
           // QuasiID.ID.getDBName() + "='" + sampleId + "'";
               //    dbMngr.execUpdate(String.format("UPDATE Student SET ExpectedWaitingTime = (%s)",  (Long.valueOf(int_waitingtime[i]+  Phase3GUI.existence_time), "WHERE ExpectedWaitingTime= ",int_waitingtime[i] )));}
             //  System.out.println("next round existence time is  " + Phase3GUI.existence_time );
               //dbMngr.runQuery( "DELETE " + " FROM Student" + "where deptID NOT IN supp_rec[1] and weight NOT IN supp_rec[2] ");
                }
              // dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime < (%s)", Phase3GUI.existence_time));
       /*    else
           {
               prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[1]),2,Phase3GUI.existence_time);
               if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", returnedArray[0], returnedArray[1], returnedArray[2]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
             //  System.out.println("next round existence time is  " + Phase3GUI.existence_time );
               //dbMngr.runQuery( "DELETE " + " FROM Student" + "where deptID NOT IN supp_rec[1] and weight NOT IN supp_rec[2] ");
           }
               else{
               prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[1]),2,Phase3GUI.existence_time);
               if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", returnedArray[0], returnedArray[1], returnedArray[2]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
             //  System.out.println("next round existence time is  " + Phase3GUI.existence_time );
               //dbMngr.runQuery( "DELETE " + " FROM Student" + "where deptID NOT IN supp_rec[1] and weight NOT IN supp_rec[2] ");
           }*/
          /* }
               else{
               //recluster
               dbMngr.execUpdate( "DELETE " + " FROM Student");
               Phase3GUI.existence_time = 5000; }
      //}
          // }
           
           
           //String[] dept_id = dbMngr.runQuery( "SELECT " + "deptID" + " FROM Student" );
          //supp_rec = subst (GeneralizationTable.suppressedRecord, );
    /*  }
      
      else
      {
      //DBManager dbMngr = new DBManager1();   
      prob = 0.00;
      RemainingTime = 0;
      dbMngr.clearTableData();
      Phase3GUI.existence_time = 5000;
          
      }
    //  loop++;
    //  ++loop;
  }
     
     
   public double PoisonModel(long times, int K_value, long SW_AcTime)
    {
       // double lamda;
        int x;
        double prb = 0.00000;
        k = 1;
        RemainingTime = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
        double lamda =  (double)(((double)k/(double)SW_AcTime) * (double)(RemainingTime));
       // double lamda = (double) (k/SW_AcTime * RemainingTime);
        x = K_value - k;
      //  System.out.println((k/SW_AcTime) * (RemainingTime));
       // System.out.println(times.length);
       // System.out.println(x);
       // System.out.println(k);
       // System.out.println(RemainingTime);
       // System.out.println(SW_AcTime);
        //System.out.println("Arrival rate prediction of more than one record within " +  RemainingTime + "ms in the next round is " + lamda);
        System.out.println("Arrival rate prediction of records that will incur less information loss " +  "in the next round is " + lamda);
        
        for (int i = 0; i <= (x-1); i++)
        //int i = 0;
        {
          double temp = (double)(pow(lamda,(double) i) * Math.exp(-lamda))/(double)(factorial(i));
         // System.out.println(temp);
          prb =  prb + temp;
         // System.out.println(prb);
        }
        
       /* for(i in (x-1):0){
    temp <- (lamda^i) * exp(-lamda) / factorial(i)
    prb <- prb + temp
  }*/
   //     prb = 1 - prb;
         
       // System.out.println("i");
    //    return prb;
        
 //   }
   
   
   
   
   
   
   /*
    
    
      
    
    */
   
 /*   public int factorial(int number) {
        int factor = 1;
        //if (number==0) return 1;
        //else{
        for (int i=1; i<=number; i++) {
            factor = factor*i;
        }
        
        return factor;
        //Prints out final number
        //System.out.println(factor);
    }
/*public static void main(String[] args) {
        // TODO code application logic here
        
        int time[] = {500, 2500};
        int time1[] = {5500, 4000};
         int time2[] = {1000};
        PoisonModel k = new PoisonModel();
        Double Result = k.PoisonModel(time, 3, 6000);
        System.out.println(Result);
        Double Result1 = k.PoisonModel(time1, 3, 6000);
        System.out.println(Result1);
        Double Result2 = k.PoisonModel(time2, 3, 6000);
        System.out.println(Result2);
        System.out.println(time.length);
    }*/
   
   // public static void main(String[] args) 
   // {
       //PoissonModel K;
       //K.K = 3;
       // int time[] = {500, 2500};
       
       // PoissonModel k = new PoissonModel();
       // Double Result = k.PoisonModel(time, 3, 6000);
       // System.out.println(Result);
        //System.out.println(time.length);
        //System.out.println(time.length);
       // PoissonModel result = PoissonModel(times, K, 6000);
        
    
   // }
    
//}
     //int kk = times.length;
     
    /*times <- c(500,2500)
times <- c(500,2500)
poisson.mod(times,3,6000)
poisson.mod(times,3,6000, F) 
   
 * 
 * 
 * 
 */
   
  // System.out.println("Hello");
 /*   poisson.mod <- function(times, K, act.time){
  k <- length(times)
  rem.time <- act.time - max(times)
  lamda <- k / act.time * rem.time
  x <- K - k
  prb <- 0
  for(i in (x-1):0){
    temp <- (lamda^i) * exp(-lamda) / factorial(i)
    prb <- prb + temp
  }
  prb <- 1 - prb
  ans <- sprintf("Probability of %.0f or more infos in %.2f secs = %.4f",
                 x, rem.time, prb)
  return(ans)*/
 // prob = 0.8;
         /*  if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", inclause));
               
               
               
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", splitsuppressedRecArray[0]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
              // Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
              long former_existence_time = Phase3GUI.existence_time;
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(maximum_waiting);
              // for (int i = 0; i <size; i++)
             /*  {
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE EntryTime < (%s)", Phase3GUI.existence_time));
               }*/
              /* age = dbMngr.runQuery(String.format("SELECT age FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               crime = dbMngr.runQuery(String.format("SELECT crime_type FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               System.out.println("crime is  " + crime[0]);
               System.out.println("crime is  " + crime);
               System.out.println("crime is  " );
               ReusableCluster.reusbale();
               if (crime[0].equalsIgnoreCase("0")) {crime[0] = "Violent Crimes";}
               if (crime[0] .equalsIgnoreCase("1")){crime[0] = "Property crimes";}
               if (crime[0].equalsIgnoreCase("2")){crime[0] = "Misdemeanors";}
               if (crime[0] .equalsIgnoreCase("3")){crime[0] = "Others";}
                System.out.println("crime is  " + crime[0]);
               String reuse = ReusableCluster.search(ReusableCluster.reusable, age, crime);
               System.out.println("Anon is  " + reuse);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", 0));
               for (int i = 0; i <size; i++)
               {
                   
                  String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[i] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
                  // String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
             	   dbMngr.execUpdate(sql);
          
                }
               System.out.println("max waiting is  " + maximum_waiting);
              
       /*    else
           {
               prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[1]),2,Phase3GUI.existence_time);
               if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", returnedArray[0], returnedArray[1], returnedArray[2]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
             //  System.out.println("next round existence time is  " + Phase3GUI.existence_time );
               //dbMngr.runQuery( "DELETE " + " FROM Student" + "where deptID NOT IN supp_rec[1] and weight NOT IN supp_rec[2] ");
           }
               else{
               prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[1]),2,Phase3GUI.existence_time);
               if (prob >= 0.4)
               // if (prob > 0.5)
           {
              // dbMngr.execUpdate( "DELETE " + " FROM Student" + "where deptID NOT IN" + supp_rec[1] + " and weight NOT IN" + supp_rec[2]);
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", returnedArray[0], returnedArray[1], returnedArray[2]));
               //dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s) and CRIME_TYPE NOT IN (%s)", supp_rec[1], supp_rec[2]));
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.parseLong(waiting_time[0]);
             //  System.out.println("next round existence time is  " + Phase3GUI.existence_time );
               //dbMngr.runQuery( "DELETE " + " FROM Student" + "where deptID NOT IN supp_rec[1] and weight NOT IN supp_rec[2] ");
           }*/
         /*  }
          else
           {
               //recluster
               
               if(maximum_waiting == int_waitingtime[size-1])
               {
               age = dbMngr.runQuery(String.format("SELECT age FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               crime = dbMngr.runQuery(String.format("SELECT crime_type FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               System.out.println("crime is  " + crime[0]);
               System.out.println("crime is  " + crime);
               System.out.println("crime is  " );
               ReusableCluster.reusbale();
               if (crime[0].equalsIgnoreCase("0")) {crime[0] = "Violent Crimes";}
               if (crime[0] .equalsIgnoreCase("1")){crime[0] = "Property crimes";}
               if (crime[0].equalsIgnoreCase("2")){crime[0] = "Misdemeanors";}
               if (crime[0] .equalsIgnoreCase("3")){crime[0] = "Others";}
               System.out.println("crime is  " + crime[0]);
               String reuse = ReusableCluster.search(ReusableCluster.reusable, age, crime);
               System.out.println("Anon is  " + reuse);
                   dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", maximum_waiting));
                  maximum_waiting = int_waitingtime[size-2];
                 double probs = 0.0;
                  probs = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[size-2]),2,Phase3GUI.existence_time);
                  System.out.println(probs);
                  System.out.println("max waiting is  " + maximum_waiting);
                  if (probs >= 0.4)
               
             {
              
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", inclause));
               
              long former_existence_time = Phase3GUI.existence_time;
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(maximum_waiting);
              
                dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", 0));
               for (int i = 0; i <size; i++)
               {
                   
                  String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[i] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
                 
             	   dbMngr.execUpdate(sql);
          
                }
            }
                  
                  else
           {
               //recluster
               
               if(maximum_waiting == int_waitingtime[size-2])
               {
               age = dbMngr.runQuery(String.format("SELECT age FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               crime = dbMngr.runQuery(String.format("SELECT crime_type FROM Student WHERE ExpectedWaitingTime = (%s) ", maximum_waiting));
               System.out.println("crime is  " + crime[0]);
               System.out.println("crime is  " + crime);
               System.out.println("crime is  " );
               ReusableCluster.reusbale();
               if (crime[0].equalsIgnoreCase("0")) {crime[0] = "Violent Crimes";}
               if (crime[0] .equalsIgnoreCase("1")){crime[0] = "Property crimes";}
               if (crime[0].equalsIgnoreCase("2")){crime[0] = "Misdemeanors";}
               if (crime[0] .equalsIgnoreCase("3")){crime[0] = "Others";}
               System.out.println("crime is  " + crime[0]);
               String reuses = ReusableCluster.search(ReusableCluster.reusable, age, crime);
               System.out.println("Anon is  " + reuses);
                  dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", maximum_waiting));
                  maximum_waiting = int_waitingtime[size-3];
                  prob = 0.0;
                  prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[size-3]),2,Phase3GUI.existence_time);
                  System.out.println(prob);
                  System.out.println("max waiting is  " + maximum_waiting);
                  if (prob >= 0.4)
               
             {
              
               dbMngr.execUpdate(String.format("DELETE FROM Student WHERE AGE NOT IN (%s)", inclause));
               
              long former_existence_time = Phase3GUI.existence_time;
               Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(maximum_waiting);
              
                dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", 0));
               for (int i = 0; i <size; i++)
               {
                   
                  String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[i] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[i] + "'" ; 
                 
             	   dbMngr.execUpdate(sql);
          
                }
            }
                   else 
               {
               //DBManager dbMngr = new DBManager1();  
               System.out.println("I am here ");
               dbMngr.execUpdate( "DELETE " + " FROM Student");
               prob = 0.00;
              RemainingTime = 0;
              dbMngr.clearTableData();
               Phase3GUI.existence_time = 5000; 
               }
               }
               
              // else if(maximum_waiting == int_waitingtime[size-1])
              // {}
               
               else 
               {
               //DBManager dbMngr = new DBManager1();  
               System.out.println("I am here ");
               dbMngr.execUpdate( "DELETE " + " FROM Student");
               prob = 0.00;
              RemainingTime = 0;
              dbMngr.clearTableData();
               Phase3GUI.existence_time = 5000; 
               }
           }
               }
               
              // else if(maximum_waiting == int_waitingtime[size-1])
              // {}
               
               else 
               {
               //DBManager dbMngr = new DBManager1();  
               System.out.println("I am here ");
               dbMngr.execUpdate( "DELETE " + " FROM Student");
               prob = 0.00;
              RemainingTime = 0;
              dbMngr.clearTableData();
               Phase3GUI.existence_time = 5000; 
               }
           }*/
      //}
          // }
           
           
           //String[] dept_id = dbMngr.runQuery( "SELECT " + "deptID" + " FROM Student" );
          //supp_rec = subst (GeneralizationTable.suppressedRecord, );
 //    System.out.println("max waiting is  " + maximum_waiting + (Phase3GUI.existence_time - Long.valueOf(maximum_waiting)));
        //   prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(maximum_waiting),2,Phase3GUI.existence_time);
           
           // prob = PoisonModel(Phase3GUI.existence_time - Long.parseLong(waiting_time[0]),2,Phase3GUI.existence_time);
    
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 * 
 */

/*import java.lang.Object;
import static java.lang.Math.pow;
import java.util.Arrays;
import java.sql.*;
//import static java.lang.Math;
public class PoisonModel {

    
     //int[] times;
    public int K,k;
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
    private String [][] Supp_table = new String [GeneralizationTable.stringList.size()][3];
    long former_existence_time;
    
    InfoLoss a = new InfoLoss();
    String[]  waiting_times;
    String[] arrival_time;
    public static String[]  number_of_rows;
    String inclause;
    String residence_inclause;
    Statement stmt;
    DBManager dbMngr = new DBManager();
    int maximum_waiting;
    public static String[] supp_rec;
   // public static String[] supp_rec2;
    
    public PoisonModel() {}
    
  
  
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
     
  public int maximum(int[] times)
  {
          int max = times[0];
    for (int i = 1; i < times.length; i++)
    {
    if(max < times[i])
    {
    max = times[i];
    }
    }
      return max;
  }
     
  public int minimum(int[] time)
  {
          int min = time[0];
    for (int i = 1; i < time.length; i++)
    {
    if(min > time[i])
    {
    min = time[i];
    }
    }
      return min;
  }
  
  
  public void delete_unsupp()
  
  {
  
   String[] suppressedRecArray = new String[GeneralizationTable.stringList.size()];
   String[] splitsuppressedRecArray = new String[GeneralizationTable.stringList.size()];
   String[] returnedArray = GeneralizationTable.stringList.toArray(suppressedRecArray);
   int size = GeneralizationTable.stringList.size();
   inclause = "";
   residence_inclause = "";
   String time_inclause = "";
   number_of_rows = dbMngr.runQuery(String.format("SELECT ID_NUMBER FROM Phase3.Crime"));
    System.out.println("The number of rows is " + number_of_rows.length );
   
   for (int i = 0; i <size; i++)
   {
       
    splitsuppressedRecArray [i] = returnedArray[i];
    if (i == size-1){inclause = inclause.concat(splitsuppressedRecArray [i]);}
    else
   {
    
    inclause = inclause.concat(splitsuppressedRecArray [i]).concat(",");
    
   }
   }
   
   
   
   if (Phase3GUI.anonymised_length  != a.database_length)
      {
          
           supp_rec = (GeneralizationTable.suppressedRecord).split(",");
        
          int int_waitingtime[] = new int[size];   
          
          for (int i = 0; i <size; i++)
          {
           
              
              waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
          
             System.out.println(waiting_time[0]);
            int_waitingtime[i] = Integer.parseInt(waiting_time[0]);
              if (i == size - 1){ time_inclause = time_inclause.concat(waiting_time[0]);}
              else{ time_inclause = time_inclause.concat(waiting_time[0]).concat(",");}
             
              
            
          } 
          
          String Gen;
          for (int i = 0; i <size; i++)
          {
           
             for (int j = 0; j <= 2; j++)
             {     
            
             
                 /* waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
          
             System.out.println(waiting_time[0]);
            int_waitingtime[i] = Integer.parseInt(waiting_time[0]);
              if (i == size - 1){ time_inclause = time_inclause.concat(waiting_time[0]);}
              else{ time_inclause = time_inclause.concat(waiting_time[0]).concat(",");}*/
          // private String [][] Supp_table = new String [GeneralizationTable.stringList.size()][3];    
         /* if (j==0)
          {Supp_table[i][j]= splitsuppressedRecArray[i];  }   
           if (j==1)
          {
              
             // residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Crime WHERE ExpectedWaitingTime = (%s) ",int_waitingtime[i]));
              //residence_temp = residence[0];
              //Supp_table[i][j]= residence_temp; 
              //System.out.println("Res is  " + residence_temp);
              Supp_table[i][j]= Generalizer.generalizeRESIDENCE(splitsuppressedRecArray[i], Phase3GUI.GenStep,QuasiID.RESIDENCE); 
              //Supp_table[i][j]= Generalizer.generalizeRESIDENCE(residence_temp, Phase3GUI.GenStep,QuasiID.RESIDENCE); 
              //residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Crime WHERE ExpectedWaitingTime = (%s) ",int_waitingtime[i]));
          //return generalizeRESIDENCE(RESIDENCE, numGeneralizations, QuasiID.RESIDENCE);
           //residence_temp = residence[0];
           //System.out.println("Res is  " + residence_temp);
          }   
            if (j==2)
          {
              Supp_table[i][j]= splitsuppressedRecArray[i];  
              waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
              Supp_table[i][j]= waiting_time[0];
          
          }   
          waiting_time = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Phase3.Crime WHERE RESIDENCE = '" + splitsuppressedRecArray[i] + "'"));
          System.out.println(waiting_time[0]);
          System.out.println(splitsuppressedRecArray[i] );
          residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Crime WHERE ExpectedWaitingTime = (%s) ",int_waitingtime[i]));
          //return generalizeRESIDENCE(RESIDENCE, numGeneralizations, QuasiID.RESIDENCE);
           residence_temp = residence[0];
           System.out.println("Res is  " + residence_temp);
           
           Gen = Generalizer.generalizeRESIDENCE(residence_temp, Phase3GUI.GenStep,QuasiID.RESIDENCE);
           System.out.println("Gen of Res is  " + Gen );
          }
              
            
          } 
          
          
          for (int i = 0; i <size; i++)
          {
           
             for (int j = 0; j <= 2; j++)
             {   
             
                  System.out.println("Supp_table " + i + " , " + j + " " + Supp_table[i][j]);
             
             }
          
          }
       
          BubbleSort(int_waitingtime);
       
             former_existence_time = Phase3GUI.existence_time;
          
            for (int i = size-1; i <size; i--)
      {
            
            if ((Phase3GUI.existence_time - int_waitingtime[i] ) < 2000 + Phase3GUI.anon_time)
            //if ((Phase3GUI.existence_time - (System.currentTimeMillis() - long_waitingtime[i]) ) < 2000 + Phase3GUI.temp_total_time)
            {
                
              //  dbMngr.execUpdate(String.format("DELETE FROM Student WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[i]));
              // prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
                prob = 0.0;
                lamda = 0.0;
               // System.out.println("Didnt go thru poisson " + Phase3GUI.anon_time);
            }
            else
            {
                
                System.out.println("Anon time is " + Phase3GUI.anon_time);
               //Phase3GUI.existence_time - (System.currentTimeMillis() - long_waitingtime[i])
                prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,Phase3GUI.kanon,Phase3GUI.existence_time);
                //prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
                //prob = PoisonModel(Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time,2,Phase3GUI.existence_time);
            }
               //System.out.println(waiting_time[i] + int_waitingtime[i]);
            if (prob >= 0.9 || lamda>= 0.9)
            {
                
                dbMngr.execUpdate(String.format("DELETE FROM Crime WHERE ExpectedWaitingTime NOT IN (%s) ", time_inclause));  
                 poi_residence = dbMngr.runQuery(("SELECT RESIDENCE FROM Crime")); 
                 //System.out.println("Poi Res is  " + poi_residence.length);
                 supp_poison = supp_poison + poi_residence.length;
                // System.out.println("supp_poison is  " + supp_poison);
                    
                     Phase3GUI.existence_time = Phase3GUI.existence_time - Long.valueOf(int_waitingtime[i]) - Phase3GUI.anon_time;
                    
               for (int j = 0; j <size; j++)
               {
                   
                  String sql = "UPDATE Crime " + " SET ExpectedWaitingTime = '" + Long.valueOf(Phase3GUI.existence_time + int_waitingtime[j] + Phase3GUI.anon_time ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[j] + "'" ; 
                 // String sql = "UPDATE Student " + " SET ExpectedWaitingTime = '" + Long.valueOf(former_existence_time-Phase3GUI.existence_time - int_waitingtime[j] ) +  "'" + "WHERE ExpectedWaitingTime = '" + int_waitingtime[j] + "'" ; 
                 dbMngr.execUpdate(sql);
          
                }
               break;
           }
                else
          {
               
            
               residence = dbMngr.runQuery(String.format("SELECT RESIDENCE FROM Crime WHERE ExpectedWaitingTime = (%s) ",int_waitingtime[i]));
              //return generalizeRESIDENCE(RESIDENCE, numGeneralizations, QuasiID.RESIDENCE);
              residence_temp = residence[0];
                System.out.println("Res is  " + residence_temp);
                //String Gen;
                Gen = Generalizer.generalizeRESIDENCE(residence_temp, Phase3GUI.GenStep,QuasiID.RESIDENCE);
                System.out.println("Gen of Res is  " + Gen );
                //age_temp = age[0];
              ReusableCluster.reusbale();
              String reuse = ReusableCluster.search(ReusableCluster.reusable, residence_temp );
               if (reuse==null ||reuse.isEmpty()) 
               {
                   suppressed_counter++;
                   if (prob == 0.0 )
                   {
                       supp_no_poison++;
                        //System.out.println("Supp Rec without Poisson  " + supp_no_poison++);
                   }
                  
               }
               else
               {
                
                   infoloss_counter++;
                 
               }
             
               ReusableCluster.reuse_return = "";
               reuse=null;
               dbMngr.execUpdate(String.format("DELETE FROM Crime WHERE ExpectedWaitingTime IN (%s)", int_waitingtime[i]));
                if(i == 0 && prob < 0.9 && lamda < 0.9)
                 
                {
                
               
                dbMngr.execUpdate( "DELETE " + " FROM Crime");
                prob = 0.00;
                lamda = 0.00;
                RemainingTime = 0;
                dbMngr.clearTableData();
                Phase3GUI.existence_time = 5000; 
                break;
                
                }
                
                else{ continue;}
          }
     }
          
     
           System.out.println("prob is  " + prob);
         
      }
      
      else
      {
         
      ReusableCluster.reusbale();
      prob = 0.00;
      RemainingTime = 0;
      dbMngr.clearTableData();
      Phase3GUI.existence_time = 5000;
          
      }
   
  }
     
     
   public double PoisonModel(long times, int K_value, long SW_AcTime)
    {
       // double lamda;
        int x;
        double prb = 0.00000;
        k = 1;
        
        lamda =  (double)(((double)k/(double)SW_AcTime) * (double)(times));
       // double lamda = (double) (k/SW_AcTime * RemainingTime);
        x = K_value - k;
      
        System.out.println("Arrival rate prediction of records that will incur less information loss " +  "in the next round is " + lamda);
        
        for (int i = 0; i <= (x-1); i++)
        //int i = 0;
        {
          double temp = (double)(pow(lamda,(double) i) * Math.exp(-lamda))/(double)(factorial(i));
         // System.out.println(temp);
          prb =  prb + temp;
         // System.out.println(prb);
        }
        
      
       prb = 1 - prb;
         
      
      return prb;
        
   
   
   
   
   
   
    }
   
    
    
      
    
    
   
    public int factorial(int number) {
        int factor = 1;
        
        for (int i=1; i<=number; i++) {
            factor = factor*i;
        }
        
        return factor;
        
    }

    
} 
    

   
  */