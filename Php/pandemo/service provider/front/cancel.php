<?php
	ob_start(); 
	if(isset($_POST['btn-cancel-'.$row["prod_id"].'-'.$row["client_id"]])){
		$update = "UPDATE request SET status=3 WHERE client_id = ".$row['client_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($con, $update);
		header("Refresh:0");
	};
	ob_flush();
?>

<form method="post">
	<button type="submit" name="btn-cancel<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>" id="btn-confirm<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>">Cancel</button>
</form>
