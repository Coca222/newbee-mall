/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;

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
import ltd.newbee.mall.entity.StockNumDTO;
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
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewBeeMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(NewBeeMallGoods record);

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(NewBeeMallGoods record);

    int updateByPrimaryKeyWithBLOBs(NewBeeMallGoods record);

    int updateByPrimaryKey(NewBeeMallGoods record);

    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);

    List<NewBeeMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<NewBeeMallGoods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("newBeeMallGoodsList") List<NewBeeMallGoods> newBeeMallGoodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);
        // added by coca 2021/04/16 イメージリストを取得
     List<GoodsImage> getImageList(Long goodsId);
        // added by coca 2021/04/16　レビューリストを取得
     List<GoodsReview> getRevList(Long goodsId);
   //adding by coca 2021/07/13
     List<GoodsReview> copyRevList(Integer[] ids);
     List<GoodsReview> getRevListLimit(PageQueryUtil pageUtil);
     int findGoodsReviewCount(PageQueryUtil pageUtil);
     	// added by coca 2021/04/16 QAリストを取得
     List<GoodsQa> getGoodsQaList(Long categoryId);
     	// added by coca 2021/04/16 説明を取得
     GoodsDesc getGoodsDesc(Long goodsId);
     List<GoodsDesc> copyGoodsDesc(Long goodsId);
     
        // Adding Mapper to achieve paging added by coca 2021/04/23
     List<GoodsQa> findGoodsQaList(PageQueryUtil pageUtil);
     int findGoodsQaCount(PageQueryUtil pageUtil);
        // Adding Mapper to sort paging added by coca 2021/04/24
     List<GoodsQa> findGoodsQaSortSubmitDate(PageQueryUtil pageUtil);
        //adding insert added by coca 2021/04/24
     int qaInsertSelective(GoodsQa qaRecord);

        // get max id of qa added by coca 2021/04/29
     Long getMaxQaId(Long goodsId);
       // add insertHelpNum by coca 2021/05/04
     boolean insertHelpNum (GoodsReviewHelpNum goodsReviewHelpNum);
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
     List<GoodsSale> getNewGoodsSale();
    // added by coca 2021/05/11 GoodsCouponリストを取得
     List<GoodsCoupon> getGoodsCoupon(Long couponId);
    // added by coca 2021/05/28 TableCategoryリストを取得
     List<TableCategory> getTableCategory(Long categoryId);
     //adding TableSale insert added by coca 2021/05/12
     int InsertTableSale(TableSale tSRecord);
     //adding TableSale insert added by coca 2021/05/12
     int InsertGoodsSale(GoodsSale gSRecord);
     //adding TableSale insert added by coca 2021/05/12
     int InsertGoodsCoupon(GoodsCoupon gCRecord);
     //adding TableSale insert added by coca 2021/05/12
     int InsertTableCategory(TableCategory tCRecord);
     
     //adding download csv file added by coca 2021/05/14
     List<GoodsSale> dlGetGoodsSale(Integer[] ids);
     
     // Adding Mapper to achieve paging added by coca 2021/05/15
     List<GoodsSale> findGoodsSalePagingBySearch(PageQueryUtil pageUtil);
     int findGoodsSaleCount(PageQueryUtil pageUtil);   
     // get max id of qa added by coca 2021/05/24
     Long findMaxGsId();
     //キャンペーンの抽出 added by coca 2021/05/28
     List<TableCategory> getGsTc(Long id);
     List<TableSale> getGsTs(Long id);
     // add delete  for tc by coca 2021/05/30
     int deleteByTcPrimaryKey(Long id);
     // to achieve insert tc record added by coca 2021/06/01
     Long findMaxTcId(Long id);
     List<GoodsSale> getGoodsSaleList(Long id); 
  // to achieve insert cs record added by coca 2021/06/02
     int insertCampaignSent (campaignSet csRecord);
     Long findMaxCsId();
     // giveaway added by coca 2021/06/03
     List<NewBeeMallGoods> findByGoodsId(Long goodsId);
     List<NewBeeMallGoods> findBygoodsCategoryId(Long goodsCategoryId);
  // add delete  for tc by coca 2021/06/19
     int deleteByTsPrimaryKey(Long goodsId);
     int deleteByCsPrimaryKey(Long primaryGoodsId);
     List<TsJoinCategory> getTsJoinCategory(Long goodsCategoryId);

	int deleteByCsTsPrimaryKey(Long primaryGoodsId);
//	=====================================================================
	 // added by coca 2021/07/20 DetailTitleリストを取得
     List<DetailTitle> getDetailTitle(Long id);
     
      // added by coca 2021/07/20 RestaurantDescリストを取得
     List<RestaurantDesc> getRestaurantDesc(Long id);
     
      // added by coca 2021/07/20 TbGenreリストを取得
     List<TbGenre> getTbGenre(Long id);
     
      // added by coca 2021/07/20 TabelogCategoryリストを取得
     List<TabelogCategory> getTabelogCategory(Long categoryId);
     // added by coca 2021/07/26 TbCommentリストを取得
     List<TbComment> getTbComment(Long id);
     int getTbCommentTotal();
     double getTbCommentAvg();
     // added by coca 2021/07/28 JoinTabelogCategoryリストを取得
     List<JoinTabelogCategory>getJoinTabelogCategory(Long id);
  // added by coca 2021/07/29 TopImageリストを取得
     List<TopImage>getTopImage(Long id);
  // added by coca 2021/07/29 TopImageリストを取得
     List<TopPage>getTopPage(Long id);
     // added by coca 2021/07/30 TopKodawariリストを取得
     List<TopKodawari>getTopKodawari(Long id);
  // added by coca 2021/07/30 TopHygieneリストを取得
     List<TopHygiene>getTopHygiene(Long id);
     // added by coca 2021/07/31 TopCourseリストを取得
     List<TopCourse>getTopCourse(Long id);
  // added by coca 2021/07/31 TopCouponリストを取得
     List<TopCoupon>getTopCoupon(Long id);

	List<TopPostphoto> getTopPostphoto(Long id);
	
	// add insertHelpNum by coca 2021/08/02
    boolean insertTopPostphotoNum (TopPostphotoNum topPostphotoNum);
      // update Review help Num updateReviewNum by coca 2021/08/02
    boolean updateTopPostphotoNum (TopPostphotoNum topPostphotoNum);
      // to get total reviewNum add by coca 2021/08/02
    long getTopPostphotoNum(int reviewId);
    
     // add delete  for tc by coca 2021/0８/02
    int deleteTopPostphotoNum(int reviewId);
    // update Review help Num updateReviewNum by coca 2021/08/02
    boolean updateMinusTopPostphotoNum (TopPostphotoNum topPostphotoNum);

	List<Kuchikomi> getKuchikomi(Long id);

	List<TopMatome> getTopMatome(Long id);

	List<BasicInformation> getBasicInformation(Long id);

	List<SeatFacility> getSeatFacility(Long id);

	List<MenuCourse> getMenuCourse(Long id);

	List<FeaturesRelatedInfo> getFeaturesRelatedInfo(Long id);
	
	List<Pension> getPension(Long id);
	List<BeforeAge> getBeforeAge(Long age);
	List<AfterAge> getAfterAge(Long age);
}