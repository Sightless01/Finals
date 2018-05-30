<?php

session_start();
$user = $_SESSION['siteuser'];
include '../dbase.php';

$conn = new mysqli($host, $username, $pass, $db);

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
	
 	if($name!=""){
 		$update = "UPDATE products SET name='$name' WHERE prod_id=". $_GET['edit_id'];
		$up = mysqli_query($conn, $update);
 	}if($description!=""){
 		$update = "UPDATE products SET description='$description' WHERE prod_id=". $_GET['edit_id'];
 		$up = mysqli_query($conn, $update);
 	}if($price!=""){
 		$update = "UPDATE products SET price='$price' WHERE prod_id=". $_GET['edit_id'];
 		$up = mysqli_query($conn, $update);
 	}if(isset($_FILES['frontview']['name']) and ($_FILES['frontview']['name']!='')){
		$filefront = $_FILES["frontview"]["name"];
		$tempfront = $_FILES["frontview"]["tmp_name"];
		$folderfront = 'http://database:2018/image/'.$user.'/'.$filefront;
		move_uploaded_file($tempfront, '../image/'.$user.'/'.$filefront);
		$update = "UPDATE products SET frontview='$folderfront' WHERE prod_id=". $_GET['edit_id'];
 		$up = mysqli_query($conn, $update);	
 	}if(isset($_FILES['sideview']['name']) and ($_FILES['sideview']['name']!='')){
		$fileside = $_FILES["sideview"]["name"];
		$tempside = $_FILES["sideview"]["tmp_name"];
		$folderside = 'http://database:2018/image/'.$user.'/'.$fileside;
		move_uploaded_file($tempside, '../image/'.$user.'/'.$fileside);
 		$update = "UPDATE products SET sideview='$folderside' WHERE prod_id=". $_GET['edit_id'];
 		$up = mysqli_query($conn, $update);
 	}if(isset($_FILES['backview']['name'])and ($_FILES['backview']['name']!='')){
		$fileback = $_FILES["backview"]["name"];
		$tempback = $_FILES["backview"]["tmp_name"];
		$folderback = 'http://database:2018/image/'.$user.'/'.$fileback;
		move_uploaded_file($tempback, '../image/'.$user.'/'.$fileback);
 		$update = "UPDATE products SET backview='$folderback' WHERE prod_id=". $_GET['edit_id'];
 		$up = mysqli_query($conn, $update);
 	}
 if(!isset($sql)){
	die ("Error $sql" .mysqli_connect_error());
 }
 else
 {
	header("location: front.php");
 }
}
?>
<!--Create Edit form -->
<!doctype html>
<html>
<head>
  <title>BrendoRent Company Services Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="front/login.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<form method="post"  enctype="multipart/form-data">
	<h1>Product Editing</h1>
	<div class="form-group">
		<label for="pname">Name: </label>
		<input type="text" name="name" placeholder="Name" value="<?php echo $row['name']; ?>"readonly><br/>
	</div>
	<div class="form-group">
		<label>Description:</label><br/>
		<textarea rows="4" cols="50" name="description" placeholder="<?php echo $row['description']; ?>"></textarea><br/>
	</div>
	<div class="form-group">	
		<label>Price:</label>
		<input type="text" name="price" placeholder="price" value="<?php echo $row['price']; ?>"><br/>
	</div>
	<div class="form-group">
		<img src="<?php echo $row['frontview'];?> " height='200' width='200'><br/>
		<input type="file" name="frontview"/><br/>
		<img src="<?php echo $row['sideview'];?> " height='200' width='200'><br/>
		<input type="file" name="sideview"/><br/>
		<img src="<?php echo $row['backview'];?> " height='200' width='200'><br/>
		<input type="file" name="backview"/><br/>
	</div>
		<input type="submit" value="update "name="btn-update" id="btn-update" onClick="update()">
		<input type="button" value="Cancel" onclick="location.href = 'front.php';">
	</form>
</div>
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