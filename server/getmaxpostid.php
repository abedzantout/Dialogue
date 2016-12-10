<?PHP

include("connect.php");

$threadid = $_GET['threadid'];

$query = "SELECT MAX(`PostID`) AS M FROM `POST` WHERE `ThreadID`=$threadid;";

$result = mysql_query($query);

if($result){
	
	$maxpostid = mysql_fetch_assoc($result);
	echo $maxpostid["M"];
	
	}else{echo "f: ".mysql_error();}

?>