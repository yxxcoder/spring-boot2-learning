package com.simple.chapter17.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片上传的几种方式
 *
 * @author yxxcoder
 * @since 2019-03-18 09:23 PM
 */
@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping
    public String index() {
        return "index";
    }


    @PostMapping("/upload1")
    @ResponseBody
    public Map<String, String> upload1(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());

        File tempFile = new File("/Users/yuxuan/data/upload1/" + file.getOriginalFilename());
        //判断目标文件所在的目录是否存在
        if (!checkDir(tempFile)) {
            log.info("创建目标文件所在目录失败！");
            return null;
        }

        // 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx 进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
        file.transferTo(tempFile);
        Map<String, String> result = new HashMap<>(16);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize() + "");
        return result;
    }

    @PostMapping("/upload2")
    @ResponseBody
    public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            File tempFile = new File("/Users/yuxuan/data/upload2/" + file.getOriginalFilename());
            //判断目标文件所在的目录是否存在
            if (!checkDir(tempFile)) {
                log.info("创建目标文件所在目录失败！");
                return null;
            }
            // Spring Mvc 提供的写入方式
            file.transferTo(tempFile);
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            results.add(map);
        }
        return results;
    }

    @PostMapping("/upload3")
    @ResponseBody
    public void upload2(String base64) throws IOException {
        final File tempFile = new File("/Users/yuxuan/data/upload3/test.jpg");
        //判断目标文件所在的目录是否存在
        if (!checkDir(tempFile)) {
            log.info("创建目标文件所在目录失败！");
            return;
        }
        // BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
        // 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, tempFile);
    }

    /**
     * 判断目标文件所在的目录是否存在，如果目标文件所在的目录不存在，则创建父目录
     */
    private boolean checkDir(File file) {
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            log.info("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                log.info("创建目标文件所在目录失败！");
                return false;
            }
        }
        return true;
    }

}
