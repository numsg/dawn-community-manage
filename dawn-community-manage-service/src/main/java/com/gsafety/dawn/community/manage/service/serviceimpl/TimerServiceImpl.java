package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.changde.RequestParamModel;
import com.gsafety.dawn.community.manage.contract.model.changde.TroubleshootRecordModel;
import com.gsafety.dawn.community.manage.contract.service.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.*;


@Component
@Service
public class TimerServiceImpl extends DataCollectionServiceImpl implements ServletContextListener, TimerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Timer timer = null;

    @Value("${app.waitTime}")
    private Integer waitTime;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        sce.getServletContext().log("定时器已启动");
        timer.schedule(new OneTask(), 10000, waitTime);//延迟60秒，定时1小时
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public Boolean flushData(String villageId) {
        timer.cancel();
        try {
            timeQuery();
        } catch (Exception e) {
            logger.error("flushData() error", e.getMessage(), e);
        }
        //执行完之后再次
        timer = new Timer(true);
        timer.schedule(new OneTask(), 10000, waitTime);//延迟60秒，定时1小时
        return true;
    }

    @Override
    public List<TroubleshootRecordModel> getDataFromPhone(RequestParamModel requestParamModel) {
        return getDataFromMobileTerminal(requestParamModel);
    }

    class OneTask extends TimerTask {//继承TimerTask类
        @Override
        public void run() {
            System.out.println("开始一次数据同步");
            try {
                timeQuery();
            } catch (Exception e) {
                logger.error("timer run() error", e.getMessage(), e);
            }
        }
    }
}
