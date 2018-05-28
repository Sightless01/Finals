<?php
	session_start()
?>	
<!DOCTYPE html>
<html>
<head>
  <title>BrendoRent Company Services Login</title>
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
		<?php
			if (isset($_SESSION["siteuser"])) {
				echo '<a href="logout.php">Logout</a></li>';
				$user = $_SESSION['siteuser'];
			} else {
				echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
			}
		?>	
	<a href="index.php">Home</a></li>
	<a href="front/requests.php">Pending Requests</a>
	<a href="front/transaction.php">Transactions</a>
	<a><?php echo 'Adding Products for User: ' . $user ?></a>
	</div>
		<form action="" method="POST" enctype="multipart/form-data">
				<div class="form-group">
					<label for="pname">Product Name: </label>
					<input type="text" name="pname" class="form-control input-sm" required>
				</div>
				<div class="form-group">
					<label for="username">Description: </label>
					<textarea name="desc" class="form-control" rows="3"></textarea>
				</div>
				<div class="form-group">
					<label for="username">Rent base cost (Php): </label>
					<input type="text" name="cost" class="form-control input-sm" required>
				</div>
				<div class="form-group">
					<label for="username">Category: </label>
					<input type="text" name="categ" class="form-control input-sm" required>
				</div>
				<div class="form-group">
					<label for="username">Event type: </label>
					<input type="text" name="event" class="form-control input-sm">
				</div>
				<div class="form-group">
					<p>Upload product images (Optional - up to three):</p>
					<label for="username">Front: </label>
					<input type="file" name="front" class="form-control-file">
					<label for="username">Side: </label>
					<input type="file" name="side" class="form-control-file">
					<label for="username">Back: </label>
					<input type="file" name="back" class="form-control-file">
				</div>
				<input type="submit" value="Add Product" name="submit">
		</form>
	
	<?php
		if(isset($_POST["submit"])){    
						$pname=$_POST['pname'];  
						$desc=$_POST['desc'];  
						$price=$_POST['cost'];
						$ctg=$_POST['categ'];
						$event=$_POST['event'];
						$user = $_SESSION['siteuser'];
						
						if(isset($_FILES['front']) or isset($_FILES['back']) or isset($_FILES['side'])){
							$filefront = $_FILES["front"]["name"];
							$tempfront = $_FILES["front"]["tmp_name"];
							$folderfront = '/image/'.$user.'/'.$filefront;
							move_uploaded_file($tempfront, '../image/'.$user.'/'.$filefront);
							
							$fileside = $_FILES["side"]["name"];
							$tempside = $_FILES["side"]["tmp_name"];
							$folderside = '/image/'.$user.'/'.$fileside;
							move_uploaded_file($tempside, '../image/'.$user.'/'.$fileside);
							
							$fileback = $_FILES["back"]["name"];
							$tempback = $_FILES["back"]["tmp_name"];
							$folderback = '/image/'.$user.'/'.$fileback;
							move_uploaded_file($tempback, '../image/'.$user.'/'.$fileback);
						}

						include 'dbase.php';
						
						$conn = new mysqli($servername, $username, $password, $dbname);
						if ($conn->connect_error) {
							die("Connection failed: " . $conn->connect_error);
						} 
						
						$query = mysqli_query($conn, "SELECT company.comp_id, company.name
						FROM products 
						JOIN company
						on products.comp_id = company.comp_id
						where company.name = '$user'");
						
						$result=mysqli_fetch_array($query);
						$cid = $result['comp_id'];
						$ps = $conn->prepare("INSERT INTO products (name, desctription, price, categories, event, frontview, sideview, backview, comp_id) VALUES (?,?,?,?,?,?,?,?,?)");
						$ps->bind_param("ssissssss", $pname, $desc, $price, $ctg, $event, $folderfront, $folderside, $folderback,$cid);

						$ps->execute();
						$ps->close();
						$conn->close();
					}
		
	?>
		<footer class="footer-site">
			<p>BrendoRent Service Provider Module</p>
		</footer>
	</div>
</body>
</html>