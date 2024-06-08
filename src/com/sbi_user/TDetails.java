package com.sbi_user;

import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class TDetails
 */
public class TDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con;
	ResultSet rs1;
	ResultSet rs2;
	Statement stmt;
	PreparedStatement pstmt;
    public TDetails() {
        super();
       doConnection();
    }

	private void doConnection() {
		try {
			con = DB_Connection.getConnection();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html");
		try {
			String sql1 = "SELECT Sender_Account , Recev_Account, Transferd_amount, date from Tdetails"; 
			stmt = con.createStatement();
			rs1 = stmt.executeQuery(sql1);
			response.getWriter().print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "  <link rel=\"stylesheet\" href=\"tstyle.css\"></head><body>\r\n"
					
					+ "             <table id=\"mytable\"><h1>Transfer Details</h1>\r\n"
					+ "            <tr>\r\n"
					+ "                <th>  Sender </th>\r\n"
					+ "                <th> Reciever</th>\r\n"
					+ "                <th>  Amount </th>\r\n"
					+ "                <th> Date </th>\r\n"
					+ "            <tr>\r\n<tr>");
			RequestDispatcher rd = request.getRequestDispatcher("/TransferDetails.html");
			rd.include(request, response);
	
			while(rs1.next())
			{
				String sname =rs1.getString("Sender_Account");
				String rname =rs1.getString("Recev_Account");
				String amount = rs1.getString("Transferd_amount");
				String date = rs1.getString("date");
				
				
				response.getWriter().print("<td name =\"sname\">"+sname+"</td>\r\n"
						+ "                <td id =\"fbname\"name=\"rname\">"+rname+"</td>\r\n"
						+ "                <td id =\"fmark\"name=\"amount\">"+amount+"</td>\r\n"
						+ "                <td id =\"fmark\"name=\"date\">"+date+"</td>\r\n"
						+ "                \r\n</tr>");
			}
			response.getWriter().print("</table></body></html>");
			System.out.println("Ho gya ab");
			 
			
			
		}catch(Exception err)
		{
			err.printStackTrace();
		}
	}

}
