package com.hc.holocook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hc.holocook.dto.CookboardReDto;

public class CookboardReDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	private CookboardReDao() {}
	private static CookboardReDao instance = new CookboardReDao();
	public static CookboardReDao getInstance() {
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
	
	//댓글 목록 불러오기
	public ArrayList<CookboardReDto> cbrGetDtos(int cbNo, int startrow, int endrow){
		ArrayList<CookboardReDto> dtos = new ArrayList<CookboardReDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 	sql = 	" SELECT * FROM(SELECT ROWNUM RN, A.* FROM( " + 
						" SELECT C.*, (SELECT MNICK FROM MEMBER WHERE MID=C.MID ) MNICK, "+
						" (SELECT MPHOTO FROM MEMBER WHERE MID=C.MID ) MPHOTO, "+
						" (SELECT MBLACK FROM MEMBER WHERE MID=C.MID ) MBLACK "+
						" FROM COOKBOARDRE C WHERE CBNO=? ORDER BY CBRNO DESC " + 
						" ) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbNo	);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow	);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int		cbrNo		= rs.getInt		("cbrNo"		);
				String	cbrContent  = rs.getString	("cbrContent" 	);
				String	cbrIP  		= rs.getString	("cbrIP"		);
				Timestamp cbrRdate	= rs.getTimestamp("cbrRdate"		);
				String	mId  		= rs.getString	("mId"			);
				String	mNick  		= rs.getString	("mNick"		);
				String	mPhoto  	= rs.getString	("mPhoto"		);
				String	mBlack  	= rs.getString	("mBlack"		);
				dtos.add(new CookboardReDto(cbrNo, cbrContent, cbrIP, cbrRdate, cbNo, mId, mNick, mPhoto, mBlack));
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
	
	//총 댓글 갯수
	public int cbrCount(int cbNo) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM COOKBOARDRE WHERE CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbNo);
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
	
	//댓글쓰기
	public int cbrWrite(int cbNo, String mId, String cbrContent, String cbrIp) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO COOKBOARDRE (CBRNO,CBNO, MID, CBRCONTENT, CBRIP) VALUES " +
					" (COOKBOARDRE_SEQ.NEXTVAL, ?,?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, cbNo		);
			pstmt.setString	(2, mId			);
			pstmt.setString	(3, cbrContent	);
			pstmt.setString	(4, cbrIp		);
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
	
	//댓글 삭제
	public int cbrDelete(int cbrNo, int cbNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM COOKBOARDRE WHERE CBRNO=? AND CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, cbrNo);
			pstmt.setInt   (2, cbNo	);
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
	
	
	//cookboard 삭제 시
	public int cbrDeleteAll(int cbNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM COOKBOARDRE WHERE CBNO=?";
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
