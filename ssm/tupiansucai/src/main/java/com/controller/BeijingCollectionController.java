











package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 高清背景收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/beijingCollection")
public class BeijingCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(BeijingCollectionController.class);

    @Autowired
    private BeijingCollectionService beijingCollectionService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private BeijingService beijingService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = beijingCollectionService.queryPage(params);

        //字典表数据转换
        List<BeijingCollectionView> list =(List<BeijingCollectionView>)page.getList();
        for(BeijingCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BeijingCollectionEntity beijingCollection = beijingCollectionService.selectById(id);
        if(beijingCollection !=null){
            //entity转view
            BeijingCollectionView view = new BeijingCollectionView();
            BeanUtils.copyProperties( beijingCollection , view );//把实体数据重构到view中

                //级联表
                BeijingEntity beijing = beijingService.selectById(beijingCollection.getBeijingId());
                if(beijing != null){
                    BeanUtils.copyProperties( beijing , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setBeijingId(beijing.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(beijingCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody BeijingCollectionEntity beijingCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,beijingCollection:{}",this.getClass().getName(),beijingCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            beijingCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        Wrapper<BeijingCollectionEntity> queryWrapper = new EntityWrapper<BeijingCollectionEntity>()
            .eq("beijing_id", beijingCollection.getBeijingId())
            .eq("yonghu_id", beijingCollection.getYonghuId())
            .eq("insert_time", beijingCollection.getInsertTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BeijingCollectionEntity beijingCollectionEntity = beijingCollectionService.selectOne(queryWrapper);
        if(beijingCollectionEntity==null){
            beijingCollection.setInsertTime(new Date());
            beijingCollection.setCreateTime(new Date());
            beijingCollectionService.insert(beijingCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BeijingCollectionEntity beijingCollection, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,beijingCollection:{}",this.getClass().getName(),beijingCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            beijingCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<BeijingCollectionEntity> queryWrapper = new EntityWrapper<BeijingCollectionEntity>()
            .notIn("id",beijingCollection.getId())
            .andNew()
            .eq("beijing_id", beijingCollection.getBeijingId())
            .eq("yonghu_id", beijingCollection.getYonghuId())
            .eq("insert_time", beijingCollection.getInsertTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BeijingCollectionEntity beijingCollectionEntity = beijingCollectionService.selectOne(queryWrapper);
        if(beijingCollectionEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      beijingCollection.set
            //  }
            beijingCollectionService.updateById(beijingCollection);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        beijingCollectionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BeijingCollectionEntity> beijingCollectionList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            BeijingCollectionEntity beijingCollectionEntity = new BeijingCollectionEntity();
//                            beijingCollectionEntity.setBeijingId(Integer.valueOf(data.get(0)));   //高清背景 要改的
//                            beijingCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            beijingCollectionEntity.setInsertTime(date);//时间
//                            beijingCollectionEntity.setCreateTime(date);//时间
                            beijingCollectionList.add(beijingCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        beijingCollectionService.insertBatch(beijingCollectionList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = beijingCollectionService.queryPage(params);

        //字典表数据转换
        List<BeijingCollectionView> list =(List<BeijingCollectionView>)page.getList();
        for(BeijingCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BeijingCollectionEntity beijingCollection = beijingCollectionService.selectById(id);
            if(beijingCollection !=null){
                //entity转view
                BeijingCollectionView view = new BeijingCollectionView();
                BeanUtils.copyProperties( beijingCollection , view );//把实体数据重构到view中

                //级联表
                    BeijingEntity beijing = beijingService.selectById(beijingCollection.getBeijingId());
                if(beijing != null){
                    BeanUtils.copyProperties( beijing , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setBeijingId(beijing.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(beijingCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody BeijingCollectionEntity beijingCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,beijingCollection:{}",this.getClass().getName(),beijingCollection.toString());
        Wrapper<BeijingCollectionEntity> queryWrapper = new EntityWrapper<BeijingCollectionEntity>()
            .eq("beijing_id", beijingCollection.getBeijingId())
            .eq("yonghu_id", beijingCollection.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BeijingCollectionEntity beijingCollectionEntity = beijingCollectionService.selectOne(queryWrapper);
        if(beijingCollectionEntity==null){
            beijingCollection.setInsertTime(new Date());
            beijingCollection.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      beijingCollection.set
        //  }
        beijingCollectionService.insert(beijingCollection);
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }



}
