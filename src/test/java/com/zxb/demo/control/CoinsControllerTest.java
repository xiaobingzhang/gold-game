package com.zxb.demo.control;

import com.zxb.demo.BaseTest;
import com.zxb.demo.model.Coins;
import com.zxb.demo.service.CoinsService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */
@WebAppConfiguration
public class CoinsControllerTest extends BaseTest {
    private static Logger logger = Logger.getLogger(CoinsControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }


    @Resource
    private CoinsService coinsService;

    @Test
    public void testqueryCoinsNumByUserId() throws Exception {
        mockMvc.perform(get("/coins/user/1000000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(false));
        Long userId = 1000000L;
        Coins coins = new Coins();
        coins.setUserId(userId);
        coins.setCoinNum(20L);
        coinsService.addUserRecord(coins);
        mockMvc.perform(get("/coins/user/1000000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(true))
                .andExpect(jsonPath("$.object.userId").value(1000000))
                .andExpect(jsonPath("$.object.coinNum").value(20));
    }
}
