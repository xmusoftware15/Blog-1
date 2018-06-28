package com.liu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liu.Utils.DateUtil;
import com.liu.Utils.PageUtil;
import com.liu.Utils.StringUtil;
import com.liu.model.Blog;
import com.liu.model.PageBean;
import com.liu.service.BlogService;

 /** 
 * @ClassName: IndexController 
 * @author: moonlight
 * @date: 2018.5.12
 * @describe:索引信息Controller
 */
@Controller
@RequestMapping("/")
public class IndexController {
	@Resource
	private BlogService blogService;
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request)throws Exception
	{
		
		ModelAndView modelAndView=new ModelAndView();
		int pageSize=5;
		int blogcounts=blogService.getAllBlog().size();//获取博客总数
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0*pageSize);
		map.put("pageSize", pageSize);
		List<Blog> blogByPage=blogService.listBlog(map);
		for (Blog blog : blogByPage) {
			blog.setReleaseDateStr(DateUtil.formatString2(blog.getReleaseDate().toString()));
		}
		int totalPage=(int)Math.ceil(blogcounts*1.0/pageSize);//获取总的页数
		modelAndView.addObject("blogList", blogByPage);
		modelAndView.addObject("firsttotalPage", totalPage);
		modelAndView.addObject("firstnowPage", 1);
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
