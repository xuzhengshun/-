package demo2;

import java.util.Date;

public class ChinesePeople {
	private Integer id;
	private int age;
	private String name;
	private String address;
	private Date date;
	private String city;
	private String petName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	@Override
	public String toString() {
		return "ChinesePeople [id=" + id + ", age=" + age + ", name=" + name + ", address=" + address + ", date=" + date
				+ ", city=" + city + ", petName=" + petName + "]";
	}
	
}
