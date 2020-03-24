----------------------------------------------------------------------
--                           LikeHistory                                 --
----------------------------------------------------------------------
DROP TABLE LIKEHISTORY;
DROP SEQUENCE LIKEHISTORY_SEQ;
CREATE SEQUENCE LIKEHISTORY_SEQ MAXVALUE 9999 NOCACHE NOCYCLE;
CREATE TABLE LIKEHISTORY(
    INO NUMBER(4),
    CBNO NOT NULL,
    MID NOT NULL,
    PRIMARY KEY(INO),
    FOREIGN KEY(CBNO) REFERENCES COOKBOARD,
    FOREIGN KEY(MID) REFERENCES MEMBER
);
COMMIT;