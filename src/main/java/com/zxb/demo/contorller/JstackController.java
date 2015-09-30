package com.zxb.demo.contorller;

import com.zxb.demo.service.CoinsService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-30
 */
@RestController
@RequestMapping("/ops")
public class JstackController {
    private static Logger logger = Logger.getLogger(JstackController.class);

    @Resource
    private CoinsService coinsService;

    @RequestMapping(value = "/jstack")
    public Map<Thread, StackTraceElement[]> getJstack() {
        logger.info("JstackController --  getJstack");
//        final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
//        final String info = runtime.getName();
//        final int index = info.indexOf("@");
//        if (index != -1) {
//            final int pid = Integer.parseInt(info.substring(0, index));
//            System.out.println(info);
//            System.out.println(pid);
//        }
        Map<Thread, StackTraceElement[]> stackInfos = Thread.getAllStackTraces();
//        Set<Thread> keySet = stackInfos.keySet();
//        for (Thread tempThread : keySet) {
//            StackTraceElement[] tempElement = stackInfos.get(tempThread);
//            System.out.println("------------------------");
//            System.out.println(tempThread.getName());
//            System.out.println(tempThread.getState());
//            System.out.println(tempThread.getThreadGroup());
//            System.out.println(tempThread.getContextClassLoader());
//            for (StackTraceElement element : tempElement) {
//                System.out.println("    "+element);
//            }
//        }
        return stackInfos;
    }
}
