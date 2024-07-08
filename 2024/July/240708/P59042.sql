-- https://school.programmers.co.kr/learn/courses/30/lessons/59042
-- 소요 시간 : 3분 38초
SELECT o.ANIMAL_ID, o.NAME FROM ANIMAL_OUTS o
LEFT JOIN ANIMAL_INS i on o.ANIMAL_ID = i.ANIMAL_ID
where i.ANIMAL_ID is null
ORDER BY o.ANIMAL_ID;