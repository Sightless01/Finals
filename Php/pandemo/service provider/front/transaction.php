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
						$user = $_SESSION["siteuser"];
						echo '<a href="../index.php">Home</a>';
						echo '<a href="front.php">Products</a>';
						echo '<a href="../addproduct.php">Add Product</a>';
						echo '<a href="requests.php">Pending Requests</a>';
						echo '<a href="../logout.php">Logout</a>';
					} else {
						echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
					}
				?>	
			</ul>
		</div>
	</nav>
<h2>Transaction History</h2>
<?php
	include '../dbase.php';
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
