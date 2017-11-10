package com.stock.capital.enterprise.sample.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stock.capital.enterprise.common.entity.Sample;
import com.stock.capital.enterprise.sample.dto.SampleDto;
import com.stock.capital.enterprise.sample.service.SampleService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.Page;
import com.stock.core.exception.ApplicationException;

@Controller
@RequestMapping("sample")
public class SampleController extends BaseController {

    @Autowired
    private SampleService sampleService;
    
    @RequestMapping(value = "crudInit", method = RequestMethod.GET)
    public ModelAndView crudInit() throws IOException {
        ModelAndView mv = new ModelAndView("sample/crud/query");
        mv.addObject("sampleDto", new SampleDto());
        return mv;
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ModelAndView crudQuery(SampleDto sampleDto) {
        ModelAndView mv = new ModelAndView("sample/crud/query");
        Page<Sample> page = super.commonQuery(
                "com.stock.capital.enterprise.sample.dao.SampleBizMapper.testPage", sampleDto, "sampleDto");
        mv.addObject("queryList", page.getData());
        return mv;
    }

    @RequestMapping(value = "ajaxData")
    @ResponseBody
    public JsonResponse<Page<Sample>> ajaxData(SampleDto sampleDto) {
        Page<Sample> page = super.commonQuery(
                "com.stock.capital.enterprise.sample.dao.SampleBizMapper.testPage", sampleDto, "sampleDto");
        JsonResponse<Page<Sample>> response = new JsonResponse<Page<Sample>>();
        response.setResult(page);
        return response;
    }

    @RequestMapping(value = "throwException", method = RequestMethod.POST)
    public ModelAndView throwException() {
        // 自己抛出业务异常或运行中出现异常
        // errorCode 需要在messages.properties定义
        if (true)
            throw new ApplicationException("error.test");
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("sample/crud/edit");
        mv.addObject("sampleDto", new SampleDto());
        mv.addObject("emptySampleDto", new SampleDto());
        return mv;
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelAndView modify(@RequestParam("id") String sampleId) {
        ModelAndView mv = new ModelAndView("sample/crud/edit");
        mv.addObject("sampleDto", sampleService.modify(sampleId));
        mv.addObject("emptySampleDto", new SampleDto());
        return mv;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(SampleDto sampleDto) {
        sampleService.save(sampleDto);
        return "forward:/sample/query?toPageState=back";
    }

    @RequestMapping("del")
    public String del(@RequestParam("id") String sampleId) {
        sampleService.del(sampleId);
        return "forward:/sample/query?toPageState=back";
    }

    @RequestMapping(value = "remoteValidate", method = RequestMethod.POST)
    @ResponseBody
    public boolean remoteValidate(String name) {
        // false是校验不通过,true是通过
        boolean validateResult = true;
        if (("12345").equals(name)) {
            validateResult = false;
        }
        return validateResult;
    }

    @RequestMapping(value = "pop", method = RequestMethod.POST)
    public ModelAndView pop() {
        ModelAndView mv = new ModelAndView("sample/crud/pop");
        mv.addObject("sampleDto", new SampleDto());
        return mv;
    }

    @RequestMapping(value = "ajaxInit", method = RequestMethod.GET)
    public ModelAndView ajaxInit() {
        ModelAndView mv = new ModelAndView("sample/ajax/query");
        mv.addObject("sampleDto", new SampleDto());
        return mv;
    }

    @RequestMapping(value = "ajaxQuery", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxQuery(SampleDto sampleDto) {
        return super.commonQuery("com.stock.capital.enterprise.sample.dao.SampleBizMapper.testPage", sampleDto);

    }

}
