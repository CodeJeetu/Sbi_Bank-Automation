
package com.sbi_user;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement pstmt;
    Statement stmt;
    Connection con= null;
    ResultSet rs = null;
    String sql;
    public Signup()
    {
    	do_connection();
    }
    
	public void do_connection(){
	     try{
	       con =  DB_Connection.getConnection();
	           }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	        catch(Exception se)
	        {
	            System.out.println(se);
	        }
	}
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String guser = request.getParameter("username");
		String gemail = request.getParameter("email");
		String gmobile = request.getParameter("mobile");
		String gpass = request.getParameter("p	assword");
	
		
		try {
		sql = "SELECT password FROM user WHERE username=?";
		pstmt= con.prepareStatement(sql);
		pstmt.setString(1, guser.trim());
	
        rs =  pstmt.executeQuery();
        int i =0;
        if(rs.next())
        {
            ++i;
        }if(i>0)
        {
//            out.println("User Already in database");
            
        	RequestDispatcher rd = request.getRequestDispatcher("/Signup.html");
			rd.include(request, response);
			out.print("<dialog open class=\"div1\" style=\"border-radius: 2px;\r\n"
					+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "    margin-top: 0%;\r\n"
					+ "    margin-left: 80%;\r\n"
					+ "    color:red;\">Sorry "+guser+" \n\n You are already registerd</dialog>");
            
//                    JOptionPane.showInputDialog(this , guser+"\nUserName Already in System\n-----------"
//                    + "-----------------"
//                    + "-------\nPlease "
//                    + "provide a Different UserName","UserName Already avilable",0);
        }
      else
//        {
    	  
    	sql ="INSERT INTO employee VALUES(?,?,?,?,?)";
   		pstmt = con.prepareStatement(sql);
   		pstmt.setString(1, guser.trim());
   		pstmt.setString(2, gpass.trim());
   		pstmt.setString(3, gemail.trim());
   		pstmt.setString(4, gmobile.trim());
   		
   		Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = sf.format(d);
        System.out.println(date); 
        pstmt.setString(5, date);
        pstmt.executeUpdate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.html");
        requestDispatcher.forward(request, response);
       
//        }	
     
	} catch(Exception error) {
				
			}

	}
	
}
