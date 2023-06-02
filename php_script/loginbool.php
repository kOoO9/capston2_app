<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $who = $_POST['who'];
    if (isset($_POST['id']) && $who == 'pro') {
        $id = $_POST['id'];
        $pw = $_POST['pw'];
        
        $query = "SELECT * FROM capston.user where user_ID = '$id' and user_pw = '$pw'";
        $stmt = mysqli_query($connection, $query);

        $response = array();
        if(mysqli_num_rows($stmt) > 0){//로그인 성공
            $response[] = array(
                'status' => urlencode('true'),
                'message' => urlencode('로그인 성공'),
            );
        }
        else{
            $response[] = array( //로그인 실패
                'status' => urlencode('false'),
                'message' => urlencode('아이디 또는 비밀번호를 확인해 주세요'),
            );
        }
    } 
    
    else if (isset($_POST['id']) && $who == 'stu') {
        $id = $_POST['id'];
        $pw = $_POST['pw'];
        
        $query = "SELECT * FROM capston.user_student where user_ID = '$id' and user_pw = '$pw'";
        $stmt = mysqli_query($connection, $query);

        $response = array();
        if(mysqli_num_rows($stmt) > 0){//로그인 성공
            $response[] = array(
                'status' => urlencode('true'),
                'message' => urlencode('로그인 성공'),
            );
        }
        else{
            $response[] = array( //로그인 실패
                'status' => urlencode('false'),
                'message' => urlencode('아이디 또는 비밀번호를 확인해 주세요'),
            );
        }
    }
    echo urldecode(json_encode($response));

} ?>