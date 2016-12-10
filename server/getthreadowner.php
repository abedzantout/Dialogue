<?PHP

include("connect.php");

$threadid = $_GET['threadid'];

$query = "SELECT `Username` FROM `OWNS` WHERE `ThreadID`=$threadid;";

$result = mysql_query($query);

if($result){
	
	if(mysql_num_rows($result) == 0){echo "f";die();}
	
	$row = mysql_fetch_row($result);
	echo $row[0];
	
}
	
	
	
	


?>