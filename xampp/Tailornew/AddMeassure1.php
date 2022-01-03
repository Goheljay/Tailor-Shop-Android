<?php
//require_once 'dbconfig.php';

$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");


$length = $_POST['length']; 	
$shoulder = $_POST['shoulder'];
$baay=$_POST['baay'];
$chest = $_POST['chest'];
$arm = $_POST['arm'];
$front = $_POST['front'];
$collor = $_POST['collor'];
$tid = $_POST['tid'];
$uid = $_POST['uid'];

if($length!="")
{	
	$sql = "INSERT INTO measure1(length,shoulder,baay,chest,arm,front,collor,tid,uid) VALUES ('$length','$shoulder','$baay','$chest','$arm','$front','$collor','$tid','$uid')";
					
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