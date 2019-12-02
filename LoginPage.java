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
@WebServlet(name = "LoginPage", urlPatterns = {"/LoginPage"})
public class LoginPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String a = request.getParameter("username");
        String p = request.getParameter("password");
          if (a==null||a.equals(null)){
        a="";
        }
       if (p==null||p.equals(null)){
        p="";
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginPage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form Method='POST' action='LoginPage'>");
            out.println("<input type='text' name='username' placeholder='username'>");
            out.println("<input type='text' name='password' placeholder='password'>");
            out.println("<input type='submit' value='login'>");
            out.println("</form>");
            out.println("<body>");
            out.println("<h1>Servlet LoginPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
	        String use=request.getParameter("username");
               String pass=request.getParameter("passsword");
                ResultSet rs = null;
                 try{
        Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/absi", "absi", "absi");
        Statement st = con.createStatement();
        rs = st.executeQuery("select * From USERS");
        }
        catch(Exception e){
        
        }
        if(rs==null){
        response.sendRedirect("http://google.com");
        }       
        else{
            
        try{
            while(rs.next()){
                if(rs.getString("password").equals(pass)){
                 response.sendRedirect("http://localhost:8080/webapp2/ListingPage");
                }
            }
            }
            catch(SQLException e){
            }
            }
            
        

        
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
