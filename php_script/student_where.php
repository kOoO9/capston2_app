<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());

// $query = "SELECT * FROM capston.studentforlecture where lecture_code = 'CIS3003-3'";
// $stmt = mysqli_query($connection, $query);

// $response = array();
// while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
//     $response[] = array(
//         'student_id' => urlencode($row['student_id']),
//         'student_name' => urlencode($row['student_name']),
//         //'professor' => $row['professor'],
//     );
// }
// echo urldecode(json_encode($response));

$month = date("m"); //현재 날짜 컬럼 생성하기
$date = date("d");
$attendence_mm_dd = 'attendence_' . $month . '_' . $date;
//echo $attendence_mm_dd;
$sql = "SHOW COLUMNS FROM capston.studentforlecture WHERE Field = '$attendence_mm_dd'";
$stmt = mysqli_query($connection, $sql);

$count = mysqli_num_rows($stmt);

if ($count == 0){
    $sql_mkcol = "
                    ALTER TABLE capston.studentforlecture ADD $attendence_mm_dd varchar(5);
                    UPDATE capston.studentforlecture SET $attendence_mm_dd ='출석';";
    $stmt_1 = mysqli_multi_query($connection, $sql_mkcol);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['lecturecode'])) {
        $attendence_mm_dd = $_POST['attendence_mm_dd'];
        $lecturecode = $_POST['lecturecode'];
        $jsonarray = $_POST['jsonarray']; //출석 수정 버튼 받아옴

        $sql = "SHOW COLUMNS FROM capston.studentforlecture WHERE Field = '$attendence_mm_dd'";
        $stmt = mysqli_query($connection, $sql);

        $count = mysqli_num_rows($stmt);

        if($jsonarray !== "hi"){
            $dataArray =  json_decode($jsonarray, true);
            foreach($dataArray as $item){
                $id = $item['id'];
                $result = $item['result'];
                $query_1 = "
                        UPDATE capston.studentforlecture
                        SET `$attendence_mm_dd` = '$result'
                        where lecture_code = '$lecturecode' and `student_id` = '$id';
                                            ";
                $stmt = mysqli_query($connection, $query_1);
    
            }
        }


        if ($count != 0){ //날짜 조회될 때만 json 파일 넘기기
            $query = "SELECT * FROM capston.studentforlecture where lecture_code = '$lecturecode'";
            $stmt = mysqli_query($connection, $query);
            
            $response = array();
            while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
                $response[] = array(
                    'student_id' => urlencode($row['student_id']),
                    'student_name' => urlencode($row['student_name']),
                    'attendence_mm_dd' => urlencode($row[$attendence_mm_dd]),
                    //'professor' => $row['professor'],
                );
            }
        }
        else{
            $response = array();
            while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
                $response[] = array(
                    'student_id' => '',
                    'student_name' => '',
                    'attendence_mm_dd' => '',
                    //'professor' => $row['professor'],
                );
        }
    }
        echo urldecode(json_encode($response));

    } else {
        echo "lecturecode 값이 제공되지 않았습니다.";
    }
} else {
    echo "올바른 요청 방법이 아닙니다.";
}

