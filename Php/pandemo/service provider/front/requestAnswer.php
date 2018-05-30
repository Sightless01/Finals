<?php
	if(isset($_POST['btn-confirm-'.$row["prod_id"].'-'.$row["client_id"]])){
		$update = "UPDATE request SET status=1 WHERE client_id = ".$row['client_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($con, $update);
		$create = "INSERT INTO transaction (client_id, prod_id) VALUES (".$row['client_id'].", ".$row['prod_id'].");";
		$trans = mysqli_query($con, $create);
		header("Refresh:0");
	};
	
	if(isset($_POST['btn-reject-'.$row["prod_id"].'-'.$row["client_id"]])){
		$update = "UPDATE request SET status=0 WHERE client_id = ".$row['client_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($con, $update);
		header("Refresh:0");
	};
?>

<form method="post">
	<button type="submit" name="btn-confirm<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>" id="btn-confirm<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>">Accept</button>
	<button type="submit" name="btn-reject<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>" id="btn-reject<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>">Reject</button>
</form>
