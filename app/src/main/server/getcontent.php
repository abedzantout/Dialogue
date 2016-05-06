<?PHP

include("connect.php");

$threadid = $_GET['threadid'];

$query = "SELECT `Content` FROM `POST` WHERE `ThreadID`=$threadid;";

$execution = mysql_query($query);


if(mysql_num_rows($execution) == 0){
		echo "f";
	}else{
		
		$rows = array();
			while($r = mysql_fetch_assoc($execution)) {
				array_push($rows, $r);
			}
		print json_encode($rows);

	}

?>