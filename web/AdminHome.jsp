<%-- 
    Document   : AdminHome
    Created on : Nov 11, 2020, 5:59:08 PM
    Author     : SEABIRDS-PC
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Admin Home</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />

<style type="text/css">
<%            
    HttpSession sn=request.getSession(true);    
%>
</style>
</head>
<body>  
    <script>        
        function check1()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./LoadFaceBookDataset";
            ff.submit();            
        }
        
        function check2()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./FirstPhase";
            ff.submit();            
        }
        
        function check3()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./SecondPhase";
            ff.submit();            
        }
        
        function check4()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./TrainingTesting";
            ff.submit();            
        }
        
        function check5()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./SVMWithRF";
            ff.submit();            
        }
        
        function check6()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./IntentionModel";
            ff.submit();            
        }
        
        function check7()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./LinearRegression";
            ff.submit();            
        }
        
        function check8()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./ComparisonGraph.jsp";
            ff.submit();            
        }
    </script>        
    
<div id="container">
<div id="header"><h1><a href="#">RISK ASSESSMENT IN SOCIAL NETWORKS</a></h1></div>
<div id="tabs">
        <ul>
            <li><a class="selected" href="AdminHome.jsp"><span>Home</span></a></li>
            <li><a href="Home.jsp"><span>Logout</span></a></li>
        </ul>
    </div>
  <div id="wrapper" style="margin-left: -165px;">
    <div id="content">
       <h2 style="padding-left: 50px;">Load Facebook Dataset</h2>
      <form class="form-signin" method="post" id="frm1">				
            <table>                                                    
                <img src="exception.jpg" alt="Login" style="width:400px;height:200px; margin-left: 30px;">  
                <tr>&nbsp;</tr>
                <tr>&nbsp;</tr>
            </table>
                           
            <div>
                <table>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr>
                    <td> <input type="button" value="Load Facebook Dataset" onclick="check1()"></td>                     
                </tr>
                
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="facebookdataset" id="facebookdataset">${facebookdataset}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td><h1>Two phase risk assesment</h1></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td> <input type="button" value="GIP = Group Identification Phase" onclick="check2()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="firstphase" id="firstphase">${firstphase}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                
                <tr>
                    <td> <input type="button" value="RAP = Risk Assesment Phase" onclick="check3()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="secondphase" id="secondphase">${secondphase}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><h1>Generate Training and Testing Datasets</h1></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td> <input type="button" value="Generate Training and Testing Datasets" onclick="check4()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr> 
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="trainingdataset" id="trainingdataset">${trainingdataset}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>                
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="testingdataset" id="testingdataset">${testingdataset}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td> <input type="button" value="SVM with Radial basis function based Classification and Prediction" onclick="check5()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="svmwithrfResults" id="svmwithrfResults">${svmwithrfResults}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                
                <tr>
                    <td> <input type="button" value="Intention Model based Classification and Prediction" onclick="check6()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="intentionmodelResults" id="intentionmodelResults">${intentionmodelResults}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                                
                <tr>
                    <td> <input type="button" value="Linear Regression based Classification and Prediction" onclick="check7()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr>                                  
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>
                        <textarea rows="20" cols="80" name="linearregressionResults" id="linearregressionResults">${linearregressionResults}</textarea>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>
                
                <tr>
                    <td> <input type="button" value="View Comparison Graph" onclick="check8()"></td>                     
                </tr>
                <tr><td>&nbsp;</td></tr> 
                <tr><td>&nbsp;</td></tr>                
            </table>
            </div>
                    
        </form>
    </div>
  </div>      
  
  <div id="footer">
    <p>&copy; 2023 Risk assessment in social networks. All rights reserved.</p>
  </div>
</div>
</body>
</html>
