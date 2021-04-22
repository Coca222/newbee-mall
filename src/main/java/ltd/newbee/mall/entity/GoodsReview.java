package ltd.newbee.mall.entity;


public class GoodsReview {

	private String star;
	private String commentDate;
	private String title;
	private String content;
	private String picture;
	private String nickName;
	private String goodsName;
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
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
	@Override
	public String toString() {
		return "GoodsReview [star=" + star + ", commentDate=" + commentDate + ", title=" + title + ", content="
				+ content + ", picture=" + picture + ", nickName=" + nickName + ", goodsName=" + goodsName + "]";
	}
	

}
