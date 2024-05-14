/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomoaki
 * 
 * 2017-03-02 紀錄 (此為2016 從OpenHouse看如何產生圖片時意識到，可以直接整個切割出來)
 * 一些Web上的功能， 例如下載檔案等的util。
 * 下載檔案時必須要有 HttpServletResponse 等變數，
 * 雖然這個變數基本上要從Servlet來，
 * 但不代表這一定要直接綁在Servlet ，這個就是實驗把他切割出來
 * 
 * 
 */
public class JavaWebUtils {
    
    public static Integer BUFFER_SIZE = 1024;

    public static String analyzeMimeType(ServletContext context, String fileName) {
        String mimeType = null;
        mimeType = context.getMimeType(fileName);
        if (mimeType == null) {
            //該不該用?
            //https://juejin.cn/post/6979224810681270309
            //https://stackoverflow.com/questions/20508788/do-i-need-content-type-application-octet-stream-for-file-download
            mimeType = "application/octet-stream";
        }
        //這樣還是會有問題，例如 docx 等無法抓出mimetype的，此時如果用doDowmload，會設定 header 所以比較沒問題
        return mimeType;
    }

    
    public static void writeImageIO(HttpServletResponse response, String fileContentType, byte[] fileBin) throws IOException {
        //ImageIO 速度較慢，在 payara4.1 上測試，大約慢 2.8秒
        response.setContentType(fileContentType);
        OutputStream os = response.getOutputStream();
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(fileBin));
        ImageIO.write(bi, fileContentType, os);
    }

    public static void temp2(HttpServletResponse response, String fileContentType, byte[] fileBin) throws IOException {
        response.setContentType(fileContentType);
        try (PrintWriter out = response.getWriter()) {
            String fileStr = new String(fileBin);
            out.write(fileStr);
            out.flush();
            out.close();
        }
    }
    
    public static void showImage(HttpServletRequest request, HttpServletResponse response, String fileName, String fileContentType, byte[] fileBin) throws IOException {
        //圖片基本上都可以直接顯示....，所以可以call
        JavaWebUtils.displayFile(request, response, fileName, fileContentType, fileBin);
    }    
     
    
    public static void displayFile(HttpServletRequest request, HttpServletResponse response, File file, String downloadedFileName) throws UnsupportedEncodingException, IOException {
        ServletContext context = request.getServletContext();
        InputStream downloadFileInputStream = new FileInputStream(file);
        JavaWebUtils.displayFile(response, downloadFileInputStream, downloadedFileName, context);
    }            
    
    public static void displayFile(HttpServletRequest request, HttpServletResponse response,  String fileName ,String fileContentType, byte[] fileBin) throws IOException {
//        response.setContentType(fileContentType + "; charset=utf-8" ); //舊方法，就算把下面 Content-Description 拿掉，只留這行也是錯，所以不是 Content-Description 的問題。
//        response.setCharacterEncoding("utf-8"); //2019-06-12 嘗試，無效        


//        response.setCharacterEncoding("UTF-8");        
//        response.setHeader("Content-Disposition", fileContentType + "; charset=utf-8" );
//        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");  //設定下載檔案名(X) 檔案名+檔案類型 --> 這行導致強制變成下載!
//        
//        OutputStream os = response.getOutputStream();
//        os.write(fileBin);
//        os.flush();
//        os.close(); 
        ServletContext context = request.getServletContext();
        InputStream is = new ByteArrayInputStream(fileBin);  
        JavaWebUtils.downloadFile(response, is, fileName, context);
    }    
    
    public static void displayFile(HttpServletResponse response, InputStream downloadFileInputStream, String downloadedFileName, ServletContext context) throws UnsupportedEncodingException, IOException {
        ServletOutputStream fileWriter = response.getOutputStream();
        String mimeType = context.getMimeType(downloadedFileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setCharacterEncoding("UTF-8");        
        response.setContentType(mimeType);   //這行是讓他變下載、而非跳轉頁面的開始
        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(downloadedFileName, "UTF-8") + "\"");  //設定下載檔案名(X) 檔案名+檔案類型 --> 這行導致強制變成下載!
//   

        byte[] buff = new byte[BUFFER_SIZE];

        int bytesReaded = -1;
        while ((downloadFileInputStream != null) && ((bytesReaded = downloadFileInputStream.read(buff)) != -1)) {
            fileWriter.write(buff, 0, bytesReaded);
        }
        downloadFileInputStream.close();
        fileWriter.flush();
        fileWriter.close();
    }    
    
    
    
    
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, byte[] fileBytes, String downloadFileName) throws IOException{
        ServletContext context = request.getServletContext();
        InputStream is = new ByteArrayInputStream(fileBytes);                
        JavaWebUtils.downloadFile(response, is, downloadFileName, context);                    
    }
                        
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file, String downloadedFileName) throws UnsupportedEncodingException, IOException {
        ServletContext context = request.getServletContext();
        InputStream downloadFileInputStream = new FileInputStream(file);
        JavaWebUtils.downloadFile(response, downloadFileInputStream, downloadedFileName, context);
    }        
    
    
    /**
     * 進行下載。
     * 此 method 會強制出現下載框。
     * (一般狀況下，如果瀏覽器無法直接顯示的，會變出下載框)
     * 
     * @param request Java Serv;et、Page Bean中可以拿到的 HttpServletRequest
     * @param response  Java Servlet 、PageBean 中 可以拿到的 HttpServletResponse
     * @param downloadFileInputStream 欲下載的檔案的 inputStream
     * @param downloadedFileName 下載檔案時，要預設的檔案名稱
     * @parm context Java Servlet 中可以拿到的 context 
     * 
     * 
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, InputStream downloadFileInputStream, String downloadedFileName) throws UnsupportedEncodingException, IOException {
        ServletContext context = request.getServletContext();
        JavaWebUtils.downloadFile(response, downloadFileInputStream, downloadedFileName, context);
    }        

    
    /**
     * 進行下載。
     * 此 method 會強制出現下載框。
     * (一般狀況下，如果瀏覽器無法直接顯示的，會變出下載框)
     * 
     * @param response  Java Servlet 、PageBean 中 可以拿到的 HttpServletResponse
     * @param downloadFileInputStream 欲下載的檔案的 inputStream
     * @param downloadedFileName 下載檔案時，要預設的檔案名稱
     * @parm context Java Servlet 中可以拿到的 context 
     * 
     * 
     */
    public static void downloadFile(HttpServletResponse response, InputStream downloadFileInputStream, String downloadedFileName, ServletContext context) throws UnsupportedEncodingException, IOException {
        ServletOutputStream fileWriter = response.getOutputStream();
        String mimeType = context.getMimeType(downloadedFileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);   //這行是讓他變下載、而非跳轉頁面的開始
        //上述描述為之前試出來的結果，但看起來可能不是這樣....，可以確定的是，和以下的配合後，現在這樣寫會強制下載
        response.setContentLength((int) downloadFileInputStream.available());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(downloadedFileName, "UTF-8") + "\"");  //設定下載檔案名(X) 檔案名+檔案類型 --> 這行導致強制變成下載!

        byte[] buff = new byte[BUFFER_SIZE];

        int bytesReaded = -1;
        while ((downloadFileInputStream != null) && ((bytesReaded = downloadFileInputStream.read(buff)) != -1)) {
            fileWriter.write(buff, 0, bytesReaded);
        }
        downloadFileInputStream.close();
        fileWriter.flush();
        fileWriter.close();
    }
    
    /**
     * 2022-07-24
     * https://cloud.tencent.com/developer/section/1189916 證明上面說怎樣強制變成下載
     */
      
}
