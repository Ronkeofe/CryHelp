import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The purpose of this Class is to set up the databases along with its
 * corresponding tables.
 * @author Jason Tennant<br>Jose Trigueros
 *
 */
public class DatabaseCreator
{
    // Global Variables
    private static String password = null;
    private static Connection connection = null;
    private static Statement statement = null;
    private static Statement stmt1;
    private final static String USER = "root";
    private final static String MYSQL_URL = "jdbc:mysql://localhost/mysql";//if db called mysql does not exist, we run into problem
    private final static int CREATE_DB  = 0,
                             USE        = 1,
                             DROP_TABLE = 2,
                             CREATE_TB  = 3;
    private final static String[] COMMAND_LIST = {
                                                   "CREATE DATABASE IF NOT EXISTS ",
                                                   "USE ",
                                                   "DROP TABLE IF EXISTS ",
                                                   "CREATE TABLE "
                                                 };
    
    /**
    , * Initialize all the connections and statements needed to start
     * creating the databases.
     */
    private static void initializeSettings()
    {
        try
        {
            // Initialize all the settings needed to start creating databases
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(MYSQL_URL, USER, password);
            statement = connection.createStatement();
            
        } catch (InstantiationException e)
           
        {
           System.out.println(e.getMessage());
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
             System.out.println(e.getMessage());
        }
    }
    
    /**
     * Creates a database with the name of the argument.
     * @param database
     */
    private static void createDatabase(String database)
    {
        try
        {
            statement.executeUpdate(COMMAND_LIST[CREATE_DB] + database);  //creating the database if it does not exist
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
    
    /**
     * Closes all open connections and statements.
     */
    private static void closeAll()
    {
        try
        {
            statement.close();
            connection.close();
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Specific to Phase 3, this sets up the Database and Tables required
     * for Phase 3.
     * @param password
     */
    public static void createPhase3Database(String password)
    {
        // Local Vars
        String database = "Phase3"; 
        String table = "Crime";
        String table2 = "EquivalenceClassTable";
        
        // Initialize Settings
        DatabaseCreator.password = password;
        initializeSettings();
        
        // Create Database
        createDatabase(database);
        
        try
        {
            // Commands for creating custom table, adding to a batch
            statement.addBatch(COMMAND_LIST[USE]+database);
            statement.addBatch(COMMAND_LIST[DROP_TABLE] 
                               + "`" + database + "`.`" + table + "`");
              statement.addBatch(COMMAND_LIST[DROP_TABLE] 
                               + "`" + database + "`.`" + table2 + "`");
            statement.addBatch(COMMAND_LIST[CREATE_TB] 
                               + "`" + database + "`.`" + table + "`" +
                               "(" + 
                               "`ID_NUMBER` varchar(13) NOT NULL," +
                               "`FIRST_NAME` varchar(50)," +
                               "`LAST_NAME` varchar(50)," +
                               "`STUDENT_NUMBER` varchar(60) NOT NULL," +
                               "`CELL_NUMBER` int  NOT NULL DEFAULT 0," +
                               "`HOME_NUMBER` int  NOT NULL DEFAULT 0," +
                               "`RESIDENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`CRIME_TYPE` varchar(60) NOT NULL DEFAULT 0," +
                               "`PLACE_OF_OCCURENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`EntryTime` Double  NOT NULL DEFAULT 0," + 
                               "`ArrivalTime` Double NOT NULL DEFAULT 0," + 
                               "`ExpectedWaitingTime` Double NOT NULL DEFAULT 0" + 
                               ") ENGINE = MyISAM;");
             statement.addBatch(COMMAND_LIST[CREATE_TB] 
                               + "`" + database + "`.`" + table2 + "`" +
                               "(" + 
                               
                               "`RESIDENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`CRIME_TYPE` varchar(60) NOT NULL DEFAULT 0," +
                               "`ANONYMIZED_RESIDENCE` varchar(60) NOT NULL DEFAULT 0" + ") ENGINE = MyISAM;");
            
            // Executing Batch of Statements
            statement.executeBatch();
            //System.out.println("WE ARE HERE");
            //createTableAnonymization();
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        finally
        {
            // Close up all the connections
            closeAll();
        }
    }
    
        public static void createEquivalenceClassTable()
        //public static void createEquivalenceClassTable(String password)
    {
        // Local Vars
        String database = "Phase3"; 
        String table = "EquivalenceClassTable";
        
        // Initialize Settings
       /* DatabaseCreator.password = password;
        initializeSettings();*/
        
        // Create Database
        //createDatabase(database);
        
        
        
        try
        {
            // Commands for creating custom table, adding to a batch
           // statement.addBatch(COMMAND_LIST[USE]+database);
          /*  statement.addBatch(COMMAND_LIST[DROP_TABLE] 
                               + "`" + database + "`.`" + table + "`");*/
            statement.addBatch(COMMAND_LIST[CREATE_TB] 
                               + "`" + database + "`.`" + table + "`" +
                               "(" + 
                               
                               "`RESIDENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`CRIME_TYPE` varchar(60) NOT NULL DEFAULT 0," +
                               "`ANONYMIZED_RESIDENCE` varchar(60) NOT NULL DEFAULT 0," + ") ENGINE = MyISAM;");
            
            // Executing Batch of Statements
            System.out.println("Table EquivalenceClass created " );
            statement.executeBatch();
            
            //System.out.println("WE ARE HERE");
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        finally
        {
            // Close up all the connections
            closeAll();
        }
    }
        
   
  /*  public void createAnonymizedTable() throws SQLException {
    String createString =
        "create table " + dbName +
        ".SUPPLIERS " +
        "(SUP_ID integer NOT NULL, " +
        "SUP_NAME varchar(40) NOT NULL, " +
        "STREET varchar(40) NOT NULL, " +
        "CITY varchar(20) NOT NULL, " +
        "STATE char(2) NOT NULL, " +
        "ZIP char(5), " +
        "PRIMARY KEY (SUP_ID))";

    Statement stmt = null;
    try {
        stmt = con.createStatement();
        stmt.executeUpdate(createString);
    } catch (SQLException e) {
        JDBCTutorialUtilities.printSQLException(e);
    } finally {
        if (stmt != null) { stmt.close(); }
    }
}*/
        
        
    public static void createTableAnonymization() {
    DBManager create_table = new DBManager();
    String CreateTableSql = "CREATE TABLE EquivalenceClassTable (" 
            + "RESIDENCE varchar(60) NOT NULL DEFAULT 0,"  
            + "CRIME_TYPE VARCHAR(60)," 
            + "ANONYMIZED_RESIDENCE VARCHAR(60))" ; 
    try{
     statement.executeUpdate(CreateTableSql);
    
    }
    catch (SQLException e)
        {
            e.printStackTrace();
        }
    
    // stmt1.execUpdate(CreateTableSql);
     //create_table.runQuery(CreateTableSql );
    
           // + "agentCount INT(64), "
           // + "PRIMARY KEY(idNo))";  
    
}
    
       
/* public static void createKAnonTable(String password)
    {
        // Local Vars
        String database = "Phase3"; 
        String table = "Crime";
        
        // Initialize Settings
        DatabaseCreator.password = password;
        initializeSettings();
        
        // Create Database
        createDatabase(database);
        
        try
        {
            // Commands for creating custom table, adding to a batch
            statement.addBatch(COMMAND_LIST[USE]+database);
            statement.addBatch(COMMAND_LIST[DROP_TABLE] 
                               + "`" + database + "`.`" + table + "`");
            statement.addBatch(COMMAND_LIST[CREATE_TB] 
                               + "`" + database + "`.`" + table + "`" +
                               "(" + 
                               "`ID_NUMBER` varchar(13) NOT NULL," +
                               "`FIRST_NAME` varchar(50)," +
                               "`LAST_NAME` varchar(50)," +
                               "`STUDENT_NUMBER` varchar(60) NOT NULL," +
                               "`CELL_NUMBER` int  NOT NULL DEFAULT 0," +
                               "`HOME_NUMBER` int  NOT NULL DEFAULT 0," +
                               "`RESIDENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`CRIME_TYPE` varchar(60) NOT NULL DEFAULT 0," +
                               "`PLACE_OF_OCCURENCE` varchar(60) NOT NULL DEFAULT 0," +
                               "`EntryTime` Double  NOT NULL DEFAULT 0," + 
                               "`ArrivalTime` Double NOT NULL DEFAULT 0," + 
                               "`ExpectedWaitingTime` Double NOT NULL DEFAULT 0" + 
                               ") ENGINE = MyISAM;");
            
            // Executing Batch of Statements
            statement.executeBatch();
            //System.out.println("WE ARE HERE");
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        finally
        {
            // Close up all the connections
            closeAll();
        }
    }*/
    
}