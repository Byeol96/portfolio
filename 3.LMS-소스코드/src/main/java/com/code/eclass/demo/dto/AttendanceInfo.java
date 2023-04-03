package com.code.eclass.demo.dto;

public class AttendanceInfo {
	
	private Integer lectureNumber;
	private Integer Attendance;
	
	public AttendanceInfo(){}
	
	public AttendanceInfo(int lectureNumber,int Attendance){
		this.lectureNumber = lectureNumber;
		this.Attendance = Attendance;
	}
	@Override
	public String toString() {
		return "AttendanceInfo [lectureNumber=" + lectureNumber + ", Attendance=" + Attendance + "]";
	}
	public Integer getLectureNumber() {
		return lectureNumber;
	}
	public void setLectureNumber(Integer lectureNumber) {
		this.lectureNumber = lectureNumber;
	}
	public Integer getAttendance() {
		return Attendance;
	}
	public void setAttendance(Integer attendance) {
		Attendance = attendance;
	}
	
	
	
}
