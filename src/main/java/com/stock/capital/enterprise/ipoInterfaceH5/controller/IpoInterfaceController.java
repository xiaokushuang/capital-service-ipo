package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ipoInterfaceH5")
public class IpoInterfaceController extends BaseController {

    private IpoInterfaceService ipoInterfaceService;
}
