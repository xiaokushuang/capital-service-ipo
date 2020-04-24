package ipoTest;

import base.BaseTest;
import com.stock.capital.enterprise.exportWord.controller.IpoExportWordActorController;
import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordService;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ipoExportWord extends BaseTest {
  final static Logger logger  =  LoggerFactory.getLogger(IpoProcessTest.class );

  @Autowired
  IpoExportWordService ipoExportWordService;

  @Autowired
  IpoInvestService ipoInvestService;

  @Autowired
  CompanyOverviewService companyOverviewService;


  @Test
  public void testSelectProcessList() throws Exception {

//    ipoExportWordService.getCompanyInformation("97952444248599354");//上级数控
//    ipoInvestService.selectInvestItem("98171333342405324");
    companyOverviewService.getIncomeData("98171333342405324");
//    ipoExportWordService.getCompanyInformation("97952444248599434");//中创物流首次公开发行
//    ipoExportWordService.getCompanyInformation("98171333342405324");//硅产业
  }
}
