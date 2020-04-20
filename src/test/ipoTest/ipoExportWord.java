package ipoTest;

import base.BaseTest;
import com.stock.capital.enterprise.exportWord.controller.IpoExportWordActorController;
import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ipoExportWord extends BaseTest {
  final static Logger logger  =  LoggerFactory.getLogger(IpoProcessTest.class );

  @Autowired
  IpoExportWordService ipoExportWordService;

  @Test
  public void testSelectProcessList() throws Exception {
    ipoExportWordService.getCompanyInformation("97952444248599354");//上级数控
  }
}
