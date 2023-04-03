package com.code.eclass.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.eclass.demo.dto.AttendanceInfo;

@Repository
public class EclassDaoJDBCImpl implements EclassDaoJDBC {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public Map<Integer,String> getAttendanceInfos(String userName, LocalDate startDate,int classNumber) {
		// TODO Auto-generated method stub
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Integer,String> AttendanceInfos = new HashMap<>();
		try {
			conn = dataSource.getConnection();
			String sql = "select lecture_number, max("
					+ "if( date_add(?,INTERVAL (lecture_weeks-1) WEEK) <= read_date  AND read_date < lecture_endDate , 2 ,"
					+ "	if( date_add(?	,INTERVAL (lecture_weeks-1) week) > read_date OR "
					+ " date_add(lecture_endDate,INTERVAL 1 WEEK) < read_date, 0 ,1))) as attendance from lecturereadrecord "
					+ "natural join lecture where username = ? AND  class_number = ? group by lecture_number";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,startDate.toString());
			pstmt.setString(2,startDate.toString());
			pstmt.setString(3,userName);
			pstmt.setInt(4,classNumber);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				int lectureNumber = rs.getInt("lecture_number");
				int attendanceNumber = rs.getInt("attendance");
				String attendance = attendanceNumber==0?"결석": attendanceNumber==1? "지각" :"출석";
				
				AttendanceInfos.put(lectureNumber,attendance);
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close();}catch(Exception e) {};
			if(pstmt!=null) try { pstmt.close();}catch(Exception e) {};
			if(rs!=null) try { rs.close();}catch(Exception e) {};
		}
		return AttendanceInfos;
	}
	
	
}
