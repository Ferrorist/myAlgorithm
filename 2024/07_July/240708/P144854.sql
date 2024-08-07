/*
* https://school.programmers.co.kr/learn/courses/30/lessons/144854
*/
SELECT b.BOOK_ID, a.AUTHOR_NAME, date_format(b.PUBLISHED_DATE, "%Y-%m-%d") as 'PUBLISHED_DATE' FROM BOOK b
INNER JOIN AUTHOR a ON b.AUTHOR_ID = a.AUTHOR_ID
WHERE b.CATEGORY = '경제'
ORDER BY b.PUBLISHED_DATE;