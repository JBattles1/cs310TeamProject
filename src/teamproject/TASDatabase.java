package teamproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TASDatabase{

 Connection conn = null;
 PreparedStatement pstSelect = null, pstUpdate = null;
 ResultSet resultset = null;
 ResultSetMetaData metadata = null;

 boolean hasresults;
 int resultCount, columnCount, updateCount = 0;
    

 /* Identify the Server */
String server = ("jdbc:mysql://localhost/TAS_FA18");
String username = "root";
String password = "96b3812W";
   


    

try
{
    /* Load the MySQL JDBC Driver */
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    /* Open Connection */
    conn = DriverManager.getConnection(server, username, password);
}

catch (InstantiationException ex) 
{
    Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
} 
catch (IllegalAccessException ex) 
{
    Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
}
catch (ClassNotFoundException ex) 
{
    Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
}

    
//Punch Method
public Punch getPunch(int a)
{

    if (conn != null) 
    {
        Punch punch = null;
        
        try
        {
            String  query = "SELECT * FROM punch WHERE id =" + a;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

             while (rs.next())
             {
                int id = rs.getInt("id");
                int terminalId = rs.getInt("terminalid");
                String badgeId = rs.getString("badgeid");
                Timestamp timeStamp = rs.getTimestamp("originaltimestamp");
                int punchTypeId = rs.getInt("punchtypeid");
                punch = new Punch(id,terminalId, badgeId,timeStamp, punchTypeId);
             }
             
              st.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    return punch;
}

//Badge Method
public Badge getBadge(int a)
{
    Badge badge = null; 
    
    if (conn != null) 
    {
        try
        {
            String  query = "SELECT * FROM badge WHERE id =" + a;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String badgeId = rs.getString("id");
                String description = rs.getString("description");
                badge = new Badge(badgeId,description);
            }

          st.close();                      
        }
        catch (SQLException ex) 
        {
          Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    return badge;   
}

public Shift getShift(int a)
{
   Shift shift = null;
   
   if (conn != null) 
   {
       try
       {
           String  query = "SELECT * FROM shift WHERE id =" + a;
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery(query);
           
            while (rs.next())
            {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                Time start = rs.getTime("start");
                Time stop = rs.getTime("stop");
                int interval = rs.getInt("interval");
                int gracePeriod = rs.getInt("graceperiod");
                int dock = rs.getInt("dock");
                Time lunchStart = rs.getTime("lunchstart");
                Time lunchStop = rs.getTime("lunchstop");
                int lunchDeduct = rs.getInt("lunchdeduct");
                new Shift(id, description, start, stop, interval, gracePeriod, dock, lunchStart, lunchStop, lunchDeduct);
            }
            
            st.close();
       }
       
       catch (SQLException ex) 
       {
         Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }    
   
   return shift;
}
                 
public Shift getShift(Badge a)
{
   Shift shift = null;
   
   
   if (conn != null) 
   {

       try {
           String  query = ("SELECT * FROM shift WHERE id =" + a.getId());
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery(query);
           
           while (rs.next())
           {
               int id = rs.getInt("id");
               String description = rs.getString("description");
               Time start = rs.getTime("start");
               Time stop = rs.getTime("stop");
               int interval = rs.getInt("interval");
               int gracePeriod = rs.getInt("graceperiod");
               int dock = rs.getInt("dock");
               Time lunchStart = rs.getTime("lunchstart");
               Time lunchStop = rs.getTime("lunchstop");
               int lunchDeduct = rs.getInt("lunchdeduct");
               
               shift = new Shift(id, description, start, stop, interval, gracePeriod, dock, lunchStart, lunchStop, lunchDeduct);
           }
           st.close();
       } 
       
       catch (SQLException ex) 
       {
           Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

  return shift;
}

       
}



             

        
        
        

    
