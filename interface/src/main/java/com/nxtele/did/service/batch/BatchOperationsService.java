package com.nxtele.did.service.batch;

import java.util.List;

/**
 * 批量操作类
 */
public interface BatchOperationsService<T> {
    /**
     * 批量插入操作
     * @param objectList        带插入的实体类集合
     * @param fullClassName    带插入实体的Dao的全类名(带包路径；必须继承BaseMapper)
     * @param batchSize         每批次的大小
     */
    void batchInsert(List<T> objectList, String fullClassName, int batchSize);

    /**
     * 批量更新操作(根据实体id)
     * @param objectList    带插入的实体类集合
     * @param fullClassName  带插入实体的Dao的全类名(带包路径；必须继承BaseMapper)
     * @param batchSize       每批次的大小
     */
    void batchUpdate(List<T> objectList, String fullClassName, int batchSize);
}
