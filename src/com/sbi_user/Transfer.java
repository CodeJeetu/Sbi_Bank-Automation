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
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
    
    public Transfer() {
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
		String acc = request.getParameter("sac");
		String sname = request.getParameter("sname");
		String rname = request.getParameter("rname");
		String rac = request.getParameter("rac");
		String gamount = request.getParameter("amount");
		
//			response.getWriter().println(acc);
		try {
			String sql = "SELECT name , balance FROM sbi_account_dtl WHERE accountno =?"; 	//searching  name and Account no.
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
				System.out.println("\n"+rs.getString(1));
				System.out.println("\n"+rs.getString(2));
				String dsname = rs.getString(1);
				String dsamount = rs.getString(2);
				if(sname.toLowerCase().equals(dsname.toLowerCase()))
				{
					pstmt.setString(1, rac);
//					response.getWriter().println("Succesfully Got It");
					rs = pstmt.executeQuery();
					int j=0;
					if(rs.next())
						{
							++j;
						}if(j>0)
							{
							String drname = rs.getString(1);
							String dramount = rs.getString(2);
							
							 if(rname.toLowerCase().equals(drname.toLowerCase()))
								{
								 if((Integer.valueOf(gamount))>(Integer.valueOf(dsamount))) 
								 {
									 System.out.print("not have balance sfficient");
									 response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
												+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
												+ "    margin-top: 32%;\r\n"
												+ "    margin-left: 600px;\r\n"
												+ "    color:red;\">Sernder  "+dsname+" Does't have sufficient balance !</dialog>");
										RequestDispatcher rd = request.getRequestDispatcher("/Transfer.html");
										rd.include(request, response);
								 }
								 else {
									 
								 
									int debit = Integer.valueOf(dsamount) - Integer.valueOf(gamount);
									int credit = Integer.valueOf(dramount) + Integer.valueOf(gamount);
									
									sql = "UPDATE sbi_account_dtl SET balance =? , lastupdate =? WHERE accountno =? "; // Updating bacha huaa balance 
									   pstmt = con.prepareStatement(sql);
									   // Debite record to sender account
									   pstmt.setString(1, String.valueOf(debit));
									   pstmt.setString(2, "Debited");
									   pstmt.setString(3,acc);
									   pstmt.executeUpdate();
									   
//									   Credit record to Reciever account
									   pstmt.setString(1, String.valueOf(credit));
									   pstmt.setString(2, "Credited");
									   pstmt.setString(3,rac);
									   pstmt.executeUpdate();
									   System.out.println(debit);
										
									   
										Date d = new Date();			// inserting debited record into debit table
										SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
								        String date = sf.format(d);
//								        sql =" INSERT INTO debit VALUES(?,?,?)";  // inserting debited record into debit table
//								        pstmt = con.prepareStatement(sql);
//								        pstmt.setString(1, sname.trim());
//								        pstmt.setString(2, gamount.trim());
//								        pstmt.setString(3, date.trim());
//								        pstmt.executeUpdate();
								        
								        // Putting records into transferDetaiols table
								        sql = "INSERT INTO Tdetails (Sender_Account,Sender_Name,Transferd_amount,Recev_Account,Recev_Name,DATE)"
								        		+ " values(?,?,?,?,?,?) "; 
								        pstmt = con.prepareStatement(sql);
								        pstmt.setString(1, acc.trim());
								        pstmt.setString(2, sname.trim());
								        pstmt.setString(3, gamount.trim());
								        pstmt.setString(4, rac.trim());
								        pstmt.setString(5, rname.trim());
								        pstmt.setString(6, date.trim());
								        pstmt.executeUpdate();
								        
								        
//								        sql =" INSERT INTO credit VALUES(?,?,?)"; // // inserting credited record into Credit table
//								        pstmt = con.prepareStatement(sql);
//								        pstmt.setString(1, rname.trim());
//								        pstmt.setString(2, gamount.trim());
//								        pstmt.setString(3, date.trim());
//								        pstmt.executeUpdate();
								        
								        
								        	
								        
								        
								        
								        response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
												+ " box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
												+ " margin-top: 15%;\r\n"
												+ " margin-left: 1200px;\r\n"
												+ " color:green;\"><h3>Succesfuly Tranfered .</h3>Name = "+dsname+"<br> A/c ="+acc+"<br> C. Amount ="+debit+"<br> Thanks for Using"
														+ "<div style =\\\"margin-left :200px; margin-top :10%;\\\"></div></dialog>");
								    	RequestDispatcher rd = request.getRequestDispatcher("/home.html");
										rd.include(request, response);
								       
								 }
								}else{
									response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
											+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
											+ "    margin-top: 32%;\r\n"
											+ "    margin-left: 600px;\r\n"
											+ "    color:red;\">Receiver's name does not matched with account !</dialog>");
									RequestDispatcher rd = request.getRequestDispatcher("/Transfer.html");
									rd.include(request, response);
									
								
								}
							}else{response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
									+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
									+ "    margin-top: 32%;\r\n"
									+ "    margin-left: 600px;\r\n"
									+ "    color:red;\">Recievers account is inorrect ! </dialog>");
							RequestDispatcher rd = request.getRequestDispatcher("/Transfer.html");
							rd.include(request, response);}
				}else{
//					response.getWriter().println("<script>window.alert(\"Sender's name does not matched with account\");</script>");
					response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
							+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
							+ "    margin-top: 32%;\r\n"
							+ "    margin-left: 600px;\r\n"
							+ "    color:red;\">Sender's name does not matched with account ! </dialog>");
				RequestDispatcher rd = request.getRequestDispatcher("/Transfer.html");
				rd.include(request, response);
				}	
				
			}else{
				response.getWriter().println("<br><dialog open class=\"div1\" style=\"border: 0 0 0 0; font-family: Verdana, Geneva, Tahoma, sans-serif;\r\n"
						+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "    margin-top: 32%;\r\n"
						+ "    margin-left: 600px;\r\n"
						+ "    color:red;\">Sender's account os incorrect ! </dialog>");
				
			RequestDispatcher rd = request.getRequestDispatcher("/Transfer.html");
			rd.include(request, response);
			}
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
