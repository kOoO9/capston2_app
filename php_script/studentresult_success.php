<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());





// //날짜 열 속성을 다 받아옴
// $query = "SELECT COLUMN_NAME
// FROM INFORMATION_SCHEMA.COLUMNS
// WHERE TABLE_NAME = 'studentforlecture' AND COLUMN_NAME LIKE 'attend%'";
// $stmt = mysqli_query($connection, $query);

// $result_day = array();
// while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
//     $result_day[] = array(
//         'COLUMN_NAME' => urlencode($row['COLUMN_NAME']),
//     );
// }            

// $day = array();
// foreach ($result_day as $item) {
//     $resultday = $item['COLUMN_NAME'];

//     $sql = "SELECT $resultday FROM capston.studentforlecture WHERE student_id = '203985' AND lecture_code = 'CIS3003-3'";

//     $stmt = mysqli_query($connection, $sql);
//     $row = mysqli_fetch_array($stmt, MYSQLI_ASSOC);
//     $day[] = array(
//         'lecture_day' => urlencode($resultday),
//         'lecture_result' => urlencode($row[$resultday]),
//     );
// }
// echo urldecode(json_encode($day));










if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    //날짜 열 속성을 다 받아옴
    $query = "SELECT COLUMN_NAME
    FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'studentforlecture' AND COLUMN_NAME LIKE 'attend%'";
    $stmt = mysqli_query($connection, $query);

    $result_day = array();
    while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
        $result_day[] = array(
            'COLUMN_NAME' => urlencode($row['COLUMN_NAME']),
        );
    }            

    if (isset($_POST['lecture_code'])) {
        $lecture_code = $_POST['lecture_code'];
        $student_id = $_POST['student_id'];

    //dayjson 파일을 코틀린에 전송할 것임
        $day = array();
        foreach ($result_day as $item) {
            $resultday = $item['COLUMN_NAME'];
        
            $sql = "SELECT $resultday FROM capston.studentforlecture WHERE student_id = '$student_id' AND lecture_code = '$lecture_code'";
        
            $stmt = mysqli_query($connection, $sql);
            $row = mysqli_fetch_array($stmt, MYSQLI_ASSOC);
            $day[] = array(
                'lecture_day' => urlencode($resultday),
                'lecture_result' => urlencode($row[$resultday]),
            );
        }
        echo urldecode(json_encode($day));
    }
}
// MySQL 연결 종료
$connection->close();
?>