package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuansuXiazaijiluEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 免扣元素下载记录 服务类
 */
public interface YuansuXiazaijiluService extends IService<YuansuXiazaijiluEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}