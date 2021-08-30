package ltd.newbee.mall.entity;

import java.util.Arrays;

public class TopPage {
	private Long id;
	private String notice1;
	private String notice2;
	private String notice3;
	private String notice4;
	private String notice5;
	private String notice6;
	private String topAppeal;
	private String topAppealContent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNotice1() {
		return notice1;
	}
	public void setNotice1(String notice1) {
		this.notice1 = notice1;
	}
	public String getNotice2() {
		return notice2;
	}
	public void setNotice2(String notice2) {
		this.notice2 = notice2;
	}
	public String getNotice3() {
		return notice3;
	}
	public void setNotice3(String notice3) {
		this.notice3 = notice3;
	}
	public String getNotice4() {
		return notice4;
	}
	public void setNotice4(String notice4) {
		this.notice4 = notice4;
	}
	public String getNotice5() {
		return notice5;
	}
	public void setNotice5(String notice5) {
		this.notice5 = notice5;
	}
	public String getNotice6() {
		return notice6;
	}
	public void setNotice6(String notice6) {
		this.notice6 = notice6;
	}
	public String getTopAppeal() {
		return topAppeal;
	}
	public void setTopAppeal(String topAppeal) {
		this.topAppeal = topAppeal;
	}
	public String getTopAppealContent() {
		return topAppealContent;
	}
	public void setTopAppealContent(String topAppealContent) {
		this.topAppealContent = topAppealContent;
	}
	@Override
	public String toString() {
		return "TopPage [id=" + id + ", notice1=" + notice1 + ", notice2=" + notice2 + ", notice3=" + notice3
				+ ", notice4=" + notice4 + ", notice5=" + notice5 + ", notice6=" + notice6 + ", topAppeal=" + topAppeal
				+ ", topAppealContent=" + topAppealContent + "]";
	}
	
	
	
	
	
}
