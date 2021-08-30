package ltd.newbee.mall.entity;

import java.util.Arrays;

public class Pension {
	private Long id;
	private Long paidInsuranceMonth;
	private Long allExemptionMonth;
	private Long oneQuarterPaidMonth;
	private Long halfPaidMonth;
	private Long threeQuarterPaidMonth;
	private Long age;
	private String name;
	private String address;
	private String sexual;
	private Long phone;
	private Long birthday;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPaidInsuranceMonth() {
		return paidInsuranceMonth;
	}
	public void setPaidInsuranceMonth(Long paidInsuranceMonth) {
		this.paidInsuranceMonth = paidInsuranceMonth;
	}
	public Long getAllExemptionMonth() {
		return allExemptionMonth;
	}
	public void setAllExemptionMonth(Long allExemptionMonth) {
		this.allExemptionMonth = allExemptionMonth;
	}
	public Long getOneQuarterPaidMonth() {
		return oneQuarterPaidMonth;
	}
	public void setOneQuarterPaidMonth(Long oneQuarterPaidMonth) {
		this.oneQuarterPaidMonth = oneQuarterPaidMonth;
	}
	public Long getHalfPaidMonth() {
		return halfPaidMonth;
	}
	public void setHalfPaidMonth(Long halfPaidMonth) {
		this.halfPaidMonth = halfPaidMonth;
	}
	public Long getThreeQuarterPaidMonth() {
		return threeQuarterPaidMonth;
	}
	public void setThreeQuarterPaidMonth(Long threeQuarterPaidMonth) {
		this.threeQuarterPaidMonth = threeQuarterPaidMonth;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
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
	public String getSexual() {
		return sexual;
	}
	public void setSexual(String sexual) {
		this.sexual = sexual;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Pension [id=" + id + ", paidInsuranceMonth=" + paidInsuranceMonth + ", allExemptionMonth="
				+ allExemptionMonth + ", oneQuarterPaidMonth=" + oneQuarterPaidMonth + ", halfPaidMonth="
				+ halfPaidMonth + ", threeQuarterPaidMonth=" + threeQuarterPaidMonth + ", age=" + age + ", name=" + name
				+ ", address=" + address + ", sexual=" + sexual + ", phone=" + phone + ", birthday=" + birthday + "]";
	}
	
	
	
	
	
	
}
