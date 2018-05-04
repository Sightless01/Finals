<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('company.db');
		
	}
}
$db = new DBphp();
echo "COMPANY","</br>","</br>";
$query1="SELECT * FROM company;";
$result=$db->query($query1);
echo "<table><tr><td>com_id</td><td>name</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr>';
echo '<tr><td>'.$row ['com_id'] . '</td>
<td>'.$row['name'] .'</td>
</tr>';
}
echo '</table>';
echo "</br>";

$db->close();

?>