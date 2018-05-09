<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('database.db');
		
	}
}
$db = new DBphp();
echo "COMPANY","</br>","</br>";
$query1="SELECT * FROM company;";
$result=$db->query($query1);
echo "<table><tr><td>comp_id</td><td>name</td><td>username</td><td>email</td><td>contact</td><td>status</td><td>block</td><td>comp_address</td><td>pw</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr>';
echo '<td>'.$row ['comp_id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['username'] .'</td>
<td>'.$row['email'] .'</td>
<td>'.$row['contact'] .'</td>
<td>'.$row['status'] .'</td>
<td>'.$row['block'] .'</td>
<td>'.$row['comp_address'] .'</td>
<td>'.$row['pw'] .'</td>
</tr>';
}
echo '</table>';
echo "</br>";

$db->close();

?>