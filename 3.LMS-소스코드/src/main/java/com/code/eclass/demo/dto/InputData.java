package com.code.eclass.demo.dto;

public class InputData {
	
	private String inputName;
	private String inputValue;
	
	@Override
	public String toString() {
		return "InputData [inputName=" + inputName + ", inputValue=" + inputValue + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public InputData() {};
	
	public InputData(String inputName,String inputValue) {
		this.inputName = inputName;
		this.inputValue = inputValue;
	};
	
}
