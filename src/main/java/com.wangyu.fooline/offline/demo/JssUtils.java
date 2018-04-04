package com.wangyu.fooline.offline.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;


/**
 * 云存储工具类
 */
public class JssUtils {
    private static final Logger logger = LoggerFactory.getLogger(JssUtils.class);

    private static String accessKey;
    private static String secertKey;
    private static String jssUrl;



    /**
     * 上传失败重试
     */
    private static final Integer RETRY_FOR_UPLOAD = 3;


    public void init(){
        try{

        }catch (Exception e){
            logger.error("JssUtils init method execute fail! param accessKey = {}, secertKey = {}, jssUrl = {}", accessKey, secertKey, jssUrl, e);
        }
    }
    /**
     * 获取云存储文件输入流
     * @param bucketName    云存储桶名称
     * @param jssFileName   云储存文件名
     *                      一定要手动关闭对象（文档中对对象进行了关闭）
     * @return
     */
    public static InputStream download(String bucketName, String jssFileName) {
        return null;
    }

    public static void delete(String bucketName, String jssFileName){
        return ;
    }



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param jssFileName   云储存文件名
     * @param uploadFile    上传文件
     * @return
     */


    public static String upload(String bucketName, String jssFileName, File uploadFile) {
        return null;
    }



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param jssFileName   云储存文件名
     * @param fileSize    上传文件的大小
     * @param inputStream 上传文件的输入流
     * @return
     */


    public static String upload(String bucketName, String jssFileName, Long fileSize , InputStream inputStream) {
        return null;
    }



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param fileSize    上传文件的大小
     * @param inputStream 上传文件的输入流
     * @return
     */


    public static String upload(String bucketName, Long fileSize , InputStream inputStream) {
        String jssFileName = getUUID();
        return null;
    }



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param file    上传文件的大小
     * @return 云存储上的文件名
     */


   /* public static String upload(String bucketName, MultipartFile file) throws IOException {
        String jssFileName = getUUID() + getExtention(file.getOriginalFilename());
        getJssService().bucket(bucketName).object(jssFileName).entity(file.getSize(), file.getInputStream()).put();
        return jssFileName;
    }*/



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param uploadFile    上传文件
     * @return
     */


    public static String upload(String bucketName, File uploadFile) {
        int uploadTime = 0;
        String jssFileName = null;
        while(uploadTime < RETRY_FOR_UPLOAD){
            try{
                logger.info("...............DownloadTask ..............ver 2.....................bucketName = {}", bucketName);
                logger.info("...............DownloadTask ..............ver 2.....................uploadFile.length() = {}", uploadFile.length());
                /*jssFileName = DateUtils.getDateStr(Calendar.getInstance().getTime(), "yyyyMMdd_") + getUUID() + getExtention(uploadFile.getName());*/
                logger.info("...............DownloadTask ..............ver 2.....................ssFileName = {}", jssFileName);
                /*getJssService().bucket(bucketName).object(jssFileName).entity(uploadFile).put();*/
                break;
            }catch(Exception e){
                logger.error("...............DownloadTask ..............ver 2.................... 云存储上传失败！ bucketName = {} uploadTime = {}", bucketName, uploadTime);
                uploadTime ++ ;
            }
        }
        if(null!=uploadFile && uploadFile.exists()){
            uploadFile.delete();
        }
        if(uploadTime < RETRY_FOR_UPLOAD){
            return jssFileName;
        }
        throw new RuntimeException("...............DownloadTask ..............ver 2...................云存储上传失败！ bucketName = " + bucketName);
    }



/**
     * 上传本地文件到云存储服务器
     * @param bucketName    云存储桶名称
     * @param fileSize    上传文件的大小
     * @param inputStream    上传文件的大小
     * @param extension    上传文件的后缀
     * @return 云存储上的文件名
     */


    public static String upload(String bucketName, Long fileSize , InputStream inputStream, String extension) {
        String jssFileName = getUUID() + "."+extension;
        /*getJssService().bucket(bucketName).object(jssFileName).entity(fileSize, inputStream).put();*/
        return jssFileName;
    }




    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }



/**
     * 得到扩展名
     *
     * @param fileName
     * @return
     */


    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos < 0) {
            return fileName;
        }
        return fileName.substring(pos);
    }

    public void setAccessKey(String accessKey) {
        JssUtils.accessKey = accessKey;
    }

    public void setSecertKey(String secertKey) {
        JssUtils.secertKey = secertKey;
    }

    public void setJssUrl(String jssUrl) {
        JssUtils.jssUrl = jssUrl;
    }

}


