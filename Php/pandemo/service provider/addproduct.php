<?php
	session_start()
?>	
<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width-device-width, initial-scale=1, shrink-to-fit-no">
        <link rel="stylesheet" href="css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="front/front.css">
        <Script type="text/javascript" src="#"></Script>
        <title>BrendoRENT Services</title>      
    </head>
	<header class="page-heading">
		<div class="contain">
			<div class="row">
				<div class="col-md-12">
					<h2>BrendoRENT Services</h2>
				</div>
			</div>
		</div>
	</header>	
	<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="navbar-nav mr-auto">
				<?php
					if (isset($_SESSION["siteuser"])) {
						$user = $_SESSION['siteuser'];
						echo '<a>Adding Products for User: ' . $user.'</a>';
						echo '<a href="index.php">Home</a></li>';
						echo '<a href="front/front.php">Products</a>';
						echo '<a href="front/transaction.php">Transactions</a>';
						echo '<a href="front/requests.php">Pending Requests</a>';
						echo '<a href="logout.php">Logout</a></li>';
					} else {
						echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
					}
				?>	
			</ul>
		</div>
	</nav>
		
		<div class="container-product">
			<form action="" method="POST" enctype="multipart/form-data">
				<div class="addProduct">
					<label for="pname">Product Name: </label>
					<input type="text" name="pname" class="form-control input-sm" required>
				</div>
				<div class="addProduct">
					<label for="username">Description: </label>
					<textarea name="desc" class="form-control" rows="3"></textarea>
				</div>
				<div class="addProduct">
					<label for="username">Rent base cost (Php): </label>
					<input type="text" name="cost" class="form-control input-sm" required>
				</div>
				<div class="addProduct">
					<label for="username">Category: </label>
					<input type="text" name="categ" class="form-control input-sm" required>
				</div>
				<div class="addProduct">
					<p>Upload product images (Optional - up to three):</p>
					<label for="username">Front: </label>
					<input type="file" name="front" class="form-control-file">
					<label for="username">Side: </label>
					<input type="file" name="side" class="form-control-file">
					<label for="username">Back: </label>
					<input type="file" name="back" class="form-control-file">
				</div>
				<div class="add">
					<input type="submit" value="Add Product" name="submit" onclick="update()">
				</div>
			</form>
		
		<?php
			if(isset($_POST["submit"])){    
							$pname=$_POST['pname'];  
							$desc=$_POST['desc'];  
							$price=$_POST['cost'];
							$ctg=$_POST['categ'];
							$user = $_SESSION['siteuser'];
							$avail = 1;
							
							if(isset($_FILES['front']) or isset($_FILES['back']) or isset($_FILES['side'])){
								$filefront = $_FILES["front"]["name"];
								$tempfront = $_FILES["front"]["tmp_name"];
								$folderfront = 'http://database:2018/image/'.$user.'/'.$filefront;
								move_uploaded_file($tempfront, 'image/'.$user.'/'.$filefront);
								
								$fileside = $_FILES["side"]["name"];
								$tempside = $_FILES["side"]["tmp_name"];
								$folderside = 'http://database:2018/image/'.$user.'/'.$fileside;
								move_uploaded_file($tempside, 'image/'.$user.'/'.$fileside);
								
								$fileback = $_FILES["back"]["name"];
								$tempback = $_FILES["back"]["tmp_name"];
								$folderback = 'http://database:2018/image/'.$user.'/'.$fileback;
								move_uploaded_file($tempback, 'image/'.$user.'/'.$fileback);
							}

							include 'dbase.php';
							
							$conn = new mysqli($host, $username, $pass, $db);
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
							$ps = $conn->prepare("INSERT INTO products (name, description, price, categories, frontview, sideview, backview, availability, comp_id) VALUES (?,?,?,?,?,?,?,?,?)");
							$ps->bind_param("ssissssis", $pname, $desc, $price, $ctg, $folderfront, $folderside, $folderback, $avail, $cid);

							$ps->execute();
							$ps->close();
							$conn->close();
						}
			
		?>
		</div>
	<script>
		function update(){
		 var x;
		 if(confirm("Product Added!") == true){
			x= "update";
		 }
		}
	</script>
	</body>
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
</html>