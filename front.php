<?php
class DBphp extends SQLite3{
function __construct(){
		$this->open('database.db');
		
	}
}
echo '<!DOCTYPE html>';
echo '<html lang="en">';
echo '<head>';
echo '<title>Bootstrap Example</title>';
echo '<meta charset="utf-8">';
echo '<meta name="viewport" content="width=device-width, initial-scale=1">';
echo '<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3 .7/css/bootstrap.min.css">';
echo '<link rel="stylesheet" href="front.css">';
echo '<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>';
echo '<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>';
echo '</head>';
echo '<body>';
echo '<div class="navbar">';
echo '<a href="#">Sign up</a>';
echo '<a href="#">Login</a></li>';
echo '<a href="#news">Notification</a>';
echo '<a href="#">Track Rentals</a></li>';
echo '<div class="dropdown">';
echo '<button class="dropbtn">Dropdown';
echo '<i class="fa fa-caret-down"></i>';
echo '</button>';
echo '<div class="dropdown-content">';
echo '<div class="catHeader">';
echo '<h2>Categories</h2>';
echo '</div>';
echo '<div class="row">';
echo '<div class="column">';
echo '<h3>Women\'s Fashion</h3>';
echo '<a href="#">Link 1</a>';
echo '<a href="#">Link 2</a>';
echo '<a href="#">Link 3</a>';
echo '</div>';
echo '<div class="column">';
echo '<h3>Men\'s Fashion</h3>';
echo '<a href="#">Link 1</a>';
echo '<a href="#">Link 2</a>';
echo '<a href="#">Link 3</a>';
echo '</div>';
echo '<div class="column">';
echo '<h3>Accessories</h3>';
echo '<a href="#">Link 1</a>';
echo '<a href="#">Link 2</a>';
echo '<a href="#">Link 3</a>';
echo '</div>';
echo '</div>';
echo '</div>';
echo '</div>';
echo '<a href="#">Home</a></li>';
echo '';
echo '<form class="navbar-form" action="/action_page.php">';
echo '<br></br>';
echo '<div class="input-group">';
echo '<input type="text" class="form-control" placeholder="Search" name="search">';
echo '<div class="input-group-btn">';
echo '<button class="btn btn-default" type="submit">';
echo '<i class="glyphicon glyphicon-search"></i>';
echo '</button>';
echo '</div>';
echo '</div>';
echo '</form>';
echo '</div>';
echo '';
echo '</body>';
echo '</html>';
echo '';
echo '';
?>