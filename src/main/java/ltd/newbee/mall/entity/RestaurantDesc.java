/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.entity;


public class RestaurantDesc {
	
    private Long Id;
    
    private Long genreId;

    private String nearbyStation;

    private String nightBudget;

    private String dayBudget;

    private String restDay;

    private String genreName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getNearbyStation() {
		return nearbyStation;
	}

	public void setNearbyStation(String nearbyStation) {
		this.nearbyStation = nearbyStation;
	}

	public String getNightBudget() {
		return nightBudget;
	}

	public void setNightBudget(String nightBudget) {
		this.nightBudget = nightBudget;
	}

	public String getDayBudget() {
		return dayBudget;
	}

	public void setDayBudget(String dayBudget) {
		this.dayBudget = dayBudget;
	}

	public String getRestDay() {
		return restDay;
	}

	public void setRestDay(String restDay) {
		this.restDay = restDay;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public String toString() {
		return "RestaurantDesc [Id=" + Id + ", genreId=" + genreId + ", nearbyStation=" + nearbyStation
				+ ", nightBudget=" + nightBudget + ", dayBudget=" + dayBudget + ", restDay=" + restDay + ", genreName="
				+ genreName + "]";
	}


	

	
	

    }