/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Administrator
 */
@WebServlet(name = "ListingPage", urlPatterns = {"/ListingPage"})
public class ListingPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String x;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
                     HttpSession session = request.getSession(true);
        String name = (String) session.getAttribute("username");
        if(name==null||name.equals(null)){
        response.sendRedirect("http://localhost:8080/webapp2/LoginPage");  
        }
        else{
        x=name;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ResultSet rs = null;
          try{
        Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/absi", "absi", "absi");
        Statement st = con.createStatement();
        rs = st.executeQuery("select * From USERS");
        }
        catch(Exception e){
        
        }
          

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("table, th, td{");
            out.println("border:1px solid black");
            out.println("}");
            out.println("<head>");
            out.println("</style>");
            out.println("<title>Servlet ListingPage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+x +"</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>username</th>");
            out.println("<th>password</th>");
            out.println("</tr>");
            try{
            while(rs.next()){
               String user=rs.getString("username");
               String pass=rs.getString("password");
            out.println("<tr>");
            out.println("<td><a href='http://localhost:8080/webapp2/Editpage?username="+user+"'>"+user+"</a></td>");
            out.println("<td>"+pass+"</td>");
            out.println("</tr>");
            }
            }
            catch(SQLException e){
            }
            
            out.println("</table>");
            request.getContextPath();
            out.println("</body>");
            out.println("</html>");
        }
    }
       public static void test() throws Exception{    
  

    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
