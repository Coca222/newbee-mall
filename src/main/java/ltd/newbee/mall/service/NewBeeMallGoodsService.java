/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.GoodsReviewVO;
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
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.entity.Pension;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.SearchHistory;
import ltd.newbee.mall.entity.SeatFacility;
import ltd.newbee.mall.entity.TabelogCategory;
import ltd.newbee.mall.entity.TableCategory;
import ltd.newbee.mall.entity.TableSale;
import ltd.newbee.mall.entity.TbComment;
import ltd.newbee.mall.entity.TbGenre;
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
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NewBeeMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);
    
    List<GoodsImage> getGoodsImageEntityByGoodsId(Long  goodsId);
    List<GoodsReviewVO> getGoodsReviewEntityByGoodsId(Long  goodsId);
  //adding by coca 2021/07/13
    List<GoodsReview> copyGoodsReviewEntityByGoodsId(Integer[] ids);
    PageResult copyGoodsReviewEntityById(PageQueryUtil pageUtil);
    List<GoodsQa> getGoodsQaEntityByGoodsId(Long  goodsId);
    GoodsDesc getGoodsDescEntityByGoodsId(Long  goodsId);
    //added for test 2021/07/10
    List< GoodsDesc> copyGoodsDescEntityByGoodsId(Long  goodsId);
//     Adding Service to achieve paging added by coca 2021/04/23
    PageResult getGoodsQaPageByUtil(PageQueryUtil pageUtil);
//     Adding Service to achieve sort paging added by coca 2021/04/23
    PageResult getGoodsQaPageBySorting(PageQueryUtil pageUtil); 
//     Adding service to achieve inserting added by coca 2021/04/24
    int qaInsertSelective(GoodsQa qaRecord);
 // get max id of qa added by coca 2021/04/29
    Long getMaxQaId(Long goodsId);

	boolean addHelpNum(GoodsReviewHelpNum goodsReviewHelpNum);
	
	// update Review help Num updateReviewNum by coca 2021/04/29
    boolean updateReviewNum (GoodsReviewHelpNum goodsReviewHelpNum);
    
    // to get total reviewNum add by coca 2021/04/29
    long getRevListHelpNum(int reviewId);
    
    //adding keyword insert added by coca 2021/05/10   
    int insertKeyword(SearchHistory keywordRecord);
    
    // get max id of SearchHistory added by coca 2021/05/10 
    Long getMaxShId(Long userId);
    
    // added by coca 2021/05/28 TableSaleリストを取得
    List<TableSale> getTableSale(Long goodsId);
    // added by coca 2021/05/28 GoodsSaleリストを取得
    List<GoodsSale> getGoodsSale();
    // added by coca 2021/05/11 GoodsCouponリストを取得
    List<GoodsCoupon> getGoodsCoupon(Long couponId);
    // added by coca 2021/05/28 TableCategoryリストを取得
    List<TableCategory> getTableCategory(Long categoryId);
    //adding TableSale insert added by coca 2021/05/12
    int InsertTableSale(TableSale tSRecord);
    //adding GoodsSale insert added by coca 2021/05/12
    int InsertGoodsSale(GoodsSale gSRecord);
    //adding GoodsCoupon insert added by coca 2021/05/12
    int InsertGoodsCoupon(GoodsCoupon gCRecord);
    //adding TableCategory insert added by coca 2021/05/12
    Boolean InsertTableCategory(TableCategory tCRecord);
    int insertTableCategory(TableCategory tCRecord);
    //adding download csv file added by coca 2021/05/14
    List<GoodsSale> dlGetGoodsSale(Integer[] ids);
    //adding Service to achieve goods sale paging added by coca 2021/05/16
    PageResult findGoodsSalePagingBySearch(PageQueryUtil pageUtil); 
    
    // get max id of qa added by coca 2021/05/24
    Long getMaxGsId();
    
    //キャンペーンの抽出 added by coca 2021/05/28
    List<TableCategory> getGsTcList(Long id);
    List<TableSale> getGsTsList(Long id);
    // add delete  for tc by coca 2021/05/30   
    Boolean deleteByTcPrimaryKey(Long categoryId);
    // to achieve insert tc record added by coca 2021/06/01
    Long GetMaxTcId(Long id);
    List<GoodsSale> findGoodsSaleList(Long id);
    // to achieve insert cs record added by coca 2021/06/02
    int getInsertCampaignSent (campaignSet csRecord);
    Long getFindMaxCsId();
   
    // giveaway added by coca 2021/06/03
    List<NewBeeMallGoods> findListByGoodsId(Long goodsId);
    List<NewBeeMallGoods> findListBygoodsCategoryId(Long goodsCategoryId);
    
    // add delete  for tc by coca 2021/06/19
    Boolean deleteByTsGoodsId(Long goodsId);
    Boolean deleteByCsId(Long primaryGoodsId);
    List<TsJoinCategory> getTsJoinCategoryList(Long goodsCategoryId);

	Boolean deleteByCsTs(Long primaryGoodsId);
//============================================================================================
	 // added by coca 2021/07/20 DetailTitleリストを取得
    List<DetailTitle> getDetailTitleList(Long id);
    
     // added by coca 2021/07/20 RestaurantDescリストを取得
    List<RestaurantDesc> getRestaurantDescList(Long id);
    
     // added by coca 2021/07/20 TbGenreリストを取得
    List<TbGenre> getTbGenreList(Long id);
    
     // added by coca 2021/07/20 TabelogCategoryリストを取得
    List<TabelogCategory> getTabelogCategoryList(Long categoryId);
    
    // added by coca 2021/07/20 getTbCommentリストを取得
    List<TbComment> getTbCommentList(Long id);
    int getTbCommentTotal();
    double getTbCommentAvg();
 // added by coca 2021/07/28 JoinTabelogCategoryリストを取得
    List<JoinTabelogCategory>getJoinTabelogCategoryList(Long id);
    
    // added by coca 2021/07/29 TopImageリストを取得
    List<TopImage>getTopImageList(Long id);
 // added by coca 2021/07/29 TopImageリストを取得
    List<TopPage>getTopPageList(Long id);
    // added by coca 2021/07/30 TopKodawariリストを取得
    List<TopKodawari>getTopKodawariList(Long id);
 // added by coca 2021/07/30 TopHygieneリストを取得
    List<TopHygiene>getTopHygieneList(Long id);
    
    // added by coca 2021/07/31 TopCourseリストを取得
    List<TopCourse>getTopCourseList(Long id);
 // added by coca 2021/07/31 TopCouponリストを取得
    List<TopCoupon>getTopCouponList(Long id);

	List<TopPostphoto> getTopPostphotoList(Long id);
	
		// add insertHelpNum by coca 2021/08/02
    boolean insertTopPostphotoNumList (TopPostphotoNum topPostphotoNum);
      // update Review help Num updateReviewNum by coca 2021/08/02
    boolean updateTopPostphotoNumList (TopPostphotoNum topPostphotoNum);
      // to get total reviewNum add by coca 2021/08/02
    long getTopPostphotoNumList(int reviewId);
    
     // add delete  for tc by coca 2021/0８/02
    Boolean deleteTopPostphotoNumList(int reviewId);
    // update Review help Num updateReviewNum by coca 2021/08/02
    boolean updateMinusTopPostphotoNumList (TopPostphotoNum topPostphotoNum);

	List<Kuchikomi> getKuchikomiList(Long id);

	List<TopMatome> getTopMatomeList(Long id);

	List<BasicInformation> getBasicInformationList(Long id);

	List<SeatFacility> getSeatFacilityList(Long id);

	List<MenuCourse> getMenuCourseList(Long id);

	List<FeaturesRelatedInfo> getFeaturesRelatedInfoList(Long id);
	
	List<Pension> getPensionList(Long id);
	List<BeforeAge> getBeforeAgeList(Long age);
	List<AfterAge> getAfterAgeList(Long age);
}
