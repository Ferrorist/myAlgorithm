--https://school.programmers.co.kr/learn/courses/30/lessons/59408
SELECT COUNT(DISTINCT NAME) AS 'count' FROM ANIMAL_INS
WHERE NAME IS NOT NULL;

-- DISTINCT는 레코드(ROW)를 유니크하게 SELECT하는 것이다.
-- 컬럼(Field)을 유니크하게 조회하는 것이 아니다.
-- SELECT DISTINCT first_name, last_name FROM employees;
-- 위의 쿼리는 first_name과 last_name의 조합이 중복되지 않게 찾는 것이다.
-- first_name만을 유니크하게 가져오고 싶다면 다음과 같이.
-- SELECT DISTINCT (first_name), last_name FROM employees;