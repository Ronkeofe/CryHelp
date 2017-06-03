//package writer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DBDataParser {

    // Global VariablesNUM_COLS     = 6
    public static int temp_i = 1, temp_j;
    //public static double[] arrival_time; 
    private static long startTime;
    private static long endTime;
    //= System.currentTimeMillis();
    private static final int NUM_COLS = 12;
    private static final int ID_NUMBER = 0,
            FIRST_NAME = 1,
            LAST_NAME = 2,
            STUDENT_NUMBER = 3,
            CELL_NUMBER = 4,
            HOME_NUMBER = 5,
            RESIDENCE = 6,
            CRIME_TYPE = 7,
            PLACE_OF_OCCURENCE = 8,
            ENTRY_TIME = 9,
            ARRIVAL_TIME = 10,
            EXPECTED_WAITING_TIME = 11;

    private static final int SLEEP_TIME_1000 = 800;
    private static final int SLEEP_TIME_5000 = 400;
    private static final int SLEEP_TIME_10000 = 200;
    private static final int SLEEP_TIME_25000 = 100;
    private static final int SLEEP_TIME_50000 = 50;
    private static final int SLEEP_TIME_100000 = 20;
    private static final int SLEEP_TIME_250000 = 10;
    private static final int SLEEP_TIME_500000 = 5;
    private static final int SLEEP_TIME_750000 = 2;
    private static final int SLEEP_TIME_1000000 = 1;

    /**
     * Reads an excel file and collects the information putting it in a
     * database.
     *
     * @param statement
     * @param filename n
     */
    public static void populateDatabase(Statement statement, String filename) {
        String[] rowBuffer = new String[NUM_COLS];

        if (Phase3GUI.row_position_in_file == 1) {
            System.out.println("The life time of buffer for round " + Phase3GUI.row_position_in_file + " is set to 5000ms ");
        }
        startTime = System.currentTimeMillis();
            //temp_i = Phase3GUI.row_position_in_file;
        // while(true){
        while (Phase3GUI.rowIterator.hasNext()) {
            endTime = System.currentTimeMillis();
            //check if we are stil save. i.e still in the buffer
            if ((endTime - startTime) >= Phase3GUI.existence_time) {
                // System.err.println("I am outta here");
                break;
            }
                //increment the row position in file

            Row row = Phase3GUI.rowIterator.next();
                //get tuple conresponding to row position in file
            //Row row = sheet1.getRow(Phase3GUI.row_position_in_file);
            Phase3GUI.row_position_in_file++;
            if (row != null) {
                   // System.out.println("I am here");
                //Iterator<Cell> cellIterator = row.cellIterator();

                for (temp_j = 0; temp_j < 12; temp_j++) {

// Store Values into the row buffer
                    // if (j<= 5)
//                    endTime = System.currentTimeMillis();
//                    //Even while reading columns to know if we have exceeded the existence time of the current buffer
//                    if (endTime - startTime >= Phase3GUI.existence_time) {
//                        break;
//                    }
                    switch (temp_j) {
                        case 10:
                            //arrival time, the time the record comes in
                            rowBuffer[temp_j] = String.valueOf(System.currentTimeMillis());
                            break;
                        case 9:
                            //entry time; the ms it takes the record to come in 
                            rowBuffer[temp_j] = String.valueOf(endTime - startTime);
                            break;
                        case 11:
                            //expected waiting time; time its takes before anomyzation begins
                            rowBuffer[temp_j] = String.valueOf(Phase3GUI.existence_time - Long.parseLong(rowBuffer[9]));
                            break;
                        default:
                            switch (row.getCell(temp_j).getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    rowBuffer[temp_j] = Double.toString(row.getCell(temp_j).getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    rowBuffer[temp_j] = row.getCell(temp_j).getStringCellValue();
                                    break;

                            }

                    }
                }

                try {
                    //inserting into the buffer
                    statement.addBatch("INSERT INTO Crime "
                            + "VALUES('"
                            + rowBuffer[ID_NUMBER] + "','"
                            + rowBuffer[FIRST_NAME] + "','"
                            + rowBuffer[LAST_NAME] + "','"
                            + rowBuffer[STUDENT_NUMBER] + "','"
                            + rowBuffer[CELL_NUMBER] + "','"
                            + rowBuffer[HOME_NUMBER] + "','"
                            + rowBuffer[RESIDENCE] + "','"
                            + rowBuffer[CRIME_TYPE] + "','"
                            + rowBuffer[PLACE_OF_OCCURENCE] + "','"
                            + rowBuffer[ENTRY_TIME] + "','"
                            + rowBuffer[ARRIVAL_TIME] + "','"
                            + rowBuffer[EXPECTED_WAITING_TIME] + "' )");

                    Random rand = new Random();
                    //this should be based on the no of rows in excel file

                    int rowsAvailable = Phase3GUI.NUMBER_OF_TUPLES;
                    //System.out.println("The Tuples is :"+rowsAvailable);
                    int n;
                    if (rowsAvailable >= 1000 && rowsAvailable < 5000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_1000);
                        n = rand.nextInt(SLEEP_TIME_1000) + 1;
                    } else if (rowsAvailable >= 5000 && rowsAvailable < 10000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_5000);
                        n = rand.nextInt(SLEEP_TIME_5000) + 1;
                    } else if (rowsAvailable >= 10000 && rowsAvailable < 25000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_10000);
                        n = rand.nextInt(SLEEP_TIME_10000) + 1;
                    } else if (rowsAvailable >= 25000 && rowsAvailable < 50000) {
                        // System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_25000);
                        n = rand.nextInt(SLEEP_TIME_25000) + 1;
                    } else if (rowsAvailable >= 50000 && rowsAvailable < 100000) {
                        // System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_50000);
                        n = rand.nextInt(SLEEP_TIME_50000) + 1;
                    } else if (rowsAvailable >= 100000 && rowsAvailable < 250000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_100000);
                        n = rand.nextInt(SLEEP_TIME_100000) + 1;
                    } else if (rowsAvailable >= 250000 && rowsAvailable < 500000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_250000);
                        n = rand.nextInt(SLEEP_TIME_250000) + 1;
                    } else if (rowsAvailable >= 500000 && rowsAvailable < 750000) {
                        ///System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_500000);
                        n = rand.nextInt(SLEEP_TIME_500000) + 1;
                    } else if (rowsAvailable >= 750000 && rowsAvailable < 1000000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_750000);
                        n = rand.nextInt(SLEEP_TIME_750000) + 1;
                    } else if (rowsAvailable >= 1000000) {
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_1000000);
                        n = rand.nextInt(SLEEP_TIME_1000000) + 1;
                    } else {
                        //file less than 1000 tuples which is the minimum
                        //System.out.println("File has "+ rowsAvailable + " rows. Using "+ SLEEP_TIME_1000);
                        n = rand.nextInt(800) + 1;
                    }

                    //generate a random number between 1 and the sleep time based on the number of records
                        //sleep for at least sleep time ms after a record fetch. To simulate delay of incoming data in real life scenario
//                    for (int i = 1; i <= n; i++) {
//                        //check to know if existence time has elaspsed
//                        if ((System.currentTimeMillis() - startTime) >= Phase3GUI.existence_time) {
//                            break;
//                        }
//                        
//                    }
                    Thread.sleep(n);
                    statement.executeBatch();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    
    
}

                      //THERE ARE TWELVE 12 COLUMNS
//                    while (cellIterator.hasNext()) {
//                        endTime = System.currentTimeMillis();
//                        //Even while reading columns to know if we have exceeded the existence time of the current buffer
//                        if (endTime - startTime >= Phase3GUI.existence_time) {
//                            break;
//                        }
//
//                        Cell cell = cellIterator.next();
//                        
//                        temp_j = cell.getColumnIndex();
//                        //Check the cell type and format accordingly
//                        switch (temp_j) {
//                            case 10:
//                                rowBuffer[temp_j] = String.valueOf(System.currentTimeMillis());
//
//                                break;
//                            case 9:
//                                rowBuffer[temp_j] = String.valueOf(endTime - startTime);
//                                break;
//                            case 11:
//                                //expected waiting time; time its takes before anomyzation begins
//                                rowBuffer[temp_j] = String.valueOf(Phase3GUI.existence_time - Long.parseLong(rowBuffer[9]));
//                                break;
//                            default:
//                                //rowBuffer[temp_j] = sheet.getCell(temp_j, temp_i).getContents();
//                                rowBuffer[temp_j] = cell.getStringCellValue();
//                                break;
//
//                        }
//                    }
// Double[] rowBuffers = new Double[3];
//        File inputExcel = new File(filename);
//        Workbook workbook;
//
//        try {
//            startTime = System.currentTimeMillis();
//            workbook = Workbook.getWorkbook(inputExcel);
//
//            // Assuming that all the data is contained is the first sheet
//            Sheet sheet = workbook.getSheet(0);
//            //int TotalData = sheet.getRows();
//
//            // Loop through all the contents reading them into a string buffer
//            // We skip the first row because that should contain the headings
//            //this is just to taking track of the record we have read
//            if (Phase3GUI.row_position_in_file == 1) {
//                System.out.println("The life time of buffer for round " + Phase3GUI.row_position_in_file + " is set to 5000ms ");
//            }
//
//            // as far as the phase we are in is less than the sheet rows
//            for (temp_i = Phase3GUI.row_position_in_file; temp_i < sheet.getRows(); temp_i++) {
//                endTime = System.currentTimeMillis();
//                //if we have exceeded the existence time of the current buffer
//                if ((endTime - startTime) >= Phase3GUI.existence_time) {
//                    break;
//                }
//                for (temp_j = 0; temp_j < sheet.getColumns(); temp_j++) {
//                    // Store Values into the row buffer
//                    // if (j<= 5)
//                    endTime = System.currentTimeMillis();
//                    //Even while reading columns to know if we have exceeded the existence time of the current buffer
//                    if (endTime - startTime >= Phase3GUI.existence_time) {
//                        break;
//                    }
//
//                    switch (temp_j) {
//                        case 10:
//                            //arrival time, the time the record comes in
//                            rowBuffer[temp_j] = String.valueOf(System.currentTimeMillis());
//                            break;
//                        case 9:
//                            //entry time; the ms it takes the record to come in 
//                            rowBuffer[temp_j] = String.valueOf(endTime - startTime);
//                            break;
//                        case 11:
//                            //expected waiting time; time its takes before anomyzation begins
//                            rowBuffer[temp_j] = String.valueOf(Phase3GUI.existence_time - Long.parseLong(rowBuffer[9]));
//                            break;
//                        default:
//                            rowBuffer[temp_j] = sheet.getCell(temp_j, temp_i).getContents();
//                            break;
//                    }
//                }
//
//                //inserting into the buffer
//                statement.addBatch("INSERT INTO Crime "
//                        + "VALUES('"
//                        + rowBuffer[ID_NUMBER] + "','"
//                        + rowBuffer[FIRST_NAME] + "','"
//                        + rowBuffer[LAST_NAME] + "','"
//                        + rowBuffer[STUDENT_NUMBER] + "','"
//                        + rowBuffer[CELL_NUMBER] + "','"
//                        + rowBuffer[HOME_NUMBER] + "','"
//                        + rowBuffer[RESIDENCE] + "','"
//                        + rowBuffer[CRIME_TYPE] + "','"
//                        + rowBuffer[PLACE_OF_OCCURENCE] + "','"
//                        + rowBuffer[ENTRY_TIME] + "','"
//                        + rowBuffer[ARRIVAL_TIME] + "','"
//                        + rowBuffer[EXPECTED_WAITING_TIME] + "' )");
//
//                Random rand = new Random();
//                //this should be based on the no of rows in excel file
//
//                int rowsAvailable = sheet.getRows();
//                int n;
//                if (rowsAvailable >= 1000 && rowsAvailable < 5000) {
//                    //System.out.println("File has "+ rowsAvailable + " rows. Using "+ );
//                    n = rand.nextInt(SLEEP_TIME_1000) + 1;
//                } else if (rowsAvailable >= 5000 && rowsAvailable < 10000) {
//                    n = rand.nextInt(SLEEP_TIME_5000) + 1;
//                } else if (rowsAvailable >= 10000 && rowsAvailable < 25000) {
//                    n = rand.nextInt(SLEEP_TIME_10000) + 1;
//                } else if (rowsAvailable >= 25000 && rowsAvailable < 50000) {
//                    n = rand.nextInt(SLEEP_TIME_25000) + 1;
//                } else if (rowsAvailable >= 50000 && rowsAvailable < 100000) {
//                    n = rand.nextInt(SLEEP_TIME_50000) + 1;
//                } else if (rowsAvailable >= 100000 && rowsAvailable < 250000) {
//                    n = rand.nextInt(SLEEP_TIME_100000) + 1;
//                } else if (rowsAvailable >= 250000 && rowsAvailable < 500000) {
//                    n = rand.nextInt(SLEEP_TIME_250000) + 1;
//                } else if (rowsAvailable >= 500000 && rowsAvailable < 750000) {
//                    n = rand.nextInt(SLEEP_TIME_500000) + 1;
//                } else if (rowsAvailable >= 750000 && rowsAvailable < 1000000) {
//                    n = rand.nextInt(SLEEP_TIME_750000) + 1;
//                } else if (rowsAvailable >= 1000000) {
//                    n = rand.nextInt(SLEEP_TIME_1000000) + 1;
//                } else {
//                    //file less than 1000 tuples which is the minimum
//                    n = rand.nextInt(800) + 1;
//                }
//
//                //generate a random number between 1 and the sleep time based on the number of records
//                try {
//                    //sleep for at least sleep time ms after a record fetch. To simulate delay of incoming data in real life scenario
//
//                    for (int i = 1; i <= n; i++) {
//                        //check to know if existence time has elaspsed
//                        if ((System.currentTimeMillis() - startTime) >= Phase3GUI.existence_time) {
//                            break;
//                        }
//                        Thread.sleep(1);
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            statement.executeBatch();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IndexOutOfBoundsException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
