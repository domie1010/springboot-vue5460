package com.dao;

import com.entity.ExampaperEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ExampaperVO;
import com.entity.view.ExampaperView;


/**
 * 在线测试表
 * 
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
public interface ExampaperDao extends BaseMapper<ExampaperEntity> {
	
	List<ExampaperVO> selectListVO(@Param("ew") Wrapper<ExampaperEntity> wrapper);
	
	ExampaperVO selectVO(@Param("ew") Wrapper<ExampaperEntity> wrapper);
	
	List<ExampaperView> selectListView(@Param("ew") Wrapper<ExampaperEntity> wrapper);

	List<ExampaperView> selectListView(Pagination page,@Param("ew") Wrapper<ExampaperEntity> wrapper);

	
	ExampaperView selectView(@Param("ew") Wrapper<ExampaperEntity> wrapper);
	

}
