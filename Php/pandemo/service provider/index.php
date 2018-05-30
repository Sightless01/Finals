<?php
	session_start();
?>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width-device-width, initial-scale=1, shrink-to-fit-no">
		<link rel="stylesheet" href="css/bootstrap-grid.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="front/front.css">
		<script type="text/javascript" src="#"></script>
		<title>BrendoRENT</title>
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
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
		  	</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="navbar-nav mr-auto">
					<?php
						if (isset($_SESSION["siteuser"])) {
							echo '<a href="logout.php">Logout</a>';
							echo '<a href="addproduct.php">Add Product</a>';
							echo '<a href="front/front.php">Check Products</a>';
							$user = $_SESSION['siteuser'];
							echo '<a>Welcome, ' .$user .'!</a>';
						} else {
							echo '<a href="login.php">Log In</a>';
							echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
						}
					?>	
				</ul>
			</div>
    	</nav>
	
		
		
		<div class="content">
			<div class="image">
				<img class=" mb-5 d-block" src="css/logo.png" alt="" width="300px" height="300px">
			</div>
			<div class="container">
        		<h4 class="text-uppercase">Welcome to the BrendoRENT Services Site!</h4>
        		<hr class="star-light">
        		<h4 class="font-weight-light ">Need a spot to put up your products?</h4>
				<h4 class="font-weight-light">Want a convenient place to view current orders?</h4>
				<h4 class="font-weight-light">You've come to the right place!</h4>
      		</div>
			<?php
				if (isset($_SESSION["siteuser"])) {
					echo '<a href="front/front.php"><h2>Check all my products</h2></a>';
				} else {
					echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018"><h2>Register Now!</h2></a>';
				}
			?>
		
			<br>
			<br>
			<br>
			<br>
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
		</footer></div>
		
		<script src="javaScript.js"></script>
		<script src="js/bootstrap.min.js"></script>
			</body>

</html>