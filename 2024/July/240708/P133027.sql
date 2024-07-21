-- https://school.programmers.co.kr/learn/courses/30/lessons/133027
-- 소요 시간 : 17분 44초
SELECT FLAVOR
FROM (
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS `TOTAL_ORDER` FROM JULY GROUP BY FLAVOR
    UNION 
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS `TOTAL_ORDER` FROM FIRST_HALF GROUP BY FLAVOR
) AS T
GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC
LIMIT 3;