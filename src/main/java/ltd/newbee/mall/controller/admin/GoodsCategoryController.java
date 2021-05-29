/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.NewBeeMallCategoryLevelEnum;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.TableCategory;
import ltd.newbee.mall.entity.TableSale;
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
    public String firstLevel(HttpServletRequest request) {
     request.setAttribute("path", "edit");
     // 查询所有的一级分类
     List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(
       Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
     List<GoodsSale> goodsSaleList = newBeeMallGoodsService.getGoodsSale();
     request.setAttribute("goodsSaleList", goodsSaleList);
     request.setAttribute("firstLevelCategories", firstLevelCategories);
     request.setAttribute("path", "goods-edit");
     //Boolean firstLevelCategory = newBeeMallCategoryService.selectFirstLevelCategoryId();
     return "admin/goodsCategory";
    }
    
	
//	  @RequestMapping(value = "/goodsSaleCategory", method = RequestMethod.POST)
//	  
//	  @ResponseBody public Result selectByLevelAndParentIdsAndCategoryId(@RequestBody Long categoryId){
//	  
//	  List<GoodsCategory> gcList=newBeeMallCategoryService.selectByLevelAndParentIdsAndCategoryId(categoryId);
//	  List<TableCategory> tcList= newBeeMallGoodsService.getTableCategory(categoryId);
//	  
//	  
//	  return ResultGenerator.genSuccessResult(gcList); }
	 
}