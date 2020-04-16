package com.lywq.demo.common.utils;

import com.lywq.demo.common.constant.ProjectConstant;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lywq WED
 * @title: UploadActionUtil
 * @projectName demo
 * @description: 文件上传工具类
 * @date 2019/11/7 11:10
 */
public class UploadActionUtil {

    public static List<String> uploadFile(HttpServletRequest request) throws Exception {
        List<String> list = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iterator.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        String fileTyps = myFileName.substring(myFileName.lastIndexOf("."));
                        // String tempName="demo"+fileTyps;
                        String tempName = UUID.randomUUID().toString() + fileTyps;

                        // 判断是否为图片
                        String[] imageTags = {".jpg", ".png", ".gif", ".jpeg", ".bmp", ".tif", ".image"};
                        Boolean isImage = false;
                        for (String imageTag : imageTags) {
                            if (fileTyps.equals(imageTag)) {
                                isImage = true;
                            }
                        }

                        // 判断是否为视频
                        String[] videoTags = {".flv", ".mp4", ".f4v", ".m3u8", ".webm", ".ogg"};
                        Boolean isVideo = false;
                        for (String videoTag : videoTags) {
                            if (fileTyps.equals(videoTag)) {
                                isVideo = true;
                            }
                        }

                        // 判断是否为压缩包
                        String[] packageTags = {".rar", ".zip", ".gz", ".7z"};
                        Boolean isPackage = false;
                        for (String packageTag : packageTags) {
                            if (fileTyps.equals(packageTag)) {
                                isPackage = true;
                            }
                        }

                        // 创建文件夹
                        String folderPath = null;
                        // 创建图片文件夹
                        if (isImage) {
                            folderPath = ProjectConstant.SAVEFILEPATH + File.separator + "images";
                            File imagesFileFolder = new File(folderPath);
                            if (!imagesFileFolder.exists() && !imagesFileFolder.isDirectory()) {
                                imagesFileFolder.mkdir();
                            }
                            File fileFolder = new File(folderPath + File.separator + folderName());
                            if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                                fileFolder.mkdir();
                            }
                        }
                        // 创建视频文件夹
                        if (isVideo) {
                            folderPath = ProjectConstant.SAVEFILEPATH + File.separator + "videos";
                            File videosFileFolder = new File(folderPath);
                            if (!videosFileFolder.exists() && !videosFileFolder.isDirectory()) {
                                videosFileFolder.mkdir();
                            }
                            File fileFolder = new File(folderPath + File.separator + folderName());
                            if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                                fileFolder.mkdir();
                            }
                        }
                        // 创建压缩包文件夹
                        if (isPackage) {
                            folderPath = ProjectConstant.SAVEFILEPATH + File.separator + "packages";
                            File videosFileFolder = new File(folderPath);
                            if (!videosFileFolder.exists() && !videosFileFolder.isDirectory()) {
                                videosFileFolder.mkdir();
                            }
                            File fileFolder = new File(folderPath + File.separator + folderName());
                            if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                                fileFolder.mkdir();
                            }
                        }
                        // 创建其他文件夹
                        else if (!isImage && !isVideo && !isPackage) {
                            folderPath = ProjectConstant.SAVEFILEPATH + File.separator + "others";
                            File othersFileFolder = new File(folderPath);
                            if (!othersFileFolder.exists() && !othersFileFolder.isDirectory()) {
                                othersFileFolder.mkdir();
                            }
                            File fileFolder = new File(folderPath + File.separator + folderName());
                            if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                                fileFolder.mkdir();
                            }
                        }
                        // 创建图片文件
                        File uploadFile = new File(folderPath + File.separator + folderName() + File.separator + tempName);
                        file.transferTo(uploadFile);

                        // 返回文件访问地址
                        if (isImage) {
                            myFileName = "images" + File.separator + folderName() + File.separator + tempName;
                        }
                        if (isVideo) {
                            myFileName = "videos" + File.separator + folderName() + File.separator + tempName;
                        }
                        if (isPackage) {
                            myFileName = "packages" + File.separator + folderName() + File.separator + tempName;
                        } else if (!isImage && !isVideo && !isPackage) {
                            myFileName = "others" + File.separator + folderName() + File.separator + tempName;
                        }

                        list.add(ProjectConstant.SAVEFILEPATH + "//" + myFileName);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 得年月日的文件夹名称
     *
     * @return
     */
    public static String getCurrentFilderName() throws Exception {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) + "" + (now.get(Calendar.MONTH) + 1) + "" + now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 创建文件夹
     *
     * @param filderName
     */
    public static void createFilder(String filderName) throws Exception {
        File file = new File(filderName);
        // 如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String extFile(String fileName) throws Exception {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 当前日期当文件夹名
     *
     * @return
     */

    public static String folderName() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(new Date());
        return str;
    }
}