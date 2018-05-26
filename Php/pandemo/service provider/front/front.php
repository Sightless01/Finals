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
	$sql = "SELECT products.comp_id, products.name, desctription, categories, event, frontview, sideview, backview, products.availability
		FROM products 
		JOIN company
		on products.comp_id = company.comp_id
		where company.name = '$user'" ;
		
	$sqltr = "select transaction.client_id, transaction.prod_id, transaction.comp_id, client.name from transaction join company
			on company.comp_id = transaction.comp_id
			join client on client.client_id = transaction.client_id
			where company.name = '$user'; ";
	
	/*
		$cid = $result['comp_id'];
		$ps = $conn->prepare("INSERT INTO products (name, desctription, price, categories, event, frontview, sideview, backview, comp_id) VALUES (?,?,?,?,?,?,?,?,?)");
		$ps->bind_param("ssissssss", $pname, $desc, $price, $ctg, $event, $folderfront, $folderside, $folderback,$cid); */
	

	$result = $conn->query($sql);
	$tresult = $conn->query($sqltr);
	$trow = $tresult->fetch_assoc();

	echo "PRODUCTS";
	echo "<div><table class='table table-responsive-md'>";
	echo "<td>Product Name:</td><td>Description</td><td>Front</td><td>Side</td><td>Back</td><td>Status</td><td>Reserved to</td>";
		while($row = $result->fetch_assoc()){
			if($ps = $conn->prepare("SELECT transaction.client_id, transaction.prod_id, transaction.comp_id, client.name FROM 
						transaction JOIN company ON company.comp_id = transaction.comp_id
						JOIN client on client.client_id = transaction.client_id
						where company.username = ? and transaction.prod_id = ?")){
							$ps->bind_param("ss", $user, $row['prod_id']);
							$ps->execute();
							$ps->bind_result($col1, $col2, $col3, $col4);
							$ps->fetch();
							echo $row['prod_id'];
							echo $col1, $col2, $col3, $col4;
						}
			echo '<tr><td>'.$row['name'] .'</td><td>'.$row['desctription'] .'</td>
				<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
				<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
				<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
				<td>';?> <?php 
					if($row['availability'] == 1) { 
						echo 'Product is still available! </td>'; 
					} else {
						echo 'Product is currently reserved. </td>';
						echo '<td>' . $trow['name']. '</td>';
					}
				echo '</tr>'; 
		}
	echo "</table></div>";
?>

</div>
	<footer class="footer-site">
			<p>MeHoney Service Provider Module</p>
	</footer>
</body>
</html>

