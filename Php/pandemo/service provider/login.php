<?php
	session_start()
?>	
<!DOCTYPE html>
<html>
<head>
  <title>Mehoney Company Services Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front/login.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
	<body>
	<div class="container">
			<div class="navbar">    
			</div>
		<form action="" method="POST"> 
			<div class="form-group">
				<label for="username">Username: </label>
				<input type="text" name="user" class="form-control input-sm">
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" name="pass" class="form-control input-sm">
			</div>
			<input type="submit" value="Login" name="submit" />  
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
							if($numrows!=0)  
							{
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
		<div class="footer-site">	
			<footer class="footer-site">
				<p>MeHoney Service Provider Module</p>
			</footer>
		</div>
	</div>
	</body>
</html>