package org.exframework.portal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.PatternSyntaxException;

public interface FileService {

	public String upload(InputStream inputStream, String filename) throws PatternSyntaxException,IOException;
	
	public String upload(String path,InputStream inputStream, String filename) throws PatternSyntaxException,IOException;
}
