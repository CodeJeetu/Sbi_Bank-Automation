package com.sbi_user;

import java.sql.*;
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
 * Servlet implementation class AccountDetail
 */
public class AccountDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
    public AccountDetail() throws SQLException ,ClassNotFoundException{
        super();
        doConnection();
    }

	private void doConnection()throws SQLException ,ClassNotFoundException {
		     
		con = DB_Connection.getConnection();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String ac = request.getParameter("acno");
		System.out.println(ac);
		try {
			
		
		String sql = "SELECT  NAME,fname,aadhar,email,mobile,balance,aType,lastupdate FROM sbi_account_dtl WHERE accountno =?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ac.trim());
		rs =pstmt.executeQuery();
		int i =0;
		if(rs.next())
		{
			++i;
		}if(i>0)
		{
			  System.out.println(rs.getString(1));
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
			  		+ "  body{\r\n"
			  		+ "      \r\n"
			  		+ "   background-image: url(\"sbibground1.jpg\");\r\n"
			  		+ "   }\r\n"
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
			  		+ "    }\r\n"
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
			  		+ "    }"
			  		+ ".cont{\r\n"
			  		+ "    text-align: left;    \r\n"
			  		+ "    margin-left: 100px;\r\n"
			  		+ "}\r\n"
			  		+ "    #div1{\r\n"
			  		+ "    border-style: double;\r\n"
			  		+ "    color:blue;\r\n"
			  		+ "    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\r\n"
			  		+ "    font-size: 20px;\r\n"
			  		+ "   \r\n"
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
			  		+ "            <!-- <h1 style=\"text-align:center; font-family: Verdana; margin-left: 20px; \">$ +rs.getString(1)</h1> -->\r\n"
			  		+ "           <br>\r\n"
			  		+ "            <form action=\"ViewDetails\" method =\"post\">\r\n"
			  		+ "            <div class=\"cont\">\r\n"
			  		+ "             <br><br>	"
			  		+ "					Name  :<label id =\"name\">"+rs.getString(1)+"</label>\r\n"
			  		+ "        		<br><br>\r\n"
			  		+ "                Father :<label id =\"name\">"+rs.getString(2)+"</label>\r\n"
			  		+ "                <br>Aadhar :<label id =\"DOC\">"+rs.getString(3)+"</label><br>Email :<label id =\\\"DOC\\\">"+rs.getString(4)+"</label>\r\n"
			  				
			  		+ "               <br> Mobile :<label id =\"DOC\">"+rs.getString(5)+"</label>\r\n"
			  		+ "        		  <br> Balance :<label>"+rs.getString(6)+" rs</rs></label>\r\n"
			  		+ "                <br>AccountType :<label>"+rs.getString(7)+"</label>\r\n"
			  		+ "                <br> Last Update :<label>"+rs.getString(8)+"</label>\r\n"
			  		+ "                <br><br>\r\n"
			  		+ "                <input class=\"buton\"type=\"submit\" value=\"Back\">\r\n"
			  		+ "            </div>\r\n"
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
			  
//			  RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewDetails.html");
//		        requestDispatcher.include(request, response);
		}
		}catch(Exception err)
		{
			err.printStackTrace();
		}
	}

}
