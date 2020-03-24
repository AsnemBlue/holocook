----------------------------------------------------------------------
--                                 Notice                                  --
----------------------------------------------------------------------
DROP TABLE NOTICE;
DROP SEQUENCE NOTICE_SEQ;
CREATE SEQUENCE NOTICE_SEQ MAXVALUE 9999 NOCACHE NOCYCLE;
CREATE TABLE NOTICE(
    NNO NUMBER(4),
    NTITLE VARCHAR2(100),
    NCONTENT VARCHAR2(1000),
    NHIT NUMBER(8) DEFAULT 0,
    NRDATE DATE DEFAULT SYSDATE,
    NIP  VARCHAR2(100) NOT NULL,
    AID NOT NULL,
    PRIMARY KEY(NNO),
    FOREIGN KEY(AID) REFERENCES ADMIN
);

COMMIT;