<?php
	$hash = '$2b$10$n2rWSB5DzTmNt29MyxjNkOK6CL56/VFN.nT8s98pZA0UBEmkiu9Gi';
	if(password_verify('letitstand', $hash)){
		echo 'fuck';
	} else{
		echo 'nope';
	}