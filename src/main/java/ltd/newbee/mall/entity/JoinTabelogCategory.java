package ltd.newbee.mall.entity;

import java.util.Arrays;

public class JoinTabelogCategory {
	private Long id;
	private String stationCollectionId;
	private String genreCollectionId;
	private Long closedStationId;
	private String station;
	private Long genreId;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStationCollectionId() {
		return stationCollectionId;
	}
	public void setStationCollectionId(String stationCollectionId) {
		this.stationCollectionId = stationCollectionId;
	}
	public String getGenreCollectionId() {
		return genreCollectionId;
	}
	public void setGenreCollectionId(String genreCollectionId) {
		this.genreCollectionId = genreCollectionId;
	}
	public Long getClosedStationId() {
		return closedStationId;
	}
	public void setClosedStationId(Long closedStationId) {
		this.closedStationId = closedStationId;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Long getGenreId() {
		return genreId;
	}
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "JointTabelogCategory [id=" + id + ", stationCollectionId=" + stationCollectionId
				+ ", genreCollectionId=" + genreCollectionId + ", closedStationId=" + closedStationId + ", station="
				+ station + ", genreId=" + genreId + ", name=" + name + "]";
	}
	
	
	
	
}
