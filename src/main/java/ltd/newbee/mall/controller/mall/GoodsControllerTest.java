package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsDesc;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
@SpringBootTest
class GoodsControllerTest {
@Resource
private NewBeeMallGoodsService newBeeMallGoodsService;

    @Test public void testGoodsImage() {
  long num =10700L;
   List<GoodsImage> list =newBeeMallGoodsService.getGoodsImageEntityByGoodsId(num); 
   		GoodsImage image =list.get(0); 
   		String path = image.getPath();
   assertEquals("/goods-img/4755f3e5-257c-424c-a5f4-63908061d6d9.jpg",path);
        long goodsId=image.getGoodsId();
        assertEquals(10700,goodsId);
   }
 



  @Test public void testGoodsReview() {
	  long numRev=10700L;
  List<GoodsReview> list=newBeeMallGoodsService.getGoodsReviewEntityByGoodsId(numRev); 
  GoodsReview review =list.get(0); 
  String nickName = review.getNickName();
  assertEquals("十三",nickName);
  String title = review.getTitle();
  assertEquals("外形外观",title);
  String star = review.getStar();
  assertEquals("4",star);
  String commentDate=review.getCommentDate();
  assertEquals("2020-02-13",commentDate);
  String content=review.getContent();
  assertEquals("京东以旧换新很给力，也很方便，拿走旧机，送货心机，没的拍机了，只能拍拍简单盒子，苹果12颜色很好看，狠喜欢，质量也特别好，卖家服务态度也很好，值得大家信赖。",content);
  String goodsName = review.getGoodsName();
  assertEquals("荣耀8X 千元屏霸 91%屏占比 2000万AI双摄",goodsName);
  String picture= review.getPicture();
  assertEquals(null,picture);
  }


 
    
    
    @Test public void testGoodsQa() {
    	long numQa=10700L;  
   List<GoodsQa> list =newBeeMallGoodsService.getGoodsQaEntityByGoodsId(numQa); 
   		GoodsQa qa =list.get(0); 
   		String id =qa.getId();
   		assertEquals("1",id); 
   		String submitDate= qa.getSubmitDate();
   		assertEquals("2020-01-13",submitDate); 
   		String question = qa.getQuestion();
   		assertEquals("この製品には耐久性がありますか？",question); 
   		String answer = qa.getAnswer();
		assertEquals("そうです",answer); 
		String answerDate=qa.getAnswerDate();
		assertEquals("2020-01-13",answerDate); 
		String helpedNum=qa.getHelpedNum();
		assertEquals("3",helpedNum); 
		long goodsId = qa.getGoodsId();
		assertEquals(10700L, goodsId); 
   }
   
   
    
    

  @Test public void testGoodsDesc() { 
	  long numDes=10700L;
	  GoodsDesc goodsDesc=newBeeMallGoodsService.getGoodsDescEntityByGoodsId(numDes);
  
  String color =goodsDesc.getColor();
  assertEquals("black",color); 
  long goodsId =goodsDesc.getGoodsId();
  assertEquals(10700,goodsId); 
  String size=goodsDesc.getSize();
  assertEquals("幅78.1×奥行7.4×長さ160.8mm",size); 
  String material=goodsDesc.getMaterial();
  assertEquals("其他",material);
  String weight=goodsDesc.getWeight();
  assertEquals("226g",weight);
  String warrantyYear=goodsDesc.getWarrantyYear();
  assertEquals("3年",warrantyYear);
  String setDate=goodsDesc.getSetDate();
  assertEquals("15分",setDate);
  String warpSize=goodsDesc.getWarpSize();
  assertEquals("幅100×奥行10×高さ188cm",warpSize);
  
  }
  
  // To test paging added by coca 2021/04/23
  @Test public void testGoodsQaPage() {
	  Map<String, Object> params = new HashMap<>();
	  params.put("page", 1);
	  params.put("limit", 3);
	  PageQueryUtil pageUtil = new PageQueryUtil(params);
	  PageResult result = newBeeMallGoodsService.getGoodsQaPageByUtil((pageUtil));
	  List<GoodsQa> qaList = (List<GoodsQa>) result.getList();
	  // confirm size = limit
	  int size = 0;
	  if(qaList!=null || !qaList.isEmpty()) {
		  size=qaList.size();
	  }
      assertEquals(3,size);
      
    //to test if lists are same
     
        GoodsQa test1= new GoodsQa();
        test1.setAnswer("そうです");
        test1.setAnswerDate("2020-01-13");
        test1.setGoodsId(10700L);
        test1.setHelpedNum("3");       
        test1.setId("1");        
        test1.setQuestion("この製品には耐久性がありますか？");     
        test1.setSubmitDate("2020-01-13");
       
        
    	List<GoodsQa> testList =new ArrayList<GoodsQa>();
    	testList.add(test1);
    	Boolean isTrue = qaList.get(0).equals(testList.get(0));
    	assertEquals(true,isTrue);
  }

  
}
