-- SELECT i.ANIMAL_ID, i.ANIMAL_TYPE, i.NAME FROM ANIMAL_INS i
-- 소요 시간 : 5분 56초
SELECT i.ANIMAL_ID, i.ANIMAL_TYPE, i.NAME FROM ANIMAL_INS i
INNER JOIN ANIMAL_OUTS o ON i.ANIMAL_ID = o.ANIMAL_ID
WHERE i.SEX_UPON_INTAKE LIKE "Intact%" AND o.SEX_UPON_OUTCOME NOT LIKE "Intact%"
ORDER BY i.ANIMAL_ID;