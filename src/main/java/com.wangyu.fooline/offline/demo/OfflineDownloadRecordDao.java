package com.wangyu.fooline.offline.demo;



import com.wangyu.test.exception.MongoOperationException;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyu21 on 2016/10/17
 */
public interface OfflineDownloadRecordDao {

    /**
     * 保存
     * @param record
     * @return
     */
    boolean save(OfflineDownloadRecordPO record);

    /**
     * 更改 record
     * @param record
     * @return
     */
    boolean update(OfflineDownloadRecordPO record) throws MongoOperationException;

    /**
     *  删除文件
     */
    boolean delete(Long id);

    /**
     *  根据record对象里设置的值查询 mongo
     * @param record
     * @param orderBy
     * @param isAsc
     * @return
     */
    List<OfflineDownloadRecordPO> find(OfflineDownloadRecordPO record, String orderBy, boolean isAsc);

    /**
     *  查询 propertyName 字段 在 beforeDate之前的 数据
     * @param propertyName
     * @param beforeDate
     * @return
     */
    List<OfflineDownloadRecordPO> findByBeforeDate(String propertyName, Date beforeDate);

    /**
     * 根据record对象字段 查询改对象是否存在
     * @param record
     * @return
     */
    boolean isExist(OfflineDownloadRecordPO record);
}
