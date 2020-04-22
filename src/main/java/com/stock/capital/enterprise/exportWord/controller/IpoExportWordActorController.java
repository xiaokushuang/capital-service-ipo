package com.stock.capital.enterprise.exportWord.controller;

import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordActorService;
import com.stock.core.web.DownloadView;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = {"IPO导出word"}, description = "IPO导出word")
@RestController
@RequestMapping(value = "IpoExportWordActorController")
public class IpoExportWordActorController {

  private static final Logger logger = LoggerFactory.getLogger(IpoExportWordActorController.class);

  @Value("#{app['file.path']}")
  private String filePath;
  @Autowired
  private IpoExportWordActorService ipoExportWordActorService;

  /**
   * 导出Word
   * @throws Exception
   */
  @RequestMapping(value = "exportWordCaseData")
  @ResponseBody
  public Map<String, String> exportWordCaseData(String caseId){
    Map<String, String> map = new HashMap<>();
    String path = filePath + "tempDocFiles";
    File dir = new File(path);
    if (!dir.exists()) {
      dir.mkdir();
    }
    String filePath = path + File.separator + UUID.randomUUID();
    logger.info("#######【路径" + filePath + "】###########");
    ipoExportWordActorService.createWordAsync(caseId,filePath);
    map.put("filePath",filePath);
    return map;
  }
  @RequestMapping(value = "exportWordIfSucess")
  @ResponseBody
  public Boolean exportWordIfSucess(String filePath) throws Exception {
    Boolean result = true;
    try {
      InputStream is = new FileInputStream(filePath + ".docx");
    }catch (Exception e){
      result = false;
    }
    return result;
  }
  @RequestMapping(value = "exportWordCase")
  @ResponseBody
  public ModelAndView exportWordCase(String title,String filePath, HttpServletResponse response) throws Exception {
    ModelAndView mv = new ModelAndView();
    try {
      mv.setView(new DownloadView());
      InputStream is = new FileInputStream(filePath + ".docx");
      logger.info("#######【重新读取】###########");
      mv.addObject(DownloadView.EXPORT_FILE, is);
      mv.addObject(DownloadView.EXPORT_FILE_NAME, title+"导出word.docx");
      mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.DOCX);
      response.setHeader("fileName", java.net.URLEncoder.encode(title+"导出word.docx", "utf-8"));
      File file = new File(filePath);
      file.delete();
      logger.info("#######【导出完成】###########");
    }catch (Exception e){

    }
    return mv;
  }

}
