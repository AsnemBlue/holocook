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

import com.hc.holocook.dto.NoticeDto;


public class NoticeDao {
	public static final int SUCCESS 	= 1;
	public static final int FAIL 		= 0;
	
	private static NoticeDao instance = new NoticeDao();
	public static NoticeDao getInstance() {
		return instance;
	}
	private NoticeDao() {}
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
	
	//글 목록 최신순
	public ArrayList<NoticeDto> nList(int startrow, int endrow){
		ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= " SELECT * FROM (SELECT ROWNUM RN, L.* FROM " + 
								  " (SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID = A.AID ORDER BY NNO DESC) L) " + 
								  "	WHERE RN BETWEEN ? AND ?"; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, startrow 	);
			pstmt.setInt	(2, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		nNo    	=rs.getInt		("nNo"   	); 
				String 	nTitle 	=rs.getString	("nTitle"	);
				String 	nContent=rs.getString	("nContent"	);
				int 	nHit   	=rs.getInt		("nHit"  	);
				Date 	nRdate	=rs.getDate		("nRdate"	);
				String 	nIp    	=rs.getString	("nIp"   	);
				String 	aId     =rs.getString  	("aId"    	);
				String 	aName  	=rs.getString  	("aName"  	);
				dtos.add(new NoticeDto(nNo, nTitle, nContent, nHit, nRdate, nIp, aId, aName));
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
	public int nCount() {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM NOTICE";
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
	
	//글 작성
	public int nWrite(NoticeDto dto) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO NOTICE (NNO, NTITLE , NCONTENT , NIP, AID) " + 
					" VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?,?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, dto.getnTitle		());
			pstmt.setString	(2, dto.getnContent		());
			pstmt.setString	(3, dto.getnIp			());
			pstmt.setString	(4, dto.getaId			());
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
	
	//HIT 증가
	public int nHitUp(int nNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET NHIT = NHIT + 1 WHERE NNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, nNo	);
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
	//글 상세보기(DTO 가져오기)
	public NoticeDto nGetDto(int nNo) {
		NoticeDto 	  	  dto 	= null;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID = A.AID AND NNO=?";
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {                                                                                                    
				String 	nTitle 	 =rs.getString	("nTitle"	);
				String 	nContent =rs.getString	("nContent"	);
				int 	nHit   	 =rs.getInt		("nHit"  	);
				Date 	nRdate	 =rs.getDate	("nRdate"	);
				String 	nIp    	 =rs.getString	("nIp"   	);
				String 	aId      =rs.getString  ("aId"    	);
				String 	aName  	 =rs.getString  ("aName"  	);                                                            
				dto = new NoticeDto(nNo, nTitle, nContent, nHit, nRdate, nIp, aId, aName);
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
	
	//글 수정하기
	public int nModify(NoticeDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET NTITLE=?, NCONTENT=?,NIP=?, AID= ? WHERE NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getnTitle()	);
			pstmt.setString(2, dto.getnContent());
			pstmt.setString(3, dto.getnIp()		);
			pstmt.setString(4, dto.getaId()		);
			pstmt.setInt   (5, dto.getnNo()		);
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
	
	//글 삭제하기
	public int nDelete(int nNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICE WHERE NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, nNo	);
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
