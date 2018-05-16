<?php

$host = "localhost";
$user = "root";
$pass="";
$db = "database";


$con = new mysqli($host, $user, $pass, $db);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
} 

echo "ADMIN","</br>";
$sql = "SELECT * FROM database.admin";
echo "<table><tr><td>admin_id</td><td>username</td><td>password</td></tr>";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        
echo  '<tr><td>'.$row['admin_id'].'</td>
<td>'.$row['username'].'</td>
<td>'.$row['password'] .'</td>
</tr>';
    }
} 
echo '</table>';
echo "----------------------------------------------------","</br>";
echo "CLIENT","</br>";
$sql = "SELECT * FROM database.client";
echo "<table><tr><td>client_id</td><td>name</td><td>address</td><td>contact</td><td>email</td><td>username</td><td>password</td><td>status</td><td>block</td></tr>";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        
echo  '<tr><td>'.$row['client_id'].'</td>
<td>'.$row['name'].'</td>
<td>'.$row['address'] .'</td>
<td>'.$row['contact'].'</td>
<td>'.$row['email'] .'</td>
<td>'.$row['username'].'</td>
<td>'.$row['password'] .'</td>
<td>'.$row['status'].'</td>
<td>'.$row['block'] .'</td>
</tr>';
    }
} 
echo '</table>';
echo "----------------------------------------------------","</br>";
echo "COMPANY","</br>";
$sql = "SELECT * FROM database.company";
echo "<table><tr><td>comp_id</td><td>name</td><td>address</td><td>contact</td><td>email</td><td>username</td><td>password</td><td><td>status</td><td>block</td></tr>";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        
echo  '<tr><td>'.$row['comp_id'].'</td>
<td>'.$row['name'].'</td>
<td>'.$row['address'] .'</td>
<td>'.$row['contact'].'</td>
<td>'.$row['email'] .'</td>
<td>'.$row['username'].'</td>
<td>'.$row['password'] .'</td>
<td>'.$row['status'].'</td>
<td>'.$row['block'] .'</td>
</tr>';
    }
} 
echo '</table>';
echo "----------------------------------------------------","</br>";
echo "COMPANY","</br>";
$sql = "SELECT * FROM database.transaction";
echo "<table><tr><td>trans_id</td><td>date_booke</td><td>date_paid</td><td>date_returned</td><td>comp_id</td><td>client_id</td></tr>";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        
echo  '<tr><td>'.$row['trans_id'].'</td>
<td>'.$row['date_booked'].'</td>
<td>'.$row['date_paid'] .'</td>
<td>'.$row['date_returned'].'</td>
<td>'.$row['comp_id'] .'</td>
<td>'.$row['client_id'].'</td>
</tr>';
    }
} 
echo '</table>';

$con->close();
?> 
