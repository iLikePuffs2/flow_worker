package com.zdf.client;

import com.zdf.client.boot.AppLaunch;
import com.zdf.client.boot.Launch;
import com.zdf.client.task.LarkTask;


public class Main {
    public static void main(String[] args) {
        // 进行配置
        Launch l = new AppLaunch(LarkTask.class);
        // 启动
        l.start();
    }
}
