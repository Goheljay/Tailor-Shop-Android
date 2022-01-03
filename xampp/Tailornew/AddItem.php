<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 
$image = $_POST['image'];
$itemname= $_POST['itemname'];
$basicrate= $_POST['basicrate'];
 
 require_once('dbconfig.php');
 
 $sql ="SELECT itemid FROM itemname ORDER BY itemid ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id =0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['itemid']+1;
 }
 
 $path = "uploads/$id.png";
 
 $actualpath = "http://192.168.43.186/Tailornew/$path";
 
 $sql = "INSERT INTO itemname (itemname,basicrate,image) VALUES ('$itemname','$basicrate','$actualpath')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }

?>