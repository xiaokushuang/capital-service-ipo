package com.stock.capital.enterprise.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.core.log.LogEvent;
import com.stock.core.log.LogProcessComponent;
import com.stock.core.service.BaseService;

@Service
public class LogService extends BaseService implements LogProcessComponent {

//    @Autowired
//    private LogMapper logMapper;

    @Transactional(rollbackFor = { Exception.class })
    public void processLog(LogEvent logEvent) {
//        if (logEvent.getTenantId() != null) {
//            MultiTenantContextHolder.set(logEvent.getTenantId());
//        }
//        Log entity = new Log();
//        entity.setLogType(logEvent.getLogType());
//        entity.setLogName(logEvent.getLogName());
//        entity.setLogContent(logEvent.getLogContent());
//        entity.setLogIp(logEvent.getLogRemoteAddress());
//        entity.setLogTime(getDBTime());       
//        //2017/5/22 需求1333 meijf start
//        entity.setLogWay(logEvent.getLogWay());
//        //2017/5/22 需求1333 meijf end        
//        entity.setCreateUser(logEvent.getLogUser());
//        entity.setUpdateUser(logEvent.getLogUser());
//        logMapper.insert(entity);
    }

}
