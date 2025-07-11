package com.dao;

import com.entity.BeijingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BeijingView;

/**
 * 高清背景 Dao 接口
 *
 * @author 
 */
public interface BeijingDao extends BaseMapper<BeijingEntity> {

   List<BeijingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
