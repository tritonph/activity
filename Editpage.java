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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "Editpage", urlPatterns = {"/Editpage"})
public class Editpage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String x = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String a = request.getParameter("username");
        String p = request.getParameter("password");
        HttpSession session = request.getSession(true);
        String name = (String) session.getAttribute("username");
        if(name==null||name.equals(null)){
        response.sendRedirect("http://localhost:8080/webapp2/LoginPage");  
        }
        if (a==null||a.equals(null)){
        x="ADD";
        a="";
        }
        else{
        x="EDIT";
        }
         if(x.equals("EDIT")){
                  ResultSet rs = null;
          try{
        Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/absi", "absi", "absi");
        Statement st = con.createStatement();
        rs = st.executeQuery("select password From USERS where username = '"+a+"'");
        }
        catch(Exception e){
        
        }
       try{
            while(rs.next()){
               p=rs.getString("password");
               
            }
            }
            catch(SQLException e){
            }
               
            }
        if (p==null||p.equals(null)){
            p="";
           }
        
        
         
         
         

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Editpage</title>");            
            out.println("</head>");
            out.println("<br>");
            out.println("<h1>"+x+"</h1>");
            out.println("<form method=\"post\" action=\"Editpage\">");
            out.println("<input type=\"textfield\" placeholder=\"username\"name='username' value=\""+a+"\">");
            out.println("<input type=\"textfield\" placeholder=\"password\"name='password' value=\""+p+"\">");
            out.println("<br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Enter\">");
            out.println("</form>");
            out.println("<body>");
            request.getContextPath();
            out.println("</body>");
            out.println("</html>");
        }


       
    }
        @Override

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
          ResultSet rs = null;
         if (x.equals("ADD")){
         try{
        Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/absi", "absi", "absi");
        PreparedStatement insert = con.prepareStatement("insert into USERS(username, password) values(?,?)");
        insert.setString(1, request.getParameter("username"));
        insert.setString(2, request.getParameter("password"));
        insert.execute();
        insert.close();
        }
        catch(Exception e){
        
        }
          }
         else if(x.equals("EDIT")){
          
       try{
        Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/absi", "absi", "absi");
        PreparedStatement insert = con.prepareStatement("update USERS set password=(?)where username='"+request.getParameter("username").trim()+"'");
        insert.setString(1, request.getParameter("password"));
        insert.execute();
        insert.close();
        }
       
        catch(Exception e){
        
        }
         }
         response.sendRedirect("http://localhost:8080/webapp2/ListingPage");  

          
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
        /*        
*/
}
