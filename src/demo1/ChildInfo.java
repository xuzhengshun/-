package demo1;

import java.util.Date;

public class ChildInfo {

	private String userName;
	private Integer age;
	private Double height;
	private Date date;
	private Long times;
	private String flage;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getTimes() {
		return times;
	}
	public void setTimes(Long times) {
		this.times = times;
	}
	public String getFlage() {
		return flage;
	}
	public void setFlage(String flage) {
		this.flage = flage;
	}
	@Override
	public String toString() {
		return "ChildInfo [userName=" + userName + ", age=" + age + ", height=" + height + ", date=" + date + ", times="
				+ times + ", flage=" + flage + "]";
	}
	
}
