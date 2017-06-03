/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 */
import java.util.*;

public class ReusableCluster {
    
    //public String [][] reusable = new String[Phase3GUI.data.length][2];
    public static String [][] reusable;
    public static ArrayList age = new ArrayList();
    public static ArrayList crime = new ArrayList();
    public static Set<String[]> rowList = new HashSet<String[]>();
   // public static Set<String>[] titles = new HashSet<String>[]();
   // List<Integer>[] array = new List<Integer>[10];
   // array = new List<Integer>[10];
    public static Set<String> reuse = new HashSet<String>();
    public static String reuse_return;
    static double maxAGE= 100;
   // int minCRIME_TYPE = 0;
    static double minAGE = 0;
    public static double info_loss = 0.0;
    public static InfoLoss reuse_infoloss = new InfoLoss();

    
    public static void reusbale()
      
    {
    
        for (int i = 0; i< Phase3GUI.data.length;i++)
        {
                     
            int j = 0;
            {
                rowList.add(new String []{Phase3GUI.data[i][j],Phase3GUI.data[i][j]});
            }
         //  System.out.println("rowlist is  " + rowList);
          // System.out.println("rowlist is  " + rowList.size());
             
        }
         reusable = (String[][])rowList.toArray(new String[rowList.size()][1]); 
        
       
        for (int i = 0; i< Phase3GUI.data.length;i++)
        {
            
            int j=0;
            {
            reuse.add(Phase3GUI.data[i][j]);
           // System.out.println("Contents of reuse: " +  reuse);  
           // System.out.println("Contents of reuse: " +  reusable[i][j]);  
            }
     }
    
    }
    
    public static String search(String [][]reusable, String RESIDENCE)
    { 
       
        
        int counter = 0;
        String temp_res = RESIDENCE;
      
        
        
                   
        for (int i = 0; i < (reusable.length); i++)
         {
            if (counter == 1){break;}
             //temp_a= a;
             //temp_b= b; 
             // for (int j = 0; j < (AfterRound1.NumberOfColumns+2); j++)
             for (int j = 0; j < 1; j++)    
             {
                if (RESIDENCE.contains(reusable[i][j]))
                    {
                        System.out.println("b is " + reusable[i][j]);
                        RESIDENCE  = reusable[i][j];
                        info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                        counter++;
                        break;
                    }
               /* else 
                {
                    System.out.println("res arr is" + RESIDENCE);
                    System.out.println("b is" + reusable[i][j]);
                    RESIDENCE  = temp_res;
                   // RESIDENCE  = "";
                }*/
                
             }  
                
                   // RESIDENCE  = "Off-Campus";
          }
             
      if (RESIDENCE  == temp_res) 
          {
             if (RESIDENCE.contains("Baxter Hall") || RESIDENCE.contains("Clarinus Village") || RESIDENCE.contains("College House")
                            || RESIDENCE .contains("Fuller Hall")|| RESIDENCE.contains( "Graca Machel Hall")|| RESIDENCE.contains("Glendower" )
                            || RESIDENCE .contains("Kilindini")|| RESIDENCE.contains("Kopano" ) || RESIDENCE.contains("Leo Marquard Hall" ) 
                            || RESIDENCE .contains("Rochester House")  || RESIDENCE .contains("Smuts Hall") || RESIDENCE .contains("Tugwell Hall")  
                            || RESIDENCE .contains("University House")  || RESIDENCE .contains("Varietas") )
           
              {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("1st Tier Accomodation".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("1st Tier Accomodation"))
                         {
                            RESIDENCE  = "1st Tier Accomodation";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
              }
               /*  else
                    {   
                        RESIDENCE  = RESIDENCE ;
                    }*/
          //   }}}
                    
            else  if ( RESIDENCE.contains("Groote Schuur Residence") || RESIDENCE .contains("Medical Residence"))
                   // Groote Schuur Residence
                   {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                        if ("Senior Catering 2nd Tier Accomodation".equals(RESIDENCE)) {break;}
                       for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Senior Catering 2nd Tier Accomodation"))
                         {
                            RESIDENCE = "Senior Catering 2nd Tier Accomodation";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                 
                  if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("2nd Tier Accomodation".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("2nd Tier Accomodation"))
                         {
                            RESIDENCE  = "2nd Tier Accomodation";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
                  
                  if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
              }
            
             else if (RESIDENCE.contains("Block A Forest Hill") ||  RESIDENCE.contains( "Block B Forest Hill") ||  RESIDENCE.contains("Block C Forest Hill") 
                           || RESIDENCE.contains("Block D Forest Hill")  || RESIDENCE.contains("Block E Forest Hill") || RESIDENCE.contains("BLOCK A Forest Hill") ||  
                              RESIDENCE.contains( "BLOCK B Forest Hill") ||  RESIDENCE.contains("BLOCK C Forest Hill") || RESIDENCE.contains("BLOCK D Forest Hill")  
                              || RESIDENCE.contains("BLOCK E Forest Hill")|| RESIDENCE.contains("Groote Schuur Mansions" )
                           || RESIDENCE.contains("Liesbeeck Gardens") ||  RESIDENCE .contains("The Woolsack")
                           || RESIDENCE .contains("Obz Square"))
                         
               {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Self Catering 2nd Tier Accomodation".equals(RESIDENCE)) {break;}
                       for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Self Catering 2nd Tier Accomodation"))
                         {
                           RESIDENCE = "Self Catering 2nd Tier Accomodation";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                  if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("2nd Tier Accomodation".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("2nd Tier Accomodation"))
                         {
                            RESIDENCE  = "2nd Tier Accomodation";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
                  
                  if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
              }  
              
            
             else if (RESIDENCE.contains("Block A Forest Hill") ||  RESIDENCE.contains( "Block B Forest Hill") ||  RESIDENCE.contains("Block C Forest Hill") 
                           || RESIDENCE.contains("Block D Forest Hill")  || RESIDENCE.contains("Block E Forest Hill") || RESIDENCE.contains("BLOCK A Forest Hill") ||  
                              RESIDENCE.contains( "BLOCK B Forest Hill") ||  RESIDENCE.contains("BLOCK C Forest Hill") || RESIDENCE.contains("BLOCK D Forest Hill")  
                              || RESIDENCE.contains("BLOCK E Forest Hill")|| RESIDENCE.contains("Groote Schuur Mansions" )
                           || RESIDENCE.contains("Liesbeeck Gardens") ||  RESIDENCE .contains("The Woolsack")|| RESIDENCE .contains("Obz Square") 
                            || RESIDENCE.contains("Groote Schuur Residence") || RESIDENCE .contains("Medical Residence"))
                         
               {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("2nd Tier Accomodation".equals(RESIDENCE)) {break;}
                       for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("2nd Tier Accomodation"))
                         {
                           RESIDENCE = "2nd Tier Accomodation";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                 
                 if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
              }  
             
             
            
                   
             else if (RESIDENCE .contains("Rondeberg") || RESIDENCE.contains("T.B. Davie Court" ) || RESIDENCE.contains("Block F Forest Hill")
                            || RESIDENCE .contains("J.P. Duminy Court")|| RESIDENCE .contains("University House Cottages")|| RESIDENCE .contains("Inglewood")
                            || RESIDENCE .contains("Avenue Road")|| RESIDENCE .contains("Woodbine Road") || RESIDENCE.contains("Linkoping" ) 
                            || RESIDENCE.contains("North Grange" ))
                   {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("3rd Tier Accomodation".equals(RESIDENCE)) {break;}
                       for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("3rd Tier Accomodation"))
                         {
                           RESIDENCE  = "3rd Tier Accomodation";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                  if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
              }    
             
          else  if (RESIDENCE.contains("Baxter Hall") || RESIDENCE.contains("Clarinus Village") || RESIDENCE.contains("College House")
                            || RESIDENCE .contains("Fuller Hall")|| RESIDENCE.contains( "Graca Machel Hall")|| RESIDENCE.contains("Glendower" )
                            || RESIDENCE .contains("Kilindini")|| RESIDENCE.contains("Kopano" ) || RESIDENCE.contains("Leo Marquard Hall" ) 
                            || RESIDENCE .contains("Rochester House")  || RESIDENCE .contains("Smuts Hall") || RESIDENCE .contains("Tugwell Hall")  
                            || RESIDENCE .contains("University House")  || RESIDENCE .contains("Varietas") || RESIDENCE.contains("Groote Schuur Residence") 
                            || RESIDENCE .contains("Medical Residence") || RESIDENCE.contains("Block A Forest Hill") ||  RESIDENCE.contains( "Block B Forest Hill") ||  RESIDENCE.contains("Block C Forest Hill") 
                           || RESIDENCE.contains("Block D Forest Hill")  || RESIDENCE.contains("Block E Forest Hill") || RESIDENCE.contains("BLOCK A Forest Hill") ||  
                              RESIDENCE.contains( "BLOCK B Forest Hill") ||  RESIDENCE.contains("BLOCK C Forest Hill") || RESIDENCE.contains("BLOCK D Forest Hill")  
                              || RESIDENCE.contains("BLOCK E Forest Hill")|| RESIDENCE.contains("Groote Schuur Mansions" )
                           || RESIDENCE.contains("Liesbeeck Gardens") ||  RESIDENCE .contains("The Woolsack")
                           || RESIDENCE .contains("Obz Square") || RESIDENCE .contains("Rondeberg") || RESIDENCE.contains("T.B. Davie Court" ) || RESIDENCE.contains("Block F Forest Hill")
                            || RESIDENCE .contains("J.P. Duminy Court")|| RESIDENCE .contains("University House Cottages")|| RESIDENCE .contains("Inglewood")
                            || RESIDENCE .contains("Avenue Road")|| RESIDENCE .contains("Woodbine Road") || RESIDENCE.contains("Linkoping" ) 
                            || RESIDENCE.contains("North Grange" ))
           
              {
                 for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("UCT RESIDENCE".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("UCT RESIDENCE"))
                         {
                            RESIDENCE  = "UCT RESIDENCE";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
              }       
          
               
            else if (RESIDENCE.contains("Rosebank")) 
               
            {
                   for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Off-Campus Accomodation (Rosebank)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       
                      
                       if (reusable[i][j].contains("Off-Campus Accomodation (Rosebank)"))
                        
                       {
                           RESIDENCE  = "Off-Campus Accomodation (Rosebank)";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                        }
                   }
                   } 
                   
                   if (RESIDENCE.equals(temp_res))
                  
                   {
                       
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      
                      // System.out.println("Off-Campus Accomodation (Claremont)" );
                       
                       if ("Off-Campus Accomodation (Southern Suburbs)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                      // System.out.println("reusable[i][j]" + reusable[i][j]); 
                       if (reusable[i][j].contains("Off-Campus Accomodation (Southern Suburbs)"))
                         {
                            RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
                   
               }
               
              else if  (RESIDENCE.contains("Claremont") ) 
               
               {
                   for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Off-Campus Accomodation (Claremont)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Off-Campus Accomodation (Claremont)"))
                         {
                          // System.out.println("Off-Campus Accomodation (Claremont)" );
                           RESIDENCE  = "Off-Campus Accomodation (Claremont)";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                         }
                   }
                   } 
                   
                    if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                      
                       
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      // System.out.println(temp_res ); 
                      // System.out.println("Off-Campus Accomodation (Claremont)" ); 
                       if ("Off-Campus Accomodation (Southern Suburbs)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       // System.out.println("reusable[i][j]" + reusable[i][j]); 
                       if (reusable[i][j].contains("Off-Campus Accomodation (Southern Suburbs)"))
                         {
                            // System.out.println("Off-Campus Accomodation (Claremont)" ); 
                             RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
               }
               
             else  if (RESIDENCE.contains("Mowbray")) 
               
               {
                   for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Off-Campus Accomodation (Mowbray)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Off-Campus Accomodation (Mowbray)"))
                         {
                           RESIDENCE  = "Off-Campus Accomodation (Mowbray)";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                           break;
                          }
                   }
                   } 
                   
                    if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                      
                       
                   for (int i = 0; i < (reusable.length); i++)
                   {
                        //System.out.println(temp_res ); 
                      // System.out.println("Off-Campus Accomodation (Claremont)" ); 
                       if ("Off-Campus Accomodation (Southern Suburbs)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       // System.out.println("reusable[i][j]" + reusable[i][j]); 
                       if (reusable[i][j].contains("Off-Campus Accomodation (Southern Suburbs)"))
                         {
                           // System.out.println("Off-Campus Accomodation (Mowbray)" );  
                            RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
               }
               
               
             else  if (RESIDENCE.contains("Rondebosch")) 
               
               {
                   for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Off-Campus Accomodation (Rondebosch)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Off-Campus Accomodation (Rondebosch)"))
                         {
                           RESIDENCE  = "Off-Campus Accomodation (Rondebosch)";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   } 
                   
                    if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                          
                       
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      // System.out.println(temp_res ); 
                       if ("Off-Campus Accomodation (Southern Suburbs)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       // System.out.println("reusable[i][j]" + reusable[i][j]); 
                       if (reusable[i][j].contains("Off-Campus Accomodation (Southern Suburbs)"))
                         {
                           //  System.out.println("Off-Campus Accomodation (Rondebosch)" ); 
                             RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
               }   
               
             else if (RESIDENCE.contains("Newsland")) 
               
               {
                   for (int i = 0; i < (reusable.length); i++)
                   {
                       if ("Off-Campus Accomodation (Newsland)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       if (reusable[i][j].contains("Off-Campus Accomodation (Newsland)"))
                         {
                           RESIDENCE  = "Off-Campus Accomodation (Newsland)";
                           info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                           break;
                         }
                   }
                   } 
                   
                    if (RESIDENCE.equals(temp_res))
                  
                   {
                   
                       
                   for (int i = 0; i < (reusable.length); i++)
                   {
                      if ("Off-Campus Accomodation (Southern Suburbs)".equals(RESIDENCE)) {break;}
                      for (int j = 0; j < 1; j++)    
                   {
                       // System.out.println("reusable[i][j]" + reusable[i][j]); 
                       if (reusable[i][j].contains("Off-Campus Accomodation (Southern Suburbs)"))
                         {
                           // System.out.println("Off-Campus Accomodation (Newsland)" );
                             RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
                            info_loss = reuse_infoloss.loss_of_info(reusable[i][j]);
                            break;
                          }
                   }
                   }
                   }
               }
               
          //   else 
             {
             
                 //if (RESIDENCE.equals(temp_res)) {RESIDENCE  = "";}  
             
             }
             
             }
             
                // else{   RESIDENCE  = "";}
            
            if (RESIDENCE  == temp_res) {RESIDENCE  = "";}  
                
           //  }
              return  RESIDENCE;
         }
}
   //  temp_diff = temp_diff1;
      // System.out.println("temp_diff is " +  temp_diff); 
       //if (temp_diff != 0)
      // {info_loss = info_loss + ((temp_diff/(maxAGE-minAGE)) + 0.5)/2; }
    //  System.out.println("Infoloss2 " +  info_loss);    
     //temp_diff2 =0;
   
   // return "a";
   // }
    
//}



        
      /*  for (int i = 0; i< Phase3GUI.data.length;i++)
        {
            
            int j=0;
            {
            
           
            reuse.add(Phase3GUI.data[i][j].concat(Phase3GUI.data[i][j+1]));
            System.out.println("Contents of reuse: " +  reuse);    
            }
        }*/
        
       /* for (int i = 0; i< Phase3GUI.data.length;i++)
        {
            
           int j = 1;
            crime.add(Phase3GUI.data[i][j]);
          
        }*/
        
 
