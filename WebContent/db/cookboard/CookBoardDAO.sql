--글목록 불러오기(최신순+재료로 검색)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (
    SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C 
    WHERE (CBINGREDIENT LIKE '%'||'마늘'||'%' OR CBTITLE LIKE '%'||'마늘'||'%') AND CBCNO like '%'||'1'||'%'
    ORDER BY CBNO DESC, CBLIKE DESC) A) AL, MEMBER M
    WHERE M.MID=AL.MID AND RN BETWEEN 1 AND 5;
--글목록 불러오기(좋아요순+ 재료로 검색)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C 
    WHERE (CBINGREDIENT LIKE '%'||'마늘'||'%' OR CBTITLE LIKE '%'||'마늘'||'%') AND CBCNO like '%'||'1'||'%'
    ORDER BY CBLIKE DESC, CBNO DESC) A) AL, MEMBER M
    WHERE M.MID=AL.MID AND RN BETWEEN 1 AND 10;
--좋아 누른 목록 불러오기
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT C.* , (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C, LIKEHISTORY L
    WHERE (CBINGREDIENT LIKE '%'||'마늘'||'%' OR CBTITLE LIKE '%'||'마늘'||'%') AND C.CBNO = L.CBNO AND L.MID='ccc1'
    ORDER BY C.CBNO DESC) A) AL, MEMBER M
    WHERE M.MID=AL.MID AND RN BETWEEN 1 AND 50;
--글 총 갯수
SELECT COUNT(*) FROM COOKBOARD WHERE CBCNO like '%'||'1'||'%';
--좋아요 누른 목록 총 갯수
SELECT COUNT(*) FROM COOKBOARD C, LIKEHISTORY L WHERE C.CBNO=L.CBNO AND L.MID='ccc3';

--내가 작성한 레시피 목록 불러오기
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C
    WHERE (CBINGREDIENT LIKE '%'||'마늘'||'%' OR CBTITLE LIKE '%'||'마늘'||'%') AND MID='ccc1'
    ORDER BY CBNO DESC) A) AL, MEMBER M
    WHERE M.MID=AL.MID AND RN BETWEEN 1 AND 50;
--내가 작성한 레시피 총 갯수
SELECT COUNT(*) FROM COOKBOARD WHERE MID='ccc1';

--글쓰기
INSERT INTO COOKBOARD (CBNO ,CBTITLE, CBIMAGE, CBINGREDIENT, CBIP, CBCNO, MID)
    VALUES(COOKBOARD_SEQ.NEXTVAL,'알리오올리오',NULL,'마늘','0.0.0.0',2,'AAAA');
--HIT 올리기
UPDATE COOKBOARD SET CBHIT = CBHIT+1 WHERE CBNO=11;
--글조회(CBNO로 DTO가져오기)
SELECT C.*,M.MNICK,M.MPHOTO, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C, MEMBER M WHERE  M.MID=C.MID AND CBNO=850;
--글수정하기
UPDATE COOKBOARD SET CBTITLE='WW', CBIMAGE=NULL, CBINGREDIENT='양파', CBIP='1.1.1.1', CBCNO=1 WHERE CBNO=11;
--글삭제하기
DELETE FROM COOKBOARD WHERE CBNO=450;

SELECT
    *
FROM COOKBOARD ORDER BY CBNO DESC;
UPDATE COOKBOARD SET CBIMAGE='1.jpg';
commit;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (
    SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C 
    WHERE (CBINGREDIENT LIKE '%'||'마늘'||'%' OR CBTITLE LIKE '%'||'마늘'||'%') AND CBCNO like '%'||'1'||'%'
    ORDER BY CBNO DESC, CBLIKE DESC) A) AL, MEMBER M
    WHERE M.MID=AL.MID AND RN BETWEEN 1 AND 5;
SELECT * FROM COOKBOARD WHERE MID like 'eee%';
SELECT * FROM COOKBOARD WHERE CBNO=336;