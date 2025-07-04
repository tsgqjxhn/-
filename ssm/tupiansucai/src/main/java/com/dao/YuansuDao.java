package com.dao;

import com.entity.YuansuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuansuView;

/**
 * 免扣元素 Dao 接口
 *
 * @author 
 */
public interface YuansuDao extends BaseMapper<YuansuEntity> {

   List<YuansuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
