<?php
//require_once 'dbconfig.php';

$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");


$length = $_POST['length']; 	
$waist = $_POST['waist'];
$seat=$_POST['seat'];
$bottom = $_POST['bottom'];
$knee = $_POST['knee'];
$thigh = $_POST['thigh'];
$level = $_POST['level'];
$tid = $_POST['tid'];
$uid = $_POST['uid'];

if($length!="")
{	
	$sql = "INSERT INTO measure2(length,waist,seat,bottom,knee,thigh,level,tid,uid) VALUES ('$length','$waist','$seat','$bottom','$knee','$thigh','$level','$tid','$uid')";
					
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