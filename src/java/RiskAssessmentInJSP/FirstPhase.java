/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

import java.io.File;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author SEABIRDS-PC
 */
@WebServlet(name = "FirstPhase", urlPatterns = {"/FirstPhase"})
public class FirstPhase extends HttpServlet {

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
        List<String> FileNames = (List<String>) session.getAttribute("FileNames");
        List<String> cluster1 = new ArrayList<String>();
        List<String> cluster2 = new ArrayList<String>();
        List<String> cluster3 = new ArrayList<String>();
        List<String> cluster4 = new ArrayList<String>();

        for (int i = 0; i < FileNames.size(); i++) {
            String fn = FileNames.get(i).toString().trim();
            System.out.println(fn.trim());
            try {
                File input = new File(fn.trim());
                Document doc = Jsoup.parse(input, "UTF-8");

                Elements links = doc.select("post");
                for (Element link : links) {
                    String postid = link.attr("id");
                    System.out.println("postid: " + postid.trim());

                    doc = Jsoup.parse(link.toString());

                    String userid = "", username = "", sex = "", age = "", city = "", province = "", country = "",
                            date = "", body = "";
                    try {
                        Elements linksk1 = doc.select("user");
                        for (Element link2 : linksk1) {
                            userid = link2.attr("id");
                            System.out.println("userid: " + userid);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Elements linksk1 = doc.select("username");
                        username = linksk1.get(0).text();
                        System.out.println("username: " + username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Elements linksk1 = doc.select("sex");
                        sex = linksk1.get(0).text();
                        System.out.println("sex: " + sex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Elements linksk1 = doc.select("age");
                        age = linksk1.get(0).text();
                        System.out.println("age: " + age);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try
                    {
                        Elements linksk1 = doc.select("city");
                        city=linksk1.get(0).text();
                        System.out.println("city: "+city);                                                                                               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        Elements linksk1 = doc.select("province");
                        province=linksk1.get(0).text();
                        System.out.println("province: "+province);                                                                                               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        Elements linksk1 = doc.select("country");
                        country=linksk1.get(0).text();
                        System.out.println("country: "+country);                                                                                               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        Elements linksk1 = doc.select("date");
                        date=linksk1.get(0).text();
                        System.out.println("date: "+date);                                                                                               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {                        
                        Elements linksk1 = doc.select("body");
                        body=linksk1.get(0).text();
                        System.out.println("body: "+body);                                                                                               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("==========================================");
                    if(sex.trim().equals("M"))
                    {
                        cluster1.add(postid+","+userid+","+username+","+sex+","+age+","+city+","+province+","+country+","+date+"-->"+body);
                    }
                    else        // Female
                    {
                        cluster2.add(postid+","+userid+","+username+","+sex+","+age+","+city+","+province+","+country+","+date+"-->"+body);
                    }
                    
                    if(Integer.parseInt(age)<=18)
                    {
                        cluster3.add(postid+","+userid+","+username+","+sex+","+age+","+city+","+province+","+country+","+date+"-->"+body);
                    }
                    else
                    {
                        cluster4.add(postid+","+userid+","+username+","+sex+","+age+","+city+","+province+","+country+","+date+"-->"+body);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }            
        }
        
        StringBuilder output = new StringBuilder();

        output.append("==============================================================================\n");
        output.append("                                 Group - 1 (Male)\n");
        output.append("==============================================================================\n");
        for(int i=0;i<cluster1.size();i++)
        {
            String s=cluster1.get(i).trim().replaceAll("-->", ",");
            output.append(s.trim()).append("\n");
        }
        output.append("\n");

        output.append("==============================================================================\n");
        output.append("                                 Group - 2 (Female)\n");
        output.append("==============================================================================\n");
        for(int i=0;i<cluster2.size();i++)
        {
            String s=cluster2.get(i).trim().replaceAll("-->", ",");
            output.append(s.trim()).append("\n");
        }
        output.append("\n");

        output.append("==============================================================================\n");
        output.append("                                 Group - 3 (<=18)\n");
        output.append("==============================================================================\n");
        for(int i=0;i<cluster3.size();i++)
        {
            String s=cluster3.get(i).trim().replaceAll("-->", ",");
            output.append(s.trim()).append("\n");
        }
        output.append("\n");

        output.append("==============================================================================\n");
        output.append("                                 Group - 4 (>18)\n");
        output.append("==============================================================================\n");
        for(int i=0;i<cluster4.size();i++)
        {
            String s=cluster4.get(i).trim().replaceAll("-->", ",");
            output.append(s.trim()).append("\n");
        }
        output.append("\n");

        String firstphaseoutputString = output.toString();
        
        session.setAttribute("cluster1", cluster1);
        session.setAttribute("cluster2", cluster2);
        session.setAttribute("cluster3", cluster3);
        session.setAttribute("cluster4", cluster4);
        
        String facebookdataset=session.getAttribute("facebookdataset").toString().trim();
        request.setAttribute("facebookdataset", facebookdataset);
        request.setAttribute("firstphase", firstphaseoutputString);
        session.setAttribute("firstphase", firstphaseoutputString);
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
