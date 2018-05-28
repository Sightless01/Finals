<?php
	session_start();
?>	
<!DOCTYPE html>
<html>
<head>
  <title>BrendoRent Company Services</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front/front.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
	<body>
		<div container>
			<div class="navbar">    
				<?php
					if (isset($_SESSION["siteuser"])) {
						echo '<a href="logout.php">Logout</a></li>';
						echo '<a href="addproduct.php">Add Product</a></li>';
						echo '<a href="front/front.php">Check Products</a></li>';
						echo '<a href="front/transaction.php">Transactions</a>';
						$user = $_SESSION['siteuser'];
						echo '<a>Welcome, ' .$user .'!</a>';
					} else {
						echo '<a href="login.php">Log In</a>';
						echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
					}
				?>	
			</div>
				<p>
					<h1>Welcome to the BrendoRent Company Services Site!</h1>
					<h3>Need a spot to put up your products?</h3>
					<h3>Want a convenient place to view current orders?</h3>
					<h3>You've come to the right place!</h3>
					<?php
						if (isset($_SESSION["siteuser"])) {
							echo '<a href="front/front.php"><h2>Check all my products</h2></a>';
						} else {
							echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018"><h2>Register Now!</h2></a>';
						}
					?>
				</p>
				<footer class="footer-site">
						<p>BrendoRent Service Provider Module</p>
				</footer>
		</div>	
	</body>
</html>