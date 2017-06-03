/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 */


/*public class AfterRound1 extends JFrame implements ActionListener {
    
   // Phase3GUI Ins = new Phase3GUI();
    //Phase3GUI Ins;
    //Ins.newScenarioPrompt();
    public void actionPerformed(ActionEvent e) {System.exit(0);}
		//if (e.getSource() == NextRoundButton) {
    public void newScenarioPrompt() {	
                //dispose();
                setup();
		JLabel idLabel = new JLabel("Select Quasi-Identifiers:");
		JCheckBox[] idCheckBoxes = new JCheckBox[QuasiID.values().length];
		for (QuasiID id : QuasiID.values()) {
			idCheckBoxes[id.getPosition()] = new JCheckBox(id.toString());
		}
		// need k anon input
		JLabel setKLabel = new JLabel("Set K Anonymity:");
		JTextField kInputField = new JTextField();
		// need suppression input
		JLabel setMaxSupLabel = new JLabel("Set Maximum Suppression:");
		JTextField maxSupInputField = new JTextField();
		
                // set buffer size
		//JLabel setBufferLabel = new JLabel("Set Buffer Size:");
		//JTextField BufferInputField = new JTextField();
                
		Object[] inputFields = {idLabel, idCheckBoxes,
								 setKLabel, kInputField,
								 setMaxSupLabel, maxSupInputField};
                                                                // setBufferLabel, BufferInputField};
		
		int choice = JOptionPane.showOptionDialog(this, inputFields, 
												  "Start New Scenario", 
												  JOptionPane.OK_CANCEL_OPTION,
												  JOptionPane.PLAIN_MESSAGE, 
												  null, null, null);
		switch(choice) {
			case JOptionPane.OK_OPTION:
				
				int numIds = 0;
				for (JCheckBox box : idCheckBoxes) {
					if (box.isSelected()) {
						NumberOfColumns  = numIds++;
                                               // NumberOfColumns  = i++;
					}
				}
				
				QuasiID[] enabledIds = new QuasiID[numIds];
				int nextIndex = 0;
				
				for (QuasiID id : QuasiID.values()) {
					if (idCheckBoxes[id.getPosition()].isSelected()) {
						enabledIds[nextIndex] = id;
						nextIndex++;
					}
				}
				
			try {
				int kanon = Integer.parseInt(kInputField.getText());
				int maxSup = Integer.parseInt(maxSupInputField.getText());
                                //int BufSize = Integer.parseInt(BufferInputField.getText());
                              
                                // the input stay(s) in the buffer till it reaches its maximum size
                                // we need a function that will be counting the tuples as they enter the buffer
                                
                                //if (BufSize == 50) {
                                 //for (int i =1; i<=50; i++)
                            //    System.out.println("kanon  " + kanon);
                              //  System.out.println("max" + maxSup);
           // {
				launch(kanon, maxSup,enabledIds);
                              //  Phase3GUI.launch(kanon, maxSup,enabledIds);
                        //}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Number input not valid.");
			}
				break;
			case JOptionPane.CANCEL_OPTION:
				// TODO: do nothing, maybe exit?
				break;
			default:
				break;
		}
                
                //setup();
	};
	
    private void launch(int kanon, int maxSup, QuasiID ... ids) {
		// get start time...
		
                // i need a method here to count the number of tuples in the buffer before
                // anonymization begins
           
          // for (int i =1; i<=50; i++)
          // {             

            try {
			long startTime = System.currentTimeMillis();
                        
                        //for (int i =1; i<=50; i++)
                        
            //{
			data = Samarati.kanon(kanon, maxSup, ids);
                        //infoloss(data);
                        System.out.println("Orginal data length is " + data.length);
                       
                        DBManager dbMngr = new DBManager();
            String[] maxarrivaltime = dbMngr.runQuery( "SELECT " + "max(ArrivalTime)" + " FROM Crime" );
			
			// get end time...
			long endTime = System.currentTimeMillis();
                        //String ArrivalTime = Phase3GUI.MinArrivalTime();
                       // long MaxWaitingTime = endTime - Long.valueOf(ArrivalTime).longValue();
                      //  long MaxWaitingTime = Long.valueOf(maxarrivaltime[0]).longValue() - Long.valueOf(ArrivalTime).longValue();
                       // long MinWaitingTime;
			double totalTime = (endTime-startTime)/1000.0;
                        //String[][] data = Samarati.kanon(kanon, maxSup, ids);
                        
                        //the claculation of info loss can come in here
                        // a loop on the database and string data
			//System.out.println("Number of anonymised rows" + data.length);
                        //System.out.println("Number of anonymised columns" + NumberOfColumns); 
                      //  infoloss(data);
			// get end time...
			//long endTime = System.currentTimeMillis();
			//double totalTime = (endTime-startTime)/1000.0;
                        System.out.println("StartTime  " + startTime);
                        System.out.println("EndTime  " + endTime);
                        System.out.println("TotalTime  " + totalTime);
                     //   Phase3GUI.MinArrivalTime();
                        
                       // System.out.println("Longest waiting time of any record in the buffer is  " + MaxWaitingTime + "ms");
                        //
                      //  int round_i = DBDataParser.temp_i;
                        //int round_j = DBDataParser.temp_j;
                        //DBDataParser.temp_i = round_i;
                        //DBDataParser.temp_j = round_j;
                        //System.out.println(round_i);
                        //System.out.println(round_i + round_j);
                        
                        //try {

            
             // stm = con.createStatement();

            //for (int i=1; i<=587; i++) 
            //{
                //String query = "INSERT INTO Testing(Id) VALUES(" + 2*i + ")";
              //String sql        = "UPDATE student SET firstName = ? "
                 //    + " WHERE studentID = 456987
              //  String query = "UPDATE Student SET CompletitionTime = "+totalTime+")";
             //   stm.executeQuery(query);
           // }

       // } catch (SQLException ex) {
            
       // }
                        
			this.timeLabel.setText("ELAPSED TIME: " + totalTime);
			this.maxSupLabel.setText("Max Suppression: " + maxSup);
			this.kAnonLabel.setText("K: " + kanon);
			
			// output the data to the table
			setTableData(data, ids);
                      //  infoloss (data, ids);
                     // InfoLoss y = new InfoLoss();
                     // y.loss_of_info(data, ids);
		} catch (Exception e) {
			e.printStackTrace();  // TODO: remove this before submitting
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
           // for (int i = 1; i++; i<= data.length)
                           
                       // {System.out.println(data.length);}
           // infoloss();
	}
    

        
        public void setup() {	
                
                
		JPanel buttonPanel = new JPanel(new FlowLayout());
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		JPanel labelPanel = new JPanel(new GridLayout(1,3));
		this.getContentPane().add(labelPanel, BorderLayout.NORTH);
		
		timeLabel = new JLabel("ELAPSED TIME: ");
		timeLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		labelPanel.add(this.timeLabel, BorderLayout.NORTH);
		kAnonLabel = new JLabel("K: ");
		kAnonLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		labelPanel.add(this.kAnonLabel, BorderLayout.NORTH);
		maxSupLabel = new JLabel("Max Suppression: ");
		maxSupLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		labelPanel.add(this.maxSupLabel, BorderLayout.NORTH);
		
		pack();
		
		dbManager = new DBManager();
             //   AfterRound1 window = new AfterRound1();
               // Toolkit theKit = window.getToolkit();
		//Dimension wndSize = theKit.getScreenSize();

		//window.setBounds(wndSize.width/4, wndSize.height/4, 
						 //wndSize.width/2, wndSize.height/2);
		//window.setVisible(true);
	}
    
	private void setTableData(String[][] data, QuasiID ... list) {		
		String quasiIds = "";
        for ( QuasiID id : list )
        {
            //int i =0;
            quasiIds += "," + id.toString();
            System.out.println("Data is setTableData is " + data.length);
           // switch (id){ 
           // case DEPT_ID: 
           // {System.out.println("Dep   ");break;}
           // NumberOfColumns  = i++;
        } 
        
        quasiIds = quasiIds.substring( 1 );
	String[] columnNames = quasiIds.split(",");
        
        
		dataTable = new JTable(data, columnNames);
		dataTable.setAutoCreateRowSorter(true);
		
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn column = null;
		for (int i = 0; i < list.length; i++) {
		    column = dataTable.getColumnModel().getColumn(i);
		    column.setPreferredWidth(200);
                   // System.out.println("this is settable " + column + data[1][1]);
		}

		try {
			this.getContentPane().remove(scrollPane);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		this.getContentPane().validate();
		scrollPane = new JScrollPane(dataTable);
                dataTable.setFillsViewportHeight(true);

		this.add(scrollPane);
               
                setVisible(true);
                setAlwaysOnTop(true);
	}
        
       // public void infoloss(String[][] datas)
        public void infoloss(String[][] datas, QuasiID ... list)
                
        {
          
         
        System.out.println("Number of anonymised rows is  " + datas.length);
        System.out.println("Number of anonymised columns is  " + NumberOfColumns); 
        // int [][] data_info;
       
         // can you call a function to convert this to integer
        
        String[] DB_Result = dbManager.getAllTableData();
         //DB_Result.to
        int DBResultLength = DB_Result.length;
         
        
        DBManager dbMngr = new DBManager();
        String[] CRIME_TYPE = dbMngr.runQuery( "SELECT " + "CRIME_TYPE " + " FROM Crime" );
        String[] AGE = dbMngr.runQuery( "SELECT " + "AGE"
                + " " + " FROM Crime" );
        
        int[] dbCRIME_TYPE = new int [CRIME_TYPE.length];
        int[] dbAGE = new int [AGE.length];
       // database_length = datas.length;
       // anonymised_length = AGE.length;
        
        for (int a=0;a<CRIME_TYPE.length;a++)
        {dbCRIME_TYPE[a] = Integer.parseInt(CRIME_TYPE[a]);}
        for (int b=0;b<CRIME_TYPE.length;b++)
        {dbAGE[b] = Integer.parseInt(AGE[b]);}
        
        
        String anonymised_data[][] = new String [datas.length ][NumberOfColumns + 1];
        double info_loss[][] = new double [datas.length ][NumberOfColumns + 1];
        double row_infoloss [] = new double [datas.length];
        double infoloss_accumulator = 0.0;
        //double infoloss_accumulator[] = new double [datas.length];
        int ad_max[][] = new int [datas.length ][NumberOfColumns + 1];
        int ad_min[][] = new int [datas.length ][NumberOfColumns + 1];
        
          for (int i = 0; i < (datas.length ); i++)
         {
             for (int j = 0; j < (NumberOfColumns + 1); j++)
             {
              // SCMax[i][j] = datas[i][j].slice(1,-1);  
                //anonymised_data[i][j] = datas[i][j].substring(1, datas[i][j].length() - 1);
                //String[] parts = anonymised_data[i][j].split("-");
                anonymised_data[i][j] = datas[i][j].substring(1, datas[i][j].length() - 1);
                if (anonymised_data[i][j].contains("-")) 
                {
                
                String[] parts = anonymised_data[i][j].split("-");
                String part1 = parts[0]; 
                String part2 = parts[1]; 
                System.out.println("part 1 is  " + part1);
                System.out.println("part 2 is  " + part2);
               
              // if (part1.contains("*") | part2.contains("*")) {part1 = "0"; part2 ="0";}
                //if (part1.contains(*) == "*" | part2 == "*") {part1 = "0"; part2 ="0";}
                if (j == 0)
                {
               //  if (part1.contains("*")) {part1 = Integer.toString(mindept_id);}
                 // (part2.contains("*")) {part2 = Integer.toString(maxdept_id);}
                 if (part1.contains("*")) {part1 = Integer.toString(minAGE);}
                 if (part2.contains("*")) {part2 = Integer.toString(maxAGE);}   
                 info_loss[i][j] = (Double.parseDouble(part2) - Double.parseDouble(part1))/((double)(maxAGE)-(double)minAGE);
                 System.out.println(anonymised_data[i][j] + "  ");
                 System.out.println("info loss of [" + i + "," + j + "] is " + info_loss[i][j]);
                
                }
                
                if (j == 1)
                 
                {
                
                 if (part1.contains("*")) {part1 = Integer.toString(minCRIME_TYPE);}
                 if (part2.contains("*")) {part2 = Integer.toString(maxCRIME_TYPE);}  
                 info_loss[i][j] = (Double.parseDouble(part2) - Double.parseDouble(part1))/((double)maxCRIME_TYPE-(double)minCRIME_TYPE);
                 System.out.println(anonymised_data[i][j] + "  ");
                 System.out.println("info loss of [" + i + "," + j + "] is " + info_loss[i][j]);
                
                    
                }
             
               
                
             }
           infoloss_accumulator = infoloss_accumulator + (info_loss[i][j]);     
         }
        
        row_infoloss[i] = infoloss_accumulator/2.0;
        infoloss_accumulator = 0.0;
        System.out.println("information loss of row  " + "  "+ i + "is  " +   "  " + row_infoloss[i]);
       
         
        }
   }
        private JTable dataTable;
	private JScrollPane scrollPane;
	private DBManager dbManager;
	private JLabel timeLabel;
	private JLabel kAnonLabel;
	private JLabel maxSupLabel;
      //  public static int database_length;
      //  public static int anonymised_length;
	//private JButton NextRoundButton;
        public static String data[][];
        public static int NumberOfColumns;
       // public String [][]CMax,CMin;
        //private static Statement stm = null;
        private Connection con = null;

}*/
