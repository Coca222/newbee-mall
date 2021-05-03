/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsDescVO;
import ltd.newbee.mall.controller.vo.GoodsImageVO;
import ltd.newbee.mall.controller.vo.GoodsQAVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class GoodsController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = newBeeMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
        //封装参数供前端回显
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        //对keyword做过滤 去掉空格
        if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        //搜索上架状态下的商品
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", newBeeMallGoodsService.searchNewBeeMallGoods(pageUtil));
        return "mall/search";
    }

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
        if (goods == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        NewBeeMallGoodsDetailVO goodsDetailVO = new NewBeeMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);
        
        // added by coca 2021/04/19 recall GoodsImage service
        List<GoodsImage> list =newBeeMallGoodsService.getGoodsImageEntityByGoodsId(goodsId);
        if (list == null || list.isEmpty()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        
        List<GoodsImageVO> imageVOList = new ArrayList<GoodsImageVO>();
//         直接将list中值copy到imageVOList中，无需for循环
//         List<GoodsImageVO> imageVOList = BeanUtil.copyList(list, GoodsImageVO.class);
        for(int i = 0; i < list.size(); i++){
        	GoodsImage image = new GoodsImage();
        	image = list.get(i);
        	if(image!=null) {
//        	String path = image.getPath();
        	GoodsImageVO imageVO = new GoodsImageVO();
//        	 imageVO.setPath(path); 
        	BeanUtil.copyProperties(image, imageVO);
        	 imageVOList.add(imageVO);
       }
   }
        
        //recall GoodsReview Service
        List<GoodsReviewVO> listRev =newBeeMallGoodsService.getGoodsReviewEntityByGoodsId(goodsId);
        if (listRev == null || listRev.isEmpty()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        List<GoodsReviewVO> reviewVOList = new ArrayList<GoodsReviewVO>();
        for(int i = 0; i < listRev.size(); i++){
        	GoodsReviewVO review = new GoodsReviewVO();
        	review = listRev.get(i);
        	if(review!=null) {
//        	Integer star = review.getStar();
        	GoodsReviewVO reviewVO = new GoodsReviewVO();
//        	 reviewVO.setStar(star); 
        	BeanUtil.copyProperties(review, reviewVO);
        	 reviewVOList.add(reviewVO);
        }
    }
        
        // recall GoodsQa Service
        Map<String, Object> params = new HashMap<>();
	   	  params.put("page", 1);
	   	  params.put("limit",Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
	   	  params.put("orderBy", "submit_date");
//	   	  params.put("orderBy", "helped_num");
		  PageQueryUtil pageUtil = new PageQueryUtil(params);
		  PageResult result = newBeeMallGoodsService.getGoodsQaPageBySorting((pageUtil));
		  
		  List<GoodsQa> listQa = (List<GoodsQa>) result.getList();
//        List<GoodsQa> listQa =newBeeMallGoodsService.getGoodsQaEntityByGoodsId(goodsId);
         if (listQa == null|| listQa.isEmpty()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        List<GoodsQAVO> qaVOList = new ArrayList<GoodsQAVO>();
        for(int i = 0; i < listQa.size();i++) {
        	GoodsQa qa = new GoodsQa();
        	qa=listQa.get(i);
        	if(qa != null) {
//        		String answer = qa.getAnswer();
        		GoodsQAVO qaVO = new GoodsQAVO();
//        		qaVO.setAnswer(answer);
        		BeanUtil.copyProperties(qa, qaVO);
        		qaVOList.add(qaVO);
        	} 
        }
        
        
        // recall GoodsDesc Service
        GoodsDesc descEntity =newBeeMallGoodsService.getGoodsDescEntityByGoodsId(goodsId);
        if (descEntity == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        GoodsDescVO descVO = new GoodsDescVO();
        BeanUtil.copyProperties(descEntity, descVO);
        
        request.setAttribute("goodsImageDetail", imageVOList);
        request.setAttribute("goodsReviewDetail", reviewVOList);
        request.setAttribute("goodsQaDetail",qaVOList);
        request.setAttribute("goodsDescDetail",descVO);
        
        return "mall/detail";    
        
	}
 // adding font paging added by coca 2021/04/26
    /**
     * 添加
     */
    @RequestMapping(value = "/goods/qaSort", method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsQaPageBySorting(@RequestBody PagingQa  pageQa) {
		   	
//		if(pageQa!=null) {
//			System.out.print(pageQa.getPage(totalPage));
//		}
    	 Map<String, Object> params = new HashMap<>();
	   	  params.put("page", pageQa.getPage());
	   	  params.put("limit",Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
	   	  params.put("orderBy", pageQa.getSubmitDate());
		  PageQueryUtil pageUtil = new PageQueryUtil(params);
		  PageResult result = newBeeMallGoodsService.getGoodsQaPageBySorting((pageUtil));
		return ResultGenerator.genSuccessResult(result);
    }

    // adding insert paging added by coca 2021/04/29
    @RequestMapping(value = "/goods/qaInsert", method = RequestMethod.POST)
    @ResponseBody
    public Result qaInsertSelective(@RequestBody GoodsQa qaRecord){
		  Integer count = null;
		  Long qaId = newBeeMallGoodsService.getMaxQaId(qaRecord.getGoodsId());
		  qaRecord.setId(qaId);
		  Date submitDate=new Date();
		  qaRecord.setSubmitDate(submitDate);
		if(qaRecord !=null) {
			count=newBeeMallGoodsService.qaInsertSelective(qaRecord);
		}
		if(!(count > 0))  {
	        return ResultGenerator.genFailResult("投稿失敗！");
	        }
		return ResultGenerator.genSuccessResult(count);
    }
    
    // adding レビューをもっと見るクリックイベント added by coca 2021/05/03
    @RequestMapping(value = "/goods/showMoreReviews", method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsReviewEntityByGoodsId(@RequestBody Long goodsId){
		 
    	List<GoodsReviewVO> listRev =newBeeMallGoodsService.getGoodsReviewEntityByGoodsId(goodsId);
		
	        
		return ResultGenerator.genSuccessResult(listRev);
    }

}