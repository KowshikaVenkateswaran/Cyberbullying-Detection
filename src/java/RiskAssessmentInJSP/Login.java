/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RiskAssessmentInJSP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SEABIRDS-PC
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        HttpSession session=request.getSession(true);
        
        String uname=request.getParameter("uname");        
        String pass=request.getParameter("pass");
        
        System.out.println("Here come in");
        
        try
        {            
            if((uname.equals("admin"))&&(pass.equals("admin")))
            {                
                session.setAttribute("facebookdataset", "");        
                session.setAttribute("firstphase", "");
                session.setAttribute("secondphase", "");
                session.setAttribute("trainingdataset", "");
                session.setAttribute("testingdataset", "");
                session.setAttribute("svmwithrfResults", "");
                session.setAttribute("intentionmodelResults", "");
                session.setAttribute("linearregressionResults", "");
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login Successfully!');");
                out.println("location='AdminHome.jsp';");
                out.println("</script>");
            }            
            else
            {                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username & Password is invalid!');");
                out.println("location='Home.jsp';");
                out.println("</script>");                               
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
