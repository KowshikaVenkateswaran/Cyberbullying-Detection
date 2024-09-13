/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author SEABIRDS-PC
 */
@WebServlet(name = "LoadFaceBookDataset", urlPatterns = {"/LoadFaceBookDataset"})
public class LoadFaceBookDataset extends HttpServlet {

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
        HttpSession session = request.getSession();
        List<String> fileNames = new ArrayList<>();
        List<String> fileContents = new ArrayList<>();

        String path = getServletContext().getRealPath("/Dataset/");
        System.out.println("path: "+path);
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        System.out.println("listOfFiles.length: "+listOfFiles.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
                System.out.println("fileName: "+fileName);
                sb.append("======================================================\n");
                sb.append("                       ").append(fileName.trim()).append("\n");
                sb.append("======================================================\n");

                try {
                    FileInputStream fis = new FileInputStream(listOfFiles[i]);
                    byte data[] = new byte[fis.available()];
                    fis.read(data);
                    fis.close();
                    String fc = new String(data);
                    sb.append(fc.trim()).append("\n\n");
                    fileNames.add(path +"\\"+ fileName.trim());
                    fileContents.add(fc.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        

        session.setAttribute("FileNames", fileNames);
        session.setAttribute("FileContents", fileContents);
        session.setAttribute("facebookdataset", sb.toString());
        request.setAttribute("facebookdataset", sb.toString());
        request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
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
