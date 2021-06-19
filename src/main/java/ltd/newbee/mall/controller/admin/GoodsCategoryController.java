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
import ltd.newbee.mall.entity.TsJoinCategory;
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
        
    	 if(!tCRecord.getFlag()){//删除成功
    		 Boolean deleteResult = newBeeMallGoodsService.deleteByTcPrimaryKey(tCRecord.getCategoryId());
    	        if (deleteResult) {
    	            return ResultGenerator.genSuccessResult();
    	        }
    	        //删除失败
    	        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    	        } else {
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
    			}
    
 // adding insert paging added by coca 2021/06/02
    @RequestMapping(value = "/insertCompaignSent", method = RequestMethod.POST)
    @ResponseBody
    public Result getInsertCampaignSent(@RequestBody campaignSet csRecord){
		  Integer count = null;
		  Integer countTbSale=null;
		
		  Long csId = newBeeMallGoodsService.getFindMaxCsId();
		  csRecord.setId(csId);
		  
		  TableSale ts= new TableSale();
		  ts.setId(csRecord.getCampaignId());
		  ts.setGoodsId(csRecord.getPrimaryGoodsId());
		  ts.setStartDate(csRecord.getStartDate());
		  ts.setEndDate(csRecord.getEndDate());
		  if(csRecord !=null) {
			  countTbSale=newBeeMallGoodsService.InsertTableSale(ts);
			}	
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
    public Result getGiveawayCompaignSent(@RequestBody TsJoinCategory tsRecord){
    	
		 //List<NewBeeMallGoods> gdlist =newBeeMallGoodsService.findListBygoodsCategoryId(goodsCategoryId);
		 // return ResultGenerator.genSuccessResult(gdlist);
    	if(!tsRecord.getFlag()){//删除成功
   	//	 Boolean deleteTsResult = newBeeMallGoodsService.deleteByTsGoodsId(tsRecord.getGoodsId());
   	//	 Boolean deleteTcResult = newBeeMallGoodsService.deleteByTcPrimaryKey(tsRecord.getGoodsId());
    		
    		Boolean deleteTcResult = newBeeMallGoodsService.deleteByCsTs(tsRecord.getGoodsId());
   	       if(deleteTcResult) {
   	            return ResultGenerator.genSuccessResult();
   	       }
   	       	return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
   	        } else {
    		List<TsJoinCategory> gdlist =newBeeMallGoodsService.getTsJoinCategoryList(tsRecord.getGoodsCategoryId());
    		  return ResultGenerator.genSuccessResult(gdlist);
   	        }
    }
    
    // adding second category added by coca 2021/06/04
    @RequestMapping(value = "/searchCategory", method = RequestMethod.POST)
    @ResponseBody
    public Result getBySecondLevelCategoryId(@RequestBody Long categoryId){
    		Map<Object, List> result =new HashMap<>();
    		
			 TcJoinCategory tc= new TcJoinCategory();
			 tc.setParentId(categoryId);
			 List<GoodsSale> gsList = newBeeMallGoodsService.getGoodsSale();
			 //List<GoodsSale> gsList = new ArrayList<>();
			 List<TcJoinCategory> tcJoinCategory = newBeeMallCategoryService.selectBySecondLevelCategoryId(tc.getParentId());
			// List<NewBeeMallGoods> gdlist =newBeeMallGoodsService.findListBygoodsCategoryId(categoryId);
			 List<TsJoinCategory> gdlist =newBeeMallGoodsService.getTsJoinCategoryList(categoryId);

			 if(!gdlist.isEmpty()){
				 result.put("list", gdlist);
				 result.put("gsList", gsList);
			 }	else {
			 result.put("list", tcJoinCategory);
			 result.put("gsList", gsList);
			 } 
		 
		 
		  return ResultGenerator.genSuccessResult(result);
    }
}