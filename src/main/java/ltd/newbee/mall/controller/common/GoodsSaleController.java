/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.common;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsQAVO;
import ltd.newbee.mall.controller.vo.GoodsSaleVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.SearchHistory;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.NewBeeMallUtils;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
@RequestMapping("/admin")
public class GoodsSaleController {
	@Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
	// to achieve goods sale paging added by coca 2021/05/17
    @GetMapping({"/goods/sale", "/goodsSale.html"})
    public String searchGsPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", 2); //Constants.GOODS_SEARCH_PAGE_LIMIT
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
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", newBeeMallGoodsService.findGoodsSalePagingBySearch(pageUtil));
        
        //create goods sale vo by coca 2021/05/19
//        Map<String, Object> par = new HashMap<>();
//	   	  par.put("page", 1);
//	   	  par.put("limit",2);
//	   	  par.put("orderBy", "id");
//		  PageQueryUtil pu = new PageQueryUtil(par);
//		  PageResult res= newBeeMallGoodsService.findGoodsSalePagingBySearch(pu);
//		  
//		  List<GoodsSale> listGs = (List<GoodsSale>) res.getList();
//       if (listGs == null|| listGs.isEmpty()) {
//          NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
//      }
//      List<GoodsSaleVO> gsVOList = BeanUtil.copyList(listGs, GoodsSaleVO.class); 
//      request.setAttribute("goodsSaleDetail",gsVOList);
      
        return "admin/goodsSale";
    }
    
 // adding test searchGsHistory added by coca 2021/05/20
	
	
 	  @RequestMapping(value = "/searchHistory/getSearchHistory", method =RequestMethod.POST)
 	  
 	  @ResponseBody 
 	  public Result getSearchHistory( HttpSession httpSession){
 	  List<NewBeeMallGoods> list = new ArrayList<NewBeeMallGoods>();
 	  
 	  NewBeeMallGoods goods1= new NewBeeMallGoods(); 
 	  goods1.setGoodsId(10700L);
 	  goods1.setGoodsName("iphone12"); 
 	  list.add(goods1);
 	  
 	  NewBeeMallGoods goods2= new NewBeeMallGoods(); 
 	  goods2.setGoodsId(10701L);
 	  goods2.setGoodsName("iphone10"); 
 	  list.add(goods2);
 	  
 	  NewBeeMallGoods goods3= new NewBeeMallGoods(); goods3.setGoodsId(10702L);
 	  goods3.setGoodsName("iphone4s"); 
 	  list.add(goods3);
 	  
 	  
 	  return ResultGenerator.genSuccessResult(list);
 	  
 	  }
 	 
 	// adding get hit goods added by coca 2021/05/08
      @RequestMapping(value =  "/goods/search", method =RequestMethod.POST)
 	 @ResponseBody
 	  public Result getHitGoodsList(@RequestBody String goodsName) {
 		   Map<String, Object> params = new HashMap<>();
 		   	  params.put("keyword", goodsName);
 		   	  params.put("page",1);
 		   	  params.put("limit",9);
 			  PageQueryUtil pageUtil = new PageQueryUtil(params);
 			  PageResult result = newBeeMallGoodsService.searchNewBeeMallGoods((pageUtil));
 			return ResultGenerator.genSuccessResult(result);  
 	   }
      
      // adding insert keyword added by coca 2021/05/10
      @RequestMapping(value = "/goods/keywordInsert", method = RequestMethod.POST)
      @ResponseBody
      public Result insertKeyword(@RequestBody SearchHistory keywordRecord,HttpSession httpSession){
     	 NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
      	if(user!=null) {
      		keywordRecord.setUserId(user.getUserId());
      	}
  		  Integer count = null;
  		  Long shId = newBeeMallGoodsService.getMaxShId(keywordRecord.getId());
  		 keywordRecord.setId(shId);
  		 
  		 SimpleDateFormat sdf = new SimpleDateFormat();
  		 sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
  		  Date date=new Date();
  		 keywordRecord.setDate(date);
//  		SearchHistory keyword = new SearchHistory();
//  		keywordRecord.setKeyword(keyword.getKeyword());
  		 
  		
  		if(keywordRecord !=null) {
  			count=newBeeMallGoodsService.insertKeyword(keywordRecord);
  		}
  		if(!(count > 0))  {
  	        return ResultGenerator.genFailResult("投稿失敗！");
  	        
  	        }
  		return ResultGenerator.genSuccessResult(count);
      }
    
}
