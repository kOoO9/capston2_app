<?php
$connection = mysqli_connect("127.0.0.2","root", "1222", "capston", 3307);
if(!$connection)
    die("could not connect".mysqli_connect_error());

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['lecture_code'])) {
        $lecture_code = $_POST['lecture_code'];
        $student_id = $_POST['student_id'];
        // SQL 쿼리 실행
        $sql = "SET @sql = NULL;
        SELECT GROUP_CONCAT(
            CONCAT('SELECT `', COLUMN_NAME, '` AS lecture_result, \"', COLUMN_NAME, '\" AS lecture_day FROM studentforlecture WHERE student_id = $student_id AND lecture_code = \"$lecture_code\"')
            ORDER BY COLUMN_NAME ASC
            SEPARATOR ' UNION ALL '
        )
        INTO @sql
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE TABLE_SCHEMA = 'capston'
        AND TABLE_NAME = 'studentforlecture'
        AND COLUMN_NAME LIKE 'attendence%';

        SET @sql = CONCAT('SELECT * FROM (', @sql, ') AS subquery');

        PREPARE stmt FROM @sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;";

        $result = $connection->multi_query($sql);
        $response = array();
        if ($result) {
            // 결과 가져오기
            do {
                if ($result = $connection->store_result()) {
                    while ($row = $result->fetch_assoc()) {
                        // 결과 출력
                        $response[] = array(
                            'lecture_day' => urlencode($row['lecture_day']),
                            'lecture_result' => urlencode($row['lecture_result']),
                        );
                    }
                    $result->free();
                }
                if ($connection->more_results()) {
                    // 다음 결과 집합으로 이동
                    $connection->next_result();
                }
            } while ($connection->more_results());
        } else {
            echo "쿼리 실행 실패: " . $connection->error;
        }
    }
    echo urldecode(json_encode($response));
}
// MySQL 연결 종료
$connection->close();
?>
