package ltd.newbee.mall.entity;

public class TopPostphotoNum {
	private Long userId;
	private int reviewId;
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	@Override
	public String toString() {
		return "TopPostphotoNum [userId=" + userId + ", reviewId=" + reviewId + "]";
	}
	
	

}
