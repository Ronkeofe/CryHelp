
//import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.StreamingReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Phase3GUI extends JFrame {

    static File file;
    public static Iterator<Row> rowIterator;

    //define the set of K values
    static Map<Integer, Integer[]> K_VALUES = new HashMap<>();

    public Phase3GUI() {
        K_VALUES.put(1, new Integer[]{2, 3, 4});
        K_VALUES.put(2, new Integer[]{3, 5, 6});
        K_VALUES.put(3, new Integer[]{6, 8, 10, 12});
        K_VALUES.put(4, new Integer[]{10, 12, 14, 16, 18, 20});
        K_VALUES.put(5, new Integer[]{19, 22, 25, 28, 31, 34, 37});
        K_VALUES.put(6, new Integer[]{46, 51, 56, 61, 66, 71, 76, 81, 86, 91});
        K_VALUES.put(7, new Integer[]{77, 84, 91, 98, 105, 112, 119, 126, 133, 140, 147, 154});
        K_VALUES.put(8, new Integer[]{132, 147, 162, 177, 192, 207, 222, 237, 252, 264});
        K_VALUES.put(9, new Integer[]{247, 270, 293, 316, 339, 362, 385, 408, 431, 454, 477});
        K_VALUES.put(10, new Integer[]{418, 458, 498, 538, 578, 618, 658, 698, 738, 778, 818});
        row_position_in_file = 1;
        round = 0;
        existence_time = 5000;
        temp_total_time = totalTime;

        // System.out.println("ROUND " + round + " ANALYSIS");
        //setupDatabasePrompt();
    }

    public void actionPerformed(){
    
        //if (e.getSource() == NextRoundButton ) 

        round++;
        System.out.println("ROUND " + round + " ANALYSIS");
        //row_position_in_file = DBDataParser.temp_i;
        //round_i = DBDataParser.temp_i+1;
        System.out.println("TEMP I INC IS  " + row_position_in_file + " ANALYSIS");
        //  System.out.println("TEMP I INC IS  " + row_po/home/asakpere/NetBeansProjects/CryHelp_DynamicBufferSize_1/src/Phase3GUI.java:80sition_in_file);
        //DBDataParser.temp_i = row_position_in_file;

        //long start = System.currentTimeMillis();
        if (row_position_in_file < NUMBER_OF_TUPLES - 1) {
            setupDatabasePrompt(file.toString());
            //long end = System.currentTimeMillis();
            try{
            newScenarioPrompt();
            }catch(WriteException e){
                e.printStackTrace();
            
            }
        } else {
            System.err.println("File end reached!");

        }

    }
    
  /*  public void actionPerformed() throws WriteException {
    
        //if (e.getSource() == NextRoundButton ) 

        round++;
        System.out.println("ROUND " + round + " ANALYSIS");
        //row_position_in_file = DBDataParser.temp_i;
        //round_i = DBDataParser.temp_i+1;
        System.out.println("TEMP I INC IS  " + row_position_in_file + " ANALYSIS");
        //  System.out.println("TEMP I INC IS  " + row_position_in_file);
        //DBDataParser.temp_i = row_position_in_file;

        //long start = System.currentTimeMillis();
        if (row_position_in_file < NUMBER_OF_TUPLES - 1) {
            setupDatabasePrompt(file.toString());
            //long end = System.currentTimeMillis();

            newScenarioPrompt();
        } else {
            System.err.println("File end reached!");

        }

    }*/

    public static Iterator<Row> streamExcelFile(String filename) {

        try {

            org.apache.poi.ss.usermodel.Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(new File(filename));

            Sheet sheet = workbook.getSheetAt(0);
            return sheet.iterator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Iterator<Row> initializeRowForFile(String filename) {

        try {

            FileInputStream fileHere = new FileInputStream(new File(filename));

            //Create Workbook instance holding reference to .xlsx file
            long start = System.currentTimeMillis();
            //XSSFReader reader = new XSSFReader(new )

            //XSSFWorkbook workbook1 = new XSSFWorkbook(new File(filename));
            XSSFWorkbook workbook1 = new XSSFWorkbook(fileHere);
            // SXSSFWorkbook workbook = new SXSSFWorkbook(workbook1);
            long end = System.currentTimeMillis();
            System.out.println("The time to load file was " + (end - start) / 1000 + " secs");
            //Get first/desired sheet from the workbook
            XSSFSheet sheet1 = workbook1.getSheetAt(0);

            //Iterate through each rows one by one
            return sheet1.iterator();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newScenarioPrompt() throws WriteException {

        start_time = System.currentTimeMillis();

        //measure to check if no record lives here more than 5000..does not make any sense
        expired_row = dbMngr.runQuery(String.format("SELECT ArrivalTime FROM Crime WHERE (%s) - ArrivalTime  > (%s) ", System.currentTimeMillis(), 5000));

        if (expired_row == null) {
            System.out.println("The number of expired row is " + expired_row);
        }
        if (expired_row != null) {
            System.out.println("The ms expired is " + (System.currentTimeMillis() - Long.parseLong(expired_row[0])));
            System.out.println("The number of expired row is " + expired_row.length);
        }

        int numIds = 1;
        num_of_col = numIds;
        QuasiID[] enabledIds = new QuasiID[numIds];   //its just an array of length one????
        int nextIndex = 0;
        for (QuasiID id : QuasiID.values()) {
            if (id.getPosition() == 1) {//for the residence
                enabledIds[nextIndex] = id;  //its just one actually
                //System.out.println(enabledIds[nextIndex]);
            }

        }

        // kAnon = 2;
        
        int maxSup = 5;
        System.out.println("Big value of k is " + kAnon);
        launch(kAnon, maxSup, enabledIds);

    }

    private void launch(int kanon, int maxSup, QuasiID... ids) throws WriteException {

        long startTime = System.currentTimeMillis();
        //for (int i =1; i<=50; i++)
        //{

        /*Note: the array would better have
         been a one dimensional array as its only have values in the first column
         but samarati still gives us a bullshit of two dim array
         */
        data = Samarati.kanon(kanon, maxSup, ids);  //check later
        //System.out.println(data.toString());

        for (int i = 0; i < data.length; i++) {

            for (int j = 0; j < data[i].length; j++) {

                System.out.println("Data at " + i + ", " + j + " is " + data[i][j]);
            }
        }

        
        anonymised_length = data.length;
        DBManager dbMngr = new DBManager();
        //  String[] maxarrivaltime = dbMngr.runQuery( "SELECT " + "max(ArrivalTime)" + " FROM Student" );
        setTableData(data, ids);
       
        long endTime = System.currentTimeMillis();
      
        //time taken to anonymised and calc info loss for each of the generalised tuple in the buffer
        anon_time = (endTime - start_time);
        
        //whatever this means doesnt make a brain for now
        totalTime = (endTime - startTime);

         LDiversity.AnonymizedInfo();
         LDiversity.populateDatabase_EquivalenceClass();
         TCloseness.setTclosenessEquivalenceClass(); 
        
        //need to undersstand the PoisonModel
        PoisonModel b = new PoisonModel();
        b.delete_unsupp();

        System.out.println("The total number of suppressed records in round  " + Phase3GUI.round + "  IS  " + PoisonModel.suppressed_counter);
        System.out.println("The total number of records that used reuse-cluster in round  " + Phase3GUI.round + "  IS  " + PoisonModel.infoloss_counter);
        System.out.println("The total number of records that poison helped to avoid suppression  " + Phase3GUI.round + "  IS  " + (GeneralizationTable.stringList.size() - PoisonModel.infoloss_counter - PoisonModel.suppressed_counter));

        System.out.println("ReusableCluster.info_loss is  " + ReusableCluster.info_loss);
        System.out.println("Gen Step is " + GenStep);
        /*if (ReusableCluster.info_loss == 0.0){ System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((InfoLoss.roundloss + PoisonModel.suppressed_counter)/(PoisonModel.suppressed_counter+1)));}
         else
         { 
         if (InfoLoss.roundloss == 0.0 || Double.isNaN(InfoLoss.roundloss) ) {System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((ReusableCluster.info_loss/PoisonModel.infoloss_counter) + PoisonModel.suppressed_counter)/(1 + PoisonModel.suppressed_counter));}
         System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  (InfoLoss.roundloss+(ReusableCluster.info_loss/PoisonModel.infoloss_counter) + PoisonModel.suppressed_counter)/(2 + PoisonModel.suppressed_counter));
         }*/

        if (ReusableCluster.info_loss == 0.0) {
            System.out.println("TOTAL INFORMATION LOSS FOR ROUND " + Phase3GUI.round + "  IS  " + ((InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter + InfoLoss.roundloss_accumulator) / (data.length + PoisonModel.infoloss_counter + PoisonModel.suppressed_counter)));
        } else if (Double.isNaN(InfoLoss.roundloss)) //if (InfoLoss.roundloss == 0.0 || Double.isNaN(InfoLoss.roundloss))
        {
            System.out.println("TOTAL INFORMATION LOSS FOR ROUND " + Phase3GUI.round + "  IS  " + ((InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter) / (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter)));
        } // System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((InfoLoss.roundloss_accumulator + ReusableCluster.info_loss + PoisonModel.suppressed_counter)/(PoisonModel.infoloss_counter + PoisonModel.suppressed_counter + data.length)));
        else {
            System.out.println("TOTAL INFORMATION LOSS FOR ROUND " + Phase3GUI.round + "  IS  " + ((InfoLoss.roundloss_accumulator + InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter) / (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter + data.length)));
            System.out.println("ROUND LOSS" + Phase3GUI.round + "  IS  " + ((InfoLoss.roundloss_accumulator)));
            //reuse_roundloss_accumulator;
            System.out.println("REUSE INFORMATION LOSS " + Phase3GUI.round + "  IS  " + ((ReusableCluster.info_loss)));

            System.out.println("SUPPRESSED COUNTER " + Phase3GUI.round + "  IS  " + ((PoisonModel.suppressed_counter)));
            System.out.println("INFORMATION LOSS COUNTER " + Phase3GUI.round + "  IS  " + ((PoisonModel.infoloss_counter)));
            System.out.println("DATA LENGTH " + Phase3GUI.round + "  IS  " + (data.length));
        }

        try {

            //We need to know the excel file to write to
            //and also the sheet to write to 
            // int a =2;
            int j = 0;

//           Workbook workbook = Workbook.getWorkbook(new File("C:\\Development\\JAVA\\CryHelp_DynamicBufferSize\\Poisson.xls"));
//           WritableWorkbook copy = Workbook.createWorkbook(new File("C:\\Development\\JAVA\\CryHelp_DynamicBufferSize\\Poisson.xls"), workbook);
//           WritableSheet sheet = copy.getSheet(0);
            
              //WritableWorkbook wworkbook;
                Workbook workbook = Workbook.getWorkbook(new File("/home/asakpere/Desktop/Poisson_Result3.xls"));
                WritableWorkbook copy = Workbook.createWorkbook(new File("/home/asakpere/Desktop/Poisson_Result3.xls"), workbook); 
                WritableSheet sheet = copy.getSheet(0);
            // WritableSheet sheet = null;
            //how do i get the appropriate sheet to write to
//                for (int i = 0; i < kValues.length; i++) {
//
//                    if (kAnon == kValues[i]) {
//                        sheet = writableWorkbook.getSheet(i);
//                        break;
//                    }
//                }

            Number rround = new Number(j, Phase3GUI.round, Phase3GUI.round);
            sheet.addCell(rround);
            j++;
            Number lamda = new Number(j, Phase3GUI.round, PoisonModel.lamda);
            sheet.addCell(lamda);
            j++;
            Number poison = new Number(j, Phase3GUI.round, PoisonModel.prob);
            sheet.addCell(poison);
            j++;
            if (PoisonModel.number_of_rows == null) {
                Number Total_Rec = new Number(j, Phase3GUI.round, 0);
                sheet.addCell(Total_Rec);
                j++;
            } else {
                Number Total_Rec = new Number(j, Phase3GUI.round, PoisonModel.number_of_rows.length);
                sheet.addCell(Total_Rec);
                j++;
            }
            if (Phase3GUI.expired_row == null) {
                Number Expired_Rec = new Number(j, Phase3GUI.round, 0);
                sheet.addCell(Expired_Rec);
                j++;
            } else {
                Number Expired_Rec = new Number(j, Phase3GUI.round, Phase3GUI.expired_row.length);
                sheet.addCell(Expired_Rec);
                j++;
            }
            Number Supp_Rec = new Number(j, Phase3GUI.round, PoisonModel.suppressed_counter);
            sheet.addCell(Supp_Rec);
            j++;
            Number Reuse_Rec = new Number(j, Phase3GUI.round, PoisonModel.infoloss_counter);
            sheet.addCell(Reuse_Rec);
            j++;
            Number Poison_Rec = new Number(j, Phase3GUI.round, (GeneralizationTable.stringList.size() - PoisonModel.infoloss_counter - PoisonModel.suppressed_counter));
            sheet.addCell(Poison_Rec);
            j++;
                //  if (InfoLoss.reuse_roundloss_accumulator  == 0.0)
            //if (InfoLoss.reuse_roundloss_accumulator  == 0.0)
            //if (ReusableCluster.info_loss reuse_roundloss_accumulator  == 0.0)
                /* { 
             Number Info_Loss = new Number(j, Phase3GUI.round,(InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter)/ (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter + data.length));
             // Number Info_Loss = new Number(j, Phase3GUI.round,(InfoLoss.roundloss_accumulator + PoisonModel.suppressed_counter )/(data.length + PoisonModel.suppressed_counter));
             sheet.addCell(Info_Loss);
             {System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter)/ (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter)));}
                     
             j++;
             } */
            //System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((InfoLoss.roundloss_accumulator + PoisonModel.suppressed_counter )/(data.length + PoisonModel.suppressed_counter)));}
            //   else
            {
                if (Double.isNaN(InfoLoss.roundloss)) //if (InfoLoss.roundloss == 0.0 || Double.isNaN(InfoLoss.roundloss)) 
                {
                    Number Info_Loss = new Number(j, Phase3GUI.round, (InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter) / (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter));
                    sheet.addCell(Info_Loss);
                    j++;
                } else {
                    Number Info_Loss = new Number(j, Phase3GUI.round, (InfoLoss.roundloss_accumulator + InfoLoss.reuse_roundloss_accumulator + PoisonModel.suppressed_counter) / (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter + data.length));
                    sheet.addCell(Info_Loss);
                    j++;
                }
                //System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((ReusableCluster.info_loss + PoisonModel.suppressed_counter)/ (PoisonModel.infoloss_counter + PoisonModel.suppressed_counter)));}
                // System.out.println("TOTAL INFORMATION LOSS FOR ROUND " +  Phase3GUI.round + "  IS  " +  ((InfoLoss.roundloss_accumulator+ReusableCluster.info_loss+ PoisonModel.suppressed_counter)/(PoisonModel.infoloss_counter + PoisonModel.suppressed_counter+data.length)));
            }
            Number Existence_Time = new Number(j, Phase3GUI.round, Phase3GUI.existence_time);
            sheet.addCell(Existence_Time);
            j++;
            Number Anonymization_Time = new Number(j, Phase3GUI.round, Phase3GUI.anon_time);
            sheet.addCell(Anonymization_Time);
            //  j++;
            copy.write();
            copy.close();

        } catch (IndexOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        ReusableCluster.info_loss = 0.0;
        PoisonModel.infoloss_counter = 0;
        PoisonModel.suppressed_counter = 0;
        InfoLoss.roundloss_accumulator = 0.0;
        InfoLoss.reuse_roundloss_accumulator = 0.0;
        System.out.println("Based on Arrival Rate and Poisson Distribution Probability Predictions, the life-time of buffer for the next round (i.e. Round " + (Phase3GUI.round + 1) + ") is set to " + existence_time + " ms");
        System.out.println("                                                                      ");
        GeneralizationTable.stringList.clear();
        GeneralizationTable.stringListCrime.clear();

    }

    private void setTableData(String[][] data, QuasiID... list) {
        String quasiIds = "";
        InfoLoss a = new InfoLoss();
        // PoisonModel b = new PoisonModel();
        System.out.println("Length of setTableData is " + data.length);

        for (QuasiID id : list) {
            quasiIds += "," + id.toString();

        }
        System.out.println("Quasi Id is " + quasiIds);

        
        //the only important thing in this method
        a.loss_of_info(data);
     
  


//  b.delete_unsupp();

        //from here downwards, not doing anything useful
//        quasiIds = quasiIds.substring(1);  //removing the first ,
//        String[] columnNames = quasiIds.split(","); //Just the RESIDENCE alone will be here
//
//        dataTable = new JTable(data, columnNames);
//        dataTable.setAutoCreateRowSorter(true);
//
//        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        TableColumn column = null;
//        for (int i = 0; i < list.length; i++) {
//            column = dataTable.getColumnModel().getColumn(i);
//            column.setPreferredWidth(200);
//            //  System.out.println("this is settable " + column);
//        }
//
//        try {
//            this.getContentPane().remove(scrollPane);
//        } catch (Exception e) {
////			e.printStackTrace();
//        }
//        this.getContentPane().validate();
//        scrollPane = new JScrollPane(dataTable);
//        dataTable.setFillsViewportHeight(true);
//
//        this.add(dataTable);
//        this.add(scrollPane);
    }

    public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());

        } catch (InstantiationException e) {
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e.getMessage());
        }

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        try {

                            createGUI();
                        } catch (WriteException ex) {
                            Logger.getLogger(Phase3GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

    }

    public static void createGUI() throws WriteException {
        Phase3GUI window = new Phase3GUI();
        //window.setVisible(true);
        // where has this function (window.newScenarioPrompt()) gone to o

        //grab the file
        NUMBER_OF_TUPLES = window.rowsAvailableFromUser();
       // NUMBER_OF_TUPLES=100;
        

        System.out.println("The number of tuples is " + NUMBER_OF_TUPLES);
        int sleepTime = 800;

        switch (NUMBER_OF_TUPLES) {
            case 1000:
                sleepTime = 800;
                //fileLoadtime =1;
                break;
            case 5000:
                sleepTime = 400;
                //fileLoadtime =5;
                break;
            case 10000:
                sleepTime = 200;
                //fileLoadtime =10;
                break;
            case 25000:
                sleepTime = 100;
                //fileLoadtime =25;
                break;
            case 50000:
                sleepTime = 50;
                //fileLoadtime =50;
                break;
            case 100000:
                sleepTime = 20;
                //fileLoadtime =100;
                break;
            case 250000:
                sleepTime = 10;
                //fileLoadtime =250;
                break;
            case 500000:
                sleepTime = 5;
                //fileLoadtime =500;
                break;
            case 750000:
                sleepTime = 20;
                // fileLoadtime =750;
                break;
            case 1000000:
                sleepTime = 1;
                //fileLoadtime =1000;
                break;

        }

        System.out.println("The max. sleep time between records in a buffer for " + NUMBER_OF_TUPLES + " is " + sleepTime);
        //dynamically cretae the excel file, the sheet

        //Label label = new Label(j, 0, "Round");
       /* String[] HEADER = {"Round", "Lamda Prediction for next round", "Poisson Prediction for next round", "Number of records", "Number of expired rows",
            "Number of Suppressed Records unrecovered", "recovered by Reuse cluster", "recovered by Poisson", "Information Loss", "Buffer Life/Existence Time"};*/
         String[] HEADER = {"Round", "Lamda Prediction for next round", "Poisson Prediction for next round", "Number of records", "Number of expired rows",
            "Number of Suppressed Records unrecovered", "recovered by Reuse cluster", "recovered by Poisson", "Information Loss", "Buffer Life/Existence Time", "Anonymization Time"};


//        try {
//            switch (NUMBER_OF_TUPLES) {
//
//                //we can create the excel file and corresponding sheets here too
//                case 1000:
//                    kValues = K_VALUES.get(1);
//                    createFile = new File("Created_Poisson_Result_1000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 5000:
//                    kValues = K_VALUES.get(2);
//                    createFile = new File("Created_Poisson_Result_5000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 10000:
//                    kValues = K_VALUES.get(3);
//                    createFile = new File("Created_Poisson_Result_10000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 25000:
//                    kValues = K_VALUES.get(4);
//                    createFile = new File("Created_Poisson_Result_25000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 50000:
//                    kValues = K_VALUES.get(5);
//                    createFile = new File("Created_Poisson_Result_50000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 100000:
//                    kValues = K_VALUES.get(6);
//                    createFile = new File("Created_Poisson_Result_100000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 250000:
//                    kValues = K_VALUES.get(7);
//                    createFile = new File("Created_Poisson_Result_250000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 500000:
//                    kValues = K_VALUES.get(8);
//                    createFile = new File("Created_Poisson_Result_500000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 750000:
//                    kValues = K_VALUES.get(9);
//                    createFile = new File("Created_Poisson_Result_750000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//                case 1000000:
//                    kValues = K_VALUES.get(10);
//                    createFile = new File("Created_Poisson_Result_1000000.xls");
//                    writableWorkbook = Workbook.createWorkbook(createFile);
//                    for (int i = 0; i < kValues.length; i++) {
//                        writableSheet = writableWorkbook.createSheet("K EQUALS " + kValues[i], i);
//                        //write the header
//                        for (int j = 0; j < HEADER.length; j++) {
//                            Label label = new Label(j, 0, HEADER[j]);
//                            writableSheet.addCell(label);
//                        }
//                    }
//                    break;
//            }
//            //append an header to the writable sheet
//
////            writableWorkbook.write();
////            writableWorkbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
        //
        // for (int value : kValues) {
        // System.out.println("Analysis for " + NUMBER_OF_TUPLES + " Tuples using a K value of" + value);
        //set k value here
        //kAnon = value;
        kAnon = 4;
        round = 0;
        rowIterator = streamExcelFile(file.toString());
        rowIterator.next(); //skipping the header
        // what happend to this line of code: window.newScenarioPrompt();
        // i grab
        while (row_position_in_file < NUMBER_OF_TUPLES) {
            Generalizer.infoloss.removeAllElements();
            window.actionPerformed();
        }
        row_position_in_file = 1;
        System.out.println("\n\n\n\n");
        //}
//        try {
//            writableWorkbook.write();
//            writableWorkbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Done reading the Excel file");
    }

    public int rowsAvailableFromUser() {
        JFileChooser fc = new JFileChooser();
        int choice = fc.showOpenDialog(this);
        file = fc.getSelectedFile();
        System.out.println(file.getName());

        // File [] fileM = fc.getSelectedFiles();
        switch (choice) {
            case JFileChooser.APPROVE_OPTION:
                //System.out.println(file.toString());

                //WOUND NOT BE NEEDING THOSE RUBBISH, TO GET THE ROW NUMBER
                String name = file.getName().split("\\.")[0];
                //System.out.println("Name is "+ name);
                String splitNames[] = name.split("_");
                String rowNumber = splitNames[splitNames.length - 1];
                return Integer.parseInt(rowNumber);
            case JFileChooser.CANCEL_OPTION:
                dbManager = new DBManager();
                break;
        }
        return 0;

    }

    private void setupDatabasePrompt(String inputFile) {
        dbManager = new DBManager(inputFile);
    }

    public static int row_position_in_file = 1;
    public static int round;
    public JTable dataTable;
    public JScrollPane scrollPane;
    public static DBManager dbManager;
    DBManager dbMngr = new DBManager();
    public static DBManager1 dbMg;
    public JLabel timeLabel;
    public JLabel kAnonLabel;
    public JLabel maxSupLabel;
    public JButton NextRoundButton;
    public static Statement stm = null;
    public Connection con = null;
    public static int num_of_col;
    public static String[][] data;
    private static String[] expired_row;
    public static long[] arrival_time;
    public static long existence_time;
    long start_time;
    // public static int[] info_loss;
    public static long anon_time;
    //public static int database_length;
    public static int anonymised_length;
    public static double totalTime;
    public static double temp_total_time;
    public static double avg_total_time;
    public static int kAnon;
    public static int GenStep;
    public static int NUMBER_OF_TUPLES;

    // public int NumberOfColumns;
    // public int[][]CMax,CMin;
    //CREATING static variable for file workbook and sheet
    private static File createFile;
    private static WritableWorkbook writableWorkbook;
    private static WritableSheet writableSheet;
    private static Integer[] kValues;
}
