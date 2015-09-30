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
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private CoinsService coinsService;

    /**
     *
     * curl -X POST -d "user_id=1&coins=10" "http://localhost:8080/gold-game/user/add"
     * @param userId
     * @param coinsNum
     * @return
     */
    @RequestMapping(value = "/add", params = {"user_id", "coins"}, method = RequestMethod.POST)
    public Message<Coins> addUserandCoins(@RequestParam("user_id") Long userId, @RequestParam("coins") Long coinsNum) {
        logger.info("CoinsService --  addUserandCoins -- userId = " + userId + " -- coinsNum = " + coinsNum);

        Coins coins = new Coins();
        coins.setUserId(userId);
        coins.setCoinNum(coinsNum);

        Message<Coins> result = new Message<Coins>();

        Long ret = coinsService.addUserRecord(coins);

        if (ret.equals(0L)) {
            result.setErrorMsg("The user information has already in the db ,you don't to add it again");
            result.setSucess(false);
        } else {
            result.setSucess(true);
        }

        return result;
    }
}
