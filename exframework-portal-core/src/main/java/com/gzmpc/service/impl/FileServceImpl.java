package com.gzmpc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzmpc.service.FileService;
import com.gzmpc.utils.Const;

@Service
public class FileServceImpl implements FileService {
	
	@Value("${file.upload.default:/uploads/common}")
	private String defaultPath;
	
	@Value("${file.upload.pattern:^(?:\\w+\\.xlsx|\\w+\\.xls)$}")
	private String defaultPattern;

	@Override
	public String upload(InputStream inputStream, String filename) throws PatternSyntaxException,IOException {
		return upload(defaultPath,inputStream,filename);
	}

	@Override
	public String upload(String path, InputStream inputStream, String filename) throws PatternSyntaxException,IOException {
		Pattern p =  Pattern.compile(defaultPattern);
        // 创建 Matcher 对象
        Matcher m = p.matcher(filename);
        if(!m.matches()) {
        	throw new PatternSyntaxException("验证文件后缀失败,不允许上传此类文件",defaultPattern,-1);
        }
        else {
        	String root = Const.getProjectPath();
        	new File(root+path).mkdirs();
        	String realname =  root + path + new java.util.Date().getTime()+"_"+filename;
        	writeToFileServer(inputStream,root+realname);
        	return realname;
        }
	}

	/**
     * 物理保存
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private void writeToFileServer(InputStream inputStream, String filePathAndName) throws IOException {
    	 
        OutputStream outputStream = null;
 
        try {
            outputStream = new FileOutputStream(new File(filePathAndName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally{
            //release resource, if any
            outputStream.close();
        }
    }
}
