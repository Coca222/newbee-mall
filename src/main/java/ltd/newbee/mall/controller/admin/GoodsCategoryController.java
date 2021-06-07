/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallCategoryLevelEnum;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.GsTcJoinCategory;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.TableCategory;
import ltd.newbee.mall.entity.TableSale;
import ltd.newbee.mall.entity.TcJoinCategory;
import ltd.newbee.mall.entity.campaignSet;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
@RequestMapping("/admin")
public class GoodsCategoryController {

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;
    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;

    @GetMapping({ "/goodsCategory", "/goodsCategory.html" })
    public String firstLevel(HttpServletRequest request,Long categoryId) {
     // 查询所有的一级分类
     List<TcJoinCategory> tcJoinCategory = newBeeMallCategoryService.selectByFirstLevelCategoryId(categoryId);
     List<GoodsSale> goodsSaleList = newBeeMallGoodsService.getGoodsSale();
   
     
     request.setAttribute("goodsSaleList", goodsSaleList);
     request.setAttribute("tcJoinCategory", tcJoinCategory);
    
     
     
     return "admin/goodsCategory";
    }
    
    //add delete TC record by coca 2021/05/30
    @RequestMapping(value = "/deleteTableCategory", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteByTcPrimaryKey(@RequestBody Long categoryId) {
        Boolean deleteResult = newBeeMallGoodsService.deleteByTcPrimaryKey(categoryId);
        //删除成功
        if (deleteResult) {
            return ResultGenerator.genSuccessResult();
        }
        //删除失败
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }
    
    //to achieve insert tc record added by coca 2021/06/01
    @RequestMapping(value = "/insertTc", method = RequestMethod.POST)
    @ResponseBody
    public Result InsertTableCategory(@RequestBody TableCategory tCRecord) {
    	TableCategory list = new TableCategory();
        Integer count = null;  
        list.setId(tCRecord.getId());
        list.setCategoryId(tCRecord.getCategoryId());
        list.setStartDate(tCRecord.getStartDate());
        list.setEndDate(tCRecord.getEndDate());
        Boolean insertResult = newBeeMallGoodsService.InsertTableCategory(tCRecord);
        if(insertResult) {
        if(tCRecord != null) {
            count = newBeeMallGoodsService.insertTableCategory(list);
        }
        if(!(count > 0)) {
        return ResultGenerator.genFailResult("投稿失敗！");
        }      
        return ResultGenerator.genSuccessResult(count); 
        }
        return ResultGenerator.genFailResult("有効期限ではありません！");
    }
    
 // adding insert paging added by coca 2021/06/02
    @RequestMapping(value = "/insertCompaignSent", method = RequestMethod.POST)
    @ResponseBody
    public Result getInsertCampaignSent(@RequestBody campaignSet csRecord){
		  Integer count = null;
		  Long csId = newBeeMallGoodsService.getFindMaxCsId(csRecord.getId());
		  csRecord.setId(csId);		
		 // List<NewBeeMallGoods> gdlist =newBeeMallGoodsService.findListByGoodsId(csRecord.getPrimaryGoodsId());
		if(csRecord !=null) {
			count=newBeeMallGoodsService.getInsertCampaignSent(csRecord);
		}
		if(!(count > 0))  {
	        return ResultGenerator.genFailResult("投稿失敗！");
	        }
		return ResultGenerator.genSuccessResult(count);
    }
    
 // adding giveaway paging added by coca 2021/06/02
    @RequestMapping(value = "/giveawayCompaignSent", method = RequestMethod.POST)
    @ResponseBody
    public Result getGiveawayCompaignSent(@RequestBody Long goodsId){
		 
		 List<NewBeeMallGoods> gdlist =newBeeMallGoodsService.findListByGoodsId(goodsId);
		
		return ResultGenerator.genSuccessResult(gdlist);
    }
    
    // adding second category added by coca 2021/06/04
    @RequestMapping(value = "/searchCategory", method = RequestMethod.POST)
    @ResponseBody
    public Result getBySecondLevelCategoryId(@RequestBody Long categoryId){
    	
			 TcJoinCategory tc= new TcJoinCategory();
			 tc.setParentId(categoryId);
			 List<GoodsSale> gsList = new ArrayList<GoodsSale>();
			 List<TcJoinCategory> tcJoinCategory = newBeeMallCategoryService.selectBySecondLevelCategoryId(tc.getParentId());
			 for(int i=0; i<tcJoinCategory.size();i++) {
				 if (tcJoinCategory.get(i).getId()!=null) {
					 List<GoodsSale> box = newBeeMallGoodsService.findGoodsSaleList(tcJoinCategory.get(i).getId());
					 gsList.addAll(box);
				 }
			 }
			 Map<Object, List> result =new HashMap<>();
			 result.put("tcJoinCategory", tcJoinCategory);
			 result.put("gsList", gsList);
//    	   GsTcJoinCategory tc = new GsTcJoinCategory();
//    	   tc.setParentId(categoryId);
//		   List<GsTcJoinCategory> gsTcJoinCategory =newBeeMallCategoryService.selectByJoinSecondLevelCategoryId(tc.getParentId());
		 
		 
		  return ResultGenerator.genSuccessResult(result);
    }
}