package com.zxb.demo.contorller;

import com.zxb.demo.model.Coins;
import com.zxb.demo.model.Message;
import com.zxb.demo.service.CoinsService;
import com.zxb.demo.util.Constant;
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
@RequestMapping("/transaction")
public class TransferController {
    private static Logger logger = Logger.getLogger(TransferController.class);

    @Resource
    private CoinsService coinsService;

    /**
     * curl -X POST -d "from_user_id=1&to_user_id=2&coins=500" "http://localhost:8080/transaction/transfer"
     * @param fromUserId
     * @param toUserId
     * @param coinsNum
     * @return
     */
    @RequestMapping(value = "/transfer", params = {"from_user_id", "to_user_id", "coins"}, method = RequestMethod.POST)
    public Message<Coins> coinsTransfer(@RequestParam("from_user_id") Long fromUserId, @RequestParam("to_user_id") Long toUserId, @RequestParam("coins") Long coinsNum) {
        logger.info("TransferController --  coinsTransfer -- fromUserId = " + fromUserId + " -- toUserId = " + toUserId + " -- coinsNum = " + coinsNum);
        Message<Coins> result = new Message<Coins>();

        Long ret = null;
        try {
            ret = coinsService.coinTransfer(fromUserId, toUserId, coinsNum);
        } catch (Exception e) {
            logger.error("TransferController -- coinsTransfer -- exception "+e.getMessage());
            result.setErrorMsg("some thing unexpect haddpened,transfer fail");
            result.setSucess(false);
            return result;
        }
        if (ret.equals(Constant.FROM_USER_INVALID)) {
            result.setErrorMsg("the from user id is invalid");
            result.setSucess(false);
        } else if (ret.equals(Constant.TO_USER_INVALID)) {
            result.setErrorMsg("the to user id is invalid");
            result.setSucess(false);
        } else if (ret.equals(Constant.FROM_USER_COINS_NOT_ENOUGH)) {
            result.setErrorMsg("the from user don't have enough money");
            result.setSucess(false);
        } else if (ret.equals(Constant.TRANSFER_SUCCESS)) {
            result.setSucess(true);
        }

        return result;
    }
}
