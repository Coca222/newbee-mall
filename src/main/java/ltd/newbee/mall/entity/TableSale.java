package ltd.newbee.mall.entity;

import java.util.Date;

public class TableSale {

	private Long id;
	private Long goodsId;
	private Date startDate;
	private Date endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
		return "TableSale [id=" + id + ", goodsId=" + goodsId + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	
	
	
}
