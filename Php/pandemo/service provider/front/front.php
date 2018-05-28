<?php
	session_start()
?>	
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Service Provider Landing Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar">    
	<?php
		if (isset($_SESSION["siteuser"])) {
			echo '<a href="../logout.php">Logout</a></li>';
			echo '<a href="../addproduct.php">Add Product</a></li>';
			echo '<a href="transaction.php">Transactions</a>';
			$user = $_SESSION['siteuser'];
		} else {
			echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
		}
	?>	
  <a href="../index.php">Home</a></li>
 <a><?php echo $user ?></a>
</div>

<div id="home">
<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "database";

	$conn = new mysqli($servername, $username, $password, $dbname);
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
	echo "<td>Product Name:</td><td>Description</td><td>Front</td><td>Side</td><td>Back</td><td>Status</td>";
		while($row = $result->fetch_assoc()){
			echo '<tr>';
			echo '<td>'.$row['name'] .'</td>
			<td>'.$row['description'] .'</td>
			<td>'.$row['price'] .'</td>
			<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><a href="edit.php?edit_id=<?php echo $row['prod_id']; ?>" alt="edit" >Edit</a><?php echo '</td>
			</tr>';
		}
	echo "</table></div>";
?>

</div>
	<footer class="footer-site">
			<p>MeHoney Service Provider Module</p>
	</footer>
</body>
</html>

