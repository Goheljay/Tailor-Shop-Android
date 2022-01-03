<?php
$conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");

$dt=$_POST['dt'];
$extra = 'In progress';
$id = $_POST['id'];
$staffid=$_POST['staffid'];

if($id!="")
{	
	$sql = "UPDATE taskassign SET deliverydate='$dt', staffid='$staffid',taskstatus='$extra' where taskid='$id'";
					
	if(mysql_query($sql))
	{
		echo "Updated";
	}
	else
	{
		echo "Error";
	}
	 
}


?>