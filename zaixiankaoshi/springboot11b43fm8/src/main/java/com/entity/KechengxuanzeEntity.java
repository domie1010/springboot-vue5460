package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 课程选择
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
@TableName("kechengxuanze")
public class KechengxuanzeEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public KechengxuanzeEntity() {
		
	}
	
	public KechengxuanzeEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 课程名称
	 */
					
	private String kechengmingcheng;
	
	/**
	 * 课程类型
	 */
					
	private String kechengleixing;
	
	/**
	 * 课程图片
	 */
					
	private String kechengtupian;
	
	/**
	 * 课程描述
	 */
					
	private String kechengmiaoshu;
	
	/**
	 * 难度级别
	 */
					
	private String nandujibie;
	
	/**
	 * 教师信息
	 */
					
	private String jiaoshixinxi;
	
	/**
	 * 选择时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date xuanzeshijian;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 姓名
	 */
					
	private String xingming;
	
	/**
	 * 是否审核
	 */
					
	private String sfsh;
	
	/**
	 * 审核回复
	 */
					
	private String shhf;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：课程名称
	 */
	public void setKechengmingcheng(String kechengmingcheng) {
		this.kechengmingcheng = kechengmingcheng;
	}
	/**
	 * 获取：课程名称
	 */
	public String getKechengmingcheng() {
		return kechengmingcheng;
	}
	/**
	 * 设置：课程类型
	 */
	public void setKechengleixing(String kechengleixing) {
		this.kechengleixing = kechengleixing;
	}
	/**
	 * 获取：课程类型
	 */
	public String getKechengleixing() {
		return kechengleixing;
	}
	/**
	 * 设置：课程图片
	 */
	public void setKechengtupian(String kechengtupian) {
		this.kechengtupian = kechengtupian;
	}
	/**
	 * 获取：课程图片
	 */
	public String getKechengtupian() {
		return kechengtupian;
	}
	/**
	 * 设置：课程描述
	 */
	public void setKechengmiaoshu(String kechengmiaoshu) {
		this.kechengmiaoshu = kechengmiaoshu;
	}
	/**
	 * 获取：课程描述
	 */
	public String getKechengmiaoshu() {
		return kechengmiaoshu;
	}
	/**
	 * 设置：难度级别
	 */
	public void setNandujibie(String nandujibie) {
		this.nandujibie = nandujibie;
	}
	/**
	 * 获取：难度级别
	 */
	public String getNandujibie() {
		return nandujibie;
	}
	/**
	 * 设置：教师信息
	 */
	public void setJiaoshixinxi(String jiaoshixinxi) {
		this.jiaoshixinxi = jiaoshixinxi;
	}
	/**
	 * 获取：教师信息
	 */
	public String getJiaoshixinxi() {
		return jiaoshixinxi;
	}
	/**
	 * 设置：选择时间
	 */
	public void setXuanzeshijian(Date xuanzeshijian) {
		this.xuanzeshijian = xuanzeshijian;
	}
	/**
	 * 获取：选择时间
	 */
	public Date getXuanzeshijian() {
		return xuanzeshijian;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
	/**
	 * 设置：是否审核
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
	/**
	 * 设置：审核回复
	 */
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}

}
