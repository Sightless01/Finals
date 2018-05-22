<?php
	session_start()
?>	
<!DOCTYPE html>
<html>

	<body>
	<form action="" method="POST">  
		Username: <input type="text" name="user"><br />  
		Password: <input type="password" name="pass"><br />   
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
						
						$query=mysqli_query($conn, "SELECT * FROM company WHERE username='$user' AND password='$pass'");  

						$numrows=mysqli_num_rows($query);;  
						
						if($numrows!=0)  
						{   
							session_start();  
							$_SESSION['siteuser']=$user;  

							header("location:front/front.php");  
						} else {  
						echo "Invalid username or password!";  
						}  
					  
					} else {  
						echo "All fields are required!";  
					}  
				}  
			?>  
	</form>
	</body>
</html>