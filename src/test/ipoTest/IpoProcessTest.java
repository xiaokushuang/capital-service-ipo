package ipoTest;

import base.BaseTest;

import com.stock.capital.enterprise.ipoCase.dto.IpoExamineDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoExamineService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

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
    @Resource
    private IpoFinanceService ipoFinanceService;
    @Resource
    private IpoFeedbackService ipoFeedbackService;
    @Resource
    private IpoExamineService ipoExamineService;
    @Resource
    private CompanyOverviewService companyOverviewService;

    @Test
    public void testSelectProcessList() throws Exception {
        ipoProcessService.selectProcessList("97952444248599354","02");
    }

    @Test
    public void testInvestItem() throws Exception {
        ipoInvestService.selectInvestItem("97952444248599344");
    }

    @Test
    public void testSelectFinanceList() throws Exception {
        ipoFinanceService.selectFinanceList("97952444248599344");
    }

    @Test
    public void testSelectFinanceOverList() throws Exception {
        ipoFinanceService.selectFinanceOverList("97952444248599344");
    }

    @Test
    public void testSelectFinanceProfitList() throws Exception {
        ipoFinanceService.selectFinanceProfitList("97952444248599344");
    }

    @Test
    public void selectFeedbackList() throws Exception {
        IpoFeedbackDto paramDto = new IpoFeedbackDto();
        paramDto.setId("97952444248599344");
        List<IpoFeedbackDto> resultList =  ipoFeedbackService.selectFeedbackList(paramDto);
    }

    @Test
    public void selectSecondLabelList() throws Exception {
        ipoFeedbackService.selectSecondLabelList("745777672754254995","745652376752683397");
    }

    @Test
    public void selectQuestionListByLetterId() throws Exception {
        IpoFeedbackDto paramDto = new IpoFeedbackDto();
        paramDto.setLetterId("745777672754254995");
        paramDto.setFirstLabelId("745439065452960302");
//        paramDto.setOnlyResponse("0");
        paramDto.setSecondLabelId("745777672749757382,745652376752623338");
        ipoFeedbackService.selectQuestionListByLetterId(paramDto);
    }

    @Test
    public void selectExamineList() throws Exception {
        IpoExamineDto resultDto = ipoExamineService.selectExamineList("97952444248599376");
    }

    @Test
    public void selectMemberList() throws Exception {
        ipoExamineService.selectMemberList("97952444248599344","2018-09-18");
    }

    @Test
    public void selectNewFeedbackList(){
        ipoFeedbackService.selectNewFeedbackList("97952444248599354");
    }

    @Test
    public void selectNewQuestionList(){
        ipoFeedbackService.selectNewQuestionList(
                "745777672754254995","2200004","2200004006","0");
    }

    @Test
    public void selectNewExamineList(){
        ipoExamineService.selectNewExamineList("97952444248599354");
    }

    @Test
    public void checkSingleAnnounce(){
        ipoProcessService.checkSingleAnnounce("AN201902131295659791");
    }

    @Test
    public void checkMultiplyFile(){
        ipoProcessService.checkMultiplyFile("AN201902131295659791,AN201902131295659792");
    }

    @Test
    public void getData(){
        companyOverviewService.getPatentData("98171333342458409");
    }
}
