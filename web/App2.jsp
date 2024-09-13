<%-- 
    Document   : App2
    Created on : Nov 11, 2020, 6:22:19 PM
    Author     : SEABIRDS-PC
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Application 2</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>  
    <script>        
        function check1()
        {            
            var ff=document.getElementById("frm1");        
            ff.action="./FDCalculator";
            ff.submit();            
        }
    </script>
<div id="container">
<div id="header"><h1><a href="#">ERROR TRACKING DASHBOARD</a></h1></div>
<div id="tabs">
        <ul>
            <li><a href="UserHome.jsp"><span>Home</span></a></li>
            <li><a class="selected" href="App2.jsp"><span>Application 2</span></a></li>
            <li><a href="Home.jsp"><span>Logout</span></a></li>
        </ul>
    </div>
  <div id="wrapper" style="margin-left: -150px;">
    <div id="content">
       <h2 style="padding-left: 50px;">Application 2: Fixed Deposit Calculator</h2>
      <form class="form-signin" method="post" id="frm1">				
            <table>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>                                    
                <img src="fd.jpeg" alt="Login" style="width:600px;height:400px;">
                <tr></tr>                                              
                <tr>
                    <td> Total Investment</td> 
                    <td><input type="text" name="ti" placeholder="Enter Total Investment"/></td>                    
                </tr>
                <tr>&nbsp;</tr>
                <tr>
                    <td> Rate of Interest Per Annum (in %)</td> 
                    <td><input type="text" name="ri" placeholder="Enter Rate of Interest"/></td>                    
                </tr>
                <tr>&nbsp;</tr>    
                <tr>
                    <td> Time Period (in years)</td> 
                    <td><input type="text" name="tp" placeholder="Enter Time Period"/></td>                    
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
