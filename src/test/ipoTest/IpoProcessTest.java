package ipoTest;

import base.BaseTest;

import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class IpoProcessTest extends BaseTest {
    final static Logger logger  =  LoggerFactory.getLogger(IpoProcessTest.class );

    @Resource
    private StatisticsService statisticsService;
    @Resource
    private IpoProcessService ipoProcessService;
    @Resource
    private IpoInvestService ipoInvestService;

    @Test
    public void testSample() throws Exception {
        ipoProcessService.selectProcessList("97952444248599350","02");
//        mockMvc.perform(post("/regulatory_statistics/ipoQuery")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn();
    }

    @Test
    public void testInvestItem() throws Exception {
        ipoInvestService.selectInvestItem("97952444248599350");
    }

    @Test
    public void testService(){
        statisticsService.getCodeAndName("IPODATA_BELONG_PLATE");

    }

    @Test
    public void testSelectFinanceOverList() throws Exception {
        ipoFinanceService.selectFinanceOverList("97952444248599350");
    }

    @Test
    public void testSelectFinanceProfitList() throws Exception {
        ipoFinanceService.selectFinanceProfitList("97952444248599350");
    }


}
