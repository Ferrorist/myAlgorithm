-- https://school.programmers.co.kr/learn/courses/30/lessons/59041
SELECT NAME, COUNT(NAME) AS 'COUNT' 
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(NAME) >= 2
ORDER BY NAME;

-- GROUP BY의 결과에 조건을 붙이려면 HAVING 절 사용하기!