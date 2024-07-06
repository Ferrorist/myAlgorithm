/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/59047
 * 소요 시간 : 3분 4초
 */
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS
WHERE ANIMAL_TYPE = 'Dog' AND NAME LIKE "%EL%"
ORDER BY NAME;
-- 만약 문자를 대소문자로 구분해야 한다면 BINARY를 사용할 것.
-- ex) SELECT 'ID' AS ID FROM MMEMBER WHERE BINARY(ID) = 'id'