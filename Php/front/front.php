<!DOCTYPE html>
<html lang="en">
<head>
  <title>Service Provider Landing Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3 .7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar">    
  <a href="#news">Logout</a>
  <a href="#">Notification</a></li>
  <a href="#">Home</a></li>
<br></br>
 <input type="text" name="search" placeholder="Search..">
</div>

<div id="home">
<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('database.db');
		
	}
}
$db = new DBphp();
echo "COMPANY PRODUCTS","</br>","</br>";
$query1="SELECT * FROM products;";
$result=$db->query($query1);
echo "<table><tr><td>prod_id</td><td>name</td><td>description</td><td>frontview</td><td>sideview</td><td>backview</td><td>com_id</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr>';
echo '<tr><td>'.$row ['prod_id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['description'] .'</td>
<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
<td>'.$row['com_id'] .'</td>
</tr>';
}
echo '</table>';
echo "</br>";

$db->close();

?>

</div>


</body>
</html>

