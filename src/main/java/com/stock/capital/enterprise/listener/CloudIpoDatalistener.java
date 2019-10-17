package com.stock.capital.enterprise.listener;

import java.util.Date;
import java.util.Map;

import com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoFileUploadController;
import com.stock.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.stock.core.dao.RedisDao;


@Component
@RabbitListener(queues = "cloud.updateIpoData")
public class CloudIpoDatalistener {

    private final Logger logger = LoggerFactory.getLogger(CloudIpoDatalistener.class);

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IpoFileUploadController ipoFileUploadController;

    @RabbitHandler
    public void process(Object message) {
      //Map<String,String> map = (Map<String,String>)message;
      if (message != null) {
          String lock = null;
          String lockFlag = null;
          String id = new String(((Message) message).getBody());
          id = (String)JsonUtil.fromJson(id,Map.class).get("id");
          try {
              lockFlag = "cloud.updateIpoData" + id;
              lock = redisDao.acquireDistributedLockWithTimeout(lockFlag, 10, 1000 * 5);
              ipoFileUploadController.ipoDataUploadAllCom(id);
          }catch (Exception e) {
              logger.error("process cloud.updateIpoData info message error by id [{}], caused by:{}",
                      id, Throwables.getStackTraceAsString(e));
          }finally {
              if (StringUtils.isNotEmpty(lock)) {
                  // 释放锁
                  redisDao.releaseDistributedLock(lockFlag, lock);
              }
          }
      }
    }
}
