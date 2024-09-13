<%-- 
    Document   : ComparisonGraph
    Created on : Feb 15, 2023, 10:23:33 AM
    Author     : SEABIRDS-PC
--%>

<%@page import="org.jfree.ui.TextAnchor"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Paint"%>
<%@page import="java.awt.GradientPaint"%>
<%@page import="java.awt.Color"%>
<%@ page import="org.jfree.chart.*" %>
<%@ page import="org.jfree.chart.axis.*" %>
<%@ page import="org.jfree.chart.entity.*" %>
<%@ page import="org.jfree.chart.labels.*" %>
<%@ page import="org.jfree.chart.plot.*" %>
<%@ page import="org.jfree.chart.renderer.category.*" %>
<%@ page import="org.jfree.chart.urls.*" %>
<%@ page import="org.jfree.data.category.*" %>
<%@ page import="org.jfree.data.general.*" %>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Comparison Graph</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />

<style type="text/css">
<%
 
        HttpSession sn = request.getSession();
        String svmaccuracy = sn.getAttribute("svmaccuracy").toString().trim();
        String svmprecision = sn.getAttribute("svmprecision").toString().trim();
        String svmrecall = sn.getAttribute("svmrecall").toString().trim();
        String svmf1score = sn.getAttribute("svmf1score").toString().trim();

        String intntnaccuracy = sn.getAttribute("intntnaccuracy").toString().trim();
        String intntnprecision = sn.getAttribute("intntnprecision").toString().trim();
        String intntnrecall = sn.getAttribute("intntnrecall").toString().trim();
        String intntnf1score = sn.getAttribute("intntnf1score").toString().trim();

        String lraccuracy = sn.getAttribute("lraccuracy").toString().trim();
        String lrprecision = sn.getAttribute("lrprecision").toString().trim();
        String lrrecall = sn.getAttribute("lrrecall").toString().trim();
        String lrf1score = sn.getAttribute("lrf1score").toString().trim();

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
                    

    JFreeChart chart = null;
    BarRenderer renderer = null;
    CategoryPlot plot = null;

    final CategoryAxis categoryAxis = new CategoryAxis("Algorithm");
    final ValueAxis valueAxis = new NumberAxis("Execution Time (in ms)");
    renderer = new BarRenderer();

    plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
    renderer);
    plot.setOrientation(PlotOrientation.VERTICAL);
    chart = new JFreeChart("Comparison Graph", JFreeChart.DEFAULT_TITLE_FONT, 
    plot, true);

    chart.setBackgroundPaint(new Color(249, 231, 236));        

    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    renderer.setBaseItemLabelsVisible(true);
    ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
            TextAnchor.TOP_CENTER);
    renderer.setBasePositiveItemLabelPosition(position);


    Paint p1 = new GradientPaint(
   0.0f, 0.0f, new Color(16, 89, 172), 0.0f, 0.0f, new Color
     (201, 201, 244));

    renderer.setSeriesPaint(1, p1);

    Paint p2 = new GradientPaint(
    0.0f, 0.0f, new Color(255, 35, 35), 0.0f, 0.0f, new Color
     (255, 180, 180));

    renderer.setSeriesPaint(2, p2);

    plot.setRenderer(renderer);

    try {
    final ChartRenderingInfo info = new ChartRenderingInfo
     (new StandardEntityCollection());

      ServletContext sc = this.getServletContext();
      String sg1=sc.getRealPath("/");
      String fname=sg1.substring(0,sg1.indexOf("build"));                    
      String localfilename=fname+"web/barchart.png";

   final File file1 = new File(localfilename);
    ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
    } catch (Exception e) {
   out.println(e);
    }
  
%>
</style>

</head>
<body>  
    <script>        
        function check1()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./Login";
            ff.submit();            
        }
    </script>
<div id="container">
<div id="header"><h1><a href="#">RISK ASSESSMENT IN SOCIAL NETWORKS</a></h1></div>
<div id="tabs">
        <ul>
            <li><a class="selected" href="ComparisonGraph.jsp"><span>Comparison Graph</span></a></li> 
            <li><a href="Home.jsp"><span>Logout</span></a></li>
        </ul>
    </div>
  <div id="wrapper">
    <div id="content" style="margin-left: -40px;">       
      <form class="form-signin" method="post" id="frm1">				
            <table>                                                                                     
                <tr>
                    <td><p></p></td>
                    <td> <img src="barchart.png" alt="Comparison Graph"></img></td>                    
                </tr>  
                <tr><td>&nbsp;</td></tr>
                
            </table>
        </form>
    </div>
  </div>      
  
  <div id="footer">
    <p>&copy; 2023 Risk assessment in social networks. All rights reserved.</p>
  </div>
</div>
</body>
</html>

