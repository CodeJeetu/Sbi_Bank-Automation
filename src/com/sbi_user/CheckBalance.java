package com.sbi_user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class CheckBalance
 */
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement stmt;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBalance() {
        super();
        
        doConnection();
    }

	
	private void doConnection() {
	try {
		con = DB_Connection.getConnection();
	}catch(Exception err)
	{
		System.out.println(err);
	}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		String gacno = request.getParameter("acno");
		String gname = request.getParameter("name");
		try {
			String sql ="SELECT balance,name, mobile, Date FROM sbi_account_dtl WHERE accountNo =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gacno.trim());
			rs = pstmt.executeQuery();
			int i=0;
			if(rs.next())
			{
				++i;
			}
			if(i>0) {
				if(gname.trim().toLowerCase().equals(rs.getString(2).trim().toLowerCase()))
				{
//					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewBalance.html");
//			        requestDispatcher.forward(request, response);
					response.getWriter().print("<!DOCTYPE html>\r\n"
							+ "\r\n"
							+ "<html>\r\n"
							+ "<head>\r\n"
							+ "    <meta charset=\"utf-8\">\r\n"
							+ "    <title>Balance View</title>\r\n"
							+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\">\r\n"
							+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">\r\n"
							+ "   <link rel=\"stylesheet\" href=\"user.css\">\r\n"
							+ "    <style>\r\n"
							+ "   \r\n"
							+ "    \r\n"
							+ "  \r\n"
							+ "    .buton {\r\n"
							+ "        background-color: #4CAF50;\r\n"
							+ "        border-radius: 4px;\r\n"
							+ "        color: white;\r\n"
							+ "        padding: 15px 32px;\r\n"
							+ "        text-align: center;\r\n"
							+ "        text-decoration: none;\r\n"
							+ "        display: inline-block;\r\n"
							+ "        font-size: 16px;\r\n"
							+ "        margin: 4px 2px;\r\n"
							+ "        cursor: pointer;\r\n"
							+ "       \r\n"
							+ "        margin-right: 60px;\r\n"
							+ "        margin-left: 50px;\r\n"
							+ "    }  body{\r\n"
							+ "      \r\n"
							+ "   background-image: url(\"sbibground1.jpg\");\r\n"
							+ "   }\r\n"
							+ "    .jk{\r\n"
							+ "        display:inline;\r\n"
							+ "        border: #4CAF50;\r\n"
							+ "        font-family: Lucida;\r\n"
							+ "        font-size: 20px;\r\n"
							+ "        margin-left: 10px;\r\n"
							+ "   \r\n"
							+ "      }\r\n"
							+ "      .css{\r\n"
							+ "          border-radius: 4px;\r\n"
							+ "          display: inline;\r\n"
							+ "          margin-left: 10pc;\r\n"
							+ "      }\r\n"
							+ "      \r\n"
							+ "    .buton:hover {\r\n"
							+ "    color: red;\r\n"
							+ "    background: #eff;\r\n"
							+ "    }\r\n"
							+ "    \r\n"
							+ "    form{\r\n"
							+ "        border-radius: 4px;\r\n"
							+ "        text-align: center;\r\n"
							+ "        border:10px;\r\n"
							+ "    }\r\n"
							+ "    #div1{\r\n"
							+ "    border-style: double;\r\n"
							+ "    color:blue;\r\n"
							+ "    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\r\n"
							+ "    font-size: 20px;\r\n"
							+ "   \r\n"
							+ "    width: 550px;height: 420px;\r\n"
							+ "    text-align: left;\r\n"
							+ "    margin-left: 520px;\r\n"
							+ "    margin-right: 100px;\r\n"
							+ "    position: relative;\r\n"
							+ "    margin-top: 5px;\r\n"
							+ "    margin-bottom: 30px;\r\n"
							+ "    \r\n"
							+ "\r\n"
							+ "}\r\n"
							+ "#div2{\r\n"
							+ "    margin-top: 10pc;\r\n"
							+ "}\r\n"
							+ "    \r\n"
							+ "    \r\n"
							+ "    </style> \r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "    <header>\r\n"
							+ "       \r\n"
							+ "        <div>\r\n"
							+ "     \r\n"
							+ "    \r\n"
							+ "        <nav>\r\n"
							+ "            <ul>\r\n"
							+ "                <li><a class=\"homeblack\" href=\"home.html\">Home</a>\r\n"
							+ "                    <li><a class=\"homeblack\" href=\"Transfer.html\">Transfer</a>\r\n"
							+ "                     <li><a class=\"homeblack\" href=\"index.html\">Signout</a>\r\n"
							+ "                \r\n"
							+ "            </ul>\r\n"
							+ "        </nav>\r\n"
							+ "        </div>\r\n"
							+ "    </header>\r\n"
							+ "    \r\n"
							+ "    <section>\r\n"
							+ "        <br><br><br>\r\n"
							+ "        \r\n"
							+ "        <div id=\"div1\">\r\n"
							+ "            <h1 style=\"text-align:center; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin-left: 20px; \">$ "+rs.getString(1)+"</h1>\r\n"
							+ "           <br>\r\n"
							+ "            <form action=\"ViewBalance\" method =\"post\">\r\n"
							+ "            \r\n"
							+ "             	Name  :<label id =\"name\">"+rs.getString(2)+"</label>\r\n"
							+ "        		<br><br>\r\n"
							+ "        		Mobile No  :<label id =\"phone\">"+rs.getString(3)+"</label>\r\n"
							+ "        		<br><br>Opening Date:<label id =\"DOC\">"+rs.getString(4)+"</label>\r\n"
							+ "                <br><br>\r\n"
							+ "                \r\n"
							+ "                <input class=\"buton\"type=\"submit\" value=\"Back\">\r\n"
							+ "          \r\n"
							+ "            </form>\r\n"
							+ "	\r\n"
							+ "    \r\n"
							+ "        </div>\r\n"
							+ "       \r\n"
							+ "    </section>\r\n"
							+ "    <footer>\r\n"
							+ "    \r\n"
							+ "    </footer> \r\n"
							+ "\r\n"
							+ "</body>\r\n"
							+ "</html>");
					
					
//				response.getWriter().println(rs.getString(1)+"\n"
//				+rs.getString(2)+"\n"
//				+rs.getString(3)+"\n"
//				+rs.getString(4));
				}
				else{
					response.getWriter().println("User name is incorrect");
				}
			}
			else{
				response.getWriter().println("Not Found Account number");
			}
			
			
		}catch(Exception error)
		{
			response.getWriter().println(error);
		}
		
	}

}
