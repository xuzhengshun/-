package demo2;

import java.util.Date;

/**
 * 
 * @Description:TODO
 * @exception:
 * @author: 徐正顺
 * @time:2017年9月25日 上午10:46:46
 */
public class Person {
	private Integer id;
	private int age;
	private String name;
	private String address;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	@Override
	public String toString() {
		return "Person [id=" + id + ", age=" + age + ", name=" + name + ", address=" + address + ", date=" + date + "]";
	}
	

}
