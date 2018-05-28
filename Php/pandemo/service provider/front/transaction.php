<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Transactions</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>

<body>
<h2>Transaction History</h2>
<?php
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "database";

	$con = new mysqli($host, $user, $pass, $db);
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
	
	$sql = "SELECT * FROM transaction;";
			echo "<table border='1'><tr>
			<td>client_id</td>
			<td>prod_id</td>
			<td>date_booked</td>
			<td>date_paid</td>
			<td>date_returned</td>
			</tr>";
			
	$result=mysqli_query($con,$sql);
	
	while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)) {
		echo '<tr>';
		echo '<td>'.$row ['client_id'] . '</td>
		<td>'.$row['prod_id'] .'</td>
		<td>'.$row['date_booked'] .'</td>
		<td>'.$row['date_paid'] .'</td>
		<td>'.$row['date_returned'] .'</td>
		</tr>';
	}
	$con->close();
?>
</body>
</html>
