package ltd.newbee.mall.entity;


public class GoodsReview {

	private int star;
	private String commentDate;
	private String title;
	private String content;
	private String picture;
	private String nickName;
	private String goodsName;
	private String helpedNum;
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getHelpedNum() {
		return helpedNum;
	}
	public void setHelpedNum(String helpedNum) {
		this.helpedNum = helpedNum;
	}
	@Override
	public String toString() {
		return "GoodsReview [star=" + star + ", commentDate=" + commentDate + ", title=" + title + ", content="
				+ content + ", picture=" + picture + ", nickName=" + nickName + ", goodsName=" + goodsName
				+ ", helpedNum=" + helpedNum + "]";
	}
	
	
}
