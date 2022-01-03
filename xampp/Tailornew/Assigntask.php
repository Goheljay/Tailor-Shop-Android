<?php
//require_once 'dbconfig.php';

$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");



$uid=$_POST['uid'];
$extra = $_POST['extra'];
$itemid = $_POST['itemid'];
$status = 'pending';


if($uid!="")
{	
	$sql = "INSERT INTO taskassign(userid,description,taskstatus,itemid) 
VALUES ('$uid','$extra','$status','$itemid')";
					
	if(mysql_query($sql))
	{
		echo "Added...!";
	}
	else
	{
		echo "Error";
	}
	 
}

?>