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
@WebServlet(name = "SecondPhase", urlPatterns = {"/SecondPhase"})
public class SecondPhase extends HttpServlet {

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
        
        ArrayList Content=new ArrayList();
        ArrayList Result=new ArrayList();
        ArrayList cyberbullyingWords=new ArrayList();
        HttpSession session = request.getSession();
        List<String> cluster1 = (List<String>) session.getAttribute("cluster1");
        List<String> cluster2 = (List<String>) session.getAttribute("cluster2");
        List<String> cluster3 = (List<String>) session.getAttribute("cluster3");
        List<String> cluster4 = (List<String>) session.getAttribute("cluster4");
        
        String cw="";
        try
        {
            String fname=getServletContext().getRealPath("/Cyberbullying Words.txt");
            File fe = new File(fname);
            FileInputStream fis=new FileInputStream(fe);
            byte data[]=new byte[fis.available()];
            fis.read(data);
            fis.close();
            cw=new String(data);            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String cyberbull[]=cw.trim().split("\n");        
        for(int i=0;i<cyberbull.length;i++)
        {
            String word=cyberbull[i].trim();
            if(!(cyberbullyingWords.contains(word.trim())))
            {
                cyberbullyingWords.add(word.trim());
            }
        }
        
        StringBuilder sb = new StringBuilder();

        sb.append("==============================================================================\n");
        sb.append("                                 Group - 1 (Male)\n");
        sb.append("==============================================================================\n");
        for(int i=0;i<cluster1.size();i++)
        {
            String record=cluster1.get(i).toString().trim();

            String status="Normal Behavior";
            String s[]=record.trim().split("-->");            
            String posts=s[s.length-1].toLowerCase().trim().replaceAll("[^\\w\\s]", "");
            String sp[]=posts.trim().split(" ");
            for(int j=0;j<sp.length;j++)
            {
                if((cyberbullyingWords.contains(sp[j].trim())))
                {
                    status="Risky";
                    break;
                }
            }            
            sb.append(record.trim().replaceAll("-->", ",")+"-->"+status+"\n");
            Content.add(record.trim().replaceAll("-->", ","));
            Result.add(status.trim());
        }
        sb.append("\n");

        sb.append("==============================================================================\n");
        sb.append("                                 Group - 2 (Female)\n");
        sb.append("==============================================================================\n");
        for(int i=0;i<cluster2.size();i++)
        {
            String record=cluster2.get(i).toString().trim();

            String status="Normal Behavior";
            String s[]=record.trim().split("-->");            
            String posts=s[s.length-1].toLowerCase().trim().replaceAll("[^\\w\\s]", "");
            String sp[]=posts.trim().split(" ");
            for(int j=0;j<sp.length;j++)
            {
                if((cyberbullyingWords.contains(sp[j].trim())))
                {
                    status="Risky";
                    break;
                }
            }            
            sb.append(record.trim().replaceAll("-->", ",")+"-->"+status+"\n");
            Content.add(record.trim().replaceAll("-->", ","));
            Result.add(status.trim());
        }
        sb.append("\n");
        
        sb.append("==============================================================================\n");
        sb.append("                                 Group - 3 (<=18)\n");
        sb.append("==============================================================================\n");
        for(int i=0;i<cluster3.size();i++)
        {
            String record=cluster3.get(i).toString().trim();
            
            String status="Normal Behavior";
            String s[]=record.trim().split("-->");            
            String posts=s[s.length-1].toLowerCase().trim().replaceAll("[^\\w\\s]", "");
            String sp[]=posts.trim().split(" ");
            for(int j=0;j<sp.length;j++)
            {
                if((cyberbullyingWords.contains(sp[j].trim())))
                {
                    status="Risky";
                    break;
                }
            }            
            sb.append(record.trim().replaceAll("-->", ",")+"-->"+status+"\n");
            Content.add(record.trim().replaceAll("-->", ","));
            Result.add(status.trim());
        }
        sb.append("\n");
        
        sb.append("==============================================================================\n");
        sb.append("                                 Group - 4 (>18)\n");
        sb.append("==============================================================================\n");
        for(int i=0;i<cluster4.size();i++)
        {
            String record=cluster4.get(i).toString().trim();
            
            String status="Normal Behavior";
            String s[]=record.trim().split("-->");            
            String posts=s[s.length-1].toLowerCase().trim().replaceAll("[^\\w\\s]", "");
            String sp[]=posts.trim().split(" ");
            for(int j=0;j<sp.length;j++)
            {
                if((cyberbullyingWords.contains(sp[j].trim())))
                {
                    status="Risky";
                    break;
                }
            }            
            sb.append(record.trim().replaceAll("-->", ",")+"-->"+status+"\n");
            Content.add(record.trim().replaceAll("-->", ","));
            Result.add(status.trim());
        }
        sb.append("\n");
        String secondphase=sb.toString().trim();
        
        session.setAttribute("secondphase", secondphase);
        session.setAttribute("Content", Content);
        session.setAttribute("Result", Result);
        session.setAttribute("cyberbullyingWords", cyberbullyingWords);
        
        String facebookdataset=session.getAttribute("facebookdataset").toString().trim();
        String firstphase=session.getAttribute("firstphase").toString().trim();
        request.setAttribute("facebookdataset", facebookdataset);        
        request.setAttribute("firstphase", firstphase);
        request.setAttribute("secondphase", secondphase);
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
