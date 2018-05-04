<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('products.db');
		
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
