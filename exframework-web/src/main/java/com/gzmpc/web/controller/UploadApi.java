package com.gzmpc.web.controller;
//package com.gzmpc.support.admin.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.regex.PatternSyntaxException;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import com.gzmpc.service.FileService;
//import com.gzmpc.web.pojo.OptionResult;
//
//@Controller
//@Path("upload")
//public class UploadApi {
//	private Log log = LogFactory.getLog(UploadApi.class.getName());
//	
//	@Autowired
//	FileService fileService;
//			
//	@Path("default")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public OptionResult upload(@FormDataParam("uploadFile") InputStream fileInputStream,
//            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition,
//            @FormDataParam("fileName") String fileName) {
//		
//		try {
//			String filename = fileService.upload(fileInputStream, fileFormDataContentDisposition.getFileName());
//			return OptionResult.ok(filename);
//		} catch (PatternSyntaxException e) {
//			log.error("校验上传文件出错:"+e.getMessage(),e);
//			OptionResult r = OptionResult.error(e.getMessage());
//			r.setStatus(Response.Status.FORBIDDEN.getStatusCode());
//			return OptionResult.error(e.getMessage());
//		} catch (IOException e) {
//			log.error("上传文件出错:"+e.getMessage(),e);
//			return OptionResult.error(e.getMessage());
//		}
//	}
//	
//}
