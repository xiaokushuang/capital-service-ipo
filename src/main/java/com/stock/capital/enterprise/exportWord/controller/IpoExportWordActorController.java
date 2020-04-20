package com.stock.capital.enterprise.exportWord.controller;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordActorService;
import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordService;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.utils.WXUtils;
import com.stock.core.dto.JsonResponse;
import com.stock.core.web.DownloadView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@Api(tags = {"IPO导出word"}, description = "IPO导出word")
@RestController
@RequestMapping(value = "IpoExportWordActorController")
public class IpoExportWordActorController {


  @Autowired
  private IpoExportWordActorService ipoExportWordActorService;
  @Autowired
  private IpoExportWordService ipoExportWordService;
  private static final Logger logger = LoggerFactory.getLogger(IpoExportWordActorController.class);

  @Value("#{app['file.path']}")
  private String filePath;
  /**
   * 导出Word
   * @throws Exception
   */
  @RequestMapping(value = "exportWordCase")
  @ResponseBody
  public ModelAndView exportWordCase(HttpServletRequest request, String caseId, HttpServletResponse response) throws Exception {
    ModelAndView mv = new ModelAndView();
    Resource resource = new ClassPathResource("templates/IPO导出word模板.docx");
    mv.setView(new DownloadView());
    Map<String,Object> dataMap = ipoExportWordService.getCompanyInformation(caseId);
    String name=((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName();
    logger.info("#######【开始导出】###########");
    Map<String,Object> exportMap = ipoExportWordActorService.exportWordCase(resource.getInputStream(),caseId);
    try {
        logger.info("#######【poi导出完成】###########");
        Document doc = new Document((InputStream)exportMap.get("inputStream"));
        logger.info("#######【转spire】###########");
        doc.updateTableOfContents();
        logger.info("#######【更新目录】###########");
        String path = filePath+"tempDocFiles";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String filePath = path + File.separator + UUID.randomUUID();
        logger.info("#######【路径"+filePath+"】###########");
        doc.saveToFile(filePath + ".docx", FileFormat.Docx);
        doc.close();
        //重新读取文档，进行操作
        InputStream is = new FileInputStream(filePath + ".docx");
        logger.info("#######【重新读取】###########");
        mv.addObject(DownloadView.EXPORT_FILE, is);
    }catch (){
        mv.addObject(DownloadView.EXPORT_FILE, (InputStream)exportMap.get("inputStream"));
    }

    mv.addObject(DownloadView.EXPORT_FILE_NAME, name+"导出word.docx");
    mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.DOCX);
    response.setHeader("fileName", java.net.URLEncoder.encode(name+"导出word.docx", "utf-8"));
    logger.info("#######【导出完成】###########");
    return mv;
  }

}
