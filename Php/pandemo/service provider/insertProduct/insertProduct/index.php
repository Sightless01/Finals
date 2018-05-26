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
<body>
	
<h2> Insert Product Info</h2><br>
	<form action="" method="post" enctype="multipart/form-data">
	prod_id: <input type="text" name="prod_id" value="" /><br><br>
	company name: <input type="text" name="companyname" value="" /><br><br>
	description: <input type="text" name="description" value="" /><br><br>
	frontview: <input type="file" name="frontview" value="" /><br><br>
	sideview: <input type="file" name="sideview" value="" /><br><br>	
	backview: <input type="file" name="backview" value="" /><br><br>	
	<input type="submit" name="submit" value="Submit" />
	</form>
	
<?php
if($_POST['submit'])
{
	$pro = $_POST['prod_id'];
	$cn = $_POST['companyname'];
	$de = $_POST['description'];
	
	$filename = $_FILES["front"]["name"];
	$tempname = $_FILES["front"]["tmp_name"];
	$folder = 'image/'.$filename;
	move_uploaded_file($tempname, $folder);
	
	$file = $_FILES["side"]["name"];
	$temp = $_FILES["side"]["tmp_name"];
	$folders = 'image/'.$file;
	move_uploaded_file($temp, $folders);
	
	$files = $_FILES["back"]["name"];
	$temps = $_FILES["back"]["tmp_name"];
	$foldering = 'image/'.$files;
	move_uploaded_file($temps, $foldering);
	
	if($pro!="" && $cn!="" && $de!="" && $filename!="" && $file!="" && $files!="" )
	{
			
		$query = "insert into products values ('$pro','$cn','$de','$folder','$folders','$foldering')";
		$data=$db->query($query);

		if($data)
		{
			echo "data inserted into database";
		}
	}
	else
	{
		echo "all field are required!";
	}

}	
	
?>
</body>
</html>