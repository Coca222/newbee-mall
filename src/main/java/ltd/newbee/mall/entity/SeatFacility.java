package ltd.newbee.mall.entity;

import java.util.Arrays;

public class SeatFacility {
	private Long id;
	private Long seatsNumber;
	private String privateRoom;
	private String reserved;
	private String smoking;
	private String parkingLot;
	private String spaceEquipment;
	private String mobilePhone;
	private String oneNeng1;
	private String oneNeng2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSeatsNumber() {
		return seatsNumber;
	}
	public void setSeatsNumber(Long seatsNumber) {
		this.seatsNumber = seatsNumber;
	}
	public String getPrivateRoom() {
		return privateRoom;
	}
	public void setPrivateRoom(String privateRoom) {
		this.privateRoom = privateRoom;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getSmoking() {
		return smoking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public String getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	public String getSpaceEquipment() {
		return spaceEquipment;
	}
	public void setSpaceEquipment(String spaceEquipment) {
		this.spaceEquipment = spaceEquipment;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getOneNeng1() {
		return oneNeng1;
	}
	public void setOneNeng1(String oneNeng1) {
		this.oneNeng1 = oneNeng1;
	}
	public String getOneNeng2() {
		return oneNeng2;
	}
	public void setOneNeng2(String oneNeng2) {
		this.oneNeng2 = oneNeng2;
	}
	@Override
	public String toString() {
		return "SeatFacility [id=" + id + ", seatsNumber=" + seatsNumber + ", privateRoom=" + privateRoom
				+ ", reserved=" + reserved + ", smoking=" + smoking + ", parkingLot=" + parkingLot + ", spaceEquipment="
				+ spaceEquipment + ", mobilePhone=" + mobilePhone + ", oneNeng1=" + oneNeng1 + ", oneNeng2=" + oneNeng2
				+ "]";
	}

	
}
