/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/131529
 * 소요 시간 : 7분 42초 (인터넷 검색함)
 */
SELECT 
LEFT(PRODUCT_CODE, 2) AS `CATEGORY`,
COUNT (LEFT(PRODUCT_CODE, 2)) AS `PRODUCTS`
FROM PRODUCT
GROUP BY `CATEGORY`;

/*
 * LEFT(column_name, num) : 해당 컬럼의 값을 좌측부터 num 글자 만큼 자른 후 return
 * RIGHT(column_name, num) : 좌측이 아닌 우측
 * SUBSTR(str, pos) : 원본 문자열을 pos번쨰 문자열부터 읽음.
 * SUBSTR(str, pos, len) : 원본 문자열을 pos번째 문자열부터 읽고, len 길이 만큼 가져오세요.
 * SUBSTR의 pos는 음수도 받음. 음수인 경우 뒤에서 pos번째부터 읽음.
 */