package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ExamrecordEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ExamrecordVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ExamrecordView;


/**
 * 测试试卷记录表
 *
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
public interface ExamrecordService extends IService<ExamrecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ExamrecordVO> selectListVO(Wrapper<ExamrecordEntity> wrapper);
   	
   	ExamrecordVO selectVO(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
   	
   	List<ExamrecordView> selectListView(Wrapper<ExamrecordEntity> wrapper);
   	
   	ExamrecordView selectView(@Param("ew") Wrapper<ExamrecordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

    PageUtils queryPageOptionsNum(Map<String, Object> params,Wrapper<ExamrecordEntity> wrapper);

}

