-- https://school.programmers.co.kr/learn/courses/30/lessons/132203
-- 소요 시간 : 4분 47초
SELECT DR_NAME, DR_ID, MCDP_CD, date_format(HIRE_YMD, "%Y-%m-%d") as HIRE_YMD FROM DOCTOR
WHERE MCDP_CD in ("CS", "GS")
ORDER BY HIRE_YMD DESC, DR_NAME ASC;
-- 다중 정렬(order by)은 쉼표(,)를 사용.
-- HIRE_YMD 를 먼저 정렬한 후 이후 DR_NAME으로 정렬.
