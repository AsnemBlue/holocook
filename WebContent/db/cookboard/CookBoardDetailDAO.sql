--CBNO로 DTO 불러오기
SELECT * FROM COOKBOARDDETAIL WHERE CBNO=452 AND CBDCOUNT>=CBDORDER ORDER BY CBDORDER;
--작성하기 
INSERT INTO COOKBOARDDETAIL (CBDNO, CBDORDER, CBDIMAGE, CBDCONTENT, CBDCOUNT, CBNO)
        VALUES (COOKBOARDDETAIL_SEQ.NEXTVAL, 5, null, '채를 썹니다4',3,1);
--수정하기
UPDATE COOKBOARDDETAIL SET CBDIMAGE='default.png', CBDCONTENT='물을 끓입니다.1' WHERE CBNO=438 AND CBDORDER=1;

--삭제하기(COOKBOARD 삭제)
DELETE FROM COOKBOARDDETAIL WHERE CBNO=12;

select * from COOKBOARDDETAIL where cbno=450;
INSERT INTO COOKBOARDDETAIL VALUES (COOKBOARDDETAIL_SEQ.NEXTVAL, 4, 'test.png', '채를 썹니다4',860);
SELECT * FROM COOKBOARDDETAIL order by cbdno desc;
SELECT * FROM COOKBOARD order by cbno desc;
commit;
ROLLBACK;
SELECT COOKBOARDDETAIL_SEQ.CURRVAL FROM DUAL;
select * from COOKBOARDDETAIL where cbno=850;
