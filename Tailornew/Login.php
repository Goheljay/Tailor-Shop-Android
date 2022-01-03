<?php
//require_once 'dbconfig.php';

$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");

$uname = $_POST['email'];
$password = $_POST['password'];
 
if($uname!="")
{
	$query = "SELECT * FROM registration WHERE email='$uname' AND password='$password' ";
	$result = mysql_query($query);

	if (mysql_num_rows($result) > 0) 
	{
		$row = mysql_fetch_array($result, MYSQL_ASSOC);
		
		echo"Login Successful";
	}
	else
	{
		echo"Invalid Login Details";
	}
}
else
{
	echo"Please Try Again";
}

mysql_close($conn);
?>