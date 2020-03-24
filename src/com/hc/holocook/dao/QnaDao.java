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

import com.hc.holocook.dto.QnaDto;


public class QnaDao {
	public static final int SUCCESS 	= 1;
	public static final int FAIL 		= 0;
	
	private static QnaDao instance = new QnaDao();
	public static QnaDao getInstance() {
		return instance;
	}
	private QnaDao() {}
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
	
	//글 목록 불러오기
	public ArrayList<QnaDto> qList(int startrow, int endrow){
		ArrayList<QnaDto> dtos = new ArrayList<QnaDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= " SELECT QNO, QTITLE, QCONTENT, QGROUP, QPNO, QSTEP, QINDENT, QFILE, QHIT ,QRDATE, QIP, AID, MID, " +
				 				  " (SELECT ANAME FROM ADMIN WHERE AID=A.AID) ANAME , " +
				 				  "	(SELECT MNAME FROM MEMBER WHERE MID=A.MID) MNAME, " + 
				 				  "	(SELECT MBLACK FROM MEMBER WHERE MID=A.MID) MBLACK " + 
								  " FROM (SELECT ROWNUM RN, L.* FROM " + 
								  " (SELECT * FROM QNA  ORDER BY QGROUP DESC, QSTEP) L) A " + 
								  " WHERE RN BETWEEN ? AND ?"; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, startrow 	);
			pstmt.setInt	(2, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		qNo    	=rs.getInt		("qNo"   	); 
				String 	qTitle 	=rs.getString	("qTitle"	);
				String 	qContent=rs.getString	("qContent"	);
				int 	qGroup  =rs.getInt		("qGroup"  	);
				int 	qPno   	=rs.getInt		("qPno"  	);
				int 	qStep  	=rs.getInt		("qStep"  	);
				int 	qIndent =rs.getInt		("qIndent"  );
				String 	qFile   =rs.getString	("qFile"   	);
				int 	qHit   	=rs.getInt		("qHit"  	);
				Timestamp 	qRdate	=rs.getTimestamp("qRdate"	);		
				String 	qIp    	=rs.getString	("qIp"   	);
				String 	aId     =rs.getString  	("aId"    	);
				String 	aName  	=rs.getString  	("aName"  	);
				String 	mId     =rs.getString  	("mId"    	);
				String 	mName  	=rs.getString  	("mName"  	);
				String 	mBlack  =rs.getString  	("mBlack"  	);
				dtos.add(new QnaDto(qNo, qTitle, qContent, qGroup, qPno, qStep, qIndent, qFile, qHit, qRdate, qIp, aId, aName, mId, mName, mBlack));
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
	
	
	//총 글갯수 카운트
	public int qCount() {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM QNA";
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
	//내가작성한 글목록
	public ArrayList<QnaDto> myqList(String mId, int startrow, int endrow){
		ArrayList<QnaDto> dtos = new ArrayList<QnaDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String 			  sql	= " SELECT QNO, QTITLE, QCONTENT, QGROUP, QPNO, QSTEP, QINDENT, QFILE, QHIT ,QRDATE, QIP, AID, MID, " +
				 				  " (SELECT ANAME FROM ADMIN WHERE AID=A.AID) ANAME , " +
				 				  "	(SELECT MNAME FROM MEMBER WHERE MID=A.MID) MNAME, " + 
				 				 "	(SELECT MBLACK FROM MEMBER WHERE MID=A.MID) MBLACK " + 
								  " FROM (SELECT ROWNUM RN, L.* FROM " + 
								  " (SELECT * FROM QNA  WHERE MID=? ORDER BY QNO DESC) L) A " + 
								  " WHERE RN BETWEEN ? AND ?"; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, mId			);
			pstmt.setInt	(2, startrow 	);
			pstmt.setInt	(3, endrow	 	);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int		qNo    	=rs.getInt		("qNo"   	); 
				String 	qTitle 	=rs.getString	("qTitle"	);
				String 	qContent=rs.getString	("qContent"	);
				int 	qGroup  =rs.getInt		("qGroup"  	);
				int 	qPno   	=rs.getInt		("qPno"  	);
				int 	qStep  	=rs.getInt		("qStep"  	);
				int 	qIndent =rs.getInt		("qIndent"  );
				String 	qFile   =rs.getString	("qFile"   	);
				int 	qHit   	=rs.getInt		("qHit"  	);
				Timestamp 	qRdate	=rs.getTimestamp("qRdate"	);		
				String 	qIp    	=rs.getString	("qIp"   	);
				String 	aId     =rs.getString  	("aId"    	);
				String 	aName  	=rs.getString  	("aName"  	);
				String 	mName  	=rs.getString  	("mName"  	);
				String 	mBlack  =rs.getString  	("mBlack"  	);
				dtos.add(new QnaDto(qNo, qTitle, qContent, qGroup, qPno, qStep, qIndent, qFile, qHit, qRdate, qIp, aId, aName, mId, mName, mBlack));
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
	//내가 작성한 글 갯수
	public int myqCount(String mId) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM QNA WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, mId	);
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
	public int qWrite(QnaDto dto) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO QNA (QNO ,QTITLE ,QCONTENT,QGROUP ,QFILE ,QIP ,AID, MID) " + 
				"    VALUES(QNA_SEQ.NEXTVAL, ?, ?, QNA_SEQ.CURRVAL, ?, ?,?,?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, dto.getqTitle	());
			pstmt.setString	(2, dto.getqContent	());
			pstmt.setString	(3, dto.getqFile	());
			pstmt.setString	(4, dto.getqIp		());
			pstmt.setString	(5, dto.getaId		());
			pstmt.setString	(6, dto.getmId		());
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
	
	//hit 증가
	public int qHitUp(int qNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QHIT = QHIT+1 WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, qNo	);
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
	
	//글조회 (dto가져오기)
	public QnaDto qGetDto(int qNo) {
		QnaDto 	  	      dto 	= null;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = " SELECT QNO, QTITLE, QCONTENT, QGROUP, QPNO, QSTEP, QINDENT, QFILE, QHIT ,QRDATE, QIP, AID, MID, " +
					 " (SELECT ANAME FROM ADMIN WHERE AID=Q.AID) ANAME, "+
					 " (SELECT MBLACK FROM MEMBER WHERE MID=Q.MID) MBLACK, "+
				     " (SELECT MNAME FROM MEMBER WHERE MID=Q.MID) MNAME  FROM QNA Q WHERE QNO=?";
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {                                                                                                    
				String 	qTitle 	=rs.getString	("qTitle"	);
				String 	qContent=rs.getString	("qContent"	);
				int 	qGroup  =rs.getInt		("qGroup"  	);
				int 	qPno   	=rs.getInt		("qPno"  	);
				int 	qStep  	=rs.getInt		("qStep"  	);
				int 	qIndent =rs.getInt		("qIndent"  );
				String 	qFile   =rs.getString	("qFile"   	);
				int 	qHit   	=rs.getInt		("qHit"  	);
				Timestamp 	qRdate	=rs.getTimestamp("qRdate");
				String 	qIp    	=rs.getString	("qIp"   	);
				String 	aId     =rs.getString  	("aId"    	);
				String 	aName  	=rs.getString  	("aName"  	);
				String 	mId     =rs.getString  	("mId"    	);
				String 	mName  	=rs.getString  	("mName"  	);                                                            
				String 	mBlack  =rs.getString  	("mBlack"  	);                                                            
				dto = new QnaDto(qNo, qTitle, qContent, qGroup, qPno, qStep, qIndent, qFile, qHit, qRdate, qIp, aId, aName, mId, mName, mBlack);
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
	public int qModify(String qTitle, String qContent, String qFile, String qIp, int qNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QTITLE=?, QCONTENT=?, QFILE=?, QIP=? WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qTitle	);
			pstmt.setString(2, qContent	);
			pstmt.setString(3, qFile	);
			pstmt.setString(4, qIp		);
			pstmt.setInt   (5, qNo		);
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
	
	//글 삭제
	public int qDelete(int qNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNA WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, qNo	);
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
	
	
	//답변글 달기
	//stepA ->원글에 달아놓은 다른 답변글이 답변 대상글 아래에 있는지 검사. 0일경우->stepB / 0보다 클경우->setpC
	public int qStepA(QnaDto dto) {
		int resultA = -1;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql =" SELECT NVL(MIN(QSTEP),0) FROM QNA " + 
					" WHERE  QGROUP = ? " + 
					" AND QSTEP > ? " + 
					" AND QINDENT <= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1 , dto.getqGroup	());
			pstmt.setInt	(2 , dto.getqStep	());
			pstmt.setInt	(3 , dto.getqIndent	());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultA = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs 	 != null) pstmt .close();
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return resultA;
	}
	//stepA=0일경우,-> 원글 그룹중 가장 아래의 qStep으로 insert
	public int qStepB(QnaDto dto) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO QNA (QNO ,QTITLE ,QCONTENT,QGROUP ,QSTEP ,QINDENT ,QFILE ,QIP ,QPNO, AID, MID) " + 
					" VALUES(QNA_SEQ.NEXTVAL, ?, ?, ?, (SELECT NVL(MAX(QSTEP),0) + 1 FROM QNA  WHERE QGROUP=?) , "+ 
					" ?, ?, ?,?,?,?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1 , dto.getqTitle	());
			pstmt.setString	(2 , dto.getqContent());
			pstmt.setInt	(3 , dto.getqGroup	());
			pstmt.setInt	(4 , dto.getqGroup	());
			pstmt.setInt	(5 , dto.getqIndent()+1);
			pstmt.setString	(6 , dto.getqFile	());
			pstmt.setString	(7 , dto.getqIp		());
			pstmt.setInt	(8 , dto.getqNo		());	//쿼리엔 qPno로 들어감. 대상글의 qno.
			pstmt.setString	(9 , dto.getaId		());
			pstmt.setString	(10, dto.getmId		());
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
	//끼여들어갈 자리 아래의 글들 qSetp+1
	public int qStepBeforeC(QnaDto dto, int resultA) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QSTEP = QSTEP + 1 WHERE QGROUP = ?  AND QSTEP >=? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, dto.getqGroup());
			pstmt.setInt   (2, resultA	);
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
	//들어갈 자리에 INSERT
	public int qStepC(QnaDto dto, int resultA) {
		int result = FAIL;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO QNA (QNO ,QTITLE ,QCONTENT,QGROUP ,QSTEP ,QINDENT ,QFILE ,QIP ,QPNO, AID, MID) " + 
					" VALUES(QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1 , dto.getqTitle	());
			pstmt.setString	(2 , dto.getqContent());
			pstmt.setInt	(3 , dto.getqGroup	());
			pstmt.setInt	(4 , resultA		  );
			pstmt.setInt	(5 , dto.getqIndent()+1);
			pstmt.setString	(6 , dto.getqFile	());
			pstmt.setString	(7 , dto.getqIp		());
			pstmt.setInt	(8 , dto.getqNo		());	//쿼리엔 qPno로 들어감. 대상글의 qno.
			pstmt.setString	(9 , dto.getaId		());
			pstmt.setString	(10, dto.getmId		());
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
