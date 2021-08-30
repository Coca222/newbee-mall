package ltd.newbee.mall.entity;

import java.util.Arrays;

public class TopMatome {
	private Long id;
	private String head;
	private String pathImg;
	private String pathHref;
	private String title;
	private String description;
	private String pathUser;
	private String pathMatome;
	private String matome;
	private String oneNeng;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getPathImg() {
		return pathImg;
	}
	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}
	public String getPathHref() {
		return pathHref;
	}
	public void setPathHref(String pathHref) {
		this.pathHref = pathHref;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPathUser() {
		return pathUser;
	}
	public void setPathUser(String pathUser) {
		this.pathUser = pathUser;
	}
	public String getPathMatome() {
		return pathMatome;
	}
	public void setPathMatome(String pathMatome) {
		this.pathMatome = pathMatome;
	}
	public String getMatome() {
		return matome;
	}
	public void setMatome(String matome) {
		this.matome = matome;
	}
	public String getOneNeng() {
		return oneNeng;
	}
	public void setOneNeng(String oneNeng) {
		this.oneNeng = oneNeng;
	}
	@Override
	public String toString() {
		return "TopMatome [id=" + id + ", head=" + head + ", pathImg=" + pathImg + ", pathHref=" + pathHref + ", title="
				+ title + ", description=" + description + ", pathUser=" + pathUser + ", pathMatome=" + pathMatome
				+ ", matome=" + matome + ", oneNeng=" + oneNeng + "]";
	}

	
}
