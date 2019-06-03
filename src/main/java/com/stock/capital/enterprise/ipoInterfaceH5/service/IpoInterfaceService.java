package com.stock.capital.enterprise.ipoInterfaceH5.service;

import com.stock.core.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public class IpoInterfaceService extends BaseService {

}
