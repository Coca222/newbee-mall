package ltd.newbee.mall.entity;



public class PagingQa {

	private String page;
	private String totalPage;
	private String submitDate;
	private String helpedNum;
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getHelpedNum() {
		return helpedNum;
	}
	public void setHelpedNum(String helpedNum) {
		this.helpedNum = helpedNum;
	}
	@Override
	public String toString() {
		return "PagingQa [page=" + page + ", totalPage=" + totalPage + ", submitDate=" + submitDate + ", helpedNum="
				+ helpedNum + "]";
	}
	
	
	
}
