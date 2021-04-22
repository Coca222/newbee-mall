package ltd.newbee.mall.entity;



public class GoodsQa {

	private String id;
	private String question;
	private String submitDate;
	private String answer;
	private String answerDate;
	private String helpedNum;
	private Long goodsId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public String getHelpedNum() {
		return helpedNum;
	}
	public void setHelpedNum(String helpedNum) {
		this.helpedNum = helpedNum;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	@Override
	public String toString() {
		return "GoodsQa [id=" + id + ", question=" + question + ", submitDate=" + submitDate + ", answer=" + answer
				+ ", answerDate=" + answerDate + ", helpedNum=" + helpedNum + ", goodsId=" + goodsId + "]";
	}
	
	
	
	
}
