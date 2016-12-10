<?PHP

include("connect.php");

$email = $_GET['email'];
$password = $_GET['password'];

$query = "SELECT * FROM USER WHERE `Email`=\"$email\" AND `Password`=\"$password\";";

$queryexec = mysql_query($query);

	if(mysql_num_rows($queryexec) == 0){
		echo "f";
	}else{
		
		$rows = array();
			while($r = mysql_fetch_assoc($queryexec)) {
				array_push($rows, $r);
			}
		print json_encode($rows);

	}


?>