package com.entity.vo;

import com.entity.ExampaperEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 在线测试表
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
public class ExampaperVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 测试试卷时长(分钟)
	 */
	
	private Integer time;
		
	/**
	 * 在线测试状态
	 */
	
	private Integer status;
				
	
	/**
	 * 设置：测试试卷时长(分钟)
	 */
	 
	public void setTime(Integer time) {
		this.time = time;
	}
	
	/**
	 * 获取：测试试卷时长(分钟)
	 */
	public Integer getTime() {
		return time;
	}
				
	
	/**
	 * 设置：在线测试状态
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：在线测试状态
	 */
	public Integer getStatus() {
		return status;
	}
			
}
