package com.entity.view;

import com.entity.ExamquestionEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 测试试题
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
@TableName("examquestion")
public class ExamquestionView  extends ExamquestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ExamquestionView(){
	}
 
 	public ExamquestionView(ExamquestionEntity examquestionEntity){
 	try {
			BeanUtils.copyProperties(this, examquestionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
