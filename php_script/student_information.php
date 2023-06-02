<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['student_id'])) {
        $student_id = $_POST['student_id'];
        
        $query = "SELECT * FROM capston.studentforlecture where student_id = '$student_id'";
        $stmt = mysqli_query($connection, $query);

        $response = array();
        while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
            $response[] = array(
                'student_id' => urlencode($row['student_id']),
                'student_name' => urlencode($row['student_name']),
                'collegemajor' => urlencode($row['collegemajor']),
                'imgurl' => urlencode($row['imgurl']),
            );
        }
        echo urldecode(json_encode($response));

    } else {
        echo "username 값이 제공되지 않았습니다.";
    }
} else {
    echo "올바른 요청 방법이 아닙니다.";
}    
    ?>