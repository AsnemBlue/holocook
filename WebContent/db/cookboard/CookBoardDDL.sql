----------------------------------------------------------------------
--                          CookBoardClass                             --
----------------------------------------------------------------------
DROP TABLE COOKBOARDCLASS;
CREATE TABLE COOKBOARDCLASS(
    CBCNO NUMBER(4),
    CBCNAME VARCHAR2(30) NOT NULL,
    PRIMARY KEY(CBCNO)
);
INSERT INTO COOKBOARDCLASS VALUES (1, '알뜰쿠킹');
INSERT INTO COOKBOARDCLASS VALUES (2, '욜로쿠킹');
SELECT * FROM COOKBOARDCLASS;
COMMIT;
----------------------------------------------------------------------
--                          CookBoard                                   --
----------------------------------------------------------------------
DROP TABLE COOKBOARD;
DROP SEQUENCE COOKBOARD_SEQ;
CREATE SEQUENCE COOKBOARD_SEQ MAXVALUE 9999 NOCACHE NOCYCLE;
CREATE TABLE COOKBOARD(
    CBNO NUMBER(4),
    CBTITLE VARCHAR2(100) NOT NULL,
    CBIMAGE VARCHAR2(100),
    CBINGREDIENT VARCHAR2(300) NOT NULL,
    CBHIT NUMBER(4) DEFAULT 0,
    CBRDATE DATE DEFAULT SYSDATE,
    CBIP VARCHAR2(100) NOT NULL,
    CBCNO NOT NULL,
    MID NOT NULL,
    PRIMARY KEY(CBNO),
    FOREIGN KEY(CBCNO) REFERENCES COOKBOARDCLASS,
    FOREIGN KEY(MID) REFERENCES MEMBER
);



