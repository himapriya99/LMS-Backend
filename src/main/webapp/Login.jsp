<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
   <h1 style = "text-align :center ;margin-left :auto ;margin-right:auto"> LMS Admin Login </h1>  
    <form action="AdminControllerServlet" method="POST">  
        <div class="container" style = "text-align :center ;margin-left :auto ;margin-right:auto">   
        	<input type="hidden" name="command" value="LOGIN" />
            <label>Username : </label>   
            <input type="text" placeholder="Enter Username" name="username" required>  
            <br/> <br/>
            <label>Password : </label>   
            <input type="password" placeholder="Enter Password" name="password" required>  
            <br/> <br/>
            <button type="submit">Login</button>   
        </div>   
    </form>     


</body>
</html>