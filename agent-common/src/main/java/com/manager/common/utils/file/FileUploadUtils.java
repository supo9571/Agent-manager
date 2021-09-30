package com.manager.common.utils.file;

import java.io.File;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.constant.Constants;
import com.manager.common.exception.file.FileNameLengthLimitExceededException;
import com.manager.common.exception.file.InvalidExtensionException;
import com.manager.common.utils.uuid.IdUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.manager.common.exception.file.FileSizeLimitExceededException;
import com.manager.common.utils.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件上传工具类
 *
 * @author marvin
 */
public class FileUploadUtils {
    /**
     * 默认大小 1g
     */
    public static final long DEFAULT_MAX_SIZE = 1024 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = ManagerConfig.getProfile();

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
//        fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
        fileName = IdUtils.fastUUID() + "." + extension;
        return fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    private static final String getPathFileName(String uploadDir, String fileName) {
        int dirLastIndex = ManagerConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = currentDir == "" ? Constants.RESOURCE_PREFIX + "/" + fileName : Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION) {
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    public static JSONObject uploadUnzip(String baseDir, MultipartFile file) throws InvalidExtensionException, IOException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        assertAllowed(file, MimeTypeUtils.ZIP);
        String fileName = extractFilename(file);
        File desc = getAbsoluteFile(baseDir, fileName);
        //上传文件
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        //解压文件
        JSONObject gameInfo = unZipFiles(new File(baseDir + "/" + fileName), baseDir, pathFileName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("apk_update_url", pathFileName);
        jsonObject.put("size", file.getSize() / 1024);
        jsonObject.put("gameInfo", gameInfo);
        return jsonObject;
    }

    /**
     * 解压文件到指定目录
     */
    private static JSONObject unZipFiles(File zipFile, String descDir, String pathName) throws IOException {
        JSONObject relust = new JSONObject();
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        //解析zip
        ZipFile zip = new ZipFile(zipFile, Charset.forName("UTF-8"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");
            //判断文件全路径是否为文件夹,如果是不需要解析
            if (new File(outPath).isDirectory()) {
                continue;
            }

            if (outPath.endsWith("NDQyMjY1/Mjk1YjM5")) {// 路径 lobby/version.manifest
                JSONObject lobby = new JSONObject();
                lobby.put("gameCode", "lobby");
                lobby.put("manifest_res", "N2ZjOTdj/NDQyMjY1/Mjk1YjM5");
                lobby.put("resources_url", pathName);
                relust.put("lobby", lobby);
            }
            if (outPath.endsWith("YzMzN2Qx/Mjk1YjM5")) {// 路径 update/version.manifest
                JSONObject update = new JSONObject();
                update.put("gameCode", "update");
                update.put("manifest_res", "N2ZjOTdj/YzMzN2Qx/Mjk1YjM5");
                update.put("resources_url", pathName);
                relust.put("update", update);
            }
            if (outPath.contains("N2ZjOTdj/NmNiOTJm")) {// 路径 src/game
                if (outPath.endsWith("Mjk1YjM5")) {
                    String gameCode = outPath.split("NmNiOTJm/")[1].split("/Mjk1YjM5")[0];
                    String gameDecode = DecodeMap.decodeMap().get(gameCode);
                    JSONObject game = new JSONObject();
                    game.put("gameCode", gameDecode);
                    game.put("manifest_res", "/N2ZjOTdj/NmNiOTJm/" + gameDecode + "/Mjk1YjM5");
                    game.put("resources_url", pathName);
                    relust.put(gameDecode, game);
                }
            }
        }
        JSONObject gameInfo = new JSONObject();
        gameInfo.put("android", relust);
        gameInfo.put("ios", relust);
        gameInfo.put("windows", relust);
        return gameInfo;
    }
}
