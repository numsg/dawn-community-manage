package com.gsafety.dawn.community.manage.service.serviceimpl;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.*;


@Component
public class DataMoveTaskImpl extends DataCollectionServiceImpl implements ServletContextListener {

    private Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        sce.getServletContext().log("定时器已启动");
        timer.schedule(new OneTask(), 10000, 3600000);//延迟60秒，定时1小时
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    class OneTask extends TimerTask {//继承TimerTask类

        @Override
        public void run() {
            System.out.println("开始一次数据迁移");
            timeQuery();
            }
        }
}
