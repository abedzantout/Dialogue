<?PHP

include("connect.php");

$username = $_GET['username'];
$email = $_GET['email'];
$password = $_GET['password'];

$query = "INSERT INTO`USER`(`Username`, `Email`, `Picture`, `Points`, `Password`, `Firstname`, `Lastname`, `BadgeTitle`) VALUES (\"$username\", \"$email\", \"/pictures/$username.jpg\", \"0\", \"$password\", NULL, NULL, \"Bronze\");";


if(mysql_query($query)){
	echo "s";
}else{
	echo "Failed: ".mysql_error();
}

?>