<?PHP

include("connect.php");

$username = $_GET['username'];
$email = $_GET['email'];
$points = $_GET['points'];
$password = $_GET['password'];
$firstname = $_GET['firstname'];
$lastname = $_GET['lastname'];
$badgetitle = $_GET['badgetitle'];

$query = "UPDATE `USER` SET `Username`=\"$username\",`Email`=\"$email\",`Picture`=\"Pictures/$username.jpg\",`Points`=$points,`Password`=\"$password\",`Firstname`=\"$firstname\",`Lastname`=\"$lastname\",`BadgeTitle`=\"Bronze\"";;
if(mysql_query($query)){echo "s";}else{echo "f";}

?>