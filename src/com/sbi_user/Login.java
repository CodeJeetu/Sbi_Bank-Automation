package com.sbi_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 Connection con =null;
     ResultSet rs = null;
     PreparedStatement pstmt;
    
     String sql =null;
    public Login() {
        
    	do_Connection();
    }

    
	 public void do_Connection() {
		try {
		con = DB_Connection.getConnection();
		}catch( Exception err)
		{
			System.out.println(err);
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String guser = request.getParameter("username");
		String gpass = request.getParameter("password");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(type.equals("User"))
		{
			
					
		
			try {
				sql = "SELECT password FROM user WHERE username=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, guser.trim());
				rs = pstmt.executeQuery();
				int i=0;
				if(rs.next())
				{
					++i;
				}if(i>0) {
					String dpass = rs.getString(1);
							
							if(gpass.toLowerCase().trim().equals(dpass.toLowerCase()))
							{
								RequestDispatcher rd = request.getRequestDispatcher("home.html");
								rd.forward(request, response);
								
//								out.println("Sucess fully logined");
							}else {
//								out.println("<!DOCTYPE html><html><body><dialog open> Sorry "+gpass+"<br> is not Correct Password. </dialog>\r\n</body>\r\n</html>");
								
								RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
								rd.include(request, response);
								out.print("<dialog open class=\"div1\" style=\"border-radius: 20px;\r\n"
										+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
										+ "    margin-top: 0%;\r\n"
										+ "    margin-left: 88%;\r\n"
										+ "    color:red;\">Password Incorrect</dialog>");
							}
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
					rd.include(request, response);
					out.print("<dialog open class=\"div1\" style=\"border-radius: 20px;\r\n"
							+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
							+ "    margin-top: 0%;\r\n"
							+ "    margin-left: 88%;\r\n"
							+ "    color:red;\">User Id incorrect</dialog>");
//					out.println("<!DOCTYPE html><html><body><dialog open> Sorry "+guser+"! you are not Creditable User </dialog>\r\n</body>\r\n</html>");
				}
				
			}catch(Exception e) {
				out.println("Error Raised "+e);
				
			}
			
		}else if(guser.equals("Jitu") && gpass.equals("jitu123")) {
			response.getWriter().println("Sucess fully logined");
		}else {
			out.println("<dialog open class=\"div1\" style=\"border-radius: 20px;\r\n"
					+ "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "    margin-top: 10%;\r\n"
					+ "    margin-left: 88%;\r\n"
					+ "    color:red;\">Select any type</dialog>");
			RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
			rd.include(request, response);
			
		}
						
			
			
		
			
		
		
	}

}
