-- https://school.programmers.co.kr/learn/courses/30/lessons/131113
SELECT ORDER_ID, PRODUCT_ID, date_format(OUT_DATE, "%Y-%m-%d") AS 'OUT_DATE',
CASE
WHEN date_format(OUT_DATE, "%Y-%m-%d") <= '2022-05-01' THEN '출고완료'
WHEN OUT_DATE IS NULL THEN '출고미정'
ELSE '출고대기'
END AS `출고여부`
FROM FOOD_ORDER
ORDER BY ORDER_ID;

-- CASE WHEN THEN 기억하기!