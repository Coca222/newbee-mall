/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.IndexConfigTypeEnum;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
//import ltd.newbee.mall.controller.vo.GoodsReviewVo;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCategoryVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.BasicInformation;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.FeaturesRelatedInfo;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.JoinTabelogCategory;
import ltd.newbee.mall.entity.Kuchikomi;
import ltd.newbee.mall.entity.MenuCourse;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.PagingQa;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.SeatFacility;
import ltd.newbee.mall.entity.TabelogCategory;
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
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.NewBeeMallIndexConfigService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class RestApiIndexController {

	@Resource
	private NewBeeMallCategoryService newBeeMallCategoryService;
	@Resource
	private NewBeeMallCarouselService newBeeMallCarouselService;

	@Resource
	private NewBeeMallIndexConfigService newBeeMallIndexConfigService;
	@Resource
	private NewBeeMallGoodsService newBeeMallGoodsService;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	@ResponseBody
	public Result categories() {

		List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
		if (CollectionUtils.isEmpty(categories)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(categories);
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/carousels", method = RequestMethod.POST)
	@ResponseBody
	public Result carousels() {

		List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService
				.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
		if (CollectionUtils.isEmpty(carousels)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(carousels);
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/hotGoodses", method = RequestMethod.POST)
	@ResponseBody
	public Result hotGoodses() {

		List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(
				IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
		if (CollectionUtils.isEmpty(hotGoodses)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(hotGoodses);
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/newAndRecommendGoodses", method = RequestMethod.POST)
	@ResponseBody
	public Result newAndRecommendGoodses() {
		Map<Object, List> result = new HashMap<>();
		List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(
				IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
		List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(
				IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
		result.put("newGoodses", newGoodses);
		result.put("recommendGoodses", recommendGoodses);
		if (CollectionUtils.isEmpty(result)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(result);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/detailPageLeftImage/{goodsId}")
	@ResponseBody
	public Result detailPageLeftImage(@PathVariable("goodsId") Long goodsId) {

		List<GoodsImage> smallImages = newBeeMallGoodsService.getGoodsImageEntityByGoodsId(goodsId);

		if (CollectionUtils.isEmpty(smallImages)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(smallImages);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/detailSize/{goodsId}")
	@ResponseBody
	public Result detailSize(@PathVariable("goodsId") Long goodsId) {

		List<GoodsDesc> detailSizeDesc = newBeeMallGoodsService.copyGoodsDescEntityByGoodsId(goodsId);

		if (CollectionUtils.isEmpty(detailSizeDesc)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(detailSizeDesc);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/detailQa/{goodsId}")
	@ResponseBody
	public Result detailQa(@PathVariable("goodsId") Long goodsId) {

		List<GoodsQa> detailQaList = newBeeMallGoodsService.getGoodsQaEntityByGoodsId(goodsId);

		if (CollectionUtils.isEmpty(detailQaList)) {
			return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR,
					Constants.CATEGORY_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(detailQaList);
		}
	}

//    @CrossOrigin(origins = "http://localhost:3000") 
//    @PostMapping(value = "/detailReview/{goodsId}")
//    @ResponseBody
//    public Result detailReview(@RequestBody  GoodsReviewVO goodsReviewVO) {
//     
//       
//    	List<GoodsReview> initialReviewList =newBeeMallGoodsService.copyGoodsReviewEntityByGoodsId(goodsReviewVO.getIds());
//    	
//    	List<GoodsReviewVO> detailReviewList =newBeeMallGoodsService.getGoodsReviewEntityByGoodsId(goodsReviewVO.getGoodsId());
//    	
//    	List<GoodsReviewVO> restReviewList = new ArrayList<GoodsReviewVO>();
//    	
//    	restReviewList =detailReviewList.stream().filter(el->!initialReviewList.contains(el)).collect(Collectors.toList());
//    	
//    	//restReviewList.forEach(item->System.out.print(item));
//    	Map<Object, List> result = new HashMap<>();
//    	result.put("initialReviewList", initialReviewList);
//        result.put("restReviewList", restReviewList);
//    	
//        if (CollectionUtils.isEmpty(result)) {
//        	return ResultGenerator.genErrorResult(Constants.CATEGORY_FETCH_ERROR, Constants.CATEGORY_FETCH_ERROR_MESSAGE);
//        }else {
//        	return ResultGenerator.genSuccessResult(result);
//        }
//        
//        }

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/detailReviewMore")
	@ResponseBody
	public Result detailReview(@RequestBody GoodsReviewVO goodsReviewVO) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("goodsId", goodsReviewVO.getGoodsId());
		params.put("limit", Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
		params.put("orderBy", goodsReviewVO.getStar());
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		PageResult initialReviewList = newBeeMallGoodsService.copyGoodsReviewEntityById((pageUtil));

		if (goodsReviewVO.getIds() == null) {
			initialReviewList.setIsInitial(true);
			return ResultGenerator.genSuccessResult(initialReviewList);
		} else {
			List<GoodsReviewVO> detailReviewList = newBeeMallGoodsService
					.getGoodsReviewEntityByGoodsId(goodsReviewVO.getGoodsId());
			List<GoodsReviewVO> restReviewList = new ArrayList<GoodsReviewVO>();
			Integer[] reviewIds = goodsReviewVO.getIds();
			List<Integer> reviewListB = Arrays.asList(reviewIds);
			restReviewList = detailReviewList.stream().filter(el -> !reviewListB.contains(el.getId()))
					.collect(Collectors.toList());
			PageResult pageResult = new PageResult(restReviewList, 0, pageUtil.getLimit(), pageUtil.getPage());
			pageResult.setIsInitial(false);
			return ResultGenerator.genSuccessResult(pageResult);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/qaPagingSort")
	@ResponseBody
	public Result getGoodsQaPageBySorting(@RequestBody PagingQa pageQa) {

//		if(pageQa!=null) {
//			System.out.print(pageQa.getPage(totalPage));
//		}
		if (Integer.parseInt(pageQa.getPage()) == -1) {
			Map<String, Object> par = new HashMap<>();
			par.put("page", 1);
			par.put("limit", 3);
			PageQueryUtil pU = new PageQueryUtil(par);
			PageResult res = newBeeMallGoodsService.getGoodsQaPageBySorting((pU));
			int a = res.getCurrPage();
			a = res.getTotalPage();

			Map<String, Object> pa = new HashMap<>();
			pa.put("page", a);
			pa.put("limit", 3);
			PageQueryUtil p = new PageQueryUtil(pa);
			PageResult re = newBeeMallGoodsService.getGoodsQaPageBySorting((p));
			return ResultGenerator.genSuccessResult(re);
		} else {
			Map<String, Object> params = new HashMap<>();
			params.put("page", pageQa.getPage());
			params.put("limit", Constants.GOODS_QA_SEARCH_PAGE_LIMIT);
//	   	  params.put("orderBy", pageQa.getSubmitDate());
			PageQueryUtil pageUtil = new PageQueryUtil(params);
			PageResult result = newBeeMallGoodsService.getGoodsQaPageBySorting((pageUtil));
			return ResultGenerator.genSuccessResult(result);
		}
	}

	// adding insert paging added by coca 2021/07/16
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/qaInsert", method = RequestMethod.POST)
	@ResponseBody
	public Result qaInsertSelective(@RequestBody GoodsQa qaRecord) {
		Integer count = null;
		Long qaId = newBeeMallGoodsService.getMaxQaId(qaRecord.getGoodsId());
		qaRecord.setId(qaId);
		Date submitDate = new Date();
		qaRecord.setSubmitDate(submitDate);
		if (qaRecord != null) {
			count = newBeeMallGoodsService.qaInsertSelective(qaRecord);
		}
		if (!(count > 0)) {
			return ResultGenerator.genFailResult("投稿失敗！");
		}
		return ResultGenerator.genSuccessResult(count);
	}

	// adding review helpNum event added by coca 2021/07/16
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/helpNum", method = RequestMethod.POST)
	@ResponseBody
	public Result helpNum(@RequestBody GoodsReviewHelpNum goodsReviewHelpNum, HttpSession httpSession) {

		NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
		if (user != null) {
			goodsReviewHelpNum.setUserId(user.getUserId());
		}
		boolean addFlag = newBeeMallGoodsService.addHelpNum(goodsReviewHelpNum);

		if (addFlag) {
			boolean updateFlag = newBeeMallGoodsService.updateReviewNum(goodsReviewHelpNum);
			if (updateFlag) {
				long helpNum = newBeeMallGoodsService.getRevListHelpNum(goodsReviewHelpNum.getReviewId());
				return ResultGenerator.genSuccessResult(helpNum);
			} else {
				return ResultGenerator.genFailResult("改修失敗！！");
			}
		} else {

			return ResultGenerator.genFailResult("挿入失敗！！");
		}
	}
//    @GetMapping({"/index", "/", "/index.html"})
//    public String indexPage(HttpServletRequest request) {
//        List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
//        if (CollectionUtils.isEmpty(categories)) {
//            return "error/error_5xx";
//        }
//        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
//        List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
//        List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
//        List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
//        request.setAttribute("categories", categories);//分类数据
//        request.setAttribute("carousels", carousels);//轮播图
//        request.setAttribute("hotGoodses", hotGoodses);//热销商品
//        request.setAttribute("newGoodses", newGoodses);//新品
//        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
//        return "mall/index";
//    }

//  ======================================================================================================================

	// adding DetailTitleList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/detailTitle", method = RequestMethod.POST)
	@ResponseBody
	public Result getDetailTitleList(@RequestBody DetailTitle detailTitle) {
		double averageStar = newBeeMallGoodsService.getTbCommentAvg();
		int totalCount = newBeeMallGoodsService.getTbCommentTotal();
		List<DetailTitle> dtList = newBeeMallGoodsService.getDetailTitleList(detailTitle.getId());
		dtList.get(0).getStar();
		dtList.get(0).setStar(averageStar);
		dtList.get(0).getCommentNum();
		dtList.get(0).setCommentNum(totalCount);

		if (dtList == null || dtList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(dtList);
	}

	// adding RestaurantDescList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/restaurantDesc", method = RequestMethod.POST)
	@ResponseBody
	public Result getRestaurantDescList(@RequestBody RestaurantDesc restaurantDesc) {

		List<RestaurantDesc> rdList = newBeeMallGoodsService.getRestaurantDescList(restaurantDesc.getId());

		if (rdList == null || rdList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(rdList);
	}

	// adding TbGenreList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/tbGenre", method = RequestMethod.POST)
	@ResponseBody
	public Result getTbGenreList(@RequestBody TbGenre tbGenre) {

		List<TbGenre> tgList = newBeeMallGoodsService.getTbGenreList(tbGenre.getId());

		if (tgList == null || tgList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tgList);
	}

	// adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/tabelogCategory", method = RequestMethod.POST)
	@ResponseBody
	public Result getTabelogCategoryList(@RequestBody TabelogCategory tabelogCategory) {

		List<TabelogCategory> tcList = newBeeMallGoodsService.getTabelogCategoryList(tabelogCategory.getCategoryId());

		if (tcList == null || tcList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tcList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/joinTabelogCategory", method = RequestMethod.POST)
	@ResponseBody
	public Result getJoinTabelogCategoryList(@RequestBody JoinTabelogCategory jointabelogCategory) {

		List<JoinTabelogCategory> jtcList = newBeeMallGoodsService
				.getJoinTabelogCategoryList(jointabelogCategory.getId());

		if (jtcList == null || jtcList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(jtcList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topImage", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopImageList(@RequestBody TopImage topImage) {

		List<TopImage> tiList = newBeeMallGoodsService.getTopImageList(topImage.getId());

		if (tiList == null || tiList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tiList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topPage", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopPageList(@RequestBody TopPage topPage) {

		List<TopPage> tpList = newBeeMallGoodsService.getTopPageList(topPage.getId());

		if (tpList == null || tpList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tpList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topKodawari", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopKodawariList(@RequestBody TopKodawari topKodawari) {

		List<TopKodawari> tkList = newBeeMallGoodsService.getTopKodawariList(topKodawari.getId());

		if (tkList == null || tkList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tkList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/21
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topHygiene", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopHygieneList(@RequestBody TopHygiene topHygiene) {

		List<TopHygiene> thList = newBeeMallGoodsService.getTopHygieneList(topHygiene.getId());

		if (thList == null || thList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(thList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/31
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topCourse", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopCourseList(@RequestBody TopCourse topCourse) {

		List<TopCourse> tcList = newBeeMallGoodsService.getTopCourseList(topCourse.getId());

		if (tcList == null || tcList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tcList);
	}

//adding TabelogCategoryList取得 added by coca 2021/07/31
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topCoupon", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopCouponList(@RequestBody TopCoupon topCoupon) {

		List<TopCoupon> tcList = newBeeMallGoodsService.getTopCouponList(topCoupon.getId());

		if (tcList == null || tcList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tcList);
	}

//adding topPostphoto取得 added by coca 2021/08/01
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/topPostphoto", method = RequestMethod.POST)
	@ResponseBody
	public Result getTopPostphotoList(@RequestBody TopPostphoto topPostphoto) {

		List<TopPostphoto> tpList = newBeeMallGoodsService.getTopPostphotoList(topPostphoto.getId());

		if (tpList == null || tpList.isEmpty()) {
			NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(tpList);
	}
	
	
	// adding review helpNum event added by coca 2021/07/16
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/topPostphotoNum", method = RequestMethod.POST)
		@ResponseBody
		public Result helpNum(@RequestBody TopPostphotoNum topPostphotoNum, HttpSession httpSession) {

			NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
			if (user != null) {
				topPostphotoNum.setUserId(user.getUserId());
			}
			String a = topPostphotoNum.getFlag();
			if(a.equals("t")){
				
				boolean addFlag = newBeeMallGoodsService.insertTopPostphotoNumList(topPostphotoNum);

				if (addFlag) {
					boolean updateFlag = newBeeMallGoodsService.updateTopPostphotoNumList(topPostphotoNum);
					if (updateFlag) {
						long helpNum = newBeeMallGoodsService.getTopPostphotoNumList(topPostphotoNum.getReviewId());
						return ResultGenerator.genSuccessResult(helpNum);
					} else {
						return ResultGenerator.genFailResult("改修失敗！！");
					}
				} else {

					return ResultGenerator.genFailResult("挿入失敗！！");
				}
			} else if(a.equals("f")) {
				boolean addFlag = newBeeMallGoodsService.deleteTopPostphotoNumList(topPostphotoNum.getReviewId());

				if (addFlag) {
					boolean updateFlag = newBeeMallGoodsService.updateMinusTopPostphotoNumList(topPostphotoNum);
					if (updateFlag) {
						long helpNum = newBeeMallGoodsService.getTopPostphotoNumList(topPostphotoNum.getReviewId());
						return ResultGenerator.genSuccessResult(helpNum);
					} else {
						return ResultGenerator.genFailResult("改修失敗！！");
					}
				} else {

					return ResultGenerator.genFailResult("挿入失敗！！");
				}
			} else {

				return ResultGenerator.genFailResult("挿入失敗！！");
			}
			
		}
		
		
		
		//adding Kuchikomi取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/kuchikomi", method = RequestMethod.POST)
		@ResponseBody
		public Result getKuchikomiList(@RequestBody Kuchikomi kuchikomi) {

			 List<Kuchikomi> tkList = newBeeMallGoodsService.getKuchikomiList(kuchikomi.getId());

			if (tkList == null || tkList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tkList);
		}
		
		
		//adding TopMatome取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/topMatome", method = RequestMethod.POST)
		@ResponseBody
		public Result getTopMatomeList(@RequestBody TopMatome topMatome) {

			List<TopMatome> tmList = newBeeMallGoodsService.getTopMatomeList(topMatome.getId());

			if (tmList == null || tmList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tmList);
		}
		
		 
		//adding BasicInformation取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/basicInformation", method = RequestMethod.POST)
		@ResponseBody
		public Result getBasicInformationList(@RequestBody BasicInformation basicInformation) {

			 List<BasicInformation> tbList = newBeeMallGoodsService.getBasicInformationList(basicInformation.getId());

			if (tbList == null || tbList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tbList);
		}
		
		 
		//adding topPostphoto取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/seatFacility", method = RequestMethod.POST)
		@ResponseBody
		public Result getSeatFacilityList(@RequestBody SeatFacility seatFacility) {

			 List<SeatFacility> tsList = newBeeMallGoodsService.getSeatFacilityList(seatFacility.getId());

			if (tsList == null || tsList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tsList);
		}
		
		 
		//adding MenuCourse取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/menuCourse", method = RequestMethod.POST)
		@ResponseBody
		public Result getMenuCourseList(@RequestBody MenuCourse menuCourse) {

			 List<MenuCourse> tmList = newBeeMallGoodsService.getMenuCourseList(menuCourse.getId());

			if (tmList == null || tmList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tmList);
		}
		
		 
		//adding MenuCourse取得 added by coca 2021/08/01
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/featuresRelatedInfo", method = RequestMethod.POST)
		@ResponseBody
		public Result getFeaturesRelatedInfoList(@RequestBody FeaturesRelatedInfo featuresRelatedInfo) {

			 List<FeaturesRelatedInfo> tfList = newBeeMallGoodsService.getFeaturesRelatedInfoList(featuresRelatedInfo.getId());

			if (tfList == null || tfList.isEmpty()) {
				NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
			}
			return ResultGenerator.genSuccessResult(tfList);
		}

}