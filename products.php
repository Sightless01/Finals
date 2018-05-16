<?php

$host = "localhost";
$user = "root";
$pass="";
$db = "database";


$con = new mysqli($host, $user, $pass, $db);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
} 

echo "PRODUCTS","</br>";
$sql = "SELECT * FROM database.products";
echo "<table><tr><td>prod_id</td><td>name</td><td>description</td><td>price</td><td>frontview</td><td>sideview</td><td>backview</td><td>availability</td><td>comp_id</td></tr>";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        
echo  '<tr><td>'.$row['prod_id'].'</td>
<td>'.$row['name'].'</td>
<td>'.$row['desctription'] .'</td>
<td>'.$row['price'].'</td>
<td>';?><img src='<?php echo$row['frontview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['sideview'];?>' height='200' width='200'> <?php echo '</td>
<td>';?><img src='<?php echo$row['backview'];?>' height='200' width='200'> <?php echo '</td>
<td>'.$row['availability'].'</td>
<td>'.$row['comp_id'].'</td>
</tr>';
    }
} 
echo '</table>';