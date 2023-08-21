package com.zdf.client.Client;

import com.alibaba.fastjson.JSON;
import com.zdf.client.constant.UserConfig;
import com.zdf.client.enums.ErrorStatus;
import com.zdf.client.data.*;
import com.zdf.client.enums.TaskStatus;
import com.zdf.client.rpc.FlowServer;
import com.zdf.client.rpc.FlowServerImpl;

import java.util.List;

public class TaskFlowerImpl implements TaskFlower{
    // 使用FlowServer来执行任务管理
    FlowServer flowServer = new FlowServerImpl();
    @Override
    public String createTask(AsyncTaskRequest asyncTaskRequest) {
        Object o = judgeReturnStatus(flowServer.createTask(asyncTaskRequest));
        String taskId = JSON.parseObject(JSON.toJSONString(o), String.class);
        return taskId;
    }

    @Override
    public void setTask(AsyncTaskSetRequest asyncTaskSetRequest) {
        judgeReturnStatus(flowServer.setTask(asyncTaskSetRequest));
    }

    @Override
    public AsyncTaskReturn getTask(Long taskId) {
        Object o = judgeReturnStatus(flowServer.getTask(taskId));
        String s = JSON.toJSONString(o);
        TaskByTaskIdReturn<AsyncTaskReturn> asyncFlowTask = JSON.parseObject(s, TaskByTaskIdReturn.class);
        AsyncTaskReturn asyncTaskReturn = JSON.parseObject(JSON.toJSONString(asyncFlowTask.getTaskData()), AsyncTaskReturn.class);
        return asyncTaskReturn;
    }

    @Override
    public List<AsyncTaskReturn> getTaskList(Class<?> taskType, int status, int limit) {
        Object o = judgeReturnStatus(flowServer.getTaskList(taskType.getSimpleName(), status, limit));
        List<AsyncTaskReturn> asyncTaskReturns = getAsyncTaskReturns(o);
        return asyncTaskReturns;
    }

    @Override
    public List<ScheduleConfig> getTaskTypeCfgList() {
        Object o = judgeReturnStatus(flowServer.getTaskTypeCfgList());
        ConfigReturn configReturn = JSON.parseObject(JSON.toJSONString(o), ConfigReturn.class);
        List<ScheduleConfig> scheduleConfigs = JSON.parseArray(JSON.toJSONString(configReturn.getScheduleCfgList()), ScheduleConfig.class);
        return scheduleConfigs;
    }


    public List<AsyncTaskReturn> doGetUserTaskList(String user_id, int status) {
        Object o = judgeReturnStatus(flowServer.getUserTaskList(user_id, status));
        List<AsyncTaskReturn> asyncTaskReturns = getAsyncTaskReturns(o);
        return asyncTaskReturns;
    }

    @Override
    public List<AsyncTaskReturn> getUserTaskList(List<TaskStatus> taskStatuses) {
        String user_id = UserConfig.USERID;
        int statusList = 0;
        for (TaskStatus status : taskStatuses) {
            statusList |= status.getStatus();
        }
        return doGetUserTaskList(user_id, statusList);
    }

    private List<AsyncTaskReturn> getAsyncTaskReturns(Object o) {
        TaskList taskList = JSON.parseObject(JSON.toJSONString(o), TaskList.class);
        List<AsyncTaskReturn> asyncTaskReturns = JSON.parseArray(JSON.toJSONString(taskList.getTaskList()), AsyncTaskReturn.class);
        return asyncTaskReturns;
    }

    @Override
    public void createTaskCFG(ScheduleConfig scheduleConfig) {
        judgeReturnStatus(flowServer.createTaskCFG(scheduleConfig));
    }

    public <E> E judgeReturnStatus(ReturnStatus<E> returnStatus) {
        if (returnStatus.getCode() != ErrorStatus.SUCCESS.getErrCode()) {
            throw new RuntimeException(returnStatus.getMsg());
        }
        return returnStatus.getResult();
    }
}
