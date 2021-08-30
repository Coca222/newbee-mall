package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsQa {

	private Long id;
	private String question;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
	private Date submitDate;
	private String answer;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
	private Date answerDate;
	private String helpedNum;
	private Long goodsId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
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
