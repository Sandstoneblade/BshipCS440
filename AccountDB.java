// The database system being used is Apache Derby within the Netbeans IDE

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import javax.swing.JOptionPane;

public class AccountDB
{
    protected Connection connect;       // Connection used to communicate with Database
    protected String connectionString;
    protected Statement stmt;             // Query used to communicate with SQL server
    protected String query;               // String representation of a query
    protected ResultSet results;          // Holds results for any queries issued
    protected String resultString;        // String representation for results

    public AccountDB()
    //Test a connection to the Database.
    {
        
        connect();

        disconnect();
        
    }

    private void connect()
    //Opens a connection to the database
    {
        
        connectionString = "jdbc:sqlserver://cs440.database.windows.net:1433;"
                + "database=Battleship;user=group7@cs440;password=September7u;"
                + "encrypt=true;trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connect = DriverManager.getConnection(connectionString);
        }
        catch(InstantiationException ie)
        {
            JOptionPane.showMessageDialog(null, "Error in creating connection.");
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null, "Error in creating connection.");
        }
        catch(IllegalAccessException iae)
        {
            JOptionPane.showMessageDialog(null, "Error in creating connection.");
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Error in creating connection.");
        }
        
    }

    private void disconnect()
    //Closes the connection
    {
        
        try
        {
            connect.close();
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Error in closing connection.");
        }
        
    }

    private void sendQuery()
    {
        
        // Sending query
        try
        {
            stmt = connect.createStatement();
            stmt.execute(query);
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Query did not execute successfully.");
        }

        // Closing stmt
        try
        {
            stmt.close();
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Error in closing query connection.");
        }
        
    }

    public void initialize ()
    //This function creates a table and inserts data into it.
    //Only needs to be called if the table isn't already created.
    {
        
        query = "CREATE TABLE Accounts(" +
            "username varchar(20), " +
            "password varchar(20))";
        sendQuery();
        System.out.println(1);
        query = "insert into Accounts values('josue', 'root')";
        sendQuery();
        System.out.println(1);
        query = "insert into Accounts values('daniel', 'root')";
        sendQuery();
        System.out.println(1);

        query = "insert into Accounts values('mark', 'root')";
        sendQuery();
        System.out.println(1);
        query = "insert into Accounts values('chris', 'root')";
        sendQuery();
        System.out.println(1);
        
    }

    private void closeResultSet()
    // stmt and results are closed after they are done being used
    {
        
        try
        {
            stmt.close();
            results.close();
        }
        catch (SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Error in closing ResultSet.");
        }
        
    }

    public boolean validateLogin(String user, String pass)
    //Validate the user's credentials
    {
        
        int count = 0;
        connect();

        try {
            query = "select Username,Password from Accounts where Username ='" + user +
                "'and Password='" + pass + "'";

            stmt = connect.createStatement();
            results = stmt.executeQuery(query);

            while(results.next()){
                count += 1;
            }

            if(count == 0){
                closeResultSet();
                disconnect();
                return false;
            }
        }
        catch (Exception ex){

        }

        closeResultSet();
        disconnect();

        return true;
        
    }

    public void create(String user, String pass)
    //Creates a new account
    {
        
        connect();

        query = String.format("insert into Accounts values('%s', '%s')",
            user, pass);
        sendQuery();

        disconnect();

    }
}
