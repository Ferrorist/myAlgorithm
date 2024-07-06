/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/59409
 * 소요 시간 : 14분 47초 (인터넷 검색함)
 */
SELECT ANIMAL_ID, NAME, 
CASE WHEN SEX_UPON_INTAKE LIKE "%Neutered%" OR SEX_UPON_INTAKE LIKE "%Spayed%"
THEN 'O'
ELSE 'X' END
AS `중성화` 
FROM ANIMAL_INS;

-- 다른 방법 (REGEXP)
SELECT ANIMAL_ID, NAME,
CASE WHEN SEX_UPON_INTAKE REGEXP 'Neutered|Spayed' THEN 'O'
ELSE 'X' END AS `중성화`
FROM ANIMAL_INS;