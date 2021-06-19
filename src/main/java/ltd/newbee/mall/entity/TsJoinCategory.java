package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TsJoinCategory {
	private  Boolean flag;
	private Long id;
	private Long goodsId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
	private Date endDate;
	private String goodsName;
	private Long goodsCategoryId;
	
	
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public void setGoodsCategoryId(Long goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	@Override
	public String toString() {
		return "TsJoinCategory [flag=" + flag + ", id=" + id + ", goodsId=" + goodsId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", goodsName=" + goodsName + ", goodsCategoryId=" + goodsCategoryId + "]";
	}
	
	
}
