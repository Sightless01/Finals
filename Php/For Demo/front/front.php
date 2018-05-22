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
			echo '<a href="logout.php">Logout</a></li>';
		} else {
			echo '<a href="http://webtechadmin.org:5001/registration?redirect=http://webtechsp.org:2018">Register</a>';
		}
	?>	
  <a href="#">Notification</a></li>
  <a href="#">Home</a></li>
<br></br>
 <input type="text" name="search" placeholder="Search..">
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
$sql = "SELECT name, desctription, categories, event, frontview, sideview, backview FROM products";
$result = $conn->query($sql);
echo "PRODUCTS";
echo "<div><table class='table table-bordered'>";
echo "<th><td>Product Name:</td><td>Description</td><td>frontview</td><td>sideview</td><td>backview</td></th>";
	while($row = $result->fetch_assoc()){
		echo '<tr><td>'.$row['name'] .'</td><td>'.$row['desctription'] .'</td>
			<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
			<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
			</tr>';
	}
echo "</table></div>";


?>

</div>


</body>
</html>

