package com.hc.holocook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hc.holocook.dto.CookboardDto;

public class CookboardDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	private CookboardDao() {}
	private static CookboardDao instance = new CookboardDao();
	public static CookboardDao getInstance() {
		return instance;
	}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	//글목록 최신순
	// keyword : 재료, 제목 검색.
	// cbcOption:게시판 번호  ->1,2
	// orderOption: cBno->최신순, cLike->좋아요순
	public ArrayList<CookboardDto> cbList(String keyword,String cbcNoOption, int startrow, int endrow, String orderOption){
		ArrayList<CookboardDto> dtos = new ArrayList<CookboardDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= ""; 
		if(orderOption.equals("cbNo")) {
		sql = 	"	 SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( " + 
				"    SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C " + 
				"    WHERE (CBINGREDIENT LIKE '%'||?||'%'  OR CBTITLE LIKE '%'||?||'%') AND CBCNO like '%'||?||'%' " + 
				"    ORDER BY CBNO DESC, CBLIKE DESC) A) AL, MEMBER M" + 
				"    WHERE M.MID=AL.MID AND RN BETWEEN ? AND ?";
		}
		if(orderOption.equals("cbLike")) {
			sql = 	"	 SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( " + 
					"    SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C " + 
					"    WHERE (CBINGREDIENT LIKE '%'||?||'%' OR CBTITLE LIKE '%'||?||'%') AND CBCNO like '%'||?||'%' " + 
					"    ORDER BY CBLIKE DESC, CBNO DESC) A) AL, MEMBER M" + 
					"    WHERE M.MID=AL.MID AND RN BETWEEN ? AND ?";
		}
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, keyword	 	);
			pstmt.setString	(2, keyword	 	);
			pstmt.setString	(3, cbcNoOption	);
			pstmt.setInt	(4, startrow 	);
			pstmt.setInt	(5, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		cbNo    	 =rs.getInt		("cbNo"   		); 
				String 	cbTitle 	 =rs.getString	("cbTitle"		);
				String 	cbImage		 =rs.getString	("cbImage"		);
				String 	cbIngredient =rs.getString	("cbIngredient"	);
				int 	cbHit   	 =rs.getInt		("cbHit"  		);
				Date 	cbRdate		 =rs.getDate	("cbRdate"		);
				String 	cbIp    	 =rs.getString	("cbIp"   		);
				int 	cbcNo    	 =rs.getInt		("cbcNo"   		);
				String 	mId     	 =rs.getString  ("mId"    		);
				String 	cbLike  	 =rs.getString  ("cbLike"  		);
				String 	mNick  	 	 =rs.getString  ("mNick"  		);
				String 	mPhoto  	 =rs.getString  ("mPhoto"  		);
				dtos.add(new CookboardDto(cbNo, cbTitle, cbImage, cbIngredient, cbHit, cbRdate, cbIp, cbcNo, mId, cbLike, mNick, mPhoto));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dtos;
	}
	
	
	//총 글 갯수
	public int cbCount(String cbcNoOption) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM COOKBOARD WHERE CBCNO like '%'||?||'%'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, cbcNoOption);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	//좋아요 누른 레시피 목록 출력
	public ArrayList<CookboardDto> cbMyLikeList(String keyword, int startrow, int endrow, String my_mId){
		ArrayList<CookboardDto> dtos = new ArrayList<CookboardDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				" (SELECT C.*, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C, LIKEHISTORY L " + 
				" WHERE (CBINGREDIENT LIKE '%'||?||'%' OR CBTITLE LIKE '%'||?||'%') AND C.CBNO = L.CBNO AND L.MID=? " + 
				" ORDER BY C.CBNO DESC) A) AL, MEMBER M " + 
				" WHERE M.MID=AL.MID AND RN BETWEEN ? AND ?"; 
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, keyword	 	);
			pstmt.setString	(2, keyword	 	);
			pstmt.setString	(3, my_mId	 	);
			pstmt.setInt	(4, startrow 	);
			pstmt.setInt	(5, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		cbNo    	 =rs.getInt		("cbNo"   		); 
				String 	cbTitle 	 =rs.getString	("cbTitle"		);
				String 	cbImage		 =rs.getString	("cbImage"		);
				String 	cbIngredient =rs.getString	("cbIngredient"	);
				int 	cbHit   	 =rs.getInt		("cbHit"  		);
				Date 	cbRdate		 =rs.getDate	("cbRdate"		);
				String 	cbIp    	 =rs.getString	("cbIp"   		);
				int 	cbcNo    	 =rs.getInt		("cbcNo"   		);
				String 	mId     	 =rs.getString  ("mId"    		);
				String 	cbLike     	 =rs.getString  ("cbLike"    	);
				String 	mNick  	 	 =rs.getString  ("mNick"  		);
				String 	mPhoto  	 =rs.getString  ("mPhoto"  		);
				dtos.add(new CookboardDto(cbNo, cbTitle, cbImage, cbIngredient, cbHit, cbRdate, cbIp, cbcNo, mId, cbLike, mNick, mPhoto));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dtos;
	}
	//좋아요 누른 레시피 갯수
	public int cbMyLikeListCount(String my_mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM COOKBOARD C, LIKEHISTORY L WHERE C.CBNO=L.CBNO AND L.MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, my_mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//내가 등록한 레시피 리스트 출력
	public ArrayList<CookboardDto> cbMyList(String keyword, int startrow, int endrow, String my_mId){
		ArrayList<CookboardDto> dtos = new ArrayList<CookboardDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
									"    (SELECT C.* , (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C " + 
									"    WHERE (CBINGREDIENT LIKE '%'||?||'%' OR CBTITLE LIKE '%'||?||'%') AND MID=? " + 
									"    ORDER BY CBNO DESC) A) AL, MEMBER M " + 
									"    WHERE M.MID=AL.MID AND RN BETWEEN ? AND ?"; 
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, keyword	 	);
			pstmt.setString	(2, keyword	 	);
			pstmt.setString	(3, my_mId	 	);
			pstmt.setInt	(4, startrow 	);
			pstmt.setInt	(5, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		cbNo    	 =rs.getInt		("cbNo"   		); 
				String 	cbTitle 	 =rs.getString	("cbTitle"		);
				String 	cbImage		 =rs.getString	("cbImage"		);
				String 	cbIngredient =rs.getString	("cbIngredient"	);
				int 	cbHit   	 =rs.getInt		("cbHit"  		);
				Date 	cbRdate		 =rs.getDate	("cbRdate"		);
				String 	cbIp    	 =rs.getString	("cbIp"   		);
				int 	cbcNo    	 =rs.getInt		("cbcNo"   		);
				String 	mId     	 =rs.getString  ("mId"    		);
				String 	cbLike     	 =rs.getString  ("cbLike"    	);
				String 	mNick  	 	 =rs.getString  ("mNick"  		);
				String 	mPhoto  	 =rs.getString  ("mPhoto"  		);
				dtos.add(new CookboardDto(cbNo, cbTitle, cbImage, cbIngredient, cbHit, cbRdate, cbIp, cbcNo, mId, cbLike, mNick, mPhoto));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dtos;
	}
	//내가 등록한 레시피 갯수
	public int cbMyListCount(String my_mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM COOKBOARD WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, my_mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	//글쓰기 step A(작성할 cbNO 부여 받기.)
	public int cbGetcbNo() {
		int cbNo = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql =" SELECT COOKBOARD_SEQ.NEXTVAL CBNO FROM DUAL";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cbNo = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	 != null) rs	.close();
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return cbNo;
	} 
	//글쓰기
	public int cbWrite(CookboardDto dto) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO COOKBOARD (CBNO ,CBTITLE, CBIMAGE, CBINGREDIENT, CBIP, CBCNO, MID) " + 
					" VALUES(?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, dto.getCbNo			());
			pstmt.setString	(2, dto.getCbTitle		());
			pstmt.setString	(3, dto.getCbImage		());
			pstmt.setString	(4, dto.getCbIngredient	());
			pstmt.setString	(5, dto.getCbIp			());
			pstmt.setInt	(6, dto.getCbcNo		());
			pstmt.setString	(7, dto.getmId			());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	} 
	
	//hit 올리기
	public void cbHitUp(int cbNo) {
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql ="UPDATE COOKBOARD SET CBHIT = CBHIT+1 WHERE CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	//cookboard dto가져오기 by cbNo

	public CookboardDto cbGetDto(int cbNo) {
		CookboardDto 	  dto 	= null;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT C.*,M.MNICK,M.MPHOTO, (SELECT COUNT(*) FROM LIKEHISTORY WHERE  CBNO=C.CBNO) CBLIKE FROM COOKBOARD C, MEMBER M WHERE  M.MID=C.MID AND CBNO=?";
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {                                                                                                    
				String 	cbTitle 	 =rs.getString	("cbTitle"		);
				String 	cbImage		 =rs.getString	("cbImage"		);
				String 	cbIngredient =rs.getString	("cbIngredient"	);
				int 	cbHit   	 =rs.getInt		("cbHit"  		);
				Date 	cbRdate		 =rs.getDate	("cbRdate"		);
				String 	cbIp    	 =rs.getString	("cbIp"   		);
				int 	cbcNo    	 =rs.getInt		("cbcNo"   		);
				String 	mId     	 =rs.getString  ("mId"    		);
				String 	cbLike  	 =rs.getString  ("cbLike"  		);                                                            
				String 	mNick  	 	 =rs.getString  ("mNick"  		);                                                            
				String 	mPhoto  	 =rs.getString  ("mPhoto"  		);                                                            
				dto = new CookboardDto(cbNo, cbTitle, cbImage, cbIngredient, cbHit, cbRdate, cbIp, cbcNo, mId, cbLike, mNick, mPhoto);
			}                                                                                                                     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	 != null) rs	.close();
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return dto;
	}
	
	//수정하기
	public int cbModify(int cbNo, String cbTitle, String cbImage, String cbIngredient, String cbIp, int cbcNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE COOKBOARD SET CBTITLE=?, CBIMAGE=?, CBINGREDIENT=?, CBIP=?, CBCNO=? WHERE CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cbTitle		);
			pstmt.setString(2, cbImage		);
			pstmt.setString(3, cbIngredient	);
			pstmt.setString(4, cbIp			);
			pstmt.setInt   (5, cbcNo		);
			pstmt.setInt   (6, cbNo			);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	
	//삭제하기
	public int cbDelete(int cbNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM COOKBOARD WHERE CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, cbNo	);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
}