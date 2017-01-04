/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 *
 * @author rpbeat
 */
@ManagedBean
@SessionScoped
public class FileBean {
    private Part file1;
    
    public String upload() throws IOException{
         InputStream inputStream = file1.getInputStream();        
         FileOutputStream outputStream = new FileOutputStream(getFileName(file1));
         
        byte[] buffer = new byte[4096];        
        int bytesRead = 0;
        while(true) {                        
            bytesRead = inputStream.read(buffer);
            if(bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }else {
                break;
            }                       
        }
        outputStream.close();
        inputStream.close();
        
        return "cuidador_view";
    }
    
    public void download(){

    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1)  {
        this.file1 = file1;
    }
    
    private static String getFileName(Part part){
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if(cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
                return  filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
            }
        }
        return null;
    }
}
