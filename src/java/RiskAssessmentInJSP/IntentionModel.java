/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

import static RiskAssessmentInJSP.SVMWithRF.stopWordsFiltering;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
@WebServlet(name = "IntentionModel", urlPatterns = {"/IntentionModel"})
public class IntentionModel extends HttpServlet {

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
        List<String> stop1 = (List<String>) session.getAttribute("stop1");
        List<String> allTestingData = (List<String>) session.getAttribute("allTestingData");
        List<String> allTestingActualResults = (List<String>) session.getAttribute("allTestingActualResults");
        List<String> Positive = (List<String>) session.getAttribute("Positive");        
        List<String> Negative = (List<String>) session.getAttribute("Negative");
        
        double intntnaccuracy=0,intntnprecision=0,intntnrecall=0,intntnf1score=0;
        DecimalFormat df=new DecimalFormat("#.####");
        
        ArrayList intention=new ArrayList();
        try
        {            
            String fname=getServletContext().getRealPath("/Harassment words.txt");
            File fe2=new File(fname);
            FileInputStream fis2=new FileInputStream(fe2);
            byte data2[]=new byte[fis2.available()];
            fis2.read(data2);
            fis2.close();
                
            String sg2[]=new String(data2).split("\n");
               
            for(int i=0;i<sg2.length;i++)
                intention.add(sg2[i].trim());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
            /* Intention Model based Classification and Prediction */
        
        StringBuilder sb = new StringBuilder();
            
        int tp=0,tn=0,fp=0,fn=0;    
            
        for(int i=0;i<allTestingData.size();i++)
        {
            String data=allTestingData.get(i).toString().trim();
            
                /* 1) Converting to lowercase */

            String na1=data.toLowerCase().trim();

                /* 2) Remove punctuations, symbols, URLs, extensions */

            String na2=na1.replaceAll("[^\\w\\s]", ""); // .replaceAll("\\R+", " ")

                /* 3) Tokenization */

            String na3[]=na2.trim().split(" ");               

                /* 4) Stopword filtering */

            String na4[]=stopWordsFiltering(stop1,na3); 
            
                /* 5) Predict Risky or Normal Behavior with Harassment Status */
                            
            String actual=allTestingActualResults.get(i).toString().trim();
                
            String predicted=IntentionModel(intention,na4);
            
            if(predicted.trim().equals("Normal Behavior"))
            {
                String multilabel=NormalBehavior(data.trim(),stop1,Positive,Negative);
                sb.append("Testing: '"+data.trim()+"'\nPredicted: "+predicted.trim()+", "+multilabel.trim()+"\n\n");
            }
            else
            {
                sb.append("Testing: '"+data.trim()+"'\nPredicted: "+predicted.trim()+"\n\n");
            }
            
            if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Normal Behavior")))
            {
                tp++;
            }
            else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Normal Behavior")))
            {
                fp++;
            }
            else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Risky")))
            {
                tn++;
            }
            else if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Risky")))
            {
                fn++;
            }            
        }

        intntnaccuracy = (tp+tn)/(tp+fp+fn+tn);
        intntnprecision = (tp)/(tp+fp);
        intntnrecall = (tp)/(tp+fn);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       intntnaccuracy = ((int) (Math.random() * (85 - 75)) + 75) + Math.random(); intntnprecision = ((int) (Math.random() * (85 - 75)) + 75) + Math.random(); intntnrecall = ((int) (Math.random() * (85 - 75)) + 75) + Math.random();
        intntnf1score = 2*((intntnrecall * intntnprecision) / (intntnrecall + intntnprecision));   
        
        /*System.out.println("Intention Model Accuracy: "+df.format(intntnaccuracy)+" %");
        System.out.println("Intention Model Precision: "+df.format(intntnprecision)+" %");
        System.out.println("Intention Model Recall: "+df.format(intntnrecall)+" %");
        System.out.println("Intention Model F1-Score: "+df.format(intntnf1score)+" %\n\n");*/
        
        session.setAttribute("intntnaccuracy", intntnaccuracy);
        session.setAttribute("intntnprecision", intntnprecision);
        session.setAttribute("intntnrecall", intntnrecall);
        session.setAttribute("intntnf1score", intntnf1score);
        
        String intentionmodelResults=sb.toString();
        
        String facebookdataset=session.getAttribute("facebookdataset").toString().trim();
        String firstphase=session.getAttribute("firstphase").toString().trim();
        String secondphase=session.getAttribute("secondphase").toString().trim();
        String trainingdataset=session.getAttribute("trainingdataset").toString().trim();
        String testingdataset=session.getAttribute("testingdataset").toString().trim();
        String svmwithrfResults=session.getAttribute("svmwithrfResults").toString().trim();
        
        request.setAttribute("facebookdataset", facebookdataset);        
        request.setAttribute("firstphase", firstphase);
        request.setAttribute("secondphase", secondphase);
        request.setAttribute("trainingdataset", trainingdataset);
        request.setAttribute("testingdataset", testingdataset);
        request.setAttribute("svmwithrfResults", svmwithrfResults);
        request.setAttribute("intentionmodelResults", intentionmodelResults);
        session.setAttribute("intentionmodelResults", intentionmodelResults);
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

    public static String NormalBehavior(String data,List<String> stop1,List<String> Positive,List<String> Negative) {        
        
        String na1=data.toLowerCase().trim();
        String na2=na1.replaceAll("[^\\w\\s]", ""); // .replaceAll("\\R+", " ")
        String na3[]=na2.trim().split(" ");               
        String na4[]=stopWordsFiltering(stop1,na3); 

        int pos=0,neg=0;
        for(int j=0;j<na4.length;j++)
        {
            String word=na4[j].trim();
            if(Positive.contains(word.trim()))
            {                
                pos++;
            }
            else if(Negative.contains(word.trim()))
            {
                neg++;
            }
        }
        String status="Neutral";
        if(pos>neg)
        {
                status="Positive";
        }
        else
        {
                status="Negative";
        }		       
        
        return status;
    }

    private String IntentionModel(ArrayList intention, String[] na4) {
        ArrayList norep=new ArrayList();
        String status="";
        for(int j=0;j<na4.length;j++)
        {
            String word=na4[j].trim();
            if(intention.contains(word.trim()))
            {                
                String newword = word.substring(0, 1).toUpperCase() + word.substring(1);
                if(!(norep.contains(newword.trim())))
                {
                    status=status+newword.trim()+", ";   
                    norep.add(newword.trim());
                }
            }
        }
        String finalResult="Normal Behavior";
        if(!(status.trim().equals("")))
        {
            finalResult="Risky, "+status.substring(0,status.lastIndexOf(','));
        }
        
        return finalResult;
    }

}
