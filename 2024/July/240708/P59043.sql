-- https://school.programmers.co.kr/learn/courses/30/lessons/59043
SELECT i.ANIMAL_ID, i.NAME FROM ANIMAL_INS i
LEFT JOIN ANIMAL_OUTS o ON i.ANIMAL_ID = o.ANIMAL_ID
WHERE i.DATETIME > o.DATETIME
ORDER BY i.DATETIME ASC;