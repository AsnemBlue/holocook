----------------------------------------------------------------------
--                          Grade table                             --
----------------------------------------------------------------------
DROP TABLE GRADE;
CREATE TABLE GRADE(
    GNO NUMBER(2),
    GNAME VARCHAR2(100) NOT NULL,
    GLOW NUMBER(4),
    GHIGH NUMBER(4),
    PRIMARY KEY(GNO)
);
INSERT INTO GRADE VALUES(1,'요리초보',0,9);
INSERT INTO GRADE VALUES(2,'요리수련생',10,49);
INSERT INTO GRADE VALUES(3,'중급셰프',50,99);
INSERT INTO GRADE VALUES(4,'랭킹셰프',100,9999);
SELECT * FROM GRADE;
COMMIT;
----------------------------------------------------------------------
--                          Member table                             --
----------------------------------------------------------------------
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    MID VARCHAR2(100),
    MPW VARCHAR2(100) NOT NULL,
    MNICK VARCHAR2(100) NOT NULL,
    MNAME VARCHAR2(100) NOT NULL,
    MTEL VARCHAR2(100),
    MEMAIL VARCHAR2(100),
    MPHOTO VARCHAR2(100) DEFAULT 'default.png',
    MRDATE DATE DEFAULT SYSDATE,
    MBLACK NUMBER(2) DEFAULT 0,
    MLIKE NUMBER(4) DEFAULT 0,
    PRIMARY KEY(MID)
);
COMMIT;