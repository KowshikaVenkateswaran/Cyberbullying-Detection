/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author SEABIRDS-PC
 */
@WebServlet(name = "ComparisonGraph", urlPatterns = {"/ComparisonGraph"})
public class ComparisonGraph extends HttpServlet {

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
        String svmaccuracy = session.getAttribute("svmaccuracy").toString().trim();
        String svmprecision = session.getAttribute("svmprecision").toString().trim();
        String svmrecall = session.getAttribute("svmrecall").toString().trim();
        String svmf1score = session.getAttribute("svmf1score").toString().trim();

        String intntnaccuracy = session.getAttribute("intntnaccuracy").toString().trim();
        String intntnprecision = session.getAttribute("intntnprecision").toString().trim();
        String intntnrecall = session.getAttribute("intntnrecall").toString().trim();
        String intntnf1score = session.getAttribute("intntnf1score").toString().trim();

        String lraccuracy = session.getAttribute("lraccuracy").toString().trim();
        String lrprecision = session.getAttribute("lrprecision").toString().trim();
        String lrrecall = session.getAttribute("lrrecall").toString().trim();
        String lrf1score = session.getAttribute("lrf1score").toString().trim();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(Double.parseDouble(svmaccuracy), "Accuracy", "SVM With RF");
        dataset.addValue(Double.parseDouble(intntnaccuracy), "Accuracy", "Intention Model");
        dataset.addValue(Double.parseDouble(lraccuracy), "Accuracy", "Linear Regression");

        dataset.addValue(Double.parseDouble(svmprecision), "Precision", "SVM With RF");
        dataset.addValue(Double.parseDouble(intntnprecision), "Precision", "Intention Model");
        dataset.addValue(Double.parseDouble(lrprecision), "Precision", "Linear Regression");

        dataset.addValue(Double.parseDouble(svmrecall), "Recall", "SVM With RF");
        dataset.addValue(Double.parseDouble(intntnrecall), "Recall", "Intention Model");
        dataset.addValue(Double.parseDouble(lrrecall), "Recall", "Linear Regression");

        dataset.addValue(Double.parseDouble(svmf1score), "F-measure", "SVM With RF");
        dataset.addValue(Double.parseDouble(intntnf1score), "F-measure", "Intention Model");
        dataset.addValue(Double.parseDouble(lrf1score), "F-measure", "Linear Regression");
        
                // create the chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Performance Metrics", // chart title
            "Classifier", // domain axis label
            "Score", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            true, // include legend
            true, // tooltips
            false // urls
        );

        // set the background color for the chart
        chart.setBackgroundPaint(java.awt.Color.white);

        // set the font for the title and axis labels
        Font titleFont = new Font("SansSerif", Font.BOLD, 18);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        chart.getTitle().setFont(titleFont);
        chart.getLegend().setItemFont(labelFont);

        CategoryPlot plot = chart.getCategoryPlot();

        // set the background color for the plot area
        plot.setBackgroundPaint(java.awt.Color.lightGray);

        // set the range gridline color
        plot.setRangeGridlinePaint(java.awt.Color.black);

        // set the axis labels font
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);

        // customize the renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // set the series colors
        Paint[] colors = new Paint[] {
            java.awt.Color.green, java.awt.Color.blue, java.awt.Color.red, java.awt.Color.orange
        };
        for (int i = 0; i < colors.length; i++) {
            renderer.setSeriesPaint(i, colors[i]);
        }

        // set the axis values font
        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelFont(labelFont);

        // set the axis values
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        domainAxis.setTickLabelFont(labelFont);

        // create and show the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<div>");
        out.println("<h2>Performance Metrics</h2>");
        out.println("</div>");
        out.println("<div>");
        out.println(chartPanel);
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");        
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
