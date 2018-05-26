<?php
	//palitan m nalang ung string to the equivalent password. pag rinun m ung site ung lalabas un ung nakahash
	$hashedPass = password_hash('champion', PASSWORD_DEFAULT);
	echo $hashedPass;
	if(password_verify('champion', '$2y$10$SjnBCNd18qBGs8nLlPSqsuTms3/N1NTg58dOBGc3P7shN36rUcbYS')){
		echo 'true';
	}
?>