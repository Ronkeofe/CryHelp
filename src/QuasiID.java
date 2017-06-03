// This enum is freaken hardcore!
public enum QuasiID
{
    
    ID_NUMBER (1,"`ID_NUMBER`", 0,"10000000000000000"),
   // PRICE (5, "`price`", 1, 99999), 
    RESIDENCE(9, "`RESIDENCE`", 1, "world"), 
    PLACE_OF_OCCURENCE(7, "`PLACE_OF_OCCURENCE`", 2, "world"),
    CRIME_TYPE(3, "`CRIME_TYPE`", 3, "crime");
   // AGE(12, "`AGE`", 1, 100), 
   // CRIME_TYPE(1, "`CRIME_TYPE`", 2, 3);
    //PRODUCT_YEAR(3, "`productYear`", 4, 2010), 
   // EXPIRE_YEAR(3, "`expireYear`", 5, 2015);
    //DELAY(6, "`Delay`", 5, 2015),
    //ARRIVAL_TIME(7, "`ARRIVAL_TIME`", 5, 2015),
    //COMPLETITION_TIME(8, "`COMPLETITION_TIME`", 5, 2015);
                               
   // DELAY(1,"`delay`", 6, 5000),
    //START_TIME(1,"`start_time`",7,10000);
    /* Note: The way to find out the number of generalizations is the following:
     * Price: ceiling ( ln( maxValue ) / ln( 10 ) )
     * Dept_ID/Weight : ceiling ( ln( maxValue ) / ln ( 2 ) )
     * 
     *    rowBuffer[PRODUCT_ID] + "','" + 
                                   rowBuffer[PRICE] + "','" + 
                                   rowBuffer[DEPT_ID] + "','" + 
                                   rowBuffer[WEIGHT] + "','" + 
                                   rowBuffer[PRODUCT_YEAR] + "','" + 
                                   rowBuffer[EXPIRE_YEAR] + "','" +
                                   rowBuffer[DELAY] + "','" +
                                   rowBuffer[START_TIME] 
     */
    //PRODUCT_ID(4, "`productID`", 0,9999), 
    //PRICE(5, "`price`", 1, 99999), 
    //DEPT_ID(6, "`deptID`", 2, 50), 
    //WEIGHT(4, "`weight`", 3, 9),
    //PRODUCT_YEAR(3, "`productYear`", 4, 2010), 
    //EXPIRE_YEAR(3, "`expireYear`", 5, 2015);

   /* final int maxGeneralization;
    final String dbName;
    final int position;
    final int maxValue;*/
    
    int maxGeneralization;
    String attributeName;
    int position;
    String maxValue;

   private QuasiID(int maxGeneralization, String attributeName, int position, String maxValue)
    {
        this.maxGeneralization = maxGeneralization;
        this.attributeName = attributeName;
        this.position = position;
        this.maxValue = maxValue;
    }

    public int getPosition()
    {
        return position;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public int getMaxGen()
    {
        return maxGeneralization;
    }
}