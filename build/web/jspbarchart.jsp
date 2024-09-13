<%-- 
    Document   : jspbarchart
    Created on : Nov 11, 2020, 7:32:25 PM
    Author     : SEABIRDS-PC
--%>


<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="ErrorTrackingDashboard.DBConnection"%>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>

<%
 
    DBConnection dbn=new DBConnection();
    Statement st=dbn.stt;
    
    DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

    try
    {
        ArrayList exceptionTypes=new ArrayList();
        ArrayList norep=new ArrayList();
        
        ResultSet rs=st.executeQuery("select * from exceptiontypes");                     
        while(rs.next())
        {                        
            String extype=rs.getString(2);
            exceptionTypes.add(extype);  
            if(!(norep.contains(extype)))
            {
                norep.add(extype);
            }
        }
        
        for(int i=0;i<norep.size();i++)
        {
            String extyp=norep.get(i).toString().trim();
            int count=Collections.frequency(exceptionTypes, extyp);
            dataset.addValue((double)count, extyp, extyp);
        }                            
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }      

  JFreeChart chart = null;
  BarRenderer renderer = null;
  CategoryPlot plot = null;


  final CategoryAxis categoryAxis = new CategoryAxis("Exception Type");
  final ValueAxis valueAxis = new NumberAxis("Count");
  renderer = new BarRenderer();

  plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
  renderer);
  plot.setOrientation(PlotOrientation.VERTICAL);
  chart = new JFreeChart("Error Tracking Graph", JFreeChart.DEFAULT_TITLE_FONT, 
  plot, true);

  chart.setBackgroundPaint(new Color(249, 231, 236));

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
    System.out.println("localfilename: "+localfilename);
    
    File file2 = new File(localfilename);
    file2.delete();
  
 final File file1 = new File(localfilename);
  ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
  } catch (Exception e) {
 out.println(e);
  }
  
%>



<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" 
   content="text/html; charset=UTF-8" >
  <meta  http-equiv="refresh" content="1">
  <title>JSP Page</title>
  </head>
  
  <body>
  <IMG SRC="barchart.png?time=<%=System.currentTimeMillis()%>" WIDTH="600" 
                             HEIGHT="400" BORDER="0" USEMAP="#chart"></IMG>
      
  </body>
</html>