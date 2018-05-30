<?php
	ob_start(); 
	if(isset($_POST[$column.'-'.$row['prod_id'].'-'.$row['client_id']])){
		$update = "UPDATE transaction SET ".$column."='".$_POST['date-'.$row['prod_id'].'-'.$row['client_id']]."' WHERE client_id = ".$row['client_id']." AND prod_id = ".$row['prod_id'].";";
		$up = mysqli_query($con, $update); 
		header ('Refresh:0');
	}; 
	ob_flush();
?>
	<form method="post">
		<div class="form-group">
			<label for="date">Date: </label>
			<input type="text" placeholder="YYYY-MM-DD" name="date<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>" id="date<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>"><br/>
			<input type="submit" name="<?php echo $column.'-'.$row['prod_id'].'-'.$row['client_id']?>" id="btn-edit<?php echo '-'.$row['prod_id'].'-'.$row['client_id']?>" id="btn-edit">
		</div>
	</form>