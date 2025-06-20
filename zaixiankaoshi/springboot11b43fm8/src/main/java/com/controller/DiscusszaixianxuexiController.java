package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscusszaixianxuexiEntity;
import com.entity.view.DiscusszaixianxuexiView;

import com.service.DiscusszaixianxuexiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 在线学习评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
@RestController
@RequestMapping("/discusszaixianxuexi")
public class DiscusszaixianxuexiController {
    @Autowired
    private DiscusszaixianxuexiService discusszaixianxuexiService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusszaixianxuexiEntity discusszaixianxuexi,
		HttpServletRequest request){
        EntityWrapper<DiscusszaixianxuexiEntity> ew = new EntityWrapper<DiscusszaixianxuexiEntity>();

		PageUtils page = discusszaixianxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszaixianxuexi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusszaixianxuexiEntity discusszaixianxuexi, 
		HttpServletRequest request){
        EntityWrapper<DiscusszaixianxuexiEntity> ew = new EntityWrapper<DiscusszaixianxuexiEntity>();

		PageUtils page = discusszaixianxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszaixianxuexi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusszaixianxuexiEntity discusszaixianxuexi){
       	EntityWrapper<DiscusszaixianxuexiEntity> ew = new EntityWrapper<DiscusszaixianxuexiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusszaixianxuexi, "discusszaixianxuexi")); 
        return R.ok().put("data", discusszaixianxuexiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusszaixianxuexiEntity discusszaixianxuexi){
        EntityWrapper< DiscusszaixianxuexiEntity> ew = new EntityWrapper< DiscusszaixianxuexiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusszaixianxuexi, "discusszaixianxuexi")); 
		DiscusszaixianxuexiView discusszaixianxuexiView =  discusszaixianxuexiService.selectView(ew);
		return R.ok("查询在线学习评论表成功").put("data", discusszaixianxuexiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusszaixianxuexiEntity discusszaixianxuexi = discusszaixianxuexiService.selectById(id);
        return R.ok().put("data", discusszaixianxuexi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusszaixianxuexiEntity discusszaixianxuexi = discusszaixianxuexiService.selectById(id);
        return R.ok().put("data", discusszaixianxuexi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusszaixianxuexiEntity discusszaixianxuexi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusszaixianxuexi);
        discusszaixianxuexiService.insert(discusszaixianxuexi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusszaixianxuexiEntity discusszaixianxuexi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusszaixianxuexi);
        discusszaixianxuexiService.insert(discusszaixianxuexi);
        return R.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusszaixianxuexiEntity discusszaixianxuexi = discusszaixianxuexiService.selectOne(new EntityWrapper<DiscusszaixianxuexiEntity>().eq("", username));
        return R.ok().put("data", discusszaixianxuexi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusszaixianxuexiEntity discusszaixianxuexi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusszaixianxuexi);
        discusszaixianxuexiService.updateById(discusszaixianxuexi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusszaixianxuexiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusszaixianxuexiEntity discusszaixianxuexi, HttpServletRequest request,String pre){
        EntityWrapper<DiscusszaixianxuexiEntity> ew = new EntityWrapper<DiscusszaixianxuexiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discusszaixianxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszaixianxuexi), params), params));
        return R.ok().put("data", page);
    }










}
