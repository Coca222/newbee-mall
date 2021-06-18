package ltd.newbee.mall.entity;

import java.util.Date;

public class TableCategory {
    private  Boolean flag;
	private Long id;
	private Long categoryId;
	private Date startDate;
	private Date endDate;
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
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
		return "TableCategory [flag=" + flag + ", id=" + id + ", categoryId=" + categoryId + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	
}
