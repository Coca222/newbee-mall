package ltd.newbee.mall.entity;

import java.util.Date;

public class campaignSet {

	private Long id;
	private Long campaignId;
	private Long primaryGoodsId;
	private Long categoryId;
	private Long subGoodsId;
	private Date startDate;
	private Date endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	public Long getPrimaryGoodsId() {
		return primaryGoodsId;
	}
	public void setPrimaryGoodsId(Long primaryGoodsId) {
		this.primaryGoodsId = primaryGoodsId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSubGoodsId() {
		return subGoodsId;
	}
	public void setSubGoodsId(Long subGoodsId) {
		this.subGoodsId = subGoodsId;
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
		return "campaignSet [id=" + id + ", campaignId=" + campaignId + ", primaryGoodsId=" + primaryGoodsId
				+ ", categoryId=" + categoryId + ", subGoodsId=" + subGoodsId + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
		
}
