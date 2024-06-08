package com.sbi_user;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	PreparedStatement pstmt;
	String sql;
	
    public NewAccount() {
        super();
     Do_Connection();
    }

	private void Do_Connection() {
		try {
			con = DB_Connection.getConnection();
			
		}catch(Exception err) 
		{
		System.out.println(err);
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
	String gname = request.getParameter("name");
	String gaadhar = request.getParameter("aadhar");
	String ggender = request.getParameter("gender");
	String gemail = request.getParameter("email");
	String gmobile = request.getParameter("mobile");
	String gfname = request.getParameter("fname");
	String gatype = request.getParameter("atype");
	String gbalance = request.getParameter("balance");
//	response.getWriter().println(gname+"  "+gaadhar+"  "+ggender+"  " +gemail+"  "+gmobile+"  "+gfname+"  "+gatype+"  "+gbalance);
	
	try {
		String sql = "INSERT INTO sbi_account_dtl (name,accountNo, aadhar, gender, email, mobile, fname, atype, balance,DATE, status) "
		           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, gname.trim());
		pstmt.setString(3, gaadhar.trim());
		pstmt.setString(4, ggender.trim());
		pstmt.setString(5, gemail.trim());
		pstmt.setString(6, gmobile.trim());
		pstmt.setString(7, gfname.trim());
		pstmt.setString(8, gatype.trim());
		pstmt.setString(9, gbalance.trim());
		pstmt.setString(11, "New A/c");
		
		long min = 18167900;
		long max = 98980000;
		long acno = (long)(Math.random()*(max-min+1)+min);
		String gacno = "SB0"+String.valueOf(acno);
		
//		response.getWriter().println(gacno);
		pstmt.setString(2, gacno.trim());
		
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String gdate = sf.format(d);
		pstmt.setString(10, gdate.trim());
		int i =pstmt.executeUpdate();
		 
		
		if(i>0)
		{
			
			response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 20px; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
					+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "    margin-top: 20%;\r\n"
					+ "    margin-left: 1220px;\r\n"
					+ "    color:green;\"><h3>Succes Fully Sumbited</h3> A/c = "+gacno+"<br>"
					+ "Name = "+gname+"<br>Email = "+gemail+"<br>Mobile No.= "+gmobile+"<br><br>Thankyou for Using"
							+ "<div style =\\\"margin-left :200px; margin-top :10%;\\\"></div></dialog>");
			RequestDispatcher rd = request.getRequestDispatcher("/home.html");
			rd.include(request, response);
            
			
			
			
			
		
		}	else {
			response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 20px; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
					+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "    margin-top: 20%;\r\n"
					+ "    margin-left: 1220px;\r\n"
					+ "    color:red;\"><h3> Operation Failed</h3><br>Thankyou for Using</dialog>");
			RequestDispatcher rd = request.getRequestDispatcher("/NewAccount.html");
			rd.include(request, response);
			
		}
			
			
//			response.getWriter().println("Succesfully Data Inserted");
	
	}catch(SQLException error)
	{
		System.out.println(error);
		error.printStackTrace();
//		response.getWriter().println(error);
	}
	}

}


