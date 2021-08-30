package ltd.newbee.mall.entity;

import java.util.Arrays;

public class TopCoupon {
	private Long id;
	private String title;
	private String path;
	private String subject;
	private String description;
	private String detail;
	private String couponNotice;
	private String oneNeng;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCouponNotice() {
		return couponNotice;
	}
	public void setCouponNotice(String couponNotice) {
		this.couponNotice = couponNotice;
	}
	public String getOneNeng() {
		return oneNeng;
	}
	public void setOneNeng(String oneNeng) {
		this.oneNeng = oneNeng;
	}
	@Override
	public String toString() {
		return "TopCoupon [id=" + id + ", title=" + title + ", path=" + path + ", subject=" + subject + ", description="
				+ description + ", detail=" + detail + ", couponNotice=" + couponNotice + ", oneNeng=" + oneNeng + "]";
	}
	
	
	
}
