-- https://school.programmers.co.kr/learn/courses/30/lessons/132202
-- 소요 시간 : 6분 18초
SELECT MCDP_CD as `진료과코드`, count(APNT_YMD) as `5월예약건수` FROM APPOINTMENT
WHERE YEAR(APNT_YMD) = 2022 AND MONTH(APNT_YMD) = 5
GROUP BY MCDP_CD
ORDER BY `5월예약건수`, `진료과코드`;