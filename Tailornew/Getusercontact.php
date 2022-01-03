<?php
 require_once('dbconfig.php');
 
 $sql = "select * from itemname";
 
 $res = mysqli_query($con,$sql);
 $conn = mysql_connect("localhost","root","");
mysql_select_db("tailornew");

$id = $_GET['id']; 

if($id!=""){



		$sql = "SELECT * FROM registration where userid='$id'";

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