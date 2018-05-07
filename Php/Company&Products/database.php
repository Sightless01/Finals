<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('database.db');
		
	}
}
$db = new DBphp();
echo "ADMIN","</br>";
$query1="SELECT * FROM admin;";
echo "<table><tr><td>admin_id</td><td>username</td><td>password</td></tr>";
$result=$db->query($query1);
while ($row = $result->fetchArray()){

echo  '<tr><td>'.$row['admin_id'].'</td>
<td>'.$row['username'].'</td>
<td>'.$row['password'] .'</td>
</tr>';
}
echo '</table>';
echo "----------------------------------------------------","</br>";

echo "CLIENT","</br>";
$query1="SELECT * FROM Client;";
$result=$db->query($query1);
 echo "<table><tr><td>client_id</td><td>name</td><td>username</td><td>email</td><td>contact</td><td>status</td>><td>block</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr><td>'.$row ['client_id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['username'] .'</td>
<td>'.$row['email'] .'</td>
<td>'.$row['contact'] .'</td>
<td>'.$row['status'] .'</td>
<td>'.$row['block'] .'</td>
</tr>';
}
echo '</table>';
echo  "----------------------------------------------------","</br>"; 

echo "SERVICE PROVIDER","</br>";
$query1="SELECT * FROM Service_provider;";
$result=$db->query($query1);
echo "<table><tr><td>id </td><td>name</td><td>products</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr><td>'.$row ['id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['products'] .'</td>
</tr>';
}
echo '</table>';
echo "</br>";

$db->close();

?>
