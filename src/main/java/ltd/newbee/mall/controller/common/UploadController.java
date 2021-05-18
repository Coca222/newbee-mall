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
import ltd.newbee.mall.controller.vo.GoodsQAVO;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsSale;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.NewBeeMallUtils;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
public class UploadController {
	
   @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;

    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.genSuccessResult();
            resultSuccess.setData(NewBeeMallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

    @PostMapping({"/upload/files"})
    @ResponseBody
    public Result uploadV2(HttpServletRequest httpServletRequest) throws URISyntaxException {
        List<MultipartFile> multipartFiles = new ArrayList<>(8);
        if (standardServletMultipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multiRequest.getFileNames();
            int total = 0;
            while (iter.hasNext()) {
                if (total > 5) {
                    return ResultGenerator.genFailResult("最多上传5张图片");
                }
                total += 1;
                MultipartFile file = multiRequest.getFile(iter.next());
                multipartFiles.add(file);
            }
        }
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (multipartFiles != null && multipartFiles.size() > 5) {
            return ResultGenerator.genFailResult("最多上传5张图片");
        }
        List<String> fileNames = new ArrayList(multipartFiles.size());
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fileName = multipartFiles.get(i).getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
            String newFileName = tempName.toString();
            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
            //创建文件
            File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
            try {
                if (!fileDirectory.exists()) {
                    if (!fileDirectory.mkdir()) {
                        throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                    }
                }
                multipartFiles.get(i).transferTo(destFile);
                fileNames.add(NewBeeMallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("文件上传失败");
            }
        }
        Result resultSuccess = ResultGenerator.genSuccessResult();
        resultSuccess.setData(fileNames);
        return resultSuccess;
    }
    
	/* add test uploadGoodsCoverImg added by coca 2021/05/13 */
    @PostMapping({"/uploadtest/file"})
    @ResponseBody
    public Result uploadtest(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException, ParseException {
        
        try {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
            Integer count = null;
        	InputStream is = file.getInputStream();
        	//bufferedReader wrap inputStream
        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            //read line 
            while((line = br.readLine())!=null) {
            //split
            	String[] arr = line.split(",");
            // set entity
            	GoodsSale gsEntity = new GoodsSale();
            	gsEntity.setId(Long.parseLong(arr[0]));
            	gsEntity.setName(arr[1]);
            	gsEntity.setStartDate(sdFormat.parse(arr[2]));
            	gsEntity.setEndDate(sdFormat.parse(arr[3]));
            	gsEntity.setCampaign(arr[4]);
            //call insert service
            	 if(gsEntity !=null) {
                 	count = newBeeMallGoodsService.InsertGoodsSale(gsEntity);
          		}
          		if(!(count > 0))  {
          	        return ResultGenerator.genFailResult("投稿失敗！");
          	        }
          		
          		return ResultGenerator.genSuccessResult(count);
              
            	
                            }
            br.close();  
           
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        return ResultGenerator.genSuccessResult();
	
           }
    
    /* add test download added by coca 2021/05/14 */
    @RequestMapping(value = "/downloadFile/post", method = RequestMethod.POST)
    @ResponseBody
    public Result downloadFile (@RequestBody Integer[] ids) {
    	 
    		  	File f = new File("C:\\upload\\test.csv");
				BufferedWriter bw=null;
				try {
					bw = new BufferedWriter(new FileWriter(f));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<GoodsSale> gsList =newBeeMallGoodsService.dlGetGoodsSale(ids);
	    	        for(int i = 0; i < gsList.size();i++) {
	    	        	 GoodsSale gs=gsList.get(i);
	    	        	if(gs != null) {
	    	        		try {
								bw.write(gs.toString());
								bw.newLine();								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    	        		 	      
	    	        		}
			
	    	        }
	    	        
//	    	        gsList.stream().forEach(c->{try {
//								bw.write(c.toString());
//								bw.newLine();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	    	        });
	    	        
	    	        try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        
				
	    	        Result resultSuccess = ResultGenerator.genSuccessResult();
	    	        resultSuccess.setData("/upload/test.csv");
	    	        return resultSuccess;
		    	        
	    }
    
}
