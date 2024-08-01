-- https://school.programmers.co.kr/learn/courses/30/lessons/132204
SELECT ap.APNT_NO, p.PT_NAME, ap.PT_NO, ap.MCDP_CD, d.DR_NAME, ap.APNT_YMD FROM APPOINTMENT ap
INNER JOIN PATIENT p ON ap.PT_NO = p.PT_NO
INNER JOIN DOCTOR d ON ap.MDDR_ID = d.DR_ID
WHERE date_format(APNT_YMD, '%Y-%m-%d') = '2022-04-13' 
AND ap.MCDP_CD LIKE 'CS' 
AND APNT_CNCL_YN NOT LIKE 'Y'
ORDER BY ap.APNT_YMD;

-- 참고로 두 테이블을 JOIN 할 경우 ON을 사용할 컬럼이 같다면,
-- USING을 사용할 수 있다.

SELECT ap.APNT_NO, p.PT_NAME, ap.PT_NO, ap.MCDP_CD, d.DR_NAME, ap.APNT_YMD FROM APPOINTMENT ap
INNER JOIN PATIENT p USING (PT_NO) -- ON ap.PT_NO = p.PT_NO
INNER JOIN DOCTOR d ON ap.MDDR_ID = d.DR_ID
WHERE date_format(APNT_YMD, '%Y-%m-%d') = '2022-04-13' 
AND ap.MCDP_CD LIKE 'CS' 
AND APNT_CNCL_YN NOT LIKE 'Y'
ORDER BY ap.APNT_YMD;