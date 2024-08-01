-- https://school.programmers.co.kr/learn/courses/30/lessons/131115
-- 소요 시간 : 3분 23초

-- Oracle 1
SELECT * FROM 
    (
        SELECT * FROM FOOD_PRODUCT 
        ORDER BY PRICE DESC
    )
WHERE ROWNUM = 1;

-- Oracle은 MySql이나 PostgreSQL과는 다르게 LIMIT을 사용할 수 없다.