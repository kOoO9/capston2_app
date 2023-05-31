<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['username'])) {
        $username = $_POST['username'];
        
        // $query = "SELECT * FROM capston.lectures
        //             WHERE lecture_code IN (
        //             SELECT lecture_code
        //             FROM capston.studentforlecture
        //             where student_id = $username
        //             )";


        $query = "SELECT capston.lectures.*, capston.studentforlecture.student_id AS student_id
        FROM capston.lectures
        LEFT JOIN capston.studentforlecture ON capston.lectures.lecture_code = capston.studentforlecture.lecture_code
        WHERE capston.studentforlecture.student_id = $username";
        

        $stmt = mysqli_query($connection, $query);

        $response = array();
        while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
            $response[] = array(
                'lecture_code' => urlencode($row['lecture_code']),
                'lecture_name' => urlencode($row['lecture_name']),
                'professor' => urlencode($row['professor']),
                'student_id' => urlencode($row['student_id']),
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