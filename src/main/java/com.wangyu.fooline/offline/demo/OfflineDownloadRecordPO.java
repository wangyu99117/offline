package com.wangyu.fooline.offline.demo;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by wangyu21 on 2016/10/14.
 */
@Document(collection = "pop_form_offline_download_record")
public class OfflineDownloadRecordPO {

    /**
     * 物理主键
     */
    @Id
    private Long id;

    /**
     * 父
     */
    private Long pid;

    /**
     * 业务类型标识
     */
    private Integer type;

    /**
     * 业务数据ID
     */
    private Long entityId;

    /**
     * 用户标识
     */
    private String user;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件扩展名
     */
    private String fileNameExt;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 云存储文件名
     */
    private String cloudKey;

    /**
     * 文件拆分得到的索引
     */
    private Integer fileIndex;

    /**
     * 用于扩展的一个字段，无确定含义，与type 字段挂钩
     */
    private String ext;

    /**
     * 文档创建时间
     */
    private Date created;

    /**
     * 文档修改时间
     */
    private Date modified;

    /**
     *  文件状态
     *  0 表示 初始化状态
     *  1 表示 后台正有程序在根据返回数据创建文件，此时文件没有准备好，不可以下载
     *  2 表示文件生成失败，
     *  3 表示文件生成成功，可以被检索和下载
     */
    private Integer downloadStatus;

    /**
     *  记录生成文件的出错信息
     */
    private String errMsg;

    /**
     *  文件删除状态 1，标识已删除，  0表示数据有效
     */
    private Integer deleteStatus;
    /**
     * 来源 ：1 来源共享导出  其他：未定
     */
    private Integer source;

    private String[] users;

    public String getCloudKey() {
        return cloudKey;
    }

    public void setCloudKey(String cloudKey) {
        this.cloudKey = cloudKey;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameExt() {
        return fileNameExt;
    }

    public void setFileNameExt(String fileNameExt) {
        this.fileNameExt = fileNameExt;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(Integer fileIndex) {
        this.fileIndex = fileIndex;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
