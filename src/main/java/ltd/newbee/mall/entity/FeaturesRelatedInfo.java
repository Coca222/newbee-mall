package ltd.newbee.mall.entity;

import java.util.Arrays;

public class FeaturesRelatedInfo {

	private Long id;
	private String useScene;
	private String withChildren;
	private String homepage;
	private String officialAccount;
	private String phoneNum;
	private String remarks;
	private String relatedStoreInfo;
	private String firstContributor;
	private String oneNeng1;
	private String oneNeng2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUseScene() {
		return useScene;
	}
	public void setUseScene(String useScene) {
		this.useScene = useScene;
	}
	public String getWithChildren() {
		return withChildren;
	}
	public void setWithChildren(String withChildren) {
		this.withChildren = withChildren;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOfficialAccount() {
		return officialAccount;
	}
	public void setOfficialAccount(String officialAccount) {
		this.officialAccount = officialAccount;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRelated_storeInfo() {
		return relatedStoreInfo;
	}
	public void setRelated_storeInfo(String related_storeInfo) {
		this.relatedStoreInfo = related_storeInfo;
	}
	public String getFirstContributor() {
		return firstContributor;
	}
	public void setFirstContributor(String firstContributor) {
		this.firstContributor = firstContributor;
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
		return "FeaturesRelatedInfo [id=" + id + ", useScene=" + useScene + ", withChildren=" + withChildren
				+ ", homepage=" + homepage + ", officialAccount=" + officialAccount + ", phoneNum=" + phoneNum
				+ ", remarks=" + remarks + ", relatedStoreInfo=" + relatedStoreInfo + ", firstContributor="
				+ firstContributor + ", oneNeng1=" + oneNeng1 + ", oneNeng2=" + oneNeng2 + "]";
	}
	
	
}
