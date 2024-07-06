/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/157343
 * 소요 시간 : 1분 59초
 */
SELECT * FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE "%네비게이션%"
ORDER BY CAR_ID DESC;