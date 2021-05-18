package ltd.newbee.mall.entity;

import java.util.Date;

public class GoodsCoupon {

	private Long couponId;
	private String couponName;
	private String flag;
	private Date startDate;
	private Date endDate;
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
		return "GoodsCoupon [couponId=" + couponId + ", couponName=" + couponName + ", flag=" + flag + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
}
