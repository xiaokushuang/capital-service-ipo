package ipoTest;

import base.BaseTest;
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SampleController extends BaseTest {
    final static Logger logger  =  LoggerFactory.getLogger(SampleController.class );

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void testSample() throws Exception {
        mockMvc.perform(post("/regulatory_statistics/ipoQuery")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testService(){
        statisticsService.getCodeAndName("IPODATA_BELONG_PLATE");
    }


}
