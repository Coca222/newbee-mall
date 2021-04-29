/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.StockNumDTO;
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
     List<GoodsReview> getReviewList(Long goodsId);
     	// added by coca 2021/04/16 QAリストを取得
     List<GoodsQa> getGoodsQaList(Long goodsId);
     	// added by coca 2021/04/16 説明を取得
     GoodsDesc getGoodsDesc(Long goodsId);
        // Adding Mapper to achieve paging added by coca 2021/04/23
     List<GoodsQa> findGoodsQaList(PageQueryUtil pageUtil);
     int findGoodsQaCount(PageQueryUtil pageUtil);
        // Adding Mapper to sort paging added by coca 2021/04/24
     List<GoodsQa> findGoodsQaSortSubmitDate(PageQueryUtil pageUtil);
        //adding insert added by coca 2021/04/24
     int qaInsertSelective(GoodsQa qaRecord);

     	// get max id of qa added by coca 2021/04/29
     Long getMaxQaId(Long goodsId);
}