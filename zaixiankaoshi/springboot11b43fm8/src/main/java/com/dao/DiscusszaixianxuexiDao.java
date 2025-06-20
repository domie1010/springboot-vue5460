package com.dao;

import com.entity.DiscusszaixianxuexiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusszaixianxuexiVO;
import com.entity.view.DiscusszaixianxuexiView;


/**
 * 在线学习评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-03 14:27:21
 */
public interface DiscusszaixianxuexiDao extends BaseMapper<DiscusszaixianxuexiEntity> {
	
	List<DiscusszaixianxuexiVO> selectListVO(@Param("ew") Wrapper<DiscusszaixianxuexiEntity> wrapper);
	
	DiscusszaixianxuexiVO selectVO(@Param("ew") Wrapper<DiscusszaixianxuexiEntity> wrapper);
	
	List<DiscusszaixianxuexiView> selectListView(@Param("ew") Wrapper<DiscusszaixianxuexiEntity> wrapper);

	List<DiscusszaixianxuexiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusszaixianxuexiEntity> wrapper);

	
	DiscusszaixianxuexiView selectView(@Param("ew") Wrapper<DiscusszaixianxuexiEntity> wrapper);
	

}
