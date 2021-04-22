package ltd.newbee.mall.entity;

public class GoodsImage {

	private Long goodsId;
	private String path;
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "GoodsImage [goodsId=" + goodsId + ", path=" + path + "]";
	}
	
	
}
