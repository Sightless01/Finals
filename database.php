<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('database.db');
		
	}
}
$db = new DBphp();
echo "ADMIN","</br>";
$query1="SELECT * FROM admin;";
$result=$db->query($query1);
echo '<table cellpadding = "2" cellspacing = "2" class "db-table">';
  echo "admin_id\tusername\tpassword\n","</br>";
while ($row = $result->fetchArray()){
 
 echo $row ['admin_id'] . 
 $row['username'] .
$row['password'] .
 '<br/>';
}
echo '</table>';
 echo "----------------------------------------------------","</br>";

$query1="SELECT * FROM Client;";
echo "CLIENT","</br>";
$result=$db->query($query1);
echo '<table cellpadding = "5" cellspacing = "2" class "db-table">';
  echo "client_id \tname\tproducts \tcontact\taddress\tpayment\n","</br>";
while ($row = $result->fetchArray()){
 
 echo $row ['client_id'] . 
 $row['name'] .
 $row['products'] .
$row['contact'] .
 $row['address'] .
 $row['payment'] .

 '<br/>';
}
echo '</table>';
echo  "----------------------------------------------------","</br>"; 

$query1="SELECT * FROM Service_provider;";
echo "SERVICE PROVIDER","</br>";
$result=$db->query($query1);
echo '<table cellpadding = "3" cellspacing = "2" class "db-table">';
  echo "id \tname \tproducts\n","</br>";
while ($row = $result->fetchArray()){
 
 echo $row ['id'] . 
 $row['name'] .
$row['products'] .

 '<br/>';
}
echo '</table>';
echo  "----------------------------------------------------","</br>"; 

$query1="SELECT * FROM Products;";
echo "PRODUCTS","</br>";
$result=$db->query($query1);
echo '<table cellpadding = "2" cellspacing = "2" class "db-table">';
  echo "prod_id\tstyle\tcolor\tsize\tprice\n","</br>";
while ($row = $result->fetchArray()){
 
 echo $row ['prod_id'] . 
 $row['style'] .
$row['color'] .
$row['size'] .
$row['price'] .
 '<br/>';
}
echo '</table>';
 echo "</br>";

$db->close();
echo '</table>';
?>
