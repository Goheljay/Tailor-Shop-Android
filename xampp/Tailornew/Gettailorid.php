<?php
 require_once('dbconfig.php');
 
 $sql = "select * from itemname";
 
 $res = mysqli_query($con,$sql);
 $conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");

$aname = $_GET['name']; 

if($aname!=""){



		$sql = "SELECT staffid FROM staff where email='$aname'";

				$result = mysql_query($sql);

		if(mysql_num_rows($result) > 0)
		{
			while( $row = mysql_fetch_assoc($result)) 
			{	
				$output['data'][] = $row;
			}
			print(json_encode($output));
		}
		else
		{
			echo "";
		}


}

?>