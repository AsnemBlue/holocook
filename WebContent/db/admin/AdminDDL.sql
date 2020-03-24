----------------------------------------------------------------------
--                                 Admin                                  --
----------------------------------------------------------------------
DROP TABLE ADMIN;
CREATE TABLE ADMIN( 
    AID VARCHAR2(100),
    APW VARCHAR2(100) NOT NULL,
    ANAME VARCHAR2(100) NOT NULL,
    PRIMARY KEY(AID)
);
INSERT INTO ADMIN VALUES('관리자1', '1111', '관리자');
INSERT INTO ADMIN VALUES('관리자2', '2222', '관리자');
INSERT INTO ADMIN VALUES('관리자3', '3333', '관리자');
COMMIT;