<?php
	if(isset($_POST['btn-unavail-'.$row["prod_id"].'-'.$row["comp_id"]])){
		$update = "UPDATE products SET availability=2 WHERE products.comp_id = ".$row['comp_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($conn, $update);
		header("Refresh:0");
	};
?>

<!DOCTYPE html>
<html>
	<body>
		<div class = "container">
			<form method="post">
				<button type="submit" name="btn-unavail<?php echo '-'.$row['prod_id'].'-'.$row['comp_id']?>" id="btn-confirm<?php echo '-'.$row['prod_id'].'-'.$row['comp_id']?>">Remove Availability</button>
			</form>
		</div>
	</body>
</html>