package com.dao;

import com.entity.YuansuCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuansuCollectionView;

/**
 * 免扣元素收藏 Dao 接口
 *
 * @author 
 */
public interface YuansuCollectionDao extends BaseMapper<YuansuCollectionEntity> {

   List<YuansuCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
