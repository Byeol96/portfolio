package com.code.eclass.demo.dao;

import java.time.LocalDate;
import java.util.Map;



public interface EclassDaoJDBC {
	

	Map<Integer, String> getAttendanceInfos(String userName, LocalDate startDate, int classNumber);
}
