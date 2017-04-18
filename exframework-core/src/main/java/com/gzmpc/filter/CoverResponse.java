package com.gzmpc.filter;

import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.StringWriter;  
  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpServletResponseWrapper;  
  
public class CoverResponse extends HttpServletResponseWrapper{  
    private PrintWriter tmpWriter;  
    private StringWriter output;  
  
    public CoverResponse(HttpServletResponse response) {  
        super(response);  
        output = new StringWriter();      
        tmpWriter = new PrintWriter(output);  
    }  
      
    public void finalize() throws Throwable {      
         super.finalize();      
         output.close();      
         tmpWriter.close();  
    }  
      
    public String getContent() {      
        tmpWriter.flush();   //刷新该流的缓冲，详看java.io.Writer.flush()      
        String s = output.toString();      
        //此处可根据需要进行对输出流以及Writer的重置操作      
        //比如tmpWriter.getByteArrayOutputStream().reset()      
        return s;      
    }      
  
    //覆盖getWriter()方法，使用我们自己定义的Writer      
    public PrintWriter getWriter() throws IOException {      
        return tmpWriter;      
    }    
      
    public void close() throws IOException {      
        tmpWriter.close();      
    }  
}