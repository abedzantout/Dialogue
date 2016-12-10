<?PHP

include("connect.php");

$query = "SELECT DISTINCT `Thread`.`ThreadID`, `Username`, `Title` FROM `THREAD` INNER JOIN `OWNS` ON `THREAD`.`ThreadID` = `OWNS`.`ThreadID`;";

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