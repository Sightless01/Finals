<?php
//Database Connection

$host = "localhost";
$user = "root";
$pass="";
$db = "database";


$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
//Get ID from Database
if(isset($_GET['edit_id'])){
 $sql = "SELECT * FROM products WHERE prod_id =" .$_GET['edit_id'];
 $result = mysqli_query($conn, $sql);
 $row = mysqli_fetch_array($result);
}
//Update Information
if(isset($_POST['btn-update'])){
 	$name = $_POST['name'];
 	$description = $_POST['description'];
	$price = $_POST['price'];
 	$frontview = $_POST['frontview'];
 	$sideview = $_POST['sideview'];
 	$backview = $_POST['backview'];
 	if($name!=""){
 		 $update = "UPDATE products SET name='$name' WHERE prod_id=". $_GET['edit_id'];
	 $up = mysqli_query($conn, $update);

 	}if($description!=""){
 		 $update = "UPDATE products SET description='$description' WHERE prod_id=". $_GET['edit_id'];
 		 $up = mysqli_query($conn, $update);
 	}if($price!=""){
 		 $update = "UPDATE products SET price='$price' WHERE prod_id=". $_GET['edit_id'];
 		 $up = mysqli_query($conn, $update);
 	}if($frontview!=""){
 		 $update = "UPDATE products SET frontview='image/$frontview' WHERE prod_id=". $_GET['edit_id'];
 		 $up = mysqli_query($conn, $update);
 	}if($sideview!=""){
 		 $update = "UPDATE products SET sideview='image/$sideview' WHERE prod_id=". $_GET['edit_id'];
 		 $up = mysqli_query($conn, $update);
 	}if($backview!=""){
 		 $update = "UPDATE products SET backview='image/$backview' WHERE prod_id=". $_GET['edit_id'];
 		 $up = mysqli_query($conn, $update);
 	}
 if(!isset($sql)){
 die ("Error $sql" .mysqli_connect_error());
 }
 else
 {
 header("location: display.php");
 }
}
?>
<!--Create Edit form -->
<!doctype html>
<html>
<body>
<form method="post">
<h1>Product Editing</h1>
<label>Name:</label><input type="text" name="name" placeholder="Name" value="<?php echo $row['name']; ?>"readonly><br/><br/>
<label>description:</label><textarea name="description" placeholder="<?php echo $row['description']; ?>"></textarea><br/><br/>
<label>price:</label><input type="text" name="price" placeholder="price" value="<?php echo $row['price']; ?>"><br/><br/>
<img src="<?php echo $row['frontview'];?> " height='200' width='200'><br/>
<input type="file" name="frontview"/><br/>
<img src="<?php echo $row['sideview'];?> " height='200' width='200'><br/>
<input type="file" name="sideview"/><br/>
<img src="<?php echo $row['backview'];?> " height='200' width='200'><br/>
<input type="file" name="backview"/><br/>
<button type="submit" name="btn-update" id="btn-update" onClick="update()"><strong>Update</strong></button>
	<button type="button" value="button">Cancel</button></a>
</form>
<!-- Alert for Updating -->
<script>
function update(){
 var x;
 if(confirm("Updated data Sucessfully") == true){
 x= "update";
 }
}
</script>
</body>
</html>