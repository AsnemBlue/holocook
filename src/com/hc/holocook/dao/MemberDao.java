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

import com.hc.holocook.dto.MemberDto;

public class MemberDao {
	public static final int NONEXISTENT = 0;
	public static final int EXISTENT 	= 1;
	public static final int SUCCESS 	= 1;
	public static final int FAIL 		= 0;
	
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {}
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
	
	//id 중복체크
	public int mIdChk(String mId) {
		int result=EXISTENT;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
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
	
	//별명 중복체크
	public int mNickChk(String mNick) {
		int result=EXISTENT;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MNICK=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mNick);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
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
	
	//회원가입
	public int mJoin(MemberDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (MID, MPW, MNICK, MNAME, MTEL, MEMAIL, MPHOTO) " +
					 "VALUES(?,?,?,?,?, ?, 'default.png')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId() 		);
			pstmt.setString(2, dto.getmPw() 		);
			pstmt.setString(3, dto.getmNick() 		);
			pstmt.setString(4, dto.getmName()		);
			pstmt.setString(5, dto.getmTel() 		);
			pstmt.setString(6, dto.getmEmail() 		);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//로그인
	public int mLogin(String mId, String mPw) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
		try {	
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = SUCCESS;
			}else {
				result = FAIL;
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
	
	//mId로 dto가져오기
	public MemberDto mGetDto(String mId){
		MemberDto dto = new MemberDto();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT M.*, GNO, GNAME FROM MEMBER M, GRADE " + 
					 "WHERE MLIKE BETWEEN GLOW AND GHIGH AND MID=? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String 	mPw		=rs.getString	("mPw"	 );
				String 	mNick	=rs.getString	("mNick" );
				String 	mName	=rs.getString	("mName" );
				String 	mTel	=rs.getString	("mTel"	 );
				String 	mEmail	=rs.getString	("mEmail");
				String 	mPhoto	=rs.getString	("mPhoto");
				Date 	mRdate	=rs.getDate		("mRdate");
				int 	mBlack	=rs.getInt		("mBlack");
				int 	mLike	=rs.getInt		("mLike" );
				int		gNo		=rs.getInt		("gNo"   );
				String	gName	=rs.getString	("gName" );
				dto = new MemberDto(mId, mPw, mNick, mName, mTel, mEmail, mPhoto, mRdate, mBlack, mLike, gNo, gName);
			}
		} catch (Exception e) {
			System.out.println("DAO : "+e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dto;
	}
	
	//정보 수정하기
	public int mModify(MemberDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MPW=?, MNICK=?, MNAME=?, "+
				 	 "MTEL=?, MEMAIL=?, MPHOTO=? WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw() 	);
			pstmt.setString(2, dto.getmNick()	);
			pstmt.setString(3, dto.getmName()	);
			pstmt.setString(4, dto.getmTel() 	);
			pstmt.setString(5, dto.getmEmail() 	);
			pstmt.setString(6, dto.getmPhoto()	);
			pstmt.setString(7, dto.getmId() 	);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//블랙하기
	public int mBlack(String mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MBLACK= MOD(MBLACK+1,2) WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//좋아요 상승
	public int mLikeUp(String mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MLIKE= MLIKE + 1 WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	//좋아요 하강
	public int mLikeDown(String mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MLIKE= MLIKE - 1 WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//회원 mId, mNick로 검색해서 목록 페이징
	public ArrayList<MemberDto> mList(String search_mId, int startrow, int endrow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
					"(SELECT M.*, GNO, GNAME FROM MEMBER M, GRADE " + 
					"WHERE MLIKE BETWEEN GLOW AND GHIGH " + 
					"AND (MID LIKE '%'||?||'%' OR MNICK LIKE '%'||?||'%' OR MNAME LIKE '%'||?||'%') " + 
					"ORDER BY MRDATE DESC) A) " + 
					"WHERE RN BETWEEN ? AND ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, search_mId	);
			pstmt.setString	(2, search_mId	);
			pstmt.setString	(3, search_mId	);
			pstmt.setInt	(4, startrow	);
			pstmt.setInt	(5, endrow		);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String 	mId		=rs.getString	("mId"	 );
				String 	mPw		=rs.getString	("mPw"	 );
				String 	mNick	=rs.getString	("mNick" );
				String 	mName	=rs.getString	("mName" );
				String 	mTel	=rs.getString	("mTel"	 );
				String 	mEmail	=rs.getString	("mEmail");
				String 	mPhoto	=rs.getString	("mPhoto");
				Date 	mRdate	=rs.getDate		("mRdate");
				int 	mBlack	=rs.getInt		("mBlack");
				int 	mLike	=rs.getInt		("mLike" );
				int		gNo		=rs.getInt		("gNo"   );
				String	gName	=rs.getString	("gName" );
				dtos.add(new MemberDto(mId, mPw, mNick, mName, mTel, mEmail, 
									   mPhoto, mRdate, mBlack, mLike, gNo, gName));
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
	//검색 count
	public int mListCount(String search_mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MID LIKE '%'||?||'%' OR MNICK LIKE '%'||?||'%' OR MNAME LIKE '%'||?||'%'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, search_mId	);
			pstmt.setString	(2, search_mId	);
			pstmt.setString	(3, search_mId	);
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
	//모든회원카운트
	public int mCountAll() {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
	
	//랭킹셰프 페이징 (gNo=4(랭킹셰프) 인 회원)
	public ArrayList<MemberDto> mListRanker(int startrow, int endrow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
					 "(SELECT M.*, GNO, GNAME FROM MEMBER M, GRADE " + 
					 "WHERE MLIKE BETWEEN GLOW AND GHIGH " + 
					 "AND GNO=4 " + 
					 "ORDER BY MLIKE DESC) A) " + 
					 "WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, startrow	);
			pstmt.setInt	(2, endrow		);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String 	mId		=rs.getString	("mId"	 );
				String 	mPw		=rs.getString	("mPw"	 );
				String 	mNick	=rs.getString	("mNick" );
				String 	mName	=rs.getString	("mName" );
				String 	mTel	=rs.getString	("mTel"	 );
				String 	mEmail	=rs.getString	("mEmail");
				String 	mPhoto	=rs.getString	("mPhoto");
				Date 	mRdate	=rs.getDate		("mRdate");
				int 	mBlack	=rs.getInt		("mBlack");
				int 	mLike	=rs.getInt		("mLike" );
				int		gNo		=rs.getInt		("gNo"   );
				String	gName	=rs.getString	("gName" );
				dtos.add(new MemberDto(mId, mPw, mNick, mName, mTel, mEmail, 
						mPhoto, mRdate, mBlack, mLike, gNo, gName));
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
	
	//랭킹셰프 카운팅(gNo=4(랭킹셰프) 인 회원)
	public int mCountRanker() {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER M, GRADE " + 
					 "WHERE MLIKE BETWEEN GLOW AND GHIGH " + 
					 "AND GNO=4";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
}
