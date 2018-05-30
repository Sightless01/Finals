<?php
	session_start()
?>	
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width-device-width, initial-scale=1, shrink-to-fit-no">
		<link rel="stylesheet" href="../css/bootstrap-grid.min.css">
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="front.css">
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
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="navbar-nav mr-auto">
		<?php
			if (isset($_SESSION["siteuser"])) {
				$user = $_SESSION['siteuser'];
				echo '<a><?php echo $user ?></a>';
				echo '<a href="../index.php">Home</a></li>';
				echo '<a href="../addproduct.php">Add Product</a>';
				echo '<a href="requests.php">Pending Requests</a>';
				echo '<a href="transaction.php">Transactions</a>';
				echo '<a href="../logout.php">Logout</a>';
			} else {
				echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
			}
		?>	
		</ul>
	</div>
				
</nav>

<div class="container">
<?php
	include '../dbase.php';

	$conn = new mysqli($host, $username, $pass, $db);
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	$sql = "SELECT prod_id, products.comp_id, products.name, description, price, categories, frontview, sideview, backview, products.availability
		FROM products 
		JOIN company
		on products.comp_id = company.comp_id
		where company.name = '$user'" ;
		
	$result = $conn->query($sql);

	echo "PRODUCTS";
	echo "<div><table class='table table-responsive-md'>";
	echo "<td>Product Name:</td><td>Description</td><td>Price</td><td>Front</td><td>Side</td><td>Back</td><td>Status</td>";
		while($row = $result->fetch_assoc()){
			echo '<tr>';
			echo '<td>'.$row['name'] .'</td>
			<td>'.$row['description'] .'</td>
			<td>'.$row['price'] .'</td>
			<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';
				if($row['availability']==1){
					echo 'Currently Available';
					include 'unavail.php';
				} else if ($row['availability']==2){
					echo 'Removed by user';
					include 'avail.php';
				} else {
					echo 'Reserved';
				}
			echo '</td>
			<td>';?><a class="edit" id="edit" href="edit.php?edit_id=<?php echo $row['prod_id']; ?>" alt="edit" >Edit</a><?php echo '</td>
			</tr>';
		}
	echo "</table></div>";
?>

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
				<hr>
			</div>
		</footer>

		<script src="javaScript.js"></script>
		<script src="../js/bootstrap.min.js"></script>
</body>
</html>

