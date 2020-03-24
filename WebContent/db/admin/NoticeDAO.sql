--1. 글목록 최신순 페이징
SELECT * FROM (SELECT ROWNUM RN, L.* FROM 
    (SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID = A.AID ORDER BY NNO DESC) L) 
    WHERE RN BETWEEN 1 AND 10;
--1. 총 글 갯수
SELECT COUNT(*) FROM NOTICE;
--2. 글쓰기
INSERT INTO NOTICE (NNO, NTITLE , NCONTENT , NIP, AID)
    VALUES(NOTICE_SEQ.NEXTVAL, '3/11공지', '코로나 19 위기 대응 단계가 ‘심각’으로 격상됨에 따라
추가 확산과 감염 예방을 위해 만개의레시피가 재택근무에 들어가게 되었습니다.


재택근무 기간에도 기본 업무는 진행됩니다.

단, 전화 문의 응대가 불가능해진 관계로

문의하기 또는 메일(help@10000recipe.com)로 문의를 남겨주시면 확인 후 조치하도록 하겠습니다.


코로나 19가 조속히 해결되길 바라며,
모든 분들의 안전을 기원합니다.


감사합니다.', '0.0.0.0','관리자1');
--2.1 글 자세히 보기
SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID = A.AID AND NNO=3;
--2.2 hit
UPDATE NOTICE SET NHIT = NHIT + 1 WHERE NNO = 3;
--3. 글 업데이트
UPDATE NOTICE SET NTITLE='3/11재 공지', NCONTENT='손 씻으세',NIP='1.1.1.1', AID= 'admin2' WHERE NNO=2;
--4.글 삭제
DELETE FROM NOTICE WHERE NNO=2;
delete from notice;
commit;