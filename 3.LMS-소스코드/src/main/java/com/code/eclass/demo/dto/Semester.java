package com.code.eclass.demo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.code.eclass.demo.entity.ClassInfo;

public class Semester implements Comparable<Semester>{

	private String semester;
	private int year;
	private static Map<String,Integer> semesterOrder;
	
	static {
		semesterOrder = new HashMap<>();
		semesterOrder.put("1학기", 1);
		semesterOrder.put("여름학기", 2);
		semesterOrder.put("2학기", 3);
		semesterOrder.put("겨울학기", 4);
	}
	
	public Semester() {};
	public Semester(ClassInfo classInfo) {
		this.semester = classInfo.getClassSemester();
		this.year = classInfo.getClassYear();
	};
	@Override
	public String toString() {
		return year + "년 "+semester;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public int compareTo(Semester o) {
		// TODO Auto-generated method stub
		if(this.year > o.year) return 1;
		
		if(this.year < o.year) return -1;
		
		if(this.year == o.year) {
			
			if(semesterOrder.get(this.semester)>semesterOrder.get(o.semester)) return 1;
			if(semesterOrder.get(this.semester)<semesterOrder.get(o.semester)) return -1;
		}
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Semester)) return false;
		Semester s = (Semester)obj;
		if(this.semester.equals(s.semester) && this.year == s.year) return true;
		
		return false;
	}
	public static List<Semester> semesterDataTypeConverter(List<ClassInfo> classInfoList){
		
		List<Semester> semesterList = new ArrayList<>(classInfoList.size());
		for(ClassInfo classInfo : classInfoList) {
			Semester addSemester = new Semester(classInfo);
			if(semesterList.contains(addSemester)) continue;
			semesterList.add(new Semester(classInfo));
		}
		return semesterList;
	}
}
