package com.entity.model;

import com.entity.ExamquestionbankEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 测试试题
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
public class ExamquestionbankModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 选项，json字符串
	 */
	
	private String options;
		
	/**
	 * 分值
	 */
	
	private Long score;
		
	/**
	 * 正确答案
	 */
	
	private String answer;
		
	/**
	 * 答案解析
	 */
	
	private String analysis;
		
	/**
	 * 测试试题类型，0：单选题 1：多选题 2：判断题 3：填空题（暂不考虑多项填空） 4:主观题
	 */
	
	private Long type;
		
	/**
	 * 测试试题排序，值越大排越前面
	 */
	
	private Long sequence;
				
	
	/**
	 * 设置：选项，json字符串
	 */
	 
	public void setOptions(String options) {
		this.options = options;
	}
	
	/**
	 * 获取：选项，json字符串
	 */
	public String getOptions() {
		return options;
	}
				
	
	/**
	 * 设置：分值
	 */
	 
	public void setScore(Long score) {
		this.score = score;
	}
	
	/**
	 * 获取：分值
	 */
	public Long getScore() {
		return score;
	}
				
	
	/**
	 * 设置：正确答案
	 */
	 
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * 获取：正确答案
	 */
	public String getAnswer() {
		return answer;
	}
				
	
	/**
	 * 设置：答案解析
	 */
	 
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	/**
	 * 获取：答案解析
	 */
	public String getAnalysis() {
		return analysis;
	}
				
	
	/**
	 * 设置：测试试题类型，0：单选题 1：多选题 2：判断题 3：填空题（暂不考虑多项填空） 4:主观题
	 */
	 
	public void setType(Long type) {
		this.type = type;
	}
	
	/**
	 * 获取：测试试题类型，0：单选题 1：多选题 2：判断题 3：填空题（暂不考虑多项填空） 4:主观题
	 */
	public Long getType() {
		return type;
	}
				
	
	/**
	 * 设置：测试试题排序，值越大排越前面
	 */
	 
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * 获取：测试试题排序，值越大排越前面
	 */
	public Long getSequence() {
		return sequence;
	}
			
}
