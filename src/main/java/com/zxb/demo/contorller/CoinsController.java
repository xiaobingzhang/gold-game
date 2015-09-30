package com.zxb.demo.contorller;

import com.zxb.demo.model.Coins;
import com.zxb.demo.model.Message;
import com.zxb.demo.service.CoinsService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */
@RestController
@RequestMapping("/coins")
public class CoinsController {
    private static Logger logger = Logger.getLogger(CoinsController.class);

    @Resource
    private CoinsService coinsService;

    /**
     * curl -X GET http://localhost:8080/gold-game/coins/user/1
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public Message<Coins> queryCoinsNumByUserId(@PathVariable("user_id") Long userId) {
        logger.info("CoinsController --  queryCoinsNumByUserId -- userId = " + userId);

        Message<Coins> result = new Message<Coins>();

        Coins coins = coinsService.getCoinsByUserId(userId);
        logger.info("CoinsController --  queryCoinsNumByUserId -- coins = " + coins);
        if (null != coins) {
            result.setSucess(true);
            result.setObject(coins);
        } else {
            result.setErrorMsg("The user doesn't exist in the db");
            result.setSucess(false);
        }
        return result;
    }
}
