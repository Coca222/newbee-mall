package ltd.newbee.mall.entity;

import java.util.Arrays;

public class GoodsReview {
	private Integer[] ids;
	private int id;
	private int star;
	private Long reviewNum;
	private Long goodsId;
	private String commentDate;
	private String title;
	private String content;
	private String picture;
	private String nickName;
	private String goodsName;
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Long getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(Long reviewNum) {
		this.reviewNum = reviewNum;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
		return "GoodsReview [ids=" + Arrays.toString(ids) + ", id=" + id + ", star=" + star + ", reviewNum=" + reviewNum
				+ ", goodsId=" + goodsId + ", commentDate=" + commentDate + ", title=" + title + ", content=" + content
				+ ", picture=" + picture + ", nickName=" + nickName + ", goodsName=" + goodsName + "]";
	}
	
	
	
	
}
