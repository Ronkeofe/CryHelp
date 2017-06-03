/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 */
public class InfoLoss {

    //private DBManager dbManager;
    DBManager dbMngr = new DBManager();
    //   String[] CRIME_TYPE = dbMngr.runQuery( "SELECT " + "CRIME_TYPE" + " FROM Crime" );
    String[] RESIDENCE = dbMngr.runQuery("SELECT " + "RESIDENCE" + " FROM Crime");
        //dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Student WHERE ExpectedWaitingTime >= (%s) ", Phase3GUI.existence_time));
    // public String[] expired_record = dbMngr.runQuery( "SELECT " + "deptID" + " FROM Student"  + "where ExpectedWaitingTime =" + Phase3GUI.existence_time);
   
    public int noOfTuplesInBuffer = RESIDENCE.length;
    
    public static double roundloss_accumulator;
    public static double roundloss;
    public static double reuse_roundloss_accumulator = 0.0;


    //Actually, these are useless: why did you say that?
    String[] dbRESIDENCE = new String[RESIDENCE.length];
     public String[] expired_record = dbMngr.runQuery(String.format("SELECT ExpectedWaitingTime FROM Crime WHERE ExpectedWaitingTime >= (%s) ", Phase3GUI.existence_time));

 
    public void loss_of_info(String[][] datas) {
        
        //an array to take the info loss for each generalisation
        double infoloss[] = new double[datas.length];
        //this is taking the entire round information loss
        roundloss_accumulator = 0.0;
        
        for (int i = 0; i < datas.length; i++) {
             double infoloss_accumulator = 0.0;
            for (int j = 0; j < 1; j++) {   
                if ("Baxter Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_baxter / total_address;

                } else if ("Clarinus Village".equals(datas[i][j])) {
                    infoloss[i] = total_room_clarinus / total_address;
                } else if ("College House".equals(datas[i][j])) {
                    infoloss[i] = total_room_college_house / total_address;
                } else if ("Fuller Hall".equals(datas[i][j])) {
                    infoloss[i] = total_room_FullerHall / total_address;

                } else if ("Graca Machel Hall".equals(datas[i][j])) {
                    infoloss[i] = total_room_GracaMachel / total_address;

                } else if ("Glendower".equals(datas[i][j])) {
                    infoloss[i] = total_room_Glendower / total_address;

                } else if ("Kilindini".equals(datas[i][j])) {
                    infoloss[i] = total_room_kilindini / total_address;
                } else if ("Kopano".equals(datas[i][j])) {
                    infoloss[i] = total_room_kopano / total_address;
                } else if ("Leo Marquard Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_leoMarquard / total_address;

                } else if ("Rochester House".equals(datas[i][j])) {

                    infoloss[i] = total_room_Rochester / total_address;

                } else if ("Smuts Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_Smuts / total_address;

                } else if ("Tugwell Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_Tugwell / total_address;

                } else if ("University House".equals(datas[i][j])) {

                    infoloss[i] = total_room_UniversityHouse / total_address;

                } else if ("The University House Cottages".equals(datas[i][j])) {

                    infoloss[i] = total_room_UnivHouseCottages / total_address;

                } else if ("Varietas".equals(datas[i][j])) {
                    infoloss[i] = total_room_Varietas / total_address;

                } else if ("Groote Schuur Residence".equals(datas[i][j])) {

                    infoloss[i] = total_room_GrooteSchuur / total_address;

                } else if ("Medical Residence".equals(datas[i][j])) {

                    infoloss[i] = total_room_MedicalRes / total_address;

                } else if (("A Forest Hill".equals(datas[i][j])) || ("B Forest Hill".equals(datas[i][j]))
                        || ("C Forest Hill".equals(datas[i][j])) || ("D Forest Hill".equals(datas[i][j]))
                        || ("E Forest Hill".equals(datas[i][j])) || ("Forest Hill A-E").equals(datas[i][j])) {

                    infoloss[i] = total_room_ForestHillA_E / total_address;

                } else if ("Groote Schuur Mansions".equals(datas[i][j])) {

                    infoloss[i] = total_GrooteSchuurMansions / total_address;

                } else if ("Liesbeeck Gardens".equals(datas[i][j])) {

                    infoloss[i] = total_room_Liesbeek / total_address;

                } else if ("Obz Square".equals(datas[i][j])) {

                    infoloss[i] = total_room_Obz / total_address;

                } else if ("Rondeberg".equals(datas[i][j])) {

                    infoloss[i] = total_Rondeberg / total_address;

                } else if ("T.B. Davie Court".equals(datas[i][j])) {

                    infoloss[i] = total_room_TBDavies / total_address;

                } else if ("Block F Forest Hill".equals(datas[i][j])) {
                    infoloss[i] = total_room_ForestHillF / total_address;
                } else if ("J.P. Duminy Court".equals(datas[i][j])) {
                    infoloss[i] = total_room_JP / total_address;
                } else if ("The Woolsack".equals(datas[i][j])) {
                    infoloss[i] = total_room_Woolsack / total_address;
                } else if ("The Inglewood".equals(datas[i][j])) {
                    infoloss[i] = total_room_Inglewood / total_address;
                } else if ("The Avenue Road".equals(datas[i][j])) {
                    infoloss[i] = total_room_Avenue / total_address;
                } else if ("The Woodbine".equals(datas[i][j])) {
                    infoloss[i] = total_room_Woodbine / total_address;
                } else if ("Linkoping".equals(datas[i][j])) {
                    infoloss[i] = total_room_Linkoping / total_address;
                } else if ("North Grange".equals(datas[i][j])) {
                    infoloss[i] = total_room_NorthGrange / total_address;
                } else if ("Alma Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_alma / total_address;

                } else if ("Smith Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_smith / total_address;
                } else if ("Pillans Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_pillans / total_address;

                } else if ("Sawkins Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_sawkins / total_address;

                } else if ("Liesbeek Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_liesbeekrd / total_address;

                } else if ("Banksia Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_banksia / total_address;

                } else if ("Langton Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_langton / total_address;

                } else if ("Durban Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_durban / total_address;

                } else if ("Clandoenian Court Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_clandoenian / total_address;

                } else if ("Bridge Street Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_bridge / total_address;

                } else if ("Klipfontein Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_klipfontein / total_address;

                } else if ("Osborne Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_osborne / total_address;

                } else if ("Bath Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_bath / total_address;

                } else if ("Protea Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_protea / total_address;

                } else if ("Main Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_clmainroad / total_address;

                } else if ("Palmyra Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_palmyra / total_address;

                } else if ("Main Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_romain / total_address;

                } else if ("Ednam Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_ednam / total_address;

                } else if ("Miner Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_miner / total_address;

                } else if ("Belmont Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_belmont / total_address;

                } else if ("Klipper Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_klipper / total_address;

                } else if ("Baxter Avenue Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_baxterAvenue / total_address;

                } else if ("Broadway Avenue Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_broadway / total_address;

                } else if ("King Street Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_kingstreet / total_address;
                } else if ("Oak Avenue Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_oakavenue / total_address;

                } else if ("Palmboom Road Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_palmboom / total_address;

                } else if ("Newsland Road Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_newslandrd / total_address;

                } else if ("1st Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_1st_tier / total_address;

                } else if ("2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2nd_tier / total_address;

                } else if ("Senior Catering 2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2ndSenior_tier / total_address;

                } else if ("Self Catering 2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2ndSelf_tier / total_address;

                } else if ("3rd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_3rd_tier / total_address;

                } else if ("Off-Campus Accomodation (Rosebank)".equals(datas[i][j])) {
                    infoloss[i] = total_rosebank / total_address;

                } else if ("Off-Campus Accomodation (Mowbray)".equals(datas[i][j])) {
                    infoloss[i] = total_mowbray / total_address;

                } else if ("Off-Campus Accomodation (Claremont)".equals(datas[i][j])) {
                    infoloss[i] = total_claremont / total_address;

                } else if ("Off-Campus Accomodation (Rondebosch)".equals(datas[i][j])) {
                    infoloss[i] = total_rondebosch / total_address;

                } else if ("Off-Campus Accomodation (Newsland)".equals(datas[i][j])) {
                    infoloss[i] = total_newsland / total_address;

                } else if ("UCT RESIDENCE".equals(datas[i][j])) {
                    infoloss[i] = total_uct_residence / total_address;

                } else if ("Off-Campus Accomodation (Southern Suburbs)".equals(datas[i][j])) {
                    infoloss[i] = total_south_surburb / total_address;

                } else if ("Cape Town".equals(datas[i][j])) {
                    infoloss[i] = total_address / total_address;

                } else if ("Cape Town".equals(datas[i][j])) {
                    infoloss[i] = total_address / total_address;

                } else {
                    if (Phase3GUI.GenStep == 0) {
                        infoloss[i] = 0;
                    }

                }
                
                System.out.println("Information loss of [" + i + "] is " + infoloss[i]);
                infoloss_accumulator = infoloss_accumulator + infoloss[i];

            }

            roundloss_accumulator = roundloss_accumulator + infoloss_accumulator;
        } 
        //getting the eventual information loss in this round
        roundloss = roundloss_accumulator / datas.length;
         System.out.println("Total Information loss of Round [" + Phase3GUI.round + "] is " + roundloss_accumulator);
        System.out.println("Average Information loss of Round [" + Phase3GUI.round + "] is " + roundloss);
        
        //from here downwards, not doing anything, i mean they are useless: really?
        for (int b = 0; b < RESIDENCE.length; b++) {

            dbRESIDENCE[b] = RESIDENCE[b];

        }
        Integer[] InfoLoss = new Integer[Generalizer.infoloss.size()];

        Integer[] rInfoLoss = Generalizer.infoloss.toArray(InfoLoss);

        int size = Generalizer.infoloss.size();


    }
     public void infoLossForLDiv(String[][] datas) {
        
        //an array to take the info loss for each generalisation
        double infoloss[] = new double[datas.length];
        //this is taking the entire round information loss
        roundloss_accumulator = 0.0;
         int totalRows=0;
        for (int i = 0; i < datas.length; i++) {
             double infoloss_accumulator = 0.0;
            int countOfAnonymized = Integer.parseInt(datas[i][1]);
            totalRows+=countOfAnonymized;
            for (int j = 0; j < 1; j++) {   
                if ("Baxter Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_baxter / total_address;

                } else if ("Clarinus Village".equals(datas[i][j])) {
                    infoloss[i] = total_room_clarinus / total_address;
                } else if ("College House".equals(datas[i][j])) {
                    infoloss[i] = total_room_college_house / total_address;
                } else if ("Fuller Hall".equals(datas[i][j])) {
                    infoloss[i] = total_room_FullerHall / total_address;

                } else if ("Graca Machel Hall".equals(datas[i][j])) {
                    infoloss[i] = total_room_GracaMachel / total_address;

                } else if ("Glendower".equals(datas[i][j])) {
                    infoloss[i] = total_room_Glendower / total_address;

                } else if ("Kilindini".equals(datas[i][j])) {
                    infoloss[i] = total_room_kilindini / total_address;
                } else if ("Kopano".equals(datas[i][j])) {
                    infoloss[i] = total_room_kopano / total_address;
                } else if ("Leo Marquard Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_leoMarquard / total_address;

                } else if ("Rochester House".equals(datas[i][j])) {

                    infoloss[i] = total_room_Rochester / total_address;

                } else if ("Smuts Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_Smuts / total_address;

                } else if ("Tugwell Hall".equals(datas[i][j])) {

                    infoloss[i] = total_room_Tugwell / total_address;

                } else if ("University House".equals(datas[i][j])) {

                    infoloss[i] = total_room_UniversityHouse / total_address;

                } else if ("The University House Cottages".equals(datas[i][j])) {

                    infoloss[i] = total_room_UnivHouseCottages / total_address;

                } else if ("Varietas".equals(datas[i][j])) {
                    infoloss[i] = total_room_Varietas / total_address;

                } else if ("Groote Schuur Residence".equals(datas[i][j])) {

                    infoloss[i] = total_room_GrooteSchuur / total_address;

                } else if ("Medical Residence".equals(datas[i][j])) {

                    infoloss[i] = total_room_MedicalRes / total_address;

                } else if (("A Forest Hill".equals(datas[i][j])) || ("B Forest Hill".equals(datas[i][j]))
                        || ("C Forest Hill".equals(datas[i][j])) || ("D Forest Hill".equals(datas[i][j]))
                        || ("E Forest Hill".equals(datas[i][j])) || ("Forest Hill A-E").equals(datas[i][j])) {

                    infoloss[i] = total_room_ForestHillA_E / total_address;

                } else if ("Groote Schuur Mansions".equals(datas[i][j])) {

                    infoloss[i] = total_GrooteSchuurMansions / total_address;

                } else if ("Liesbeeck Gardens".equals(datas[i][j])) {

                    infoloss[i] = total_room_Liesbeek / total_address;

                } else if ("Obz Square".equals(datas[i][j])) {

                    infoloss[i] = total_room_Obz / total_address;

                } else if ("Rondeberg".equals(datas[i][j])) {

                    infoloss[i] = total_Rondeberg / total_address;

                } else if ("T.B. Davie Court".equals(datas[i][j])) {

                    infoloss[i] = total_room_TBDavies / total_address;

                } else if ("Block F Forest Hill".equals(datas[i][j])) {
                    infoloss[i] = total_room_ForestHillF / total_address;
                } else if ("J.P. Duminy Court".equals(datas[i][j])) {
                    infoloss[i] = total_room_JP / total_address;
                } else if ("The Woolsack".equals(datas[i][j])) {
                    infoloss[i] = total_room_Woolsack / total_address;
                } else if ("The Inglewood".equals(datas[i][j])) {
                    infoloss[i] = total_room_Inglewood / total_address;
                } else if ("The Avenue Road".equals(datas[i][j])) {
                    infoloss[i] = total_room_Avenue / total_address;
                } else if ("The Woodbine".equals(datas[i][j])) {
                    infoloss[i] = total_room_Woodbine / total_address;
                } else if ("Linkoping".equals(datas[i][j])) {
                    infoloss[i] = total_room_Linkoping / total_address;
                } else if ("North Grange".equals(datas[i][j])) {
                    infoloss[i] = total_room_NorthGrange / total_address;
                } else if ("Alma Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_alma / total_address;

                } else if ("Smith Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_smith / total_address;
                } else if ("Pillans Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_pillans / total_address;

                } else if ("Sawkins Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_sawkins / total_address;

                } else if ("Liesbeek Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_liesbeekrd / total_address;

                } else if ("Banksia Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_banksia / total_address;

                } else if ("Langton Road Rosebank".equals(datas[i][j])) {
                    infoloss[i] = total_langton / total_address;

                } else if ("Durban Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_durban / total_address;

                } else if ("Clandoenian Court Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_clandoenian / total_address;

                } else if ("Bridge Street Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_bridge / total_address;

                } else if ("Klipfontein Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_klipfontein / total_address;

                } else if ("Osborne Road Mowbray".equals(datas[i][j])) {
                    infoloss[i] = total_osborne / total_address;

                } else if ("Bath Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_bath / total_address;

                } else if ("Protea Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_protea / total_address;

                } else if ("Main Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_clmainroad / total_address;

                } else if ("Palmyra Road Claremont".equals(datas[i][j])) {
                    infoloss[i] = total_palmyra / total_address;

                } else if ("Main Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_romain / total_address;

                } else if ("Ednam Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_ednam / total_address;

                } else if ("Miner Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_miner / total_address;

                } else if ("Belmont Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_belmont / total_address;

                } else if ("Klipper Road Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_klipper / total_address;

                } else if ("Baxter Avenue Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_baxterAvenue / total_address;

                } else if ("Broadway Avenue Rondebosch".equals(datas[i][j])) {
                    infoloss[i] = total_broadway / total_address;

                } else if ("King Street Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_kingstreet / total_address;
                } else if ("Oak Avenue Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_oakavenue / total_address;

                } else if ("Palmboom Road Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_palmboom / total_address;

                } else if ("Newsland Road Newsland".equals(datas[i][j])) {
                    infoloss[i] = total_newslandrd / total_address;

                } else if ("1st Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_1st_tier / total_address;

                } else if ("2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2nd_tier / total_address;

                } else if ("Senior Catering 2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2ndSenior_tier / total_address;

                } else if ("Self Catering 2nd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_2ndSelf_tier / total_address;

                } else if ("3rd Tier Accomodation".equals(datas[i][j])) {
                    infoloss[i] = total_3rd_tier / total_address;

                } else if ("Off-Campus Accomodation (Rosebank)".equals(datas[i][j])) {
                    infoloss[i] = total_rosebank / total_address;

                } else if ("Off-Campus Accomodation (Mowbray)".equals(datas[i][j])) {
                    infoloss[i] = total_mowbray / total_address;

                } else if ("Off-Campus Accomodation (Claremont)".equals(datas[i][j])) {
                    infoloss[i] = total_claremont / total_address;

                } else if ("Off-Campus Accomodation (Rondebosch)".equals(datas[i][j])) {
                    infoloss[i] = total_rondebosch / total_address;

                } else if ("Off-Campus Accomodation (Newsland)".equals(datas[i][j])) {
                    infoloss[i] = total_newsland / total_address;

                } else if ("UCT RESIDENCE".equals(datas[i][j])) {
                    infoloss[i] = total_uct_residence / total_address;

                } else if ("Off-Campus Accomodation (Southern Suburbs)".equals(datas[i][j])) {
                    infoloss[i] = total_south_surburb / total_address;

                } else if ("Cape Town".equals(datas[i][j])) {
                    infoloss[i] = total_address / total_address;

                } else if ("Cape Town".equals(datas[i][j])) {
                    infoloss[i] = total_address / total_address;

                } else {
                    if (Phase3GUI.GenStep == 0) {
                        infoloss[i] = 0;
                    }

                }
                infoloss[i] = infoloss[i] * countOfAnonymized;
                
                System.out.println("L div Information loss of [" + i + "] is " + infoloss[i]);
                infoloss_accumulator = infoloss_accumulator + infoloss[i];

            }

            roundloss_accumulator = roundloss_accumulator + infoloss_accumulator;
        } 
        //getting the eventual information loss in this round
        roundloss = roundloss_accumulator / totalRows;
         System.out.println("Total Information loss for ldiv of Round [" + Phase3GUI.round + "] is " + roundloss_accumulator);
        System.out.println("Average Information loss for ldiv of Round [" + Phase3GUI.round + "] is " + roundloss);
        
        //from here downwards, not doing anything, i mean they are useless: really?
        for (int b = 0; b < RESIDENCE.length; b++) {

            dbRESIDENCE[b] = RESIDENCE[b];

        }
        Integer[] InfoLoss = new Integer[Generalizer.infoloss.size()];

        Integer[] rInfoLoss = Generalizer.infoloss.toArray(InfoLoss);

        int size = Generalizer.infoloss.size();


    }

    public double loss_of_info(String datas) {
        double infoloss = 0.0;
        // double row_infoloss [] = new double [datas.length];
        double infoloss_accumulator = 0.0;

        {
            if ("Baxter Hall".equals(datas)) {

                infoloss = total_room_baxter / total_address;

            } else if ("Clarinus Village".equals(datas)) {
                infoloss = total_room_clarinus / total_address;
            } else if ("College House".equals(datas)) {
                infoloss = total_room_college_house / total_address;
            } else if ("Fuller Hall".equals(datas)) {
                infoloss = total_room_FullerHall / total_address;

            } else if ("Graca Machel Hall".equals(datas)) {
                infoloss = total_room_GracaMachel / total_address;

            } else if ("Glendower".equals(datas)) {
                infoloss = total_room_Glendower / total_address;

            } else if ("Kilindini".equals(datas)) {
                infoloss = total_room_kilindini / total_address;
            } else if ("Kopano".equals(datas)) {
                infoloss = total_room_kopano / total_address;
            } else if ("Leo Marquard Hall".equals(datas)) {

                infoloss = total_room_leoMarquard / total_address;

            } else if ("Rochester House".equals(datas)) {

                infoloss = total_room_Rochester / total_address;

            } else if ("Smuts Hall".equals(datas)) {

                infoloss = total_room_Smuts / total_address;

            } else if ("Tugwell Hall".equals(datas)) {

                infoloss = total_room_Tugwell / total_address;

            } else if ("University House".equals(datas)) {

                infoloss = total_room_UniversityHouse / total_address;

            } else if ("The University House Cottages".equals(datas)) {

                infoloss = total_room_UnivHouseCottages / total_address;

            } else if ("Varietas".equals(datas)) {
                infoloss = total_room_Varietas / total_address;

            } else if ("Groote Schuur Residence".equals(datas)) {

                infoloss = total_room_GrooteSchuur / total_address;

            } else if ("Medical Residence".equals(datas)) {

                infoloss = total_room_MedicalRes / total_address;

            } /* else  if ("Medical Residence".equals(datas))
                  
             {
                        
             infoloss  = total_room_MedicalRes/total_address;        
                       
             }*/ else if (("A Forest Hill".equals(datas)) || ("B Forest Hill".equals(datas))
                    || ("C Forest Hill".equals(datas)) || ("D Forest Hill".equals(datas))
                    || ("E Forest Hill".equals(datas)) || ("Forest Hill A-E").equals(datas)) {

                infoloss = total_room_ForestHillA_E / total_address;

            } else if ("Groote Schuur Mansions".equals(datas)) {

                infoloss = total_GrooteSchuurMansions / total_address;

            } else if ("Liesbeeck Gardens".equals(datas)) {

                infoloss = total_room_Liesbeek / total_address;

            } else if ("Obz Square".equals(datas)) {

                infoloss = total_room_Obz / total_address;

            } else if ("Rondeberg".equals(datas)) {

                infoloss = total_Rondeberg / total_address;

            } else if ("T.B. Davie Court".equals(datas)) {

                infoloss = total_room_TBDavies / total_address;

            } else if ("Block F Forest Hill".equals(datas)) {
                infoloss = total_room_ForestHillF / total_address;
            } else if ("J.P. Duminy Court".equals(datas)) {
                infoloss = total_room_JP / total_address;
            } else if ("The Woolsack".equals(datas)) {
                infoloss = total_room_Woolsack / total_address;
            } else if ("The Inglewood".equals(datas)) {
                infoloss = total_room_Inglewood / total_address;
            } else if ("The Avenue Road".equals(datas)) {
                infoloss = total_room_Avenue / total_address;
            } else if ("The Woodbine".equals(datas)) {
                infoloss = total_room_Woodbine / total_address;
            } else if ("Linkoping".equals(datas)) {
                infoloss = total_room_Linkoping / total_address;
            } else if ("North Grange".equals(datas)) {
                infoloss = total_room_NorthGrange / total_address;
            } else if ("Alma Road Rosebank".equals(datas)) {
                infoloss = total_alma / total_address;

            } /*  else if ("Lower York Rosebank".equals(datas)) 
                   
             {
             infoloss = total_lowerYork/total_address;
                       
             }*/ else if ("Smith Road Rosebank".equals(datas)) {
                infoloss = total_smith / total_address;

            } else if ("Pillans Road Rosebank".equals(datas)) {
                infoloss = total_pillans / total_address;

            } else if ("Sawkins Road Rosebank".equals(datas)) {
                infoloss = total_sawkins / total_address;

            } else if ("Liesbeek Road Rosebank".equals(datas)) {
                infoloss = total_liesbeekrd / total_address;

            } else if ("Banksia Road Rosebank".equals(datas)) {
                infoloss = total_banksia / total_address;

            } else if ("Langton Road Rosebank".equals(datas)) {
                infoloss = total_langton / total_address;

            } else if ("Bath Road Claremont".equals(datas)) {
                infoloss = total_bath / total_address;

            } /* else if ("Molloy Court Claremont".equals(datas))
                   
             {
             infoloss  = total_molloy/total_address;
                       
             }
                   
             else if ("Cavendish Avenue Claremont".equals(datas))
                   
             {
             infoloss  = total_cavendish/total_address;
                       
             }*/ else if ("Protea Road Claremont".equals(datas)) {
                infoloss = total_protea / total_address;

            } else if ("Main Road Claremont".equals(datas)) {
                infoloss = total_clmainroad / total_address;

            } else if ("Palmyra Road Claremont".equals(datas)) {
                infoloss = total_palmyra / total_address;

            } else if ("Main Road Rondebosch".equals(datas)) {
                infoloss = total_romain / total_address;

            } else if ("Ednam Road Rondebosch".equals(datas)) {
                infoloss = total_ednam / total_address;

            } else if ("Miner Road Rondebosch".equals(datas)) {
                infoloss = total_miner / total_address;

            } else if ("Belmont Road Rondebosch".equals(datas)) {
                infoloss = total_belmont / total_address;

            } else if ("Klipper Road Rondebosch".equals(datas)) {
                infoloss = total_klipper / total_address;

            } else if ("Baxter Avenue Rondebosch".equals(datas)) {
                infoloss = total_baxterAvenue / total_address;

            } else if ("Broadway Avenue Rondebosch".equals(datas)) {
                infoloss = total_broadway / total_address;

            } else if ("King Street Newsland".equals(datas)) {
                infoloss = total_kingstreet / total_address;
            } else if ("Oak Avenue Newsland".equals(datas)) {
                infoloss = total_oakavenue / total_address;

            } else if ("Palmboom Road Newsland".equals(datas)) {
                infoloss = total_palmboom / total_address;

            } else if ("Newsland Road Newsland".equals(datas)) {
                infoloss = total_newslandrd / total_address;

            } else if ("Durban Road Mowbray".equals(datas)) {
                infoloss = total_durban / total_address;

            } else if ("Clandoenian Court Mowbray".equals(datas)) {
                infoloss = total_clandoenian / total_address;

            } else if ("Bridge Street Mowbray".equals(datas)) {
                infoloss = total_bridge / total_address;

            } else if ("Klipfontein Road Mowbray".equals(datas)) {
                infoloss = total_klipfontein / total_address;

            } else if ("Osborne Road Mowbray".equals(datas)) {
                infoloss = total_osborne / total_address;

            } else if ("1st Tier Accomodation".equals(datas)) {
                infoloss = total_1st_tier / total_address;

            } else if ("2nd Tier Accomodation".equals(datas)) {
                infoloss = total_2nd_tier / total_address;

            } else if ("Senior Catering 2nd Tier Accomodation".equals(datas)) {
                infoloss = total_2ndSenior_tier / total_address;

            } else if ("Self Catering 2nd Tier Accomodation".equals(datas)) {
                infoloss = total_2ndSelf_tier / total_address;

            } else if ("3rd Tier Accomodation".equals(datas)) {
                infoloss = total_3rd_tier / total_address;

            } else if ("Off-Campus Accomodation (Rosebank)".equals(datas)) {
                infoloss = total_rosebank / total_address;

            } else if ("Off-Campus Accomodation (Mowbray)".equals(datas)) {
                infoloss = total_mowbray / total_address;

            } else if ("Off-Campus Accomodation (Claremont)".equals(datas)) {
                infoloss = total_claremont / total_address;

            } else if ("Off-Campus Accomodation (Rondebosch)".equals(datas)) {
                infoloss = total_rondebosch / total_address;

            } else if ("Off-Campus Accomodation (Newsland)".equals(datas)) {
                infoloss = total_newsland / total_address;

            } else if ("UCT RESIDENCE".equals(datas)) {
                infoloss = total_uct_residence / total_address;

            } else if ("Off-Campus Accomodation (Southern Suburbs)".equals(datas)) {
                infoloss = total_south_surburb / total_address;

            } else if ("Cape Town".equals(datas)) {
                infoloss = total_address / total_address;

            } else {
                if (Phase3GUI.GenStep == 0) {
                    infoloss = 0;
                }

            }

        }
        System.out.println("reuse iloss " + infoloss);
        reuse_roundloss_accumulator = reuse_roundloss_accumulator + infoloss;
        return infoloss;
    }

    int total_room_baxter = 5;
    int total_room_clarinus = 12;
    int total_room_college_house = 3;
    int total_room_FullerHall = 5;
    int total_room_GracaMachel = 8;
    int total_room_Glendower = 3;
    int total_room_kilindini = 1;
    int total_room_kopano = 8;
    int total_room_leoMarquard = 9;
    int total_room_Rochester = 10;
    int total_room_Smuts = 5;
    int total_room_Tugwell = 9;
    int total_room_UniversityHouse = 3;
    int total_room_Varietas = 3;
    int total_room_GrooteSchuur = 2;
    int total_room_MedicalRes = 3;
    int total_room_ForestHillA_E = 5;
    int total_GrooteSchuurMansions = 2;
    int total_room_Liesbeek = 9;
    int total_room_Woolsack = 5;
    int total_room_Obz = 12;
    int total_Rondeberg = 1;
    int total_room_TBDavies = 1;
    int total_room_ForestHillF = 1;
    int total_room_JP = 3;
    int total_room_UnivHouseCottages = 1;
    int total_room_Inglewood = 1;
    int total_room_Avenue = 1;
    int total_room_Woodbine = 1;
    int total_room_Linkoping = 1;
    int total_room_NorthGrange = 1;
    double total_1st_tier = total_room_baxter + total_room_clarinus + total_room_college_house
            + total_room_FullerHall + total_room_GracaMachel + total_room_Glendower + total_room_kilindini
            + total_room_kopano + total_room_leoMarquard + total_room_Rochester + total_room_Smuts
            + total_room_Tugwell + total_room_UniversityHouse + total_room_Varietas;
    double total_2ndSelf_tier = total_room_ForestHillA_E + total_GrooteSchuurMansions + total_room_Liesbeek
            + total_room_Woolsack + total_room_Obz;
    double total_2ndSenior_tier = total_room_GrooteSchuur + total_room_MedicalRes;
    double total_2nd_tier = total_2ndSelf_tier + total_2ndSenior_tier;
    double total_3rd_tier = total_Rondeberg + total_room_TBDavies + total_room_ForestHillF
            + total_room_JP + total_room_UnivHouseCottages + total_room_Inglewood + total_room_Avenue
            + total_room_Woodbine + total_room_Linkoping + total_room_NorthGrange;
    int total_alma = 1;
    int total_smith = 1, total_pillans = 1, total_liesbeekrd = 1;// total_lowerYork = 1;
    int total_langton = 1, total_banksia = 1, total_sawkins = 1;
    int total_durban = 1, total_bridge = 1, total_klipfontein = 1, total_osborne = 1;
    int total_clandoenian = 1, total_church = 1;
    int total_clmainroad = 1, total_protea = 1, total_palmyra = 1, total_bath = 1;
    //total_cavendish =1, + total_molloy total_molloy = 1 + total_dean
    int total_ednam = 1, total_belmont = 1, total_miner = 1, total_klipper = 1, total_baxterAvenue = 1, total_romain = 1;
    int total_broadway = 1, total_kingstreet = 1, total_oakavenue = 1, total_palmboom = 1, total_newslandrd = 1, total_dean = 1;
    int total_rosebank = 7;
    int total_mowbray = total_durban + total_bridge + total_klipfontein + total_osborne
            + total_clandoenian + total_church;
    int total_claremont = total_clmainroad + total_protea + total_palmyra + total_bath;
    int total_rondebosch = total_ednam + total_belmont + total_miner + total_klipper + total_baxterAvenue
            + total_romain;
    int total_newsland = total_broadway + total_kingstreet + total_oakavenue + total_palmboom
            + total_newslandrd;
    double total_south_surburb = total_mowbray + total_claremont + total_rondebosch + total_newsland
            + total_rosebank;
    double total_uct_residence = total_1st_tier + total_2nd_tier + total_3rd_tier;
    double total_address = total_uct_residence + total_south_surburb;
}
