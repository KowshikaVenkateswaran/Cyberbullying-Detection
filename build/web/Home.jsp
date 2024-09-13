<%-- 
    Document   : Home
    Created on : Nov 11, 2020, 1:26:15 PM
    Author     : SEABIRDS-PC
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Homepage</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
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
            <li><a class="selected" href="Home.jsp"><span>Home</span></a></li>            
        </ul>
    </div>
  <div id="wrapper">
    <div id="content">
       <h2 style="padding-left: 50px;">LOGIN</h2>
      <form class="form-signin" method="post" id="frm1">				
            <table>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>                                    
                <img src="log1.png" alt="Login" style="width:200px;height:200px;">
                <tr></tr>                                              
                <tr>
                    <td> User name</td> 
                    <td><input type="text" name="uname" required /></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>

                <tr>
                    <td> Password</td>
                    <td><input type="password" name="pass" required /></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td> <input type="button" value="Login" onclick="check1()"></td>                     
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
    <p>&copy; 2023 Risk assessment in social networks. All rights reserved.</p>
  </div>
</div>
</body>
</html>
