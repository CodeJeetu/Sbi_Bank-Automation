package com.sbi_user;

import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Date;
import java.text.*;
/**
 * Servlet implementation class Debit
 */
public class Debit extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
    
    public Debit() {
        super();
        try {
        	
        	con = DB_Connection.getConnection();
        	
        }catch(Exception e)
        {
        	System.out.println(e);
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String acc = request.getParameter("acc");
		String name = request.getParameter("name");
		String ammount = request.getParameter("amount");
		
		try {
			String sql = "SELECT name , balance FROM sbi_account_dtl WHERE accountno =?"; 	//searching   and Account no
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, acc);
			
			rs = pstmt.executeQuery();
			int i =0;
			if(rs.next())
			{
				++i;
			}
			if(i>0)
			{
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				
				if(name.toLowerCase().equals(rs.getString(1).toLowerCase()))
				{
					int gammount = Integer.valueOf(ammount);
					int dammount = Integer.valueOf(rs.getString(2));
					if(gammount <= dammount)
					{
					int debit = dammount - gammount;
					sql = "UPDATE sbi_account_dtl SET balance =? , lastupdate =? WHERE accountno =? "; // Updating bacha huaa balance 
				   pstmt = con.prepareStatement(sql);
				   pstmt.setString(1, String.valueOf(debit));
				   pstmt.setString(2, "Debited");
				   pstmt.setString(3,acc);
				   pstmt.executeUpdate();
				   System.out.println(debit);
					
					Date d = new Date();			// inserting debited record into debit table
					SimpleDateFormat sf = new SimpleDateFormat("dd-MMMM-yyyy");
			        String date = sf.format(d);
			        sql ="INSERT INTO ac_debit_dtl (NAME,cr_amount,Date,Accout_No) VALUES(?,?,?,?)";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, name.trim());
			        pstmt.setString(2, ammount.trim());
			        pstmt.setString(3, date.trim());
			        pstmt.setString(4, acc.trim());
			        pstmt.executeUpdate();
			        
			        
			    	response.getWriter().println("<dialog open class=\"div1\" style=\"border-radius: 20px; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
							+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
							+ "    margin-top: 10%;\r\n"
							+ "    margin-left: 600px;\r\n"
							+ "    color:green;\"><h3>Succes Fully Debited</h3> A/c = "+acc+"<br>"
							+ "Name = "+name+"<br>Curr. Amount ="+debit+"<br><br>Thankyou for Using"
									+ "<div style =\\\"margin-left :200px; margin-top :10%;\\\"></div></dialog>");
					
			    	RequestDispatcher rd = request.getRequestDispatcher("/home.html");
					rd.include(request, response);
			        
			        
					}else {
						response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
								+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
								+ "    margin-top: 32%;\r\n"
								+ "    margin-left: 1250px;\r\n"
								+ "    color:red;\"> You Have not sufficiant balance ..!</dialog>");
						RequestDispatcher rd = request.getRequestDispatcher("/Debit.html");
						rd.include(request, response);
					}
				}else {
//			    response.getWriter().println(" ");
			    response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
						+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "    margin-top: 32%;\r\n"
						+ "    margin-left: 1250px;\r\n"
						+ "    color:red;\"> Name doesn't matching with AccountNo.. !</dialog>");
				RequestDispatcher rd = request.getRequestDispatcher("/Debit.html");
				rd.include(request, response);
				}
			}else {
				response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
						+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "    margin-top: 32%;\r\n"
						+ "    margin-left: 1200px;\r\n"
						+ "    color:red;\"> Account not Found !</dialog>");
				RequestDispatcher rd = request.getRequestDispatcher("/Debit.html");
				rd.include(request, response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
