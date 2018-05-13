<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('data.db');
		
	}
}
$db = new DBphp();
error_reporting(0);
?>


<html>
<head><title>Products</title></head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="addProduct.css">
<body>
	
<h2> Insert Product Info</h2><br>
<div id="container">
	<form action="" method="post" enctype="multipart/form-data">
	<div class="row">
      <div class="col-20">
      	prod_id:
      </div>
      <div class="col-75">
      	<input type="text" name="prod_id" value="" /><br><br>
      </div>
    </div>
    <div class="row">
      <div class="col-20">
      	company name:
      </div>
      <div class="col-75">
      	<input type="text" name="companyname" value="" /><br><br>
      </div>
     </div>
    <div class="row">
		<div class="col-20">
			description:
		</div>
		<div class="col-75">
			<textarea id="subject" name="description" placeholder="Write Description.." style="height:200px"></textarea>
			
		</div>
	</div>
	
	<div class="row">
		<div class="col-20">
			frontview:
		</div>
		<div class="col-75">
			<input type="file" name="frontview" value="" /><br><br>
		</div>
	</div>
	<div class="row">
		<div class="col-20">
			sideview:
		</div>
		<div class="col-75">
			<input type="file" name="sideview" value="" /><br><br>
		</div>
	</div>
	<div class="row">
		<div class="col-20">
			backview:
		</div>
		<div class="col-75">
			<input type="file" name="backview" value="" /><br><br>
		</div>
	</div>
	<div class="row">
		<div class="col-sub">
			<input type="submit" name="submit" value="Submit" />
		</div>
	</div>
</div>
	</form>
	
<?php
if($_POST['submit'])
{
	$pro = $_POST['prod_id'];
	$cn = $_POST['companyname'];
	$de = $_POST['description'];
	
	$filename = $_FILES["frontview"]["name"];
	$tempname = $_FILES["frontview"]["tmp_name"];
	$folder = 'image/'.$filename;
	move_uploaded_file($tempname, $folder);
	
	$file = $_FILES["sideview"]["name"];
	$temp = $_FILES["sideview"]["tmp_name"];
	$folders = 'image/'.$file;
	move_uploaded_file($temp, $folders);
	
	$files = $_FILES["backview"]["name"];
	$temps = $_FILES["backview"]["tmp_name"];
	$foldering = 'image/'.$files;
	move_uploaded_file($temps, $foldering);
	
	if($pro!="" && $cn!="" && $de!="" && $filename!="" && $file!="" && $files!="" )
	{
			
		$query = "insert into products values ('$pro','$cn','$de','$folder','$folders','$foldering')";
		$data=$db->query($query);

		if($data)
		{
			echo "<script type='text/javascript'>alert('Data Inserted to database');</script>";
		}
	}
	else
	{
		echo "<script type='text/javascript'>alert('all fields are required');</script>";
	}

}	
	
?>
</body>
</html>