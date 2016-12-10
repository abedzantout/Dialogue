<?PHP

// insert into thread, owns,post, posted

include("connect.php");

$username = $_GET['username'];
$title = $_GET['title'];
$content = $_GET['content'];
$numoflines = $_GET['numoflines'];

// get max thread id
$maxtid = 0;
$q = "SELECT MAX(`ThreadID`) FROM `THREAD`;";
$r = mysql_query($q);
if($r){
	$row = mysql_fetch_row($r);
	$maxtid = intval($row[0]);
}

$threadid = $maxtid+1;

$threadq = "INSERT INTO `THREAD`(`Title`, `PostID`) VALUES (\"$title\", 0);";
if(mysql_query($threadq)){echo "s";}else{echo "f";}

$ownsq = "INSERT INTO `OWNS`(`Username`, `ThreadID`) VALUES (\"$username\", $threadid)";
if(mysql_query($ownsq)){echo "s";}else{echo "f";}

$postq = "INSERT INTO `POST`(`ThreadID`, `PostID`, `Content`, `NumberOfLines`) VALUES ($threadid, 0, \"$content\", $numoflines)";
if(mysql_query($postq)){echo "s";}else{echo "f";}

$postedq = "INSERT INTO `POSTED`(`Username`, `ThreadID`, `PostID`) VALUES (\"$username\", $threadid, 0);";
if(mysql_query($postedq)){echo "s";}else{echo "f";}



?>