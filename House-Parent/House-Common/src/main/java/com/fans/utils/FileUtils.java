package com.fans.utils;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @ClassName FileUtils
 * @Description: 文件上传工具
 * @Author fan
 * @Date 2019-04-04 14:30
 * @Version 1.0
 **/
@Component(value = "fileUtils")
@PropertySource(value = "classpath:/properties/config.properties")
public class FileUtils {
    @Value("${file.path}")
    private String filePath;

    public List<String> getImgPath(List<MultipartFile> fileList) {
        List<String> paths = Lists.newArrayList();
        fileList.forEach(file -> {
            File localFile;
            if (!file.isEmpty()) {
                try {
                    localFile = saveToLocal(file, filePath);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);

                }
                assert localFile != null;
                String path = StringUtils.substringAfterLast(localFile.getAbsolutePath().replaceAll("\\\\", "/"), filePath);
                paths.add(path);
            }
        });
        return paths;
    }

    private File saveToLocal(MultipartFile file, String filePath) throws IOException {
        File newFile = new File(filePath + "/" + Instant.now().getEpochSecond() + "/" + file.getOriginalFilename());
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();

        }
        Files.write(file.getBytes(), newFile);
        return newFile;
    }

}
