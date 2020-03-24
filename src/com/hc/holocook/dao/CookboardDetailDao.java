package com.hc.holocook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hc.holocook.dto.CookboardDetailDto;


public class CookboardDetailDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	private CookboardDetailDao() {}
	private static CookboardDetailDao instance = new CookboardDetailDao();
	public static CookboardDetailDao getInstance() {
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
	
	//dto 불러오기
	public ArrayList<CookboardDetailDto> cbdGetDtos(int cbNo){
		ArrayList<CookboardDetailDto> dtos = new ArrayList<CookboardDetailDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 	sql = "SELECT * FROM COOKBOARDDETAIL WHERE CBNO=? AND CBDCOUNT>=CBDORDER ORDER BY CBDORDER";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, cbNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int 	cbdNo  		= rs.getInt   ("cbdNo"  	);
				int		cbdOrder    = rs.getInt   ("cbdOrder"  	);
				String	cbdImage    = rs.getString("cbdImage"  	);
				String	cbdContent  = rs.getString("cbdContent"	);
				int		cbdCount    = rs.getInt   ("cbdCount"  	);
				dtos.add(new CookboardDetailDto(cbdNo, cbNo, cbdOrder, cbdImage, cbdContent, cbdCount));
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
	
	//작성하기
	public int cbdWrite(CookboardDetailDto dto) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO COOKBOARDDETAIL (CBDNO, CBDORDER, CBDIMAGE, CBDCONTENT, CBDCOUNT, CBNO) " + 
					" VALUES (COOKBOARDDETAIL_SEQ.NEXTVAL, ?, ?, ?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, dto.getCbdOrder()	);
			pstmt.setString	(2, dto.getCbdImage()	);
			pstmt.setString	(3, dto.getCbdContent()	);
			pstmt.setInt	(4, dto.getCbdCount()	);
			pstmt.setInt	(5, dto.getCbNo()		);
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
	
	//수정하기
	public int cbdModify(int cbdOrder, String cbdImage, String cbdContent, int cbNo) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" UPDATE COOKBOARDDETAIL SET CBDIMAGE=?, CBDCONTENT=? WHERE CBNO=? AND CBDORDER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, cbdImage	);
			pstmt.setString	(2, cbdContent	);
			pstmt.setInt	(3, cbNo		);
			pstmt.setInt	(4, cbdOrder	);
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
	
	//삭제하기(cookboard 삭제시)
	public int cbdDelete(int cbNo) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" DELETE FROM COOKBOARDDETAIL WHERE CBNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, cbNo);
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
}
