<?PHP

include("connect.php");

$threadid = $_GET['threadid'];
$username = $_GET['username'];
$content = $_GET['content'];
$numberoflines = $_GET['numoflines'];


// get max postID
$maxpostid = 0;
$q = "SELECT MAX(`PostID`) FROM `POST` WHERE `ThreadID`=$threadid;";
$r = mysql_query($q);
if($r){
	$row = mysql_fetch_row($r);
	$maxpostid = intval($row[0]);
}

$postid = $maxpostid+1;


$query = "INSERT INTO `POST`(`ThreadID`, `PostID`, `Content`, `NumberOfLines`) VALUES ($threadid, $postid, \"$content\", $numberoflines);";
if(mysql_query($query)){echo "s";}else{echo "f: ".mysql_error();}

$query2 = "INSERT INTO `POSTED`(`Username`, `ThreadID`, `PostID`) VALUES (\"$username\", $threadid, $postid);";
if(mysql_query($query2)){echo "s";}else{echo "f: ".mysql_error();}



?>