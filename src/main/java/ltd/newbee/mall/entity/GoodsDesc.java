/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.entity;


public class GoodsDesc {
	
    private Long goodsId;

    private String color;

    private String size;

    private String material;

    private String weight;

    private String warrantyYear;

    private String setDate;

    private String warpSize;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWarrantyYear() {
		return warrantyYear;
	}

	public void setWarrantyYear(String warrantyYear) {
		this.warrantyYear = warrantyYear;
	}

	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	public String getWarpSize() {
		return warpSize;
	}

	public void setWarpSize(String warpSize) {
		this.warpSize = warpSize;
	}

	@Override
	public String toString() {
		return "GoodsDesc [goodsId=" + goodsId + ", color=" + color + ", size=" + size + ", material=" + material
				+ ", weight=" + weight + ", warrantyYear=" + warrantyYear + ", setDate=" + setDate + ", warpSize="
				+ warpSize + "]";
	}

	

	

    }