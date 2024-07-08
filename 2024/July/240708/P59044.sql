-- https://school.programmers.co.kr/learn/courses/30/lessons/59044
-- 소요 시간 : 10분
SELECT i.NAME, i.DATETIME FROM ANIMAL_INS i
LEFT JOIN ANIMAL_OUTS o ON i.ANIMAL_ID = o.ANIMAL_ID
WHERE o.ANIMAL_ID IS NULL
ORDER BY i.DATETIME
LIMIT 3;