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
						echo '<a href="front.php">Products</a>';
						echo '<a href="../addproduct.php">Add Product</a>';
						echo '<a href="transaction.php">Transactions</a>';
						echo '<a href="../logout.php">Logout</a>';
					} else {
						echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
					}
				?>
			</ul>
		</div>
	</nav>
<?php
	echo '<h2>Current Rent Requests for '.$user.'</h2>';
	include '../dbase.php';

	$con = new mysqli($host, $username, $pass, $db);
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
	
	$sql = "SELECT request.client_id, request.prod_id, client.name as cname, products.name as pname, start_date, end_date, request.status, request_date 
			FROM request
			JOIN products
			ON request.prod_id = products.prod_id
			JOIN company
			ON products.comp_id = company.comp_id
			JOIN client
			ON client.client_id = request.client_id
			WHERE company.name = '$user';";
	
	
	$result=mysqli_query($con,$sql);

	if($result){
		echo "<table class='table table-responsive-md'><tr>
			<td>Client</td>
			<td>Product</td>
			<td>Request Start Date</td>
			<td>Request End Date</td>
			<td>Status</td>
			<td>Request Creation Date</td>
			</tr>";
		while ($row = mysqli_fetch_assoc($result)) {
			echo '<tr>';
			echo '<td>'.$row ['cname'] . '</td>
			<td>'. $row['pname'].'</td>
			<td>'.$row['start_date'] .'</td>
			<td>'.$row['end_date'] .'</td>
			<td>';
				if(is_null($row['status'])){
					echo 'Pending';
					include 'requestAnswer.php';
				} else if($row['status'] == 0){
					echo 'Rejected';
				} else if ($row['status'] == 1){
					echo 'Accepted';
					include 'cancel.php';
				} else if ($row['status'] == 2){
					echo 'Client Cancelled';
				} else if ($row['status'] == 3){
					echo 'Cancelled by you';
				}
			echo '</td>
			<td>'.$row['request_date'] .'</td>
			</tr>';
		}
	} else {
		echo 'No requests pending!';
	}
	$con->close();
?>
</body>
</html>
