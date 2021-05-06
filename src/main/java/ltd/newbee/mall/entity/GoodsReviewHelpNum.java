package ltd.newbee.mall.entity;


public class GoodsReviewHelpNum {

	private int reviewId;
	private long userId;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "GoodsReviewHelpNum [reviewId=" + reviewId + ", userId=" + userId + "]";
	}
	
	
}
