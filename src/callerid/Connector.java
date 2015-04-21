/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callerid;

import java.sql.*;
import javax.swing.*;

        
/**
 *
 * @author Nikhil
 */
public class Connector 
{
    Connection conn = null;
    
    
    public static Connection connectDB()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/data","root","root");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
                    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
            
        }
    }
}
