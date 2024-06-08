package com.sbi_user;

import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	PreparedStatement pstmt;
	Connection con ;
	ResultSet rs;
    public ChangePassword() {
        do_Connection();
       
    }
	public void do_Connection() {
		try {
			con = DB_Connection.getConnection();
		}catch(Exception exception)
		{
		System.out.println(exception);
		}
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
	String guser  = request.getParameter("username");
	String gpass = request.getParameter("password");
	String ngpass = request.getParameter("ngpass");
	
	response.setContentType("text/html");
	
		String sql ="SELECT name FROM employee WHERE password =?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, gpass.trim());
		rs = pstmt.executeQuery();
		int i = 0;
		if(rs.next())
		{
			++i;
		}if(i>0) 
		{
			String duser = rs.getString(1);
		
		 if(guser.toLowerCase().equals(duser.toLowerCase()))
		 {
			 sql ="UPDATE employee SET password =?,Date=? WHERE name=?";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, ngpass.trim());
			 pstmt.setString(3, guser.trim());
			 Date d = new Date();
			 SimpleDateFormat sf = new SimpleDateFormat("dd-MMMM-yyyy");
			 String date = sf.format(d);
			 System.out.println(date);
			 pstmt.setString(2, date);
			 pstmt.executeUpdate();
			 response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 2px;\r\n"
						+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "    margin-top: 20%;\r\n"
						+ "    margin-left: 88%;\r\n"
						+ "    color:green;\"><h1>Succesfuly updated</h1> Name ="+guser+"<br> new pass : "+ngpass+"</dialog>");
			 RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
				rd.include(request, response);
	}else 
	{
		
		response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 20px;\r\n"
				+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
				+ "    margin-top: 20%;\r\n"
				+ "    margin-left: 88%;\r\n"
				+ "    color:red;\">User Name Incorrect</dialog>");
		RequestDispatcher rd = request.getRequestDispatcher("/ChangePassword.html");
		rd.include(request, response);
		}
	}
		else {
		RequestDispatcher rd = request.getRequestDispatcher("/ChangePassword.html");
		rd.include(request, response);
		response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 20px;\r\n"
				+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
				+ "    margin-top: 15%;\r\n"
				+ "    margin-left: 88%;\r\n"
				+ "    color:red;\">Old Password not Correct</dialog>");
	}
	}
	catch(Exception error) 
	{
		response.getWriter().println(error);
	    }
	}
}
