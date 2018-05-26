<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('data.db');
		
	}
}
$db = new DBphp();
error_reporting(0);

echo "<h2>New Products</h2>","</br>";
$query1="SELECT * FROM products;";
$result=$db->query($query1);
echo "<table><tr><td>prod_id</td><td>company name</td><td>description</td><td>frontview</td><td>sideview</td><td>backview</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr>';
echo '<td>'.$row ['prod_id'] . '</td>
<td>'.$row['companyname'] .'</td>
<td>'.$row['description'] .'</td>
<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
</tr>';
}
echo '</table>';
echo "</br>";

$db->close();

?>