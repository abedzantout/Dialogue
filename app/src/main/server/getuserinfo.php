<?PHP

include("connect.php");

$username = $_GET['username'];

$query = "SELECT * FROM `USER` WHERE `Username`=\"$username\";";
$exec = mysql_query($query);

if($exec){
	
	
	if(mysql_num_rows($exec) == 0){
		echo "f";
	}else{
		
		$rows = array();
			while($r = mysql_fetch_assoc($exec)) {
				array_push($rows, $r);
			}
		print json_encode($rows);

	}
	
	
}


?>