<%-- 
    Document   : UserHome
    Created on : Nov 11, 2020, 4:21:30 PM
    Author     : SEABIRDS-PC
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>User Home</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>  
    <script>        
        function check1()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./AgeCalculator";
            ff.submit();            
        }
    </script>
<div id="container">
<div id="header"><h1><a href="#">ERROR TRACKING DASHBOARD</a></h1></div>
<div id="tabs">
        <ul>
            <li><a class="selected" href="UserHome.jsp"><span>Home</span></a></li>
            <li><a href="App2.jsp"><span>Application 2</span></a></li>
            <li><a href="Home.jsp"><span>Logout</span></a></li>
        </ul>
    </div>
  <div id="wrapper" style="margin-left: -150px;">
    <div id="content">
       <h2 style="padding-left: 50px;">Application 1: Age Calculator</h2>
      <form class="form-signin" method="post" id="frm1">				
            <table>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>                                    
                <img src="ageCalculator.png" alt="Login" style="width:600px;height:200px;">
                <tr></tr>                                              
                <tr>
                    <td> Date of Birth</td> 
                    <td><input type="text" name="date1" placeholder="Enter Date"/></td>
                    <td><input type="text" name="month1" placeholder="Enter Month"/></td>
                    <td><input type="text" name="year1" placeholder="Enter Year"/></td>
                </tr>
                <tr>&nbsp;</tr>
                <tr>
                    <td> Age at the Date of</td> 
                    <td><input type="text" name="date2" placeholder="Enter Date"/></td>
                    <td><input type="text" name="month2" placeholder="Enter Month"/></td>
                    <td><input type="text" name="year2" placeholder="Enter Year"/></td>
                </tr>
                <tr>&nbsp;</tr>                                
                <tr>
                    <td> <input type="button" value="Calculate" onclick="check1()"></td>                     
                </tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>															
            </table>
        </form>
    </div>
  </div>      
  
  <div id="footer">
    <p>&copy; 2020 Error Tracking Dashboard. All rights reserved.</p>
  </div>
</div>
</body>
</html>
