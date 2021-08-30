/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallSearchGoodsVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.AfterAge;
import ltd.newbee.mall.entity.BasicInformation;
import ltd.newbee.mall.entity.BeforeAge;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.FeaturesRelatedInfo;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsCoupon;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.JoinTabelogCategory;
import ltd.newbee.mall.entity.Kuchikomi;
import ltd.newbee.mall.entity.MenuCourse;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.Pension;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.SearchHistory;
import ltd.newbee.mall.entity.SeatFacility;
import ltd.newbee.mall.entity.TabelogCategory;
import ltd.newbee.mall.entity.TableCategory;
import ltd.newbee.mall.entity.TableSale;
import ltd.newbee.mall.entity.TbComment;
import ltd.newbee.mall.entity.TbGenre;
import ltd.newbee.mall.entity.TcJoinCategory;
import ltd.newbee.mall.entity.TopCoupon;
import ltd.newbee.mall.entity.TopCourse;
import ltd.newbee.mall.entity.TopHygiene;
import ltd.newbee.mall.entity.TopImage;
import ltd.newbee.mall.entity.TopKodawari;
import ltd.newbee.mall.entity.TopMatome;
import ltd.newbee.mall.entity.TopPage;
import ltd.newbee.mall.entity.TopPostphoto;
import ltd.newbee.mall.entity.TopPostphotoNum;
import ltd.newbee.mall.entity.TsJoinCategory;
import ltd.newbee.mall.entity.campaignSet;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList) {
        if (!CollectionUtils.isEmpty(newBeeMallGoodsList)) {
            goodsMapper.batchInsert(newBeeMallGoodsList);
        }
    }

    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        NewBeeMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        List<NewBeeMallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(goodsList, NewBeeMallSearchGoodsVO.class);
            for (NewBeeMallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

	@Override
	public List<GoodsImage> getGoodsImageEntityByGoodsId(Long goodsId) {
		
		List<GoodsImage> list = goodsMapper.getImageList(goodsId);
		return list;
	}
	@Override
	public List<GoodsReviewVO> getGoodsReviewEntityByGoodsId(Long  goodsId){
		List<GoodsReview> list =goodsMapper.getRevList(goodsId);
		 List<GoodsReviewVO> reviewVOList = BeanUtil.copyList(list, GoodsReviewVO.class);
		return reviewVOList;
	}
	@Override
	public List<GoodsQa> getGoodsQaEntityByGoodsId(Long  goodsId){
		List<GoodsQa> list =goodsMapper.getGoodsQaList(goodsId);
		return list;
	}
	@Override
	public GoodsDesc getGoodsDescEntityByGoodsId(Long  goodsId){
		GoodsDesc goodsDesc=goodsMapper. getGoodsDesc(goodsId);
		return goodsDesc;
	}

	 // Adding ServiceImp to achieve paging added by coca 2021/04/23
	@Override
	public PageResult getGoodsQaPageByUtil(PageQueryUtil pageUtil){
		List <GoodsQa> qaPageList =goodsMapper.findGoodsQaList(pageUtil);;	
		int total = goodsMapper.findGoodsQaCount(pageUtil);
		PageResult pageResult = new PageResult(qaPageList, total, pageUtil.getLimit(), pageUtil.getPage());
       return pageResult;
	}
	// adding sorting way added by coca 2021/04/24
	@Override
	public PageResult getGoodsQaPageBySorting(PageQueryUtil pageUtil) {
		List <GoodsQa> qaSortList =goodsMapper.findGoodsQaSortSubmitDate(pageUtil);	
		int total = goodsMapper.findGoodsQaCount(pageUtil);
		PageResult pageResult = new PageResult(qaSortList, total, pageUtil.getLimit(), pageUtil.getPage());
       return pageResult;
		}
	// adding inserting way added by coca 2021/04/24
	@Override
    public int qaInsertSelective(GoodsQa qaRecord){
    	int count = goodsMapper.qaInsertSelective(qaRecord);
    	return count;
	}
	// adding get max id added by coca 2021/04/29
		@Override
	    public Long getMaxQaId(Long goodsId){
	    	Long maxGoodsId = goodsMapper.getMaxQaId(goodsId);
	    	if (maxGoodsId != null) {
	    		return maxGoodsId + 1;	
	    	} else {
	    		return 1L;
	    	}
	    	
		}
		
	// insert helpNum added by coca 2021/05/04	
	@Override
	public boolean addHelpNum (GoodsReviewHelpNum goodsReviewHelpNum) {
		
		return goodsMapper.insertHelpNum(goodsReviewHelpNum);
	}

	@Override
	public boolean updateReviewNum(GoodsReviewHelpNum goodsReviewHelpNum) {
		// TODO Auto-generated method stub
		return goodsMapper.updateReviewNum(goodsReviewHelpNum);
	}

	@Override
	public long getRevListHelpNum(int reviewId) {
		// TODO Auto-generated method stub
		return goodsMapper.getRevListHelpNum(reviewId);
	}

	// adding inserting way added by coca 2021/05/10
	@Override
	public int insertKeyword(SearchHistory keywordRecord) {
		int keywordCount = goodsMapper.insertKeyword(keywordRecord);
    	return keywordCount;
	}

	// adding get max id added by coca 2021/04/29
	@Override
    public Long getMaxShId(Long userId){
    	Long maxShId = goodsMapper.getMaxShId(userId);
    	if (maxShId != null) {
    		return maxShId + 1;	
    	} else {
    		return 1L;
    	}
    	
	}

	// added by coca 2021/05/28 TableSaleリストを取得
	@Override
	public List<TableSale> getTableSale(Long goodsId) {
		List<TableSale> tSlist =goodsMapper.getTableSale(goodsId);
		return tSlist;
	}
	// added by coca 2021/05/28 GoodsSaleリストを取得
	@Override
	public List<GoodsSale> getGoodsSale() {
		List<GoodsSale> gSlist =goodsMapper.getNewGoodsSale();
		return gSlist;
	}
	 // added by coca 2021/05/11 GoodsCouponリストを取得
	@Override
	public List<GoodsCoupon> getGoodsCoupon(Long couponId) {
		List<GoodsCoupon> gClist =goodsMapper.getGoodsCoupon(couponId);
		return gClist;
	}
	// added by coca 2021/05/28 TableCategoryリストを取得
	@Override
	public List<TableCategory> getTableCategory(Long categoryId) {
		List<TableCategory> tClist =goodsMapper.getTableCategory(categoryId);
		return tClist;
	}
	//adding TableSale insert added by coca 2021/05/12
	@Override
	public int InsertTableSale(TableSale tSRecord) {
		int tSCount = goodsMapper.InsertTableSale(tSRecord);
    	return tSCount;
	}
	//adding TableSale insert added by coca 2021/05/12
	@Override
	public int InsertGoodsSale(GoodsSale gSRecord) {
		int gSCount = goodsMapper.InsertGoodsSale(gSRecord);
    	return gSCount;
	}
	//adding TableSale insert added by coca 2021/05/12
	@Override
	public int InsertGoodsCoupon(GoodsCoupon gCRecord) {
		int gCCount = goodsMapper.InsertGoodsCoupon(gCRecord);
    	return gCCount;
	}
	//adding TableSale insert added by coca 2021/06/01
	@Override
	public Boolean InsertTableCategory(TableCategory tCRecord) {
		List<GoodsSale> gSlist =goodsMapper.getGoodsSaleList(tCRecord.getId());
		if(tCRecord.getStartDate().compareTo(gSlist.get(0).getStartDate())>=0
				&& tCRecord.getEndDate().compareTo(gSlist.get(0).getEndDate())<=0) {
			return true;
		} 			 
		
    	return false;
	}

	//adding download csv file added by coca 2021/05/14
	@Override
	public List<GoodsSale> dlGetGoodsSale(Integer[] ids) {
		List<GoodsSale> gSlist =goodsMapper.dlGetGoodsSale(ids);
        return gSlist ;
	}

	//adding Service to achieve goods sale paging added by coca 2021/05/16
	@Override
	public PageResult findGoodsSalePagingBySearch(PageQueryUtil pageUtil) {
		List <GoodsSale> gsPageList =goodsMapper.findGoodsSalePagingBySearch(pageUtil);;	
		int total = goodsMapper.findGoodsSaleCount(pageUtil);
		PageResult pageResult = new PageResult(gsPageList, total, pageUtil.getLimit(), pageUtil.getPage());
       return pageResult;
	}

	@Override
	public Long getMaxGsId() {
		Long maxGsId = goodsMapper.findMaxGsId();
    	if (maxGsId != null) {
    		return maxGsId + 1;	
    	} else {
    		return 1L;
    	}
	}
	 //キャンペーンの抽出 added by coca 2021/05/28
	@Override
	public List<TableCategory> getGsTcList(Long id) {
		List<TableCategory> tcList =goodsMapper.getGsTc(id);
		return tcList;
	}
	 //キャンペーンの抽出 added by coca 2021/05/28
	@Override
	public List<TableSale> getGsTsList(Long id) {
		List<TableSale> tsList =goodsMapper.getGsTs(id);
		return tsList;
	}

	@Override
	public Boolean deleteByTcPrimaryKey(Long categoryId) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteByTcPrimaryKey(categoryId)>0;
	}

	@Override
	public Long GetMaxTcId(Long id) {
		Long maxTcId = goodsMapper.findMaxTcId(id);
    	if (maxTcId != null) {
    		return maxTcId + 1;	
    	} else {
    		return 1L;
    	}
	}

	@Override
	public List<GoodsSale> findGoodsSaleList(Long id) {
		List<GoodsSale> gSlist =goodsMapper.getGoodsSaleList(id);
		return gSlist;
	}

	@Override
	public int getInsertCampaignSent(campaignSet csRecord) {
		int cSCount = goodsMapper.insertCampaignSent(csRecord);
    	return cSCount;
	}

	@Override
	public Long getFindMaxCsId() {
		Long maxCsId = goodsMapper.findMaxCsId();
    	if (maxCsId != null) {
    		return maxCsId + 1;	
    	} else {
    		return 1L;
    	}
    	
	}

	@Override
	public int insertTableCategory(TableCategory tCRecord) {
		int tC = goodsMapper.InsertTableCategory(tCRecord);
    	return tC;
	}

	@Override
	public List<NewBeeMallGoods> findListByGoodsId(Long goodsId) {
		List<NewBeeMallGoods> gdlist =goodsMapper.findByGoodsId(goodsId);
		
		return gdlist;
	}

	@Override
	public List<NewBeeMallGoods> findListBygoodsCategoryId(Long goodsCategoryId) {
		List<NewBeeMallGoods> cdlist =goodsMapper.findBygoodsCategoryId(goodsCategoryId);
		return cdlist;
	}

	@Override
	public Boolean deleteByTsGoodsId(Long goodsId) {
		return goodsMapper.deleteByTsPrimaryKey(goodsId)>0;
	}

	@Override
	public Boolean deleteByCsId(Long primaryGoodsId) {
		return goodsMapper.deleteByCsPrimaryKey(primaryGoodsId)>0;
	}

	
	@Override
	public Boolean deleteByCsTs(Long primaryGoodsId) {
		return goodsMapper.deleteByCsTsPrimaryKey(primaryGoodsId)>0;
	}
	
	@Override
	public List<TsJoinCategory> getTsJoinCategoryList(Long goodsCategoryId) {
		List<TsJoinCategory> tsList = goodsMapper.getTsJoinCategory(goodsCategoryId);
		return tsList;
	}

	@Override
	public List<GoodsDesc> copyGoodsDescEntityByGoodsId(Long goodsId) {
		List<GoodsDesc> goodsDescList= goodsMapper.copyGoodsDesc(goodsId);
		return goodsDescList;
	}

	@Override
	public List<GoodsReview> copyGoodsReviewEntityByGoodsId(Integer[] ids) {
		List<GoodsReview> gRlist =goodsMapper.copyRevList(ids);
        return gRlist ;
	}

	@Override
	public PageResult copyGoodsReviewEntityById(PageQueryUtil pageUtil) {
		List <GoodsReview> reviewList =goodsMapper.getRevListLimit(pageUtil);	
		int total = goodsMapper.findGoodsReviewCount(pageUtil);
		PageResult pageResult = new PageResult(reviewList, total, pageUtil.getLimit(), pageUtil.getPage());
       return pageResult;
	}
	
//	=============================================================================================================

	@Override
	public List<DetailTitle> getDetailTitleList(Long id) {
		List<DetailTitle> dtlist = goodsMapper.getDetailTitle(id);
		
		return dtlist;
	}

	@Override
	public List<RestaurantDesc> getRestaurantDescList(Long id) {
		List<RestaurantDesc> rdlist = goodsMapper.getRestaurantDesc(id);
		return rdlist;
	}

	@Override
	public List<TbGenre> getTbGenreList(Long id) {
		List<TbGenre> tglist = goodsMapper.getTbGenre(id);
		return tglist;
	}

	@Override
	public List<TabelogCategory> getTabelogCategoryList(Long categoryId) {
		List<TabelogCategory> tclist = goodsMapper.getTabelogCategory(categoryId);
		return tclist;
	}

	@Override
	public List<TbComment> getTbCommentList(Long id) {
		List<TbComment> tbClist = goodsMapper.getTbComment(id);
		return tbClist;
	}

	@Override
	public int getTbCommentTotal() {
		int total = goodsMapper.getTbCommentTotal();
		return total;
	}

	@Override
	public double getTbCommentAvg() {
		double averStore = goodsMapper.getTbCommentAvg();
		return averStore;
	}

	@Override
	public List<JoinTabelogCategory> getJoinTabelogCategoryList(Long id) {
		List<JoinTabelogCategory> jtClist = goodsMapper.getJoinTabelogCategory(id);
		return jtClist;
	}

	@Override
	public List<TopImage> getTopImageList(Long id) {
		List<TopImage> list = goodsMapper.getTopImage(id);
		return list;
	}

	@Override
	public List<TopPage> getTopPageList(Long id) {
		List<TopPage> tplist = goodsMapper.getTopPage(id);
		return tplist;
	}

	@Override
	public List<TopKodawari> getTopKodawariList(Long id) {
		List<TopKodawari> tklist = goodsMapper.getTopKodawari(id);
		return tklist;
	}

	@Override
	public List<TopHygiene> getTopHygieneList(Long id) {
		List<TopHygiene> thlist = goodsMapper.getTopHygiene(id);
		return thlist;
	}

	@Override
	public List<TopCourse> getTopCourseList(Long id) {
		List<TopCourse> thlist = goodsMapper.getTopCourse(id);
		return thlist;
	}

	@Override
	public List<TopCoupon> getTopCouponList(Long id) {
		List<TopCoupon> tclist = goodsMapper.getTopCoupon(id);
		return tclist;
	}
	
	
	@Override
	public List<TopPostphoto> getTopPostphotoList(Long id) {
		List<TopPostphoto> tclist = goodsMapper.getTopPostphoto(id);
		return tclist;
	}

	@Override
	public boolean insertTopPostphotoNumList(TopPostphotoNum topPostphotoNum) {
		// TODO Auto-generated method stub
		return goodsMapper.insertTopPostphotoNum(topPostphotoNum);
	}

	@Override
	public boolean updateTopPostphotoNumList(TopPostphotoNum topPostphotoNum) {
		// TODO Auto-generated method stub
		return goodsMapper.updateTopPostphotoNum(topPostphotoNum);
	}

	@Override
	public long getTopPostphotoNumList(int reviewId) {
		// TODO Auto-generated method stub
		return goodsMapper.getTopPostphotoNum(reviewId);
	}

	@Override
	public Boolean deleteTopPostphotoNumList(int reviewId) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteTopPostphotoNum(reviewId)>0;
	}

	@Override
	public boolean updateMinusTopPostphotoNumList(TopPostphotoNum topPostphotoNum) {
		// TODO Auto-generated method stub
		return goodsMapper.updateMinusTopPostphotoNum(topPostphotoNum);
	}
	
	

	@Override
	public List<Kuchikomi> getKuchikomiList(Long id) {
		List<Kuchikomi> tklist = goodsMapper.getKuchikomi(id);
		return tklist;
	}
	
	@Override
	public List<TopMatome> getTopMatomeList(Long id) {
		List<TopMatome> tmlist = goodsMapper.getTopMatome(id);
		return tmlist;
	}
	
	
	
	@Override
	public List<BasicInformation> getBasicInformationList(Long id) {
		List<BasicInformation> tblist = goodsMapper.getBasicInformation(id);
		return tblist;
	}
	
	
	@Override
	public List<SeatFacility> getSeatFacilityList(Long id) {
		List<SeatFacility> tslist = goodsMapper.getSeatFacility(id);
		return tslist;
	}
	
	

	@Override
	public List<MenuCourse> getMenuCourseList(Long id) {
		List<MenuCourse> tmlist = goodsMapper.getMenuCourse(id);
		return tmlist;
	}
	
	
	@Override
	public List<FeaturesRelatedInfo> getFeaturesRelatedInfoList(Long id) {
		List<FeaturesRelatedInfo> tmlist = goodsMapper.getFeaturesRelatedInfo(id);
		return tmlist;
	}

	@Override
	public List<Pension> getPensionList(Long id) {
		List<Pension> list = goodsMapper.getPension(id);
		return list;
	}

	public List<BeforeAge> getBeforeAgeList(Long age) {
		List<BeforeAge> list = goodsMapper.getBeforeAge(age);
		return list;
	}

	public List<AfterAge> getAfterAgeList(Long age) {
		List<AfterAge> list = goodsMapper.getAfterAge(age);
		return list;
	}


	
}
