----------------------------------------------------------------------
--                                  Qna                                    --
----------------------------------------------------------------------
DROP TABLE QNA;
DROP SEQUENCE QNA_SEQ;
CREATE SEQUENCE QNA_SEQ MAXVALUE 9999 NOCACHE NOCYCLE;
CREATE TABLE QNA(
    QNO NUMBER(4),
    QTITLE VARCHAR2(100) NOT NULL,
    QCONTENT VARCHAR2(1000),
    QGROUP NUMBER(4),
    QPNO NUMBER(4) DEFAULT 0,
    QSTEP NUMBER(4) DEFAULT 0,
    QINDENT NUMBER(4) DEFAULT 0,
    QFILE VARCHAR2(100),
    QHIT NUMBER(8) DEFAULT 0,
    QRDATE DATE DEFAULT SYSDATE,
    QIP VARCHAR2(100) NOT NULL,
    AID,
    MID,
    PRIMARY KEY(QNO),
    FOREIGN KEY(AID) REFERENCES ADMIN,
    FOREIGN KEY(MID) REFERENCES MEMBER
);
COMMIT;