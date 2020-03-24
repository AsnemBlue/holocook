import java.sql.Date;
import java.text.SimpleDateFormat;

public class temp {
	public static void main(String[] args) {
//		String[] arr = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii", "jjj"};
//		
//		
//		
		for(int i=1 ; i<11; i++) {
			
			System.out.println(
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'aaa"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'bbb"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'ccc"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'ddd"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'eee"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'fff"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'ggg"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'hhh"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'iii"+i+"');" + 
					"INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,336,'jjj"+i+"');"
			);		
		}
		
	}
}
