package ltd.newbee.mall.entity;

import java.util.Date;

public class TabelogCategory {

	
	private Long categoryId;
	private String categoryName;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "TabelogCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
	
	
	
}
