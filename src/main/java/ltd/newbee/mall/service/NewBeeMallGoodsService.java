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
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;

import java.util.List;

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
    List<GoodsQa> getGoodsQaEntityByGoodsId(Long  goodsId);
    GoodsDesc getGoodsDescEntityByGoodsId(Long  goodsId);
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
    
    // add inquiry drop down list by coca 2021/05/07
    List<NewBeeMallGoods> getInforDropDownList(NewBeeMallGoods keyword);
	
}
