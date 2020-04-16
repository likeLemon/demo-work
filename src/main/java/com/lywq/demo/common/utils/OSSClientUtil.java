package com.lywq.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lywq WED
 * @title: OSSClientUtil
 * @projectName demo
 * @description: OSS上传工具类
 * @date 2019/12/3 16:36
 */

@Slf4j
@Configuration
@PropertySource("classpath:config/oss.properties")
public class OSSClientUtil {

    /**
     * 阿里云API的密钥Access Key ID
     */
    private static String accessKeyId;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    private static String accessKeySecret;
    /**
     * 阿里云API的内或外网域名
     */
    private static String endpoint;
    /**
     * 阿里云API的bucket名称
     */
    private static String bucketName;
    /**
     * 阿里云API的文件夹名称
     */
    private static String folder;

    @Resource
    private Environment env;

    @PostConstruct
    public void initParam() {
        accessKeyId = env.getProperty("aliyun.accessKeyId");
        accessKeySecret = env.getProperty("aliyun.accessKeySecret");
        endpoint = env.getProperty("aliyun.oss.endpoint");
        bucketName = env.getProperty("aliyun.oss.bucketName");
        folder = env.getProperty("aliyun.oss.folder");
    }


    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            log.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient  oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
        log.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"liuyiwuqing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder + "/";
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            log.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"liuyiwuqing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/lywq.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder + key);
        log.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 根据名称删除
     *
     * @param ossClient
     * @param fileName  全路径名称 如“upload/images/lywq_2019-12-06 16:34:51:060.jpg”
     */
    public static void delete(OSSClient ossClient, String fileName) {
        // 根据objectName删除文件
        ossClient.deleteObject(bucketName, fileName);
        log.info("删除" + bucketName + "下的文件" + fileName + "成功");
    }

//    /**
//     * 上传文件至OSS
//     *
//     * @param ossClient oss连接
//     * @param file      上传文件（文件全路径如：D:\\image\\lywq.jpg）
//     * @return String 返回的唯一MD5数字签名
//     */
//    public static String uploadObjectOSS(OSSClient ossClient, File file) {
//        String resultStr = null;
//        try {
//            // 以输入流的形式上传文件
//            InputStream is = new FileInputStream(file);
//            // 文件名
//            String fileName = file.getName();
//            // 文件大小
//            Long fileSize = file.length();
//            // 创建上传Object的Metadata
//            ObjectMetadata metadata = new ObjectMetadata();
//            // 上传的文件的长度
//            metadata.setContentLength(is.available());
//            // 指定该Object被下载时的网页的缓存行为
//            metadata.setCacheControl("no-cache");
//            // 指定该Object下设置Header
//            metadata.setHeader("Pragma", "no-cache");
//            // 指定该Object被下载时的内容编码格式
//            metadata.setContentEncoding("utf-8");
//            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
//            // 如果没有扩展名则填默认值application/octet-stream
//            metadata.setContentType(getContentType(fileName));
//            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
//            // 上传文件 (上传文件流的形式)
//            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
//            log.info("上传文件返回结果：{}", JSON.toJSONString(putResult));
//            // 解析结果
//            resultStr = putResult.getETag();
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
//        }
//        return resultStr;
//    }

    /**
     * 上传文件至OSS
     *
     * @param ossClient oss连接
     * @param file      上传文件（文件全路径如：D:\\images\\lywq.jpg）
     *                  //@param bucketName 如"liuyiwuqing"
     *                  //@param folder 如"upload/"
     *                  子文件夹 如"images/"
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadFileToOSS(OSSClient ossClient, MultipartFile file) {
        String fileFolder = null; // 定义文件夹
        Long fileSize = null; // 定义文件大小
        String downFileName = null; // 定义文件名
        try {
            // 以输入流的形式上传文件
            InputStream is = file.getInputStream();
            // 文件名
            String fileName = file.getOriginalFilename();
            // 文件类型
            String type = getContentType(fileName);
            if ("image".equalsIgnoreCase(type)) {
                fileFolder = "images/";
                // 文件大小
                fileSize = file.getSize();
                if (fileSize > 1024 * 1024 * 2) {
                    return "上传文件大于2M";
                }
            }
            if ("video".equalsIgnoreCase(type)) {
                fileFolder = "videos/";
                // 文件大小
                fileSize = file.getSize();
                if (fileSize > 1024 * 1024 * 50) {
                    return "上传文件大于50M";
                }
            }
            if ("text".equalsIgnoreCase(type)) {
                fileFolder = "texts/";
                // 文件大小
                fileSize = file.getSize();
                if (fileSize > 1024 * 1024 * 5) {
                    return "上传文件大于5M";
                }
            }
            if ("package".equalsIgnoreCase(type)) {
                fileFolder = "packages/";
                // 文件大小
                fileSize = file.getSize();
                if (fileSize > 1024 * 1024 * 100) {
                    return "上传文件大于100M";
                }
            }
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = df.format(new Date());
            StringBuilder StringBuilder = new StringBuilder();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            downFileName = StringBuilder.append(prefix).append("_").append(time).append(".").append(suffix).toString();
            metadata.setContentDisposition("filename/filesize=" + downFileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileFolder + timeFolder() + downFileName, is, metadata);
            log.info("上传文件返回结果：{}", JSON.toJSONString(putResult));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return folder + fileFolder + timeFolder() + downFileName;
    }

    /**
     * 下载文件
     *
     * @param ossClient
     * @param os
     * @param fileName
     * @throws IOException
     */
    public static void downloadFileForOss(OSSClient ossClient, OutputStream os, String fileName) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        // 读取文件内容。
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght = 0;
        while ((lenght = in.read(buffer)) != -1) {
            out.write(buffer, 0, lenght);
        }
        if (out != null) {
            log.info("下载" + bucketName + "下的文件" + fileName + "成功");
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        // 判断文件类型为图片
        if (".bmp".equalsIgnoreCase(fileExtension)
                || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)
                || ".gif".equalsIgnoreCase(fileExtension)
                || ".jpeg".equalsIgnoreCase(fileExtension)) {
            return "image";
        }
        // 判断文件类型为视频
        if (".mp4".equalsIgnoreCase(fileExtension)
                || ".flv".equalsIgnoreCase(fileExtension)
                || ".mp4".equalsIgnoreCase(fileExtension)
                || ".f4v".equalsIgnoreCase(fileExtension)
                || ".m3u8".equalsIgnoreCase(fileExtension)
                || ".webm".equalsIgnoreCase(fileExtension)
                || ".ogg".equalsIgnoreCase(fileExtension)) {
            return "video";
        }
        // 判断文件类型为文本
        if (".txt".equalsIgnoreCase(fileExtension)
                || ".ppt".equalsIgnoreCase(fileExtension)
                || "pptx".equalsIgnoreCase(fileExtension)
                || ".doc".equalsIgnoreCase(fileExtension)
                || "docx".equalsIgnoreCase(fileExtension)
                || ".xml".equalsIgnoreCase(fileExtension)
                || ".vsd".equalsIgnoreCase(fileExtension)
                || ".html".equalsIgnoreCase(fileExtension)) {
            return "text";
        }
        // 判断文件类型为压缩包
        if (".rar".equalsIgnoreCase(fileExtension)
                || ".zip".equalsIgnoreCase(fileExtension)
                || "gz".equalsIgnoreCase(fileExtension)
                || ".7z".equalsIgnoreCase(fileExtension)) {
            return "package";
        }
        // 默认返回类型
        return "";
    }

    /**
     * 获得私有url链接
     *
     * @param fileName
     * @return
     */
    @SuppressWarnings("unused")
    public static String getPrivateUrl(OSSClient ossClient, String fileName) {
        // 设置URL过期时间为100年 3600l* 1000*24*365*100
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        log.info("获得url链接：{}", url.toString());
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 获得共有url链接
     *
     * @param fileName
     * @return
     */
    @SuppressWarnings("unused")
    public static String getPublicUrl(OSSClient ossClient, String fileName) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        log.info("获得url链接：{}", url.toString());
        String[] split = url.toString().split("[?]");
        if (url != null) {
            return split[0];
        }
        return null;
    }

    /**
     * 当前日期当文件夹名
     *
     * @return
     */

    public static String timeFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");
        String str = sdf.format(new Date());
        return str;
    }

//    /**
//     * 下载文件
//     *
//     * @param ossClient
//     * @param fileName
//     * @return
//     */
//    public static OSSObject downLoadFile(OSSClient ossClient, String fileName) {
//        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
//        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
//        // 设置过期时间。
//        request.setExpiration(expiration);
//        // 生成签名URL（HTTP GET请求）。
//        URL signedUrl = ossClient.generatePresignedUrl(request);
//        // 使用签名URL发送请求。
//        Map<String, String> customHeaders = new HashMap<String, String>();
//        // 添加GetObject请求头。
//        //customHeaders.put("Range", "bytes=100-1000");
//        OSSObject object = ossClient.getObject(signedUrl, customHeaders);
//        return object;
//    }

}