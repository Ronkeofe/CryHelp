
import java.util.ArrayList;
import java.util.Vector;

/**
 * Generalizes attributes of a single row or a cluster
 */
/**
 * @author Jose Trigueros
 *
 */
public class Generalizer {

    // Index names, needed to do this nonetheless
    //  static final int PRODUCT_ID = 0, PRICE = 1, DEPT_ID = 2, WEIGHT = 3, PRODUCT_YEAR = 4, EXPIRE_YEAR = 5;
    

    //defining the position of each quasi identifier as we have in the QuasiID file
    
    static final int ID_NUMBER = 0, RESIDENCE = 1, PLACE_OF_OCCURENCE = 2, CRIME_TYPE = 3;
    // static final int ID = 0, AGE = 1, CRIME_TYPE = 2;
    // TODO: Since this is a property of PRODUCT_ID, it may be moved to the enum stuff
    static final String ID_MAX_GEN = "****";
    // static final String WEIGHT_MAX_GEN = "<0-" + QuasiID.RESIDENCE.maxValue + ">";
   // static final String PLACE_MAX_GEN = "****";
    static final String PLACE_MAX_GEN = "Universe";
    ///static final double WEIGHT_CLUSTER_FACTOR = 2.0;
    // static final double DEPT_ID_CLUSTER_FACTOR = WEIGHT_CLUSTER_FACTOR;
    static final double AGE_CLUSTER_FACTOR = 2.0;
    static final double PRICE_CLUSTER_FACTOR = 10.0;
    public static Vector<Integer> infoloss = new Vector<Integer>();
    public static Vector<Integer[]> iloss = new Vector<Integer[]>();
    //public static ArrayList<String> stringList = new ArrayList<String>();
    //public static ArrayList<String> stringListCrime = new ArrayList<String>();

    // If the year has length one then it means, it must be our empty value of
    // "0"
    // static final int EMPTY = 1;
    // To verify that the algorithm works in all cases
    //  static final String EMPTY_YEAR = YEAR_MAX_GEN;
    // Find a better way to create these global ID
    /**
     * This method takes care of generalizing a given attribute a certain amount
     * of levels
     *
     * @param attribute The attribute that needs to be generalized.
     * @param id Determines what kind of generalization to perform.
     * @param infoLossLevels The number of levels to generalize.
     * @return String
     */
    public static String generalize(String attribute, QuasiID id, int infoLossLevels) {
        // Select the kind of generalization to perform
        switch (id.getPosition()) {
            case ID_NUMBER:
                //ID_NUMBER is not really a quasi identifier
                attribute = generalizeID(attribute, infoLossLevels);
                break;
            // case PRICE:
            //     attribute = generalizePrice( attribute, infoLossLevels );
            //     break;
            case PLACE_OF_OCCURENCE:
                attribute = generalizePLACE_OF_OCCURENCE(attribute, infoLossLevels);
                break;
            case RESIDENCE:
                //this is the main thing
                attribute = generalizeRESIDENCE(attribute, infoLossLevels);
                break;
            case CRIME_TYPE:
                attribute = generalizeCRIME_TYPE(attribute, infoLossLevels);
                break;
        }
        return attribute;
    }

    /**
     * Generalizes productID a certain amount of times
     *
     * @param numGeneralizations Determines the number of generalizations to
     * apply to productID
     * @return Returns the generalized productID
     */
    private static String generalizeID(String ID, int numGeneralizations) {

        // If numGenerlizations is 0 or less then nothing will be generalized
        if (numGeneralizations <= 0) {
            return ID;
        }

        // Determine the length of the remaining substring
        int endIndex = (ID.length() - numGeneralizations) > 0 ? ID.length() - numGeneralizations
                : QuasiID.ID_NUMBER.maxGeneralization;

        if (endIndex != QuasiID.ID_NUMBER.maxGeneralization) {
            // Trim the string down to it's general form
            ID = ID.substring(0, endIndex);

            // Ahh!! I got distracted! Back to coding :)
            // Append 'i' amount of asterisks to the general string
            for (int i = 0; i < (QuasiID.ID_NUMBER.maxGeneralization - endIndex); i++) {
                ID += "*";
            }
        } else {
            ID = ID_MAX_GEN;
        }

        return ID;
    }

    /**
     * Generalizes price a certain amount of times
     *
     * @return Returns the generalized price
     */
    /* private static String generalizePrice(String price, int numGeneralizations)
    {
        return generalizeRanges( price, PRICE_CLUSTER_FACTOR, numGeneralizations, QuasiID.PRICE );
    }*/
    /**
     * Generalizes deptID a certain amount of times
     *
     * @return Returns the generalized deptID
     */
    private static String generalizeRESIDENCE(String residenceAtrribute, int numGeneralizations) {
        return generalizeRESIDENCE(residenceAtrribute, numGeneralizations, QuasiID.RESIDENCE);
    }

    private static String generalizePLACE_OF_OCCURENCE(String PLACE_OF_OCCURENCE, int numGeneralizations) {
        return generalizePLACE_OF_OCCURENCE(PLACE_OF_OCCURENCE, numGeneralizations, QuasiID.PLACE_OF_OCCURENCE);
    }

    /**
     * Generalizes weight a certain amount of times
     *
     * @return Returns the generalized weight
     */
    private static String generalizeCRIME_TYPE(String weight, int numGeneralizations) {

        return (weight);
    }

    private static String generalizePLACE_OF_OCCURENCE(String PLACE_OF_OCCURENCE, int numGeneralizations, QuasiID id) {
        if (numGeneralizations <= 0) {
            return PLACE_OF_OCCURENCE;
        }

        // Check the case of the empty year and set empty year with a workable
        // string
        if (PLACE_OF_OCCURENCE.isEmpty()) {
            PLACE_OF_OCCURENCE = "   ";
        }

        // Perform the generalizations specified
        if (numGeneralizations < id.maxGeneralization) {
            // I guess the only way to do this is to brute force it
            switch (numGeneralizations) {

                // So if you have 1992 -> 90s
                case 1:

                    // System.out.println("numGeneralizations is  " + numGeneralizations);
                    if ("Bergvliet".contains(PLACE_OF_OCCURENCE) || " Bishopscourt".contains(PLACE_OF_OCCURENCE)
                            || "Claremont".contains(PLACE_OF_OCCURENCE) || "Constantia".contains(PLACE_OF_OCCURENCE)
                            || "Diep River".contains(PLACE_OF_OCCURENCE) || "Heathfield".contains(PLACE_OF_OCCURENCE)
                            || "Kenilworth".contains(PLACE_OF_OCCURENCE) || " Kenwyn".contains(PLACE_OF_OCCURENCE)
                            || "Kreupelbosch".contains(PLACE_OF_OCCURENCE) || "Meadowridge".contains(PLACE_OF_OCCURENCE)
                            || "Mowbray".contains(PLACE_OF_OCCURENCE) || "Newlands".contains(PLACE_OF_OCCURENCE)
                            || "Observatory".contains(PLACE_OF_OCCURENCE) || "Pinelands".contains(PLACE_OF_OCCURENCE)
                            || "Plumstead".contains(PLACE_OF_OCCURENCE) || "Retreat".contains(PLACE_OF_OCCURENCE)
                            || "Rondebosch".contains(PLACE_OF_OCCURENCE) || "Rondebosch East".contains(PLACE_OF_OCCURENCE)
                            || "Rosebank".contains(PLACE_OF_OCCURENCE) || "Steenberg".contains(PLACE_OF_OCCURENCE)
                            || "Tokai".contains(PLACE_OF_OCCURENCE) || "Wynberg".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "Southern Suburbs";
                    }

                    if ("Bellville".contains(PLACE_OF_OCCURENCE) || " Bothasig".contains(PLACE_OF_OCCURENCE)
                            || "Brackenfell".contains(PLACE_OF_OCCURENCE) || "Brooklyn".contains(PLACE_OF_OCCURENCE)
                            || "Durbanville".contains(PLACE_OF_OCCURENCE) || "Edgemead".contains(PLACE_OF_OCCURENCE)
                            || "Elsie's River".contains(PLACE_OF_OCCURENCE) || " Goodwood".contains(PLACE_OF_OCCURENCE)
                            || "Kensington".contains(PLACE_OF_OCCURENCE) || "Kraaifontein".contains(PLACE_OF_OCCURENCE)
                            || "Kuils River".contains(PLACE_OF_OCCURENCE) || "Monte Vista".contains(PLACE_OF_OCCURENCE)
                            || "Panorama".contains(PLACE_OF_OCCURENCE) || "Maitland".contains(PLACE_OF_OCCURENCE)
                            || "Parow".contains(PLACE_OF_OCCURENCE) || "Thornton".contains(PLACE_OF_OCCURENCE)
                            || "Table View".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "Northern Suburbs";
                    }

                    if ("Bo-Kaap (Malay Quarter)".contains(PLACE_OF_OCCURENCE) || "Devils Peak".contains(PLACE_OF_OCCURENCE)
                            || "Devil's Peak".contains(PLACE_OF_OCCURENCE)
                            || "De Waterkant".contains(PLACE_OF_OCCURENCE) || "Foreshore".contains(PLACE_OF_OCCURENCE)
                            || "Gardens".contains(PLACE_OF_OCCURENCE) || "Higgovale".contains(PLACE_OF_OCCURENCE)
                            || "Lower Vrede (District Six)".contains(PLACE_OF_OCCURENCE) || " Oranjezicht".contains(PLACE_OF_OCCURENCE)
                            || "Salt River".contains(PLACE_OF_OCCURENCE) || "Schotse Kloof (Malay Quarter)".contains(PLACE_OF_OCCURENCE)
                            || "Vredehoek".contains(PLACE_OF_OCCURENCE) || "University Estate".contains(PLACE_OF_OCCURENCE)
                            || "Tamboerskloof".contains(PLACE_OF_OCCURENCE) || "Walmer Estate (District Six)".contains(PLACE_OF_OCCURENCE)
                            || "Woodstock (including Upper Woodstock)".contains(PLACE_OF_OCCURENCE) || "Zonnebloem (District Six)".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "City Bowl";
                    }

                    if ("Capri Village".contains(PLACE_OF_OCCURENCE) || "Clovelly".contains(PLACE_OF_OCCURENCE)
                            || "Fish Hoek".contains(PLACE_OF_OCCURENCE) || "Glencairn".contains(PLACE_OF_OCCURENCE)
                            || "Kalk Bay".contains(PLACE_OF_OCCURENCE) || "Kommetjie".contains(PLACE_OF_OCCURENCE)
                            || "Lakeside".contains(PLACE_OF_OCCURENCE) || " Marina da Gama".contains(PLACE_OF_OCCURENCE)
                            || "Masiphumelele".contains(PLACE_OF_OCCURENCE) || "Muizenberg".contains(PLACE_OF_OCCURENCE)
                            || "Noordhoek (PO boxes only)".contains(PLACE_OF_OCCURENCE) || "Ocean View".contains(PLACE_OF_OCCURENCE)
                            || "Scarborough (PO boxes only)".contains(PLACE_OF_OCCURENCE) || " Simon's Town".contains(PLACE_OF_OCCURENCE)
                            || " Simons Town".contains(PLACE_OF_OCCURENCE) || "St James".contains(PLACE_OF_OCCURENCE) || "Sunnydale".contains(PLACE_OF_OCCURENCE) || " Simon's Town".contains(PLACE_OF_OCCURENCE)
                            || "Sun Valley".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "South Peninsula";
                    }

                    if ("Athlone".contains(PLACE_OF_OCCURENCE) || "Belhar".contains(PLACE_OF_OCCURENCE)
                            || "Bishop Lavis".contains(PLACE_OF_OCCURENCE) || "Blue Downs".contains(PLACE_OF_OCCURENCE)
                            || "Bonteheuwel".contains(PLACE_OF_OCCURENCE) || "Crawford".contains(PLACE_OF_OCCURENCE)
                            || "Delft".contains(PLACE_OF_OCCURENCE) || "Electric City".contains(PLACE_OF_OCCURENCE)
                            || "Elsies River".contains(PLACE_OF_OCCURENCE) || "Elsie's River".contains(PLACE_OF_OCCURENCE)
                            || "Epping".contains(PLACE_OF_OCCURENCE)
                            || "Grassy Park".contains(PLACE_OF_OCCURENCE) || "Gugulethu".contains(PLACE_OF_OCCURENCE)
                            || "Hanover Park".contains(PLACE_OF_OCCURENCE) || "Kalksteenfontein".contains(PLACE_OF_OCCURENCE)
                            || "Khayelitsha".contains(PLACE_OF_OCCURENCE) || "Langa".contains(PLACE_OF_OCCURENCE) || " Simon's Town".contains(PLACE_OF_OCCURENCE)
                            || "Lansdowne".contains(PLACE_OF_OCCURENCE) || "Lavender Hill".contains(PLACE_OF_OCCURENCE) || "Gugulethu".contains(PLACE_OF_OCCURENCE)
                            || "Lotus River".contains(PLACE_OF_OCCURENCE) || "Macassar".contains(PLACE_OF_OCCURENCE)
                            || "Manenberg".contains(PLACE_OF_OCCURENCE) || "Mitchell's Plain".contains(PLACE_OF_OCCURENCE) || " Simon's Town".contains(PLACE_OF_OCCURENCE)
                            || "Nyanga".contains(PLACE_OF_OCCURENCE) || "Ottery".contains(PLACE_OF_OCCURENCE) || "Lavender Hill".contains(PLACE_OF_OCCURENCE) || "Gugulethu".contains(PLACE_OF_OCCURENCE)
                            || "Pellican Park".contains(PLACE_OF_OCCURENCE) || "Philippi".contains(PLACE_OF_OCCURENCE)
                            || "Samora Micheal".contains(PLACE_OF_OCCURENCE) || "Strandfontein".contains(PLACE_OF_OCCURENCE) || " Simon's Town".contains(PLACE_OF_OCCURENCE)
                            || "Wetton".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "Cape Flat";
                    }

                    if ("Gordon's Bay".contains(PLACE_OF_OCCURENCE) || "Somerset West".contains(PLACE_OF_OCCURENCE)
                            || "Strand".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "Helderberg";
                    }

                    if ("Bloubergstrand".contains(PLACE_OF_OCCURENCE) || "Milnerton".contains(PLACE_OF_OCCURENCE)
                            || "Tableview".contains(PLACE_OF_OCCURENCE) || "West Beach".contains(PLACE_OF_OCCURENCE)
                            || "Parklands".contains(PLACE_OF_OCCURENCE)) {
                        PLACE_OF_OCCURENCE = "West Coast";
                    }

                    break;
                // So if you have 1992 -> 19**
                case 2:

                    PLACE_OF_OCCURENCE = "Cape Town";
                    break;

                case 3:

                    PLACE_OF_OCCURENCE = "South Africa";
                    break;

                case 4:

                    PLACE_OF_OCCURENCE = "Southern Africa";
                    break;

                case 5:

                    PLACE_OF_OCCURENCE = "Africa";
                    break;

                case 6:

                    PLACE_OF_OCCURENCE = "World";
                    break;

                // year = year.substring( 0, 2 ) + "**";
            }

        } else {
            PLACE_OF_OCCURENCE = PLACE_MAX_GEN;
        }

        return PLACE_OF_OCCURENCE;
    }

    //return generalizeRESIDENCE(RESIDENCE, numGeneralizations, QuasiID.RESIDENCE);
    public static String generalizeRESIDENCE(String RESIDENCE, int numGeneralizations, QuasiID id) {
        if (numGeneralizations <= 0) {
            return RESIDENCE;
        }

        // Check the case of the empty year and set empty year with a workable
        // string
        //  if (RESIDENCE.isEmpty())
        //      RESIDENCE = "   ";
        // Perform the generalizations specified
        if (numGeneralizations < id.maxGeneralization) {
            // I guess the only way to do this is to brute force it
            switch (numGeneralizations) {
                // So if you have 1992 -> 90s
                case 1:
                    if (RESIDENCE.contains("Baxter Hall")) {
                        /*if (RESIDENCE.contains("Flat 1") || RESIDENCE.contains ("Flat 2") 
                       ||RESIDENCE.contains("Flat 3") ) */
                        RESIDENCE = "Baxter Hall";
                    } else if (RESIDENCE.contains("Clarinus Village")) {
                        RESIDENCE = "Clarinus Village";
                    } else if (RESIDENCE.contains("College House")) {
                        RESIDENCE = "College House";
                    } else if (RESIDENCE.contains("Fuller Hall")) {
                        RESIDENCE = "Fuller Hall";
                    } else if (RESIDENCE.contains("Graca Machel Hall")) {
                        RESIDENCE = "Graca Machel Hall";
                    } else if (RESIDENCE.contains("Glendower")) {
                        RESIDENCE = "Glendower ";
                    } else if (RESIDENCE.contains("Kilindini")) {
                        RESIDENCE = "Kilindini";
                    } else if (RESIDENCE.contains("Kopano")) {
                        RESIDENCE = "Kopano";
                    } else if (RESIDENCE.contains("Leo Marquard Hall")) {
                        RESIDENCE = "Leo Marquard Hall";
                    } else if (RESIDENCE.contains("Rochester House")) {
                        RESIDENCE = "Rochester House";
                    } else if (RESIDENCE.contains("Smuts Hall")) {
                        RESIDENCE = "Smuts Hall";
                    } else if (RESIDENCE.contains("Tugwell Hall")) {
                        RESIDENCE = "Tugwell Hall";
                    } else if (RESIDENCE.contains("University House Cottages")) //^ RESIDENCE.contains("Cottages"))
                    {
                        RESIDENCE = "The University House Cottages";
                    } else if (RESIDENCE.contains("University House")) {
                        RESIDENCE = "University House";
                    } else if (RESIDENCE.contains("Varietas")) {
                        RESIDENCE = "Varietas";
                    } else if (RESIDENCE.contains("Groote Schuur Residence")) {
                        RESIDENCE = "Groote Schuur Residence";
                    } else if (RESIDENCE.contains("Medical Residence")) {
                        RESIDENCE = "Medical Residence";
                    } /* else  if (RESIDENCE.contains("Medical Residence"))
                    {
                    RESIDENCE  = "Medical Residence";
                    } */ else if ((RESIDENCE.contains("A Forest Hill")) || (RESIDENCE.contains("B Forest Hill"))
                            || (RESIDENCE.contains("C Forest Hill")) || (RESIDENCE.contains("D Forest Hill"))
                            || (RESIDENCE.contains("E Forest Hill"))) {
                        RESIDENCE = "Forest Hill A-E";
                    } else if (RESIDENCE.contains("Groote Schuur Mansions")) {
                        RESIDENCE = "Groote Schuur Mansions";
                    } else if (RESIDENCE.contains("Liesbeeck Gardens")) {
                        RESIDENCE = "Liesbeeck Gardens";
                    } else if (RESIDENCE.contains("Obz Square")) {
                        RESIDENCE = "Obz Square";
                    } else if (RESIDENCE.contains("Rondeberg")) {
                        RESIDENCE = "Rondeberg";
                    } else if (RESIDENCE.contains("T.B. Davie Court")) {
                        RESIDENCE = "T.B. Davie Court";
                    } else if (RESIDENCE.contains("Block F Forest Hill")) {
                        RESIDENCE = "Block F Forest Hill";
                    } else if (RESIDENCE.contains("J.P. Duminy Court")) {
                        RESIDENCE = "J.P. Duminy Court";
                    } else if (RESIDENCE.contains("Woolsack")) {
                        RESIDENCE = "The Woolsack";
                    } else if (RESIDENCE.contains("Inglewood")) {
                        RESIDENCE = "The Inglewood";
                    } else if (RESIDENCE.contains("Avenue Road")) {
                        RESIDENCE = "The Avenue Road";
                    } else if (RESIDENCE.contains("Woodbine Road")) {
                        RESIDENCE = "The Woodbine Road";
                    } else if (RESIDENCE.contains("Linkoping")) {
                        RESIDENCE = "Linkoping";
                    } else if (RESIDENCE.contains("North Grange")) {
                        RESIDENCE = "North Grange";
                    } else {

                        if (RESIDENCE.contains("Rosebank")) {
                            if (RESIDENCE.contains("Alma Road")) {
                                RESIDENCE = "Alma Road Rosebank";
                            } else if (RESIDENCE.contains("Lower York")) {
                                RESIDENCE = "Lower York Rosebank";
                            } else if (RESIDENCE.contains("Smith Road")) {
                                RESIDENCE = "Smith Road Rosebank";
                            } else if (RESIDENCE.contains("Pillans Road")) {
                                RESIDENCE = "Pillans Road Rosebank";
                            } else if (RESIDENCE.contains("Sawkins Road")) {
                                RESIDENCE = "Sawkins Road Rosebank";
                            } else if (RESIDENCE.contains("Liesbeek Road")) {
                                RESIDENCE = "Liesbeek Road Rosebank";
                            } else if (RESIDENCE.contains("Banksia Road")) {
                                RESIDENCE = "Banksia Road Rosebank";
                            } else if (RESIDENCE.contains("Langton Road")) {
                                RESIDENCE = "Langton Road Rosebank";
                            }
                            //RESIDENCE  = "Off-Campus Accomodation (Rosebank)";
                        }
                        if (RESIDENCE.contains("Claremont")) {
                            if (RESIDENCE.contains("Bath Road")) {
                                RESIDENCE = "Bath Road Claremont";
                            } else if (RESIDENCE.contains("Molloy Court")) {
                                RESIDENCE = "Molloy Court Claremont";
                            } else if (RESIDENCE.contains("Cavendish Avenue")) {
                                RESIDENCE = "Cavendish Avenue Claremont";
                            } else if (RESIDENCE.contains("Protea Road")) {
                                RESIDENCE = "Protea Road Claremont";
                            } else if (RESIDENCE.contains("Main Road")) {
                                RESIDENCE = "Main Road Claremont";
                            } else if (RESIDENCE.contains("Palmyra Road")) {
                                RESIDENCE = "Palmyra Road Claremont";
                            }
                        }

                        if (RESIDENCE.contains("Rondebosch")) {
                            if (RESIDENCE.contains("Main Road")) {
                                RESIDENCE = "Main Road Rondebosch";
                            } else if (RESIDENCE.contains("Ednam Road")) {
                                RESIDENCE = "Ednam Road Rondebosch";
                            } else if (RESIDENCE.contains("Miner Road")) {
                                RESIDENCE = "Miner Road Rondebosch";
                            } else if (RESIDENCE.contains("Belmont Road")) {
                                RESIDENCE = "Belmont Road Rondebosch";
                            } else if (RESIDENCE.contains("Klipper Road")) {
                                RESIDENCE = "Klipper Road Rondebosch";
                            } else if (RESIDENCE.contains("Baxter Avenue")) {
                                RESIDENCE = "Baxter Avenue Rondebosch";
                            }
                        }

                        if (RESIDENCE.contains("Mowbray")) {
                            if (RESIDENCE.contains("Durban Road")) {
                                RESIDENCE = "Durban Road Mowbray";
                            } else if (RESIDENCE.contains("Clandoenian Court")) {
                                RESIDENCE = "Clandoenian Court Mowbray";
                            } else if (RESIDENCE.contains("Church Road")) {
                                RESIDENCE = "Church Road Mowbray";
                            } else if (RESIDENCE.contains("Bridge Street")) {
                                RESIDENCE = "Bridge Street Mowbray";
                            } else if (RESIDENCE.contains("Klipfontein Road")) {
                                RESIDENCE = "Klipfontein Road Mowbray";
                            } else if (RESIDENCE.contains("Osborne Road")) {
                                RESIDENCE = "Osborne Road Mowbray";
                            }
                        }

                        if (RESIDENCE.contains("Newsland")) {
                            if (RESIDENCE.contains("Broadway Avenue")) {
                                RESIDENCE = "Broadway Avenue Newsland";
                            } else if (RESIDENCE.contains("King Street")) {
                                RESIDENCE = "King Street Newsland";
                            } else if (RESIDENCE.contains("Oak Avenue")) {
                                RESIDENCE = "Oak Avenue Newsland";
                            } else if (RESIDENCE.contains("Palmboom Road")) {
                                RESIDENCE = "Palmboom Road Newsland";
                            } else if (RESIDENCE.contains("Newsland Road")) {
                                RESIDENCE = "Newsland Road Newsland";
                            }
                            /* else if (RESIDENCE.contains("Baxter Avenue"))
                   {
                       RESIDENCE  = "Baxter Avenue (Rondebosch)";
                   }*/
                        }

                        // RESIDENCE  = "Off-Campus";
                    }

                    break;

                case 2:
                    if (RESIDENCE.contains("Baxter Hall") || RESIDENCE.contains("Clarinus Village") || RESIDENCE.contains("College House")
                            || RESIDENCE.contains("Fuller Hall") || RESIDENCE.contains("Graca Machel Hall") || RESIDENCE.contains("Glendower")
                            || RESIDENCE.contains("Kilindini") || RESIDENCE.contains("Kopano") || RESIDENCE.contains("Leo Marquard Hall")
                            || RESIDENCE.contains("Rochester House") || RESIDENCE.contains("Smuts Hall") || RESIDENCE.contains("Tugwell Hall")
                            || RESIDENCE.contains("University House") || RESIDENCE.contains("Varietas")) //  System.out.println("numGeneralizations is  " + numGeneralizations);    
                    {
                        // System.out.println("numGeneralizations is  " + numGeneralizations);
                        RESIDENCE = "1st Tier Accomodation";
                    } else if (RESIDENCE.contains("Groote Schuur Residence") || RESIDENCE.contains("Medical Residence")) // Groote Schuur Residence
                    {
                        RESIDENCE = "Senior Catering 2nd Tier Accomodation";
                        break;
                    } else if (RESIDENCE.contains("Block A Forest Hill") || RESIDENCE.contains("Block B Forest Hill") || RESIDENCE.contains("Block C Forest Hill")
                            || RESIDENCE.contains("Block D Forest Hill") || RESIDENCE.contains("Block E Forest Hill") || RESIDENCE.contains("BLOCK A Forest Hill")
                            || RESIDENCE.contains("BLOCK B Forest Hill") || RESIDENCE.contains("BLOCK C Forest Hill") || RESIDENCE.contains("BLOCK D Forest Hill")
                            || RESIDENCE.contains("BLOCK E Forest Hill") || RESIDENCE.contains("Groote Schuur Mansions")
                            || RESIDENCE.contains("Liesbeeck Gardens") || RESIDENCE.contains("The Woolsack")
                            || RESIDENCE.contains("Obz Square")) {
                        //System.out.println("numGeneralizations is  " + numGeneralizations);
                        RESIDENCE = "Self Catering 2nd Tier Accomodation";
                        break;
                    } /*  else if ( RESIDENCE .contains("Groote Schuur Residence") ||  RESIDENCE.contains("Medical Residence") 
                       || RESIDENCE.contains("Groote Schuur Mansions" ) ||  RESIDENCE.contains("Liesbeeck Gardens" ) 
                      ||  RESIDENCE.contains("A Forest Hill") ||  RESIDENCE.contains( "B Forest Hill") ||  RESIDENCE.contains("C Forest Hill") 
                           || RESIDENCE.contains("D Forest Hill")  || RESIDENCE.contains("E Forest Hill")  ||  RESIDENCE.contains("The Woolsack" )
                           ||RESIDENCE.contains("Obz Square" ))
                    {
                     RESIDENCE = "2nd Tier Accomodation";
                    }*/ else if (RESIDENCE.contains("Rondeberg") || RESIDENCE.contains("T.B. Davie Court") || RESIDENCE.contains("Block F Forest Hill")
                            || RESIDENCE.contains("J.P. Duminy Court") || RESIDENCE.contains("University House Cottages") || RESIDENCE.contains("Inglewood")
                            || RESIDENCE.contains("Avenue Road") || RESIDENCE.contains("Woodbine Road") || RESIDENCE.contains("Linkoping")
                            || RESIDENCE.contains("North Grange")) {
                        // System.out.println("numGeneralizations is  " + numGeneralizations);
                        RESIDENCE = "3rd Tier Accomodation";
                    } else {

                        /* if (RESIDENCE.contains("Rosebank") || RESIDENCE.contains("Claremont") || 
                       RESIDENCE.contains("Mowbray") || RESIDENCE.contains("Rondebosch") ) 
               
               {
                    RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
               }*/
                        if (RESIDENCE.contains("Rosebank")) {
                            RESIDENCE = "Off-Campus Accomodation (Rosebank)";
                        }

                        if (RESIDENCE.contains("Claremont")) {
                            RESIDENCE = "Off-Campus Accomodation (Claremont)";
                        }

                        if (RESIDENCE.contains("Mowbray")) {
                            RESIDENCE = "Off-Campus Accomodation (Mowbray)";
                        }
                        if (RESIDENCE.contains("Rondebosch")) {
                            RESIDENCE = "Off-Campus Accomodation (Rondebosch)";
                        }
                        if (RESIDENCE.contains("Newsland")) {
                            RESIDENCE = "Off-Campus Accomodation (Newsland)";
                        }
                    }

                    break;

                // So if you have 1992 -> 19**
                /* case 3:
                    
                    if ( RESIDENCE .contains("Groote Schuur Residence") ||  RESIDENCE.contains("Medical Residence") 
                       || RESIDENCE.contains("Groote Schuur Mansions" ) ||  RESIDENCE.contains("Liesbeeck Gardens" ) 
                      ||  RESIDENCE.contains("A Forest Hill") ||  RESIDENCE.contains( "B Forest Hill") ||  RESIDENCE.contains("C Forest Hill") 
                           || RESIDENCE.contains("D Forest Hill")  || RESIDENCE.contains("E Forest Hill")  ||  RESIDENCE.contains("The Woolsack" )
                           ||RESIDENCE.contains("Obz Square" ))
                    {
                     RESIDENCE = "2nd Tier Accomodation";
                    }
                    
                    break;*/
                case 3:
                    if (RESIDENCE.contains("Baxter Hall") || RESIDENCE.contains("Clarinus Village") || RESIDENCE.contains("College House")
                            || RESIDENCE.contains("Fuller Hall") || RESIDENCE.contains("Graca Machel Hall") || RESIDENCE.contains("Glendower")
                            || RESIDENCE.contains("Kilindini") || RESIDENCE.contains("Kopano") || RESIDENCE.contains("Leo Marquard Hall")
                            || RESIDENCE.contains("Rochester House") || RESIDENCE.contains("Smuts Hall") || RESIDENCE.contains("Tugwell Hall")
                            || RESIDENCE.contains("University House") || RESIDENCE.contains("Varietas")) //  System.out.println("numGeneralizations is  " + numGeneralizations);    
                    {
                        // System.out.println("numGeneralizations is  " + numGeneralizations);
                        RESIDENCE = "1st Tier Accomodation";
                    } else if (RESIDENCE.contains("Groote Schuur Residence") || RESIDENCE.contains("Medical Residence")
                            || RESIDENCE.contains("Groote Schuur Mansions") || RESIDENCE.contains("Liesbeeck Gardens")
                            || RESIDENCE.contains("A Forest Hill") || RESIDENCE.contains("B Forest Hill") || RESIDENCE.contains("C Forest Hill")
                            || RESIDENCE.contains("D Forest Hill") || RESIDENCE.contains("E Forest Hill") || RESIDENCE.contains("The Woolsack")
                            || RESIDENCE.contains("Obz Square")) {
                        RESIDENCE = "2nd Tier Accomodation";
                    } else if (RESIDENCE.contains("Rondeberg") || RESIDENCE.contains("T.B. Davie Court") || RESIDENCE.contains("Block F Forest Hill")
                            || RESIDENCE.contains("J.P. Duminy Court") || RESIDENCE.contains("University House Cottages") || RESIDENCE.contains("Inglewood")
                            || RESIDENCE.contains("Avenue Road") || RESIDENCE.contains("Woodbine Road") || RESIDENCE.contains("Linkoping")
                            || RESIDENCE.contains("North Grange")) {
                        // System.out.println("numGeneralizations is  " + numGeneralizations);
                        RESIDENCE = "3rd Tier Accomodation";
                    } else {

                        /* if (RESIDENCE.contains("Rosebank") || RESIDENCE.contains("Claremont") || 
                       RESIDENCE.contains("Mowbray") || RESIDENCE.contains("Rondebosch") ) 
               
               {
                    RESIDENCE  = "Off-Campus Accomodation (Southern Suburbs)";
               }*/
                        if (RESIDENCE.contains("Rosebank")) {
                            RESIDENCE = "Off-Campus Accomodation (Rosebank)";
                        }

                        if (RESIDENCE.contains("Claremont")) {
                            RESIDENCE = "Off-Campus Accomodation (Claremont)";
                        }

                        if (RESIDENCE.contains("Mowbray")) {
                            RESIDENCE = "Off-Campus Accomodation (Mowbray)";
                        }
                        if (RESIDENCE.contains("Rondebosch")) {
                            RESIDENCE = "Off-Campus Accomodation (Rondebosch)";
                        }
                        if (RESIDENCE.contains("Newsland")) {
                            RESIDENCE = "Off-Campus Accomodation (Newsland)";
                        }
                    }

                    break;

                case 4:

                    // System.out.println("numGeneralizations is  " + numGeneralizations);
                    if (RESIDENCE.contains("Rosebank") || RESIDENCE.contains("Claremont")
                            || RESIDENCE.contains("Mowbray") || RESIDENCE.contains("Rondebosch")
                            || RESIDENCE.contains("Newsland")) {
                        RESIDENCE = "Off-Campus Accomodation (Southern Suburbs)";
                    } else {
                        RESIDENCE = "UCT RESIDENCE";
                    }

                    break;

                /* case 5:
                    
                    RESIDENCE  = "Southern Suburbs";
                    break;*/
                case 5:

                    RESIDENCE = "Cape Town";
                    break;

                case 6:
                    RESIDENCE = "South Africa";

                    break;

                case 7:
                    RESIDENCE = "Southern Africa";

                    break;

                case 8:
                    RESIDENCE = "Africa";
                    break;

                case 9:
                    RESIDENCE = "World";
                    break;

                // year = year.substring( 0, 2 ) + "**";
            }

        } else {
            RESIDENCE = PLACE_MAX_GEN;
        }

        return RESIDENCE;
    }

    /**
     * This method takes two string attributes and generalizes each attribute
     * until the attributes match and then returns the number of times that the
     * attributes had to be generalized.
     *
     * @param attribute1
     * 
     * @param attribute2
     * @param id
     * @return Returns the number of times it takes for the two attributes to be
     * generalized.
     */
    public static int getNumGeneralization(String attribute1, String attribute2, QuasiID id) {
        int numGeneralizations = 0;

        // First check obvious scenario
        if (attribute1.equals(attribute2)) {
            return numGeneralizations;
        }

        // Now iterate through the generalizations until both match
        for (int i = 1; i <= id.maxGeneralization; i++) {
            numGeneralizations++;
            if (generalize(attribute1, id, i).equals(generalize(attribute2, id, i))) {
                // System.out.println(" numGeneralizations is " + numGeneralizations);
                break;
            }// It's a match!}
        }
        //  System.out.println(" numGeneralizations is " + numGeneralizations);
        return numGeneralizations;
    }

    public static String[][] getGeneralizedDataArray(GeneralizationSteps solution, String[] getAttributesToGeneralize) {        
        // get all the enabled quasi-ids.  
        // the number of ids is useful data.
        // the comma separated string of ids is useful 
        // for database queries...
        QuasiID[] enabledIds = solution.getEnabledQuasiIds();
        String quasiIds = "";
        for (QuasiID id : enabledIds) {
            quasiIds += "," + id.getAttributeName();  //could produce ,crime, entry_time
        }
        // get comma separated ids for db queries
        quasiIds = quasiIds.substring(1);  //removing the first comma from the string. Used in querying the database basically

        // get number of ids for use later
        int numQuasiIds = enabledIds.length;
        //System.out.println("enabled QI is " + numQuasiIds);

        // If getAttributesToGeneralize is null, just return all data..not of much interest to us joor
        if (getAttributesToGeneralize == null) {
            DBManager dbManager = new DBManager();

            String[] data = dbManager.runQuery("SELECT " + quasiIds + " FROM Crime ");
            String[][] output = new String[(data.length / numQuasiIds)][numQuasiIds];

            dbManager.closeConnection(false);

            int outputPointer = 0;
            for (int i = 0; i < data.length; i += numQuasiIds) {
                for (int j = 0; j < numQuasiIds; j++) {
                    //   System.out.println("Unanonymised data is   " + data[i + j]);
                    output[outputPointer][j] = data[i + j];
                }
                outputPointer++;
            }
            return output;

        } else {

            // if the getAttributesToGeneralize arg has data, we have to generalize
            // Arraylist of string[] to hold our complete set of generalized output data
            ArrayList<String[]> generalizedData = new ArrayList<String[]>();
            
             //System.out.println(getAttributesToGeneralize);
//            for(String data:getAttributesToGeneralize){
//               // System.out.println("Here "+ data);
//            }
//            
           
            
           // System.out.println("Num of quasi Id "+ numQuasiIds);
            // go through the entire getAttributesToGeneralize, an array of records attribute to 
            for (int i = 0; i < getAttributesToGeneralize.length; i++) {
                int info_loss = 0;
                
                // split the attribute value of this particular entry string e.g 1, Rm 4 Abolajoko where 1 is the number of times to repeat this data
                String[] temp1 = getAttributesToGeneralize[i].split(",");
                // repeats holds the number of time to repeat the data set
                // to the output
                int repeats = Integer.parseInt(temp1[0]);
              

                // temp2 will only hold the attribute data, basically a array of length 1
                String[] temp2 = new String[temp1.length - 1];

                // generalize each of the attributes and place into temp2
                for (int j = 0; j < numQuasiIds; j++) {
                   // System.out.println("The enable Id is "+ enabledIds[j]);
                    String generalizedAttribute = generalize(temp1[j + 1], enabledIds[j], solution.getGenStepValue(enabledIds[j]));
                    // temp2 now contains the generalized attribute for position j
                    Phase3GUI.GenStep = solution.getGenStepValue(enabledIds[j]);
                    System.out.println("GenStepValue  " + solution.getGenStepValue(enabledIds[j]));
                    info_loss = solution.getGenStepValue(enabledIds[j]);
                    // infoloss.get;
                    //  Integer info_loss = solution.getGenStepValue( enabledIds[j] );
                    temp2[j] = generalizedAttribute;
                    //  System.out.println("gen att is   " + generalizedAttribute);

                }

                // now place the generalized string array into the 
                // generalizedData vector for the number of times 
                // it is repeated in the database.
                for (int k = 0; k < repeats; k++) {
                    // add a new string array to generalizedData
                    generalizedData.add(new String[numQuasiIds]);
                    iloss.add(new Integer[numQuasiIds]);

                    // place the generalized attributes into the proper
                    // position of the string array in generalizedData
                    for (int l = 0; l < numQuasiIds; l++) {
                        generalizedData.get(generalizedData.size() - 1)[l] = temp2[l];
                        //   System.out.println("info loss is   " + iloss.get(iloss.size()-1)[l]);
                        iloss.get(iloss.size() - 1)[l] = info_loss;
                        infoloss.add(info_loss);
                        //   System.out.println("info loss is   " + iloss.get(iloss.size()-1)[l]);
                    }
                }
            }

            // Time to prepare the final 2D string array to be returned by the method
            String[][] output = new String[generalizedData.size()][numQuasiIds];

            // Go through generalizedData and place all data into the 2D string
            // array output
            for (int i = 0; i < generalizedData.size(); i++) {
                for (int j = 0; j < numQuasiIds; j++) {
                    output[i][j] = generalizedData.get(i)[j];
                }
            }

            return output;
        }
    }

}
