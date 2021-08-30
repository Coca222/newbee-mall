package ltd.newbee.mall.entity;

import java.util.Arrays;

public class MenuCourse {
	private Long id;
	private String menuCourse;
	private String drink;
	private String food;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuCourse() {
		return menuCourse;
	}
	public void setMenuCourse(String menuCourse) {
		this.menuCourse = menuCourse;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	@Override
	public String toString() {
		return "MenuCourse [id=" + id + ", menuCourse=" + menuCourse + ", drink=" + drink + ", food=" + food + "]";
	}
	
	
}
