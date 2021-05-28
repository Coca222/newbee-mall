package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsSale {

	private Long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String campaign;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private String content5;
	private String flag;
	private String format;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getContent4() {
		return content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public String getContent5() {
		return content5;
	}

	public void setContent5(String content5) {
		this.content5 = content5;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "\"" + id + "\"" + "," + name + ","  + startDate + "," +  endDate+ ","  + campaign+ ","  +  content1+ ","  +  content2+ ","  + 
				content3+ ","  + content4+ ","  +  content5+ ","  +  flag;
	}
	
	
	
	
	
}
