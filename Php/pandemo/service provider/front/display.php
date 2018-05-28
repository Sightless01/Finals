<?php


$host = "localhost";
$user = "root";
$pass="";
$db = "database";


$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 


echo "<h2>New Products</h2>","</br>";
$sql = "SELECT * FROM products";
$result = $conn->query($sql);
echo "<table><tr><td>prod_id</td><td>company name</td><td>description</td><td>price</td><td>frontview</td><td>sideview</td><td>backview</td></tr>";
if($result->num_rows > 0){
 while($row = $result->fetch_assoc()){
 
echo '<tr>';
echo '<td>'.$row ['prod_id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['description'] .'</td>
<td>'.$row['price'] .'</td>
<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><a href="edit.php?edit_id=<?php echo $row['prod_id']; ?>" alt="edit" >Edit</a><?php echo '</td>
</tr>';
}
}
echo '</table>';
echo "</br>";

?>