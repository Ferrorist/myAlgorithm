-- https://school.programmers.co.kr/learn/courses/30/lessons/144853
-- 소요 시간 : 11분 24초 (mysql)
-- 명령어 인터넷 검색함...
SELECT BOOK_ID, date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE FROM BOOK
-- date_format을 통해 포맷 변경
WHERE CATEGORY LIKE "인문"
AND
YEAR(PUBLISHED_DATE) = 2021
-- YEAR(), MONTH(), DAY(), HOUR(), MINUTE(), SECOND()
-- 일자의 년도, 월, 일 등을 반환함.
ORDER BY PUBLISHED_DATE;