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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */
@WebAppConfiguration
public class TransgerControllerTest extends BaseTest {
    private static Logger logger = Logger.getLogger(TransgerControllerTest.class);

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
    public void testcoinsTransfer() throws Exception {
        Long userId = 1000000L;
        Coins coins = new Coins();
        coins.setUserId(userId);
        coins.setCoinNum(20L);
        coinsService.addUserRecord(coins);

        mockMvc.perform(post("/transaction/transfer?from_user_id=1000000&to_user_id=1000001&coins=500"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(false));

        mockMvc.perform(post("/transaction/transfer?from_user_id=1000001&to_user_id=1000000&coins=500"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(false));
        userId = 1000001L;
        coins = new Coins();
        coins.setUserId(userId);
        coins.setCoinNum(20L);
        coinsService.addUserRecord(coins);

        mockMvc.perform(post("/transaction/transfer?from_user_id=1000001&to_user_id=1000000&coins=100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(false));


        mockMvc.perform(post("/transaction/transfer?from_user_id=1000001&to_user_id=1000000&coins=15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.sucess").value(true));
    }
}
