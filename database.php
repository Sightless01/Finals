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
 echo "<table><tr><td>client_id</td><td>name</td><td>address</td><td>contact</td><td>email</td><td>username</td><td>password</td><td>status</td><td>block</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr><td>'.$row ['client_id'] . '</td>
<td>'.$row['name'] .'</td>
<td>'.$row['address'] .'</td>
<td>'.$row['contact'] .'</td>
<td>'.$row['email'] .'</td>
<td>'.$row['username'] .'</td>
<td>'.$row['password'] .'</td>
<td>'.$row['status'] .'</td>
<td>'.$row['block'] .'</td>
</tr>';
}
echo '</table>';
echo "----------------------------------------------------","</br>";

echo "TRANSACTION","</br>";
$query1="SELECT * FROM transact;";
$result=$db->query($query1);
 echo "<table><tr><td>trans_id</td><td>date_booked</td><td>date_paid</td><td>date_returned</td><td>comp_id</td><td>client_id</td></tr>";
while ($row = $result->fetchArray()){
 
echo '<tr><td>'.$row ['trans_id'] . '</td>
<td>'.$row['date_booked'] .'</td>
<td>'.$row['date_paid'] .'</td>
<td>'.$row['date_returned'] .'</td>
<td>'.$row['comp_id'] .'</td>
<td>'.$row['client_id'] .'</td>

</tr>';
}
echo '</table>';
$db->close();

?>
