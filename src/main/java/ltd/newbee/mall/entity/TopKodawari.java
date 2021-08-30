package ltd.newbee.mall.entity;

import java.util.Arrays;

public class TopKodawari {
	private Long id;
	private String path;
	private String modalTitle;
	private String label;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getModalTitle() {
		return modalTitle;
	}
	public void setModalTitle(String modalTitle) {
		this.modalTitle = modalTitle;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "TopKodawari [id=" + id + ", path=" + path + ", modalTitle=" + modalTitle + ", label=" + label
				+ ", content=" + content + "]";
	}
	
	
	
	
	
}
