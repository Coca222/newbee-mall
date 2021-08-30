package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.DetailTitle;
import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.entity.RestaurantDesc;
import ltd.newbee.mall.entity.TabelogCategory;
import ltd.newbee.mall.entity.TbGenre;
import ltd.newbee.mall.entity.TcJoinCategory;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

@SpringBootTest
class GoodsControllerTest {
@Autowired
private NewBeeMallGoodsMapper goodsMapper;
@Resource
private NewBeeMallGoodsService newBeeMallGoodsService;
@Resource
private NewBeeMallCategoryService newBeeMallCategoryService;
//    @Test public void testGoodsImage() {
//  long num =10700L;
//   List<GoodsImage> list =newBeeMallGoodsService.getGoodsImageEntityByGoodsId(num); 
//   		GoodsImage image =list.get(0); 
//   		String path = image.getPath();
//   assertEquals("/goods-img/4755f3e5-257c-424c-a5f4-63908061d6d9.jpg",path);
//        long goodsId=image.getGoodsId();
//        assertEquals(10700,goodsId);
//   }
// 
//
//
//
//  @Test public void testGoodsReview() {
//	  long numRev=10700L;
//  List<GoodsReviewVO> list=newBeeMallGoodsService.getGoodsReviewEntityByGoodsId(numRev); 
//  GoodsReviewVO review =list.get(0); 
//  String nickName = review.getNickName();
//  assertEquals("测试用户2测试用户2测试用户2测试用户2",nickName);
//  String title = review.getTitle();
//  assertEquals("功能",title);
//  int star = review.getStar();
//  assertEquals(6L,star);
//  String commentDate=review.getCommentDate();
//  assertEquals("2020-05-13",commentDate);
//  String content=review.getContent();
//  assertEquals("功能很强大",content);
//  String goodsName = review.getGoodsName();
//  assertEquals("荣耀8X 千元屏霸 91%屏占比 2000万AI双摄",goodsName);
//  String picture= review.getPicture();
//  assertEquals(null,picture);
//  }
//
//
// 
//    
//    
//    @Test public void testGoodsQa() {
//    	long numQa=10700L; 
//   List<GoodsQa> list =newBeeMallGoodsService.getGoodsQaEntityByGoodsId(numQa); 
//   		GoodsQa qa =list.get(0); 
//   		Long id =qa.getId();
//   		Long a=1L;
//   		assertEquals(a,id); 
//   		Date submitDate= qa.getSubmitDate();
//   		assertEquals("2020-08-13",submitDate); 
//   		String question = qa.getQuestion();
//   		assertEquals("この製品には耐久性がありますか？",question); 
//   		String answer = qa.getAnswer();
//		assertEquals("そうです",answer); 
//		Date answerDate=qa.getAnswerDate();
//		assertEquals("2020-01-13",answerDate); 
//		String helpedNum=qa.getHelpedNum();
//		assertEquals("3",helpedNum); 
//		long goodsId = qa.getGoodsId();
//		assertEquals(10700L, goodsId); 
//   }
//   
//   
//    
//    
//
//  @Test public void testGoodsDesc() { 
//	  long numDes=10700L;
//	  GoodsDesc goodsDesc=newBeeMallGoodsService.getGoodsDescEntityByGoodsId(numDes);
//  
//  String color =goodsDesc.getColor();
//  assertEquals("black",color); 
//  long goodsId =goodsDesc.getGoodsId();
//  assertEquals(10700,goodsId); 
//  String size=goodsDesc.getSize();
//  assertEquals("幅78.1×奥行7.4×長さ160.8mm",size); 
//  String material=goodsDesc.getMaterial();
//  assertEquals("其他",material);
//  String weight=goodsDesc.getWeight();
//  assertEquals("226g",weight);
//  String warrantyYear=goodsDesc.getWarrantyYear();
//  assertEquals("3年",warrantyYear);
//  String setDate=goodsDesc.getSetDate();
//  assertEquals("15分",setDate);
//  String warpSize=goodsDesc.getWarpSize();
//  assertEquals("幅100×奥行10×高さ188cm",warpSize);
//  
//  }
//  
//  // To test paging added by coca 2021/04/23
//  @Test public void testGoodsQaPage() {
//	  Map<String, Object> params = new HashMap<>();
//	  params.put("page", 1);
//	  params.put("limit", 3);
//	  PageQueryUtil pageUtil = new PageQueryUtil(params);
//	  PageResult result = newBeeMallGoodsService.getGoodsQaPageByUtil((pageUtil));
//	  List<GoodsQa> qaList = (List<GoodsQa>) result.getList();
//	  // confirm size = limit
//	  int size = 0;
//	  if(qaList!=null || !qaList.isEmpty()) {
//		  size=qaList.size();
//	  }
//      assertEquals(3,size);
//      
//    //to test if lists are same
//	      Long a = 1L;
//	      Long b = 2L;
//	      Long c = 3L;
//    	assertEquals(a,qaList.get(0).getId());
//    	assertEquals(b,qaList.get(1).getId());
//    	assertEquals(c,qaList.get(2).getId());
//       	
//  }
//
//    //To test qa sorting added by coca 2021/04/24
//  @Test public void testGoodsQaSorting() {
//	  Map<String, Object> params = new HashMap<>();
//	  params.put("page", 1);
//	  params.put("limit", 3);
//	  params.put("orderBy", "submit_date");
//	  PageQueryUtil pageUtil = new PageQueryUtil(params);
//	  PageResult result = newBeeMallGoodsService.getGoodsQaPageBySorting((pageUtil));
//	  List<GoodsQa> qaList = (List<GoodsQa>) result.getList();
//	  assertEquals("2020-01-13",qaList.get(0).getSubmitDate());
//  	  assertEquals("2020-04-06",qaList.get(1).getSubmitDate());
//  	  assertEquals("2020-06-06",qaList.get(2).getSubmitDate());
//	 
//	  }
//  
//    //To test inserting  added by coca 2021/04/24
//	
////	  @Test public void testGoodsQaInserting() { 
////		  GoodsQa qa = new GoodsQa();
////	  qa.setId("21"); 
////	  qa.setGoodsId(10700L);
////	  qa.setQuestion("crazy");
////	  qa.setSubmitDate("2020-11-28");
////	  qa.setAnswer("crazy");
////	  qa.setAnswerDate("2020-12-28"); 
////	  String rs = newBeeMallGoodsService.int qaInsert(qa);
////	 
////	  assertEquals(ServiceResultEnum.SUCCESS.getResult(), rs);
//// }
//  
//  // To test paging added by coca 2021/05/18
//  @Test public void testGoodsSalePage() {
//	  Map<String, Object> params = new HashMap<>();
//	  params.put("orderBy", "id");
//	  params.put("page", 1);
//	  params.put("limit", 2);
////	  params.put("keyword", "大");
//	  params.put("desAsc", "desc");
//	  PageQueryUtil pageUtil = new PageQueryUtil(params);
//	  PageResult result = newBeeMallGoodsService.findGoodsSalePagingBySearch(pageUtil);
//	  List<GoodsSale> gsList = (List<GoodsSale>) result.getList();
//	  // confirm size = limit
//	  int size = 0;
//	  if(gsList!=null || !gsList.isEmpty()) {
//		  size=gsList.size();
//	  }
//      assertEquals(2,size);
//      
//    //to test if lists are same
//	      Long a = 1L;
//	      Long b = 3L;
//	      Long c = 4L;
//    	assertEquals(a,gsList.get(0).getId());
//    	assertEquals(b,gsList.get(1).getId());
//   // 	assertEquals(c,gsList.get(2).getId());
//    	
//    	assertEquals("夏季大甩卖",gsList.get(0).getName());
//    	assertEquals("清仓处理",gsList.get(1).getName());
//   // 	assertEquals("冬季清仓处理",gsList.get(2).getName());
//    	
//    	assertEquals("满三百减一百",gsList.get(0).getCampaign());
//    	assertEquals("打折50%",gsList.get(1).getCampaign());
//   // 	assertEquals("打折20%",gsList.get(2).getCampaign());
//    	
//    	SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//    	 String startDate1 = dmyFormat.format(gsList.get(0).getStartDate());
//    	 String startDate2 = dmyFormat.format(gsList.get(1).getStartDate());
//   //	 String startDate3 = dmyFormat.format(gsList.get(2).getStartDate());
//    	assertEquals("2021-03-31",startDate1);
//    	assertEquals("2021-04-30",startDate2);
//   // 	assertEquals("2021-12-01",startDate3);
//    	
//    	 String endDate1 = dmyFormat.format(gsList.get(0).getEndDate());
//    	 String endDate2 = dmyFormat.format(gsList.get(1).getEndDate());
//   //	 String endDate3 = dmyFormat.format(gsList.get(2).getEndDate());
//    	assertEquals("2021-06-30",endDate1);
//    	assertEquals("2021-05-30",endDate2);
//   // 	assertEquals("2021-12-30",endDate3);
//  }
//
//  // To test paging added by coca 2021/05/19
//  @Test public void testGoodsSalePage2() {
//	  Map<String, Object> params = new HashMap<>();
//	  params.put("orderBy", "id");
//	  params.put("page", 2);
//	  params.put("limit", 2);
////	  params.put("keyword", "大");
//	  params.put("desAsc", "desc");
//	  PageQueryUtil pageUtil = new PageQueryUtil(params);
//	  PageResult result = newBeeMallGoodsService.findGoodsSalePagingBySearch(pageUtil);
//	  List<GoodsSale> gsList = (List<GoodsSale>) result.getList();
//	  // confirm size = limit
//	  int size = 0;
//	  if(gsList!=null || !gsList.isEmpty()) {
//		  size=gsList.size();
//	  }
//      assertEquals(1,size);
//      
//    //to test if lists are same
//	      Long a = 1L;
//	      Long b = 3L;
//	      Long c = 4L;
////    	assertEquals(a,gsList.get(0).getId());
////    	assertEquals(b,gsList.get(1).getId());
//    	assertEquals(c,gsList.get(0).getId());
//    	
////    	assertEquals("夏季大甩卖",gsList.get(0).getName());
////    	assertEquals("清仓处理",gsList.get(1).getName());
//    	assertEquals("冬季清仓处理",gsList.get(0).getName());
//    	
////    	assertEquals("满三百减一百",gsList.get(0).getCampaign());
////    	assertEquals("打折50%",gsList.get(1).getCampaign());
//    	assertEquals("打折20%",gsList.get(0).getCampaign());
//    	
//    	SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
////    	 String startDate1 = dmyFormat.format(gsList.get(0).getStartDate());
////    	 String startDate2 = dmyFormat.format(gsList.get(1).getStartDate());
//   	 String startDate3 = dmyFormat.format(gsList.get(0).getStartDate());
////    	assertEquals("2021-03-31",startDate1);
////    	assertEquals("2021-04-30",startDate2);
//    	assertEquals("2021-12-01",startDate3);
//    	
////    	 String endDate1 = dmyFormat.format(gsList.get(0).getEndDate());
////    	 String endDate2 = dmyFormat.format(gsList.get(1).getEndDate());
//   	 String endDate3 = dmyFormat.format(gsList.get(0).getEndDate());
////    	assertEquals("2021-06-30",endDate1);
////    	assertEquals("2021-05-30",endDate2);
//    	assertEquals("2021-12-30",endDate3);
//  }
//  
//  @Test public void testGoodsSale() {
//	  List<GoodsSale> goodsSaleList = newBeeMallGoodsService.getGoodsSale(); 
//	  GoodsSale goodsSale =goodsSaleList.get(0); 
//	   		String name = goodsSale.getName();
//	        assertEquals("夏季大甩卖",name);
//	        Long categoryId= 16L;
//	  List<TcJoinCategory> tcJoinCategory = newBeeMallCategoryService.selectByFirstLevelCategoryId(categoryId);
//	  assertEquals("女装 男装 穿搭",tcJoinCategory.get(0).getCategoryName());
//	   }
 //======================================================================================================================
  
  @Test public void testDetailTitle() {
	  long num=1L;  
	List <DetailTitle> dtList =goodsMapper.getDetailTitle(num);
	DetailTitle dt =dtList.get(0); 
	  String name = dt.getName();
	  assertEquals("coca",name);
	  
	  double star = dt.getStar();
	  assertEquals("2",star);
	  
//	  String score = dt.getScore();
//	  assertEquals("3",score);
	  
	  
	  String saveNum = dt.getSaveNum();
	  assertEquals("5",saveNum);
  }
  
 @Test public void testRestaurantDesc() {
	 long num=1L;  
		List <RestaurantDesc> rtdList =goodsMapper.getRestaurantDesc(num);
		RestaurantDesc rtd =rtdList.get(0); 
		  String nearbyStation = rtd.getNearbyStation();
		  assertEquals("matsudo",nearbyStation);
		  
		  Long a =6L;
		  Long genreId = rtd.getGenreId();
		  assertEquals(a,genreId);
		  
		  String nightBudget = rtd.getNightBudget();
		  assertEquals("11",nightBudget);
		  
		  String dayBudget = rtd.getDayBudget();
		  assertEquals("11",dayBudget);
		  
		  String restDay = rtd.getRestDay();
		  assertEquals("11",restDay);
		  
		  String genreName = rtd.getGenreName();
		  assertEquals("car",genreName);
  }
 
 @Test public void testTbGenre() {
	 	long num=6L;  
		List <TbGenre> tgList =goodsMapper.getTbGenre(num);
		TbGenre tg = tgList.get(0); 
		  String name1 = tg.getName1();
		  assertEquals("car",name1);
		  
		  String name2 = tg.getName2();
		  assertEquals("chi",name2);
		  
		  String name3 = tg.getName3();
		  assertEquals("jan",name3);
 }
 
 @Test public void testTabelogCategory() {
	 	long num=7L;  
		List <TabelogCategory> tgList =goodsMapper.getTabelogCategory(num);
		TabelogCategory tg = tgList.get(0); 
		  String categoryName = tg.getCategoryName();
		  assertEquals("Ame",categoryName);
 }
}
