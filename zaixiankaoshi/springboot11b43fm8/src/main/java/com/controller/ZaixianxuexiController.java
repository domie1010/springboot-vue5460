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

import com.entity.ZaixianxuexiEntity;
import com.entity.view.ZaixianxuexiView;

import com.service.ZaixianxuexiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 在线学习
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
@RestController
@RequestMapping("/zaixianxuexi")
public class ZaixianxuexiController {
    @Autowired
    private ZaixianxuexiService zaixianxuexiService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZaixianxuexiEntity zaixianxuexi,
		HttpServletRequest request){
        EntityWrapper<ZaixianxuexiEntity> ew = new EntityWrapper<ZaixianxuexiEntity>();

		PageUtils page = zaixianxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zaixianxuexi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZaixianxuexiEntity zaixianxuexi, 
		HttpServletRequest request){
        EntityWrapper<ZaixianxuexiEntity> ew = new EntityWrapper<ZaixianxuexiEntity>();

		PageUtils page = zaixianxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zaixianxuexi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZaixianxuexiEntity zaixianxuexi){
       	EntityWrapper<ZaixianxuexiEntity> ew = new EntityWrapper<ZaixianxuexiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zaixianxuexi, "zaixianxuexi")); 
        return R.ok().put("data", zaixianxuexiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZaixianxuexiEntity zaixianxuexi){
        EntityWrapper< ZaixianxuexiEntity> ew = new EntityWrapper< ZaixianxuexiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zaixianxuexi, "zaixianxuexi")); 
		ZaixianxuexiView zaixianxuexiView =  zaixianxuexiService.selectView(ew);
		return R.ok("查询在线学习成功").put("data", zaixianxuexiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZaixianxuexiEntity zaixianxuexi = zaixianxuexiService.selectById(id);
        return R.ok().put("data", zaixianxuexi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZaixianxuexiEntity zaixianxuexi = zaixianxuexiService.selectById(id);
        return R.ok().put("data", zaixianxuexi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ZaixianxuexiEntity zaixianxuexi = zaixianxuexiService.selectById(id);
        if(type.equals("1")) {
        	zaixianxuexi.setThumbsupnum(zaixianxuexi.getThumbsupnum()+1);
        } else {
        	zaixianxuexi.setCrazilynum(zaixianxuexi.getCrazilynum()+1);
        }
        zaixianxuexiService.updateById(zaixianxuexi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZaixianxuexiEntity zaixianxuexi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zaixianxuexi);
        zaixianxuexiService.insert(zaixianxuexi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZaixianxuexiEntity zaixianxuexi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zaixianxuexi);
        zaixianxuexiService.insert(zaixianxuexi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZaixianxuexiEntity zaixianxuexi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zaixianxuexi);
        zaixianxuexiService.updateById(zaixianxuexi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zaixianxuexiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
