package com.stock.capital.enterprise.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.common.dao.UserInputIndexMapper;
import com.stock.capital.enterprise.common.entity.UserInputIndex;
import com.stock.core.Constant;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.dto.Indexable;
import com.stock.core.service.BaseService;
import com.stock.core.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HuiXue on 2017/4/24 0024.
 */
@Service
public class IndexFieldBindService extends BaseService{

    private static final Logger logger = LoggerFactory.getLogger(IndexFieldBindService.class);

    private static final Map<Class,Map<String,String>> infoCache = new ConcurrentHashMap();

    private static final Map<Class,String> moduleCache = new ConcurrentHashMap();

    private FieldAnnotation annotation;

    @Autowired
    UserInputIndexMapper userInputIndexMapper;

    public <T extends Indexable> void dealNoResultKeyWord(String queryStr, Class<T> clazz){
        Map<String,String> indexBindMap = getIndexBindMap(clazz);
        //抽取查询关键字
        String reg = "[^\u4e00-\u9fa5]";
        Set<String> tSet = new HashSet<String>(Arrays.asList(queryStr.replaceAll(reg, ",").split(",")));
        String resultStr = StringUtils.EMPTY;
        for(String str:tSet){
            resultStr += str + ",";
        }
        //映射查询语句
        for(String str:indexBindMap.keySet()){
            queryStr = queryStr.replaceAll(indexBindMap.get(str),str);
        }
        //获取模块
        String moduleValue = getModuleValue(clazz);
        String projectCode = WebUtil.getCurrentRequest().getHeader(Constant.REQUEST_PROJECT);
        //插入DB
        UserInputIndex userInputIndex = new UserInputIndex();
        if(StringUtils.isNotEmpty(resultStr) && resultStr.length()>1 ){
            userInputIndex.setInputIndex(resultStr.substring(1,resultStr.length()-1));
        }else{
            return;
        }
        userInputIndex.setInputIndexQuery(queryStr);
        userInputIndex.setIndexProject(projectCode);
        userInputIndex.setIndexModule(moduleValue);
        userInputIndexMapper.insertSelective(userInputIndex);
    }

    private <T extends Indexable> Map<String,String> getIndexBindMap(Class<T> clazz){
        Map<String,String> indexBindMap = Maps.newHashMap();
        if(infoCache.get(clazz) == null){
            List<Field> fields = Lists.newArrayList();
            Class superClazz = clazz;
            while (superClazz != null && superClazz != Object.class) {
                fields.addAll(Arrays.asList(superClazz.getDeclaredFields()));
                superClazz = superClazz.getSuperclass();
            }
            if(fields != null) {
                for (Field field : fields) {
                    annotation = field.getAnnotation(FieldAnnotation.class);
                    if(annotation != null) {
                        indexBindMap.put(annotation.name(), annotation.value());
                    }
                }
                synchronized (infoCache){
                    infoCache.put(clazz,indexBindMap);
                }
            }
        }else{
            indexBindMap = infoCache.get(clazz);
        }
        return indexBindMap;
    }

    private <T extends Indexable> String getModuleValue(Class<T> clazz){
       String moduleValue = StringUtils.EMPTY;
       if(StringUtils.isEmpty(moduleCache.get(clazz))){
           moduleValue = clazz.getAnnotation(FieldAnnotation.class).value();
           synchronized (moduleCache){
               moduleCache.put(clazz,moduleValue);
           }
       }else{
           moduleValue = moduleCache.get(clazz);
       }
       return moduleValue;
    }
}
