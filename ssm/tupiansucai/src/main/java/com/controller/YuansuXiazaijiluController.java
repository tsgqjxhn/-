











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
 * 免扣元素下载记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuansuXiazaijilu")
public class YuansuXiazaijiluController {
    private static final Logger logger = LoggerFactory.getLogger(YuansuXiazaijiluController.class);

    @Autowired
    private YuansuXiazaijiluService yuansuXiazaijiluService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YuansuService yuansuService;



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
        PageUtils page = yuansuXiazaijiluService.queryPage(params);

        //字典表数据转换
        List<YuansuXiazaijiluView> list =(List<YuansuXiazaijiluView>)page.getList();
        for(YuansuXiazaijiluView c:list){
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
        YuansuXiazaijiluEntity yuansuXiazaijilu = yuansuXiazaijiluService.selectById(id);
        if(yuansuXiazaijilu !=null){
            //entity转view
            YuansuXiazaijiluView view = new YuansuXiazaijiluView();
            BeanUtils.copyProperties( yuansuXiazaijilu , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yuansuXiazaijilu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YuansuEntity yuansu = yuansuService.selectById(yuansuXiazaijilu.getYuansuId());
                if(yuansu != null){
                    BeanUtils.copyProperties( yuansu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuansuId(yuansu.getId());
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
    public R save(@RequestBody YuansuXiazaijiluEntity yuansuXiazaijilu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuansuXiazaijilu:{}",this.getClass().getName(),yuansuXiazaijilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            yuansuXiazaijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        Wrapper<YuansuXiazaijiluEntity> queryWrapper = new EntityWrapper<YuansuXiazaijiluEntity>()
            .eq("yuansu_id", yuansuXiazaijilu.getYuansuId())
            .eq("yonghu_id", yuansuXiazaijilu.getYonghuId())
            .eq("insert_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuansuXiazaijiluEntity yuansuXiazaijiluEntity = yuansuXiazaijiluService.selectOne(queryWrapper);
        if(yuansuXiazaijiluEntity==null){
            yuansuXiazaijilu.setInsertTime(new Date());
            yuansuXiazaijilu.setCreateTime(new Date());
            yuansuXiazaijiluService.insert(yuansuXiazaijilu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据1");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuansuXiazaijiluEntity yuansuXiazaijilu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yuansuXiazaijilu:{}",this.getClass().getName(),yuansuXiazaijilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            yuansuXiazaijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YuansuXiazaijiluEntity> queryWrapper = new EntityWrapper<YuansuXiazaijiluEntity>()
            .notIn("id",yuansuXiazaijilu.getId())
            .andNew()
            .eq("yuansu_id", yuansuXiazaijilu.getYuansuId())
            .eq("yonghu_id", yuansuXiazaijilu.getYonghuId())
            .eq("insert_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuansuXiazaijiluEntity yuansuXiazaijiluEntity = yuansuXiazaijiluService.selectOne(queryWrapper);
        if(yuansuXiazaijiluEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yuansuXiazaijilu.set
            //  }
            yuansuXiazaijiluService.updateById(yuansuXiazaijilu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据2");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yuansuXiazaijiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<YuansuXiazaijiluEntity> yuansuXiazaijiluList = new ArrayList<>();//上传的东西
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
                            YuansuXiazaijiluEntity yuansuXiazaijiluEntity = new YuansuXiazaijiluEntity();
//                            yuansuXiazaijiluEntity.setYuansuId(Integer.valueOf(data.get(0)));   //免扣元素 要改的
//                            yuansuXiazaijiluEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yuansuXiazaijiluEntity.setInsertTime(date);//时间
//                            yuansuXiazaijiluEntity.setCreateTime(date);//时间
                            yuansuXiazaijiluList.add(yuansuXiazaijiluEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yuansuXiazaijiluService.insertBatch(yuansuXiazaijiluList);
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
        PageUtils page = yuansuXiazaijiluService.queryPage(params);

        //字典表数据转换
        List<YuansuXiazaijiluView> list =(List<YuansuXiazaijiluView>)page.getList();
        for(YuansuXiazaijiluView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YuansuXiazaijiluEntity yuansuXiazaijilu = yuansuXiazaijiluService.selectById(id);
            if(yuansuXiazaijilu !=null){
                //entity转view
                YuansuXiazaijiluView view = new YuansuXiazaijiluView();
                BeanUtils.copyProperties( yuansuXiazaijilu , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yuansuXiazaijilu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    YuansuEntity yuansu = yuansuService.selectById(yuansuXiazaijilu.getYuansuId());
                if(yuansu != null){
                    BeanUtils.copyProperties( yuansu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuansuId(yuansu.getId());
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
    public R add(@RequestBody YuansuXiazaijiluEntity yuansuXiazaijilu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yuansuXiazaijilu:{}",this.getClass().getName(),yuansuXiazaijilu.toString());
        Wrapper<YuansuXiazaijiluEntity> queryWrapper = new EntityWrapper<YuansuXiazaijiluEntity>()
            .eq("yuansu_id", yuansuXiazaijilu.getYuansuId())
            .eq("yonghu_id", yuansuXiazaijilu.getYonghuId())
            .eq("insert_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuansuXiazaijiluEntity yuansuXiazaijiluEntity = yuansuXiazaijiluService.selectOne(queryWrapper);
        if(yuansuXiazaijiluEntity==null){
            yuansuXiazaijilu.setInsertTime(new Date());
            yuansuXiazaijilu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yuansuXiazaijilu.set
        //  }
        yuansuXiazaijiluService.insert(yuansuXiazaijilu);
            return R.ok();
        }else {
            return R.ok();
        }
    }



}
