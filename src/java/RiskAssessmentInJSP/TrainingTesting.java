/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

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
@WebServlet(name = "TrainingTesting", urlPatterns = {"/TrainingTesting"})
public class TrainingTesting extends HttpServlet {

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
        
        ArrayList allTrainingData=new ArrayList();
        ArrayList allTrainingResults=new ArrayList(); 
        ArrayList allTestingData=new ArrayList();
        ArrayList allTestingActualResults=new ArrayList();
        HttpSession session = request.getSession();
        List<String> Content = (List<String>) session.getAttribute("Content");
        List<String> Result = (List<String>) session.getAttribute("Result");
        
            /* Training Dataset (80 %) */
            
        ArrayList allRiskyData=new ArrayList();
        ArrayList allRiskyResults=new ArrayList();
        
        ArrayList allNormalData=new ArrayList();
        ArrayList allNormalResults=new ArrayList();
            
        for(int i=0;i<((Content.size()*80)/100);i++)
        {
            String data=Content.get(i).toString().trim();
            String result=Result.get(i).toString().trim();
            if(result.trim().equals("Risky"))
            {
                allRiskyData.add(data);
                allRiskyResults.add(result.trim());
            }
            else
            {
                allNormalData.add(data);
                allNormalResults.add(result.trim());
            }
        }
        
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        sb1.append("=================================================\n");
        sb1.append("                  "+"Training Dataset"+"\n");
        sb1.append("=================================================\n");
        for(int i=0;i<((allRiskyData.size()*80)/100);i++)
        {
            String data=allRiskyData.get(i).toString().trim();
            String result=allRiskyResults.get(i).toString().trim();            
            sb1.append(data.trim()).append(" --> ").append(result.trim()).append("\n"); 
            allTrainingData.add(data.trim());
            allTrainingResults.add(result.trim());
        }
        for(int i=0;i<((allNormalData.size()*80)/100);i++)
        {
            String data=allNormalData.get(i).toString().trim();
            String result=allNormalResults.get(i).toString().trim();            
            sb1.append(data.trim()).append(" --> ").append(result.trim()).append("\n"); 
            allTrainingData.add(data.trim());
            allTrainingResults.add(result.trim());
        }
        
        sb2.append("=================================================\n");
        sb2.append("                  "+"Testing Dataset"+"\n");
        sb2.append("=================================================\n");
        for(int i=((allRiskyData.size()*80)/100);i<allRiskyData.size();i++)
        {
            String data=allRiskyData.get(i).toString().trim();
            String result=allRiskyResults.get(i).toString().trim();            
            sb2.append(data.trim()).append(" --> ?\n"); 
            allTestingData.add(data.trim());
            allTestingActualResults.add(result.trim());
        }
        for(int i=((allNormalData.size()*80)/100);i<allNormalData.size();i++)
        {
            String data=allNormalData.get(i).toString().trim();
            String result=allNormalResults.get(i).toString().trim();            
            sb2.append(data.trim()).append(" --> ?\n");
            allTestingData.add(data.trim());
            allTestingActualResults.add(result.trim());
        }
        
        session.setAttribute("allTrainingData", allTrainingData);
        session.setAttribute("allTrainingResults", allTrainingResults);
        session.setAttribute("allTestingData", allTestingData);
        session.setAttribute("allTestingActualResults", allTestingActualResults);
        
        String trainingdataset=sb1.toString().trim();
        String testingdataset=sb2.toString().trim();
        
        session.setAttribute("trainingdataset", trainingdataset);
        session.setAttribute("testingdataset", testingdataset);
        
        String facebookdataset=session.getAttribute("facebookdataset").toString().trim();
        String firstphase=session.getAttribute("firstphase").toString().trim();
        String secondphase=session.getAttribute("secondphase").toString().trim();
        
        request.setAttribute("facebookdataset", facebookdataset);        
        request.setAttribute("firstphase", firstphase);
        request.setAttribute("secondphase", secondphase);
        request.setAttribute("trainingdataset", trainingdataset);
        request.setAttribute("testingdataset", testingdataset);
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
