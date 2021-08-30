package ltd.newbee.mall.entity;

import java.util.Arrays;

public class TopCourse {
	private Long id;
	private String title;
	private String subTitle;
	private String courseTitle;
	private String courseNumber;
	private String courseList;
	private String courseBook;
	private String courseDetail;
	private String coursePrice;
	private String tax;
	private String member;
	private String detailMember;
	private String oneNeng;
	private String pathPic;
	private String pathLink;
	private String pathMore;
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseList() {
		return courseList;
	}
	public void setCourseList(String courseList) {
		this.courseList = courseList;
	}
	public String getCourseBook() {
		return courseBook;
	}
	public void setCourseBook(String courseBook) {
		this.courseBook = courseBook;
	}
	public String getCourseDetail() {
		return courseDetail;
	}
	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}
	public String getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getDetailMember() {
		return detailMember;
	}
	public void setDetailMember(String detailMember) {
		this.detailMember = detailMember;
	}
	public String getOneNeng() {
		return oneNeng;
	}
	public void setOneNeng(String oneNeng) {
		this.oneNeng = oneNeng;
	}
	public String getPathPic() {
		return pathPic;
	}
	public void setPathPic(String pathPic) {
		this.pathPic = pathPic;
	}
	public String getPathLink() {
		return pathLink;
	}
	public void setPathLink(String pathLink) {
		this.pathLink = pathLink;
	}
	public String getPathMore() {
		return pathMore;
	}
	public void setPathMore(String pathMore) {
		this.pathMore = pathMore;
	}
	@Override
	public String toString() {
		return "TopCourse [id=" + id + ", title=" + title + ", subTitle=" + subTitle + ", courseTitle=" + courseTitle
				+ ", courseNumber=" + courseNumber + ", courseList=" + courseList + ", courseBook=" + courseBook
				+ ", courseDetail=" + courseDetail + ", coursePrice=" + coursePrice + ", tax=" + tax + ", member="
				+ member + ", detailMember=" + detailMember + ", oneNeng=" + oneNeng + ", pathPic=" + pathPic
				+ ", pathLink=" + pathLink + ", pathMore=" + pathMore + "]";
	}
	
	
}
