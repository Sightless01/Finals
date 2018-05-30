<?php
	session_start()
?>
<!DOCTYPE html>
<html lang="en">   
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width-device-width, initial-scale=1, shrink-to-fit-no">
        <link rel="stylesheet" href="css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="front/front.css">
        <Script type="text/javascript" src="#"></Script>
        <title>BrendoRENT Services</title>      
    </head>
<body>
	<header class="page-heading">
			<div class="contain">
				<div class="row">
					<div class="col-md-12">
						<h2>BrendoRENT Services</h2>
					</div>
				</div>
			</div>
		</header>
        
	<div class="container-login">
    	<form action="" method="POST">
        	<div class="form-group">
				<div class="head">
					<p>SERVICE PROVIDER</p>
				</div>
				<hr>
             	<label for="username" id="margin">Username </label>
                <input type="text" class="form-control" id="InputEmail" name="user">
            </div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="text" class="form-control" id="InputPassword" name="pass">
			</div>
            <button type="submit" name="submit" class="btn login">Log In</button>
			  
        	<?php  
				if(isset($_POST["submit"])){    
					if(!empty($_POST['user']) && !empty($_POST['pass'])) {  
						$user=$_POST['user'];  
						$pass=$_POST['pass'];  

						$servername = "localhost";
						$username = "root";
						$password = "";
						$dbname = "database";

						$conn = new mysqli($servername, $username, $password, $dbname);
						if ($conn->connect_error) {
							die("Connection failed: " . $conn->connect_error);
						} 

						$query=mysqli_query($conn, "SELECT username, password FROM company WHERE username = '$user'");  
						$result=mysqli_fetch_array($query);
						$numrows=mysqli_num_rows($query);
						$hashed = $result['password'];
						if($numrows!=0) {
							if(password_verify($pass, $hashed)){
								echo 'true';
								session_start();  
								$_SESSION['siteuser']=$user;  
								header("location:front/front.php");  
							}
						} else {  
							echo "Invalid username or password!";  
						}  

						} else {  
							echo "All fields are required!";  
						}  
					}  
				?>
		</form>
	</div>
	<footer class="footer">
		<div class="contain">
			<hr>
			<div class="row">
				<div class="col footer">
					<p>Web Systems and Technologies</p>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<p>&copy; Group 2. 2018</p>
				</div>
			</div>
		</div>
	</footer>
	
    <script src="javaScript.js"></script>
    <srcipt src="js/bootstrap.min.js"></srcipt>
</body>
</html>
