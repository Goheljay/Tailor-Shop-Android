<?php
//require_once 'dbconfig.php';

$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");


$uname = $_POST['uname']; 	
$gender = $_POST['gender'];
$address=$_POST['address'];
$password = $_POST['password'];
$email = $_POST['email'];
$cno = $_POST['cno'];


if($uname!="")
{	
	$sql = "INSERT INTO registration(name,gender,address,email,password,contactno) VALUES ('$uname','$gender','$address','$email','$password','$cno')";
					
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