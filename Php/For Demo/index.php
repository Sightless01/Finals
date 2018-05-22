<?php
	session_start();
?>	
<!DOCTYPE html>

<html>
<head>
  <title>Service Provider Landing Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front/front.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
	<body>
		<div class="navbar">    
			<?php
				if (isset($_SESSION["siteuser"])) {
					echo '<a href="logout.php">Logout</a></li>';
				} else {
					echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
				}
			?>	
		  <a href="#">Notification</a></li>
		  <a href="#">Home</a></li>
		</div>
		
	</body>
</html>