<?php
	if(isset($_POST['btn-avail-'.$row["prod_id"].'-'.$row["comp_id"]])){
		$update = "UPDATE products SET availability=1 WHERE products.comp_id = ".$row['comp_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($conn, $update);
		header("Refresh:0");
	};
?>

<!DOCTYPE html>

<form method="post">
	<button type="submit" name="btn-avail<?php echo '-'.$row['prod_id'].'-'.$row['comp_id']?>" id="btn-confirm<?php echo '-'.$row['prod_id'].'-'.$row['comp_id']?>">Make Available</button>
</form>
