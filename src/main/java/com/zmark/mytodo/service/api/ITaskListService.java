package com.zmark.mytodo.service.api;

import com.zmark.mytodo.bo.list.req.TaskListCreateReq;
import com.zmark.mytodo.dto.list.TaskListDTO;
import com.zmark.mytodo.exception.NoDataInDataBaseException;
import com.zmark.mytodo.exception.RepeatedEntityInDatabase;

/**
 * @author ZMark
 * @date 2023/12/7 19:22
 */
public interface ITaskListService {

    TaskListDTO findById(long taskListId) throws NoDataInDataBaseException;

    /**
     * @throws NoDataInDataBaseException 如果没有找到对应的分组
     * @throws RepeatedEntityInDatabase  如果分组中同名的清单已经存在
     */
    TaskListDTO createNewTaskList(TaskListCreateReq creatReq) throws NoDataInDataBaseException, RepeatedEntityInDatabase;

    long countByTaskGroup(long tagGroupId);

    long countByTaskGroup(String groupName);
}
