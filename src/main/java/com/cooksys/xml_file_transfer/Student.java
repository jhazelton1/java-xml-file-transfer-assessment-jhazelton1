package com.cooksys.xml_file_transfer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Student {
	
	
	private String userName;
	
	private Date date;
	
	private String fileName;
	
	private byte[] contents;
	
	public Student() {
		
	}

	public Student(String userName, Date date, String fileName, byte[] contents) {
		this.userName = userName;
		this.date = date;
		this.fileName = fileName;
		this.contents = contents;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContents() {
		return contents;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
	}
	
	public String getFormattedDate() {
		return new SimpleDateFormat("yy-MM-dd").format(this.date);
	}
	
	
	
	

}
