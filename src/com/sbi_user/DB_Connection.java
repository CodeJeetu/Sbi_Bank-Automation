/**
 * 
 */
package com.sbi_user;
import java.sql.*;


/**
 * @author Jitendra
 *
 */
public class DB_Connection {
	static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    static final String Db_Url = "jdbc:mysql://localhost:3307/sbi_bank";
    static final String user = "root";
    static final String pass = "root";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
             Class.forName("com.mysql.cj.jdbc.Driver");
                  Connection con = DriverManager.getConnection(Db_Url, user, pass);
                  System.out.print("Connection Stablisted");
                    return con;
        
    }
  }


