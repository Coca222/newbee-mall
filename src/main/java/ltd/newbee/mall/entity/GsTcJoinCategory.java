package ltd.newbee.mall.entity;

import java.util.Date;

public class GsTcJoinCategory {
	   
	private String name;
	private String campaign;
	private String content5;
	private Long parentId;
	private String categoryName;
	private Long id;
	private Long categoryId;
	private Date startDate;
	private Date endDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getContent5() {
		return content5;
	}
	public void setContent5(String content5) {
		this.content5 = content5;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	@Override
	public String toString() {
		return "GsTcJoinCategory [name=" + name + ", campaign=" + campaign + ", content5=" + content5 + ", parentId="
				+ parentId + ", categoryName=" + categoryName + ", id=" + id + ", categoryId=" + categoryId
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
}
