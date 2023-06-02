<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());
// else
//     echo "connection established";

// $query = "SELECT * FROM capston.lectures where professors_id = 'syyoo'";
// $stmt = mysqli_query($connection, $query);

// $response = array();
// while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
//     $response[] = array(
//         'lecture_code' => urlencode($row['lecture_code']),
//         'lecture_name' => urlencode($row['lecture_name']),
//         //'professor' => $row['professor'],
//     );
// }
// echo urldecode(json_encode($response));

// 데이터 조회 -> 앱에서 로그인 성공하고 아이디랑 같은 강의명 리스트 출력
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['username'])) {
        $username = $_POST['username'];
        
        $query = "SELECT * FROM capston.lectures where professors_id = '$username'";
        $stmt = mysqli_query($connection, $query);

        $response = array();
        while($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)){
            $response[] = array(
                'lecture_code' => urlencode($row['lecture_code']),
                'lecture_name' => urlencode($row['lecture_name']),
                //'professor' => $row['professor'],
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
