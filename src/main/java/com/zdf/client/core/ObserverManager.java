package com.zdf.client.core;

import com.zdf.client.boot.AppLaunch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObserverManager {
    // 观察者列表
    List<ObserverFunction> observers;

    public ObserverManager() {
        observers = new ArrayList<>();
    }

    // 注册新的观察者
    public void registerEventObserver(ObserverFunction observerFunction) {
        observers.add(observerFunction);
    }

    // 唤醒观察者
    // 反射让观察者调用形参里的那个方法
    public void wakeupObserver(AppLaunch.ObserverType observerType, Object... params) throws InvocationTargetException, IllegalAccessException {
        for (ObserverFunction observer : observers) {
            for (Method method : observer.getClass().getMethods()) {
                if (method.getName().equals(observerType.name())) {
                    method.invoke(observer, params);
                }
            }
        }
    }
}
