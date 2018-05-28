<?php
	session_start()
?>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>BrendoRent Transaction History</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="login.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

<body>
<div class="navbar">    
	<?php
		if (isset($_SESSION["siteuser"])) {
			echo '<a href="../logout.php">Logout</a>';
			echo '<a href="../addproduct.php">Add Product</a>';
			$user = $_SESSION["siteuser"];
		} else {
			echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
		}
	?>	
  <a href="../index.php">Home</a></li>
</div>
<h2>Transaction History</h2>
<?php
	$host = "localhost";
	$username = "root";
	$pass = "";
	$db = "database";

	$con = new mysqli($host, $username, $pass, $db);
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
	
	$sql = "SELECT * 
			FROM transaction 
			JOIN products
			ON transaction.prod_id = products.prod_id
			JOIN company
			ON products.comp_id = company.comp_id
			WHERE company.name = '$user';";
			
			echo "<table class='table table-responsive-md'><tr>
			<td>client_id</td>
			<td>prod_id</td>
			<td>date_paid</td>
			<td>date_returned</td>
			</tr>";
			
	$result=mysqli_query($con,$sql);
	
	while ($row = mysqli_fetch_array($result, MYSQLI_BOTH)) {
		echo '<tr>';
		echo '<td>'.$row ['client_id'] . '</td>
		<td>'.$row['prod_id'] .'</td>
		<td>';if(is_null($row['date_paid'])){
				echo 'Manually Edit: ';
				$column = 'date_paid';
				include 'editTx.php';
			} else {
				echo $row['date_paid'];
			}
		echo '</td>
		<td>';
			if(is_null($row['date_returned'])){
				echo 'Input: ';
				$column = 'date_returned';
				include 'editTx.php';
			} else {
				echo $row['date_returned'];
			}
		echo '</td>
		</tr>';
	}
	$con->close();
?>
</body>
</html>
