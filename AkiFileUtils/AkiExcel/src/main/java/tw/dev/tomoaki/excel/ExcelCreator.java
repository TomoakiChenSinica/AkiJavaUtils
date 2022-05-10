/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.excel;

import java.io.File;
import java.io.IOException;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import tw.dev.tomoaki.excel.entity.ExcelTable;

/**
 *
 * @author Tomoaki Chen
 */
public class ExcelCreator {

    private WritableWorkbook excelWritebook;       //這才是能寫入的
    private Workbook excelReadbook;                //這是用來讀的
    private File theFile;
    int numberOfSheet;
    String tempFilePath;

    public ExcelCreator(String filePath) throws IOException, BiffException {
        /*
        theFile = new File(filePath);                   //這裡的檔案未置是製造臨時的excel的位置(然後再用DownloadFileServlet讓使用者下載該檔案)
        excelWritebook   = Workbook.getWorkbook(theFile); //excel檔案
        numberOfSheet = 0;
         */
        tempFilePath = filePath;
    }

    public void WriteData(ExcelTable dataTable, String sheetName) throws WriteException, IOException {
        int row = dataTable.getTableRow();
        int col = dataTable.getTableCol();
        excelWritebook = Workbook.createWorkbook(new File(tempFilePath));  //新增一個excel檔案
        WritableSheet excelWriteSheet = excelWritebook.createSheet(sheetName, 0);  //新增一個sheet，前面是一個sheet名稱，後面是這是第幾個sheet，也是從0開始

        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            for (int colIndex = 0; colIndex < col; colIndex++) {
                /*設定sheet中的儲存格大小，利用setAutosize()這個function*/
                if (rowIndex == 0) {
                    CellView cell = excelWriteSheet.getColumnView(colIndex);
                    //CellView cell = excelWriteSheet.getColumnView(rowIndex);
                    cell.setAutosize(true);
                    excelWriteSheet.setColumnView(colIndex, cell);
                    //excelWriteSheet.setColumnView(rowIndex, cell);
                }
                /*可以開始寫東西了*/
                //決定字型
                WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);   //基本上作這步時至少要先把字型先決定好，其他的還有setXXX 可以改
                arial10font.setColour(Colour.BLACK);

                //決定「儲存格」格式，
                WritableCellFormat arial10format = new WritableCellFormat();          //這是「整個儲存格」的設定，基本上在宣告時可以不用傳參數，都可以再這之後設定
                arial10format.setFont(arial10font);
                arial10format.setWrap(true);  //如果要有換行效果(\n)出來要有這個
                arial10format.setBorder(Border.ALL, BorderLineStyle.THIN);
                arial10format.setVerticalAlignment(VerticalAlignment.CENTRE);
                if (rowIndex == 0) {
                    arial10format.setBackground(Colour.LIME);
                    arial10format.setAlignment(Alignment.CENTRE);
                }
                if (rowIndex % 2 == 0 && rowIndex != 0) {
                    arial10format.setBackground(Colour.GRAY_25);
                }

                /*決定好儲存格格式後，就是正式寫入儲存格(Cell)，有Formula、Label、Number三種*/
                String theData = dataTable.getData(rowIndex, colIndex);

                Label label1 = new Label(colIndex, rowIndex, theData);  //一定要先傳入 x軸、y軸(從0開始算)、文字，再加個format)，此api是col在前(x軸) row在後(y軸)
                label1.setCellFormat(arial10format);

                /*將儲存格存到分頁中*/
                excelWriteSheet.addCell(label1);     //最後用addCell寫入該sheet的該儲存格 WritableCell 有各種類型

            }
        }
        /*正式寫入檔案*/
        excelWritebook.write();
        excelWritebook.close();
    }

    public void TestFunction() throws IOException, WriteException {

        excelWritebook = Workbook.createWorkbook(new File(tempFilePath));  //新增一個excel檔案
        WritableSheet excelWriteSheet = excelWritebook.createSheet("學生資料", 0);  //新增一個sheet，前面是一個sheet名稱，後面是這是第幾個sheet，也是從0開始
        //Label label = new Label();
        /*可以開始寫東西了*/
        //決定字型
        WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);   //基本上作這步時至少要先把字型先決定好，其他的還有setXXX 可以改
        arial10font.setColour(Colour.BLACK);

        //決定「儲存格」格式，
        WritableCellFormat arial10format = new WritableCellFormat();          //這是「整個儲存格」的設定，基本上在宣告時可以不用傳參數，都可以再這之後設定
        arial10format.setFont(arial10font);
        arial10format.setBackground(Colour.RED);

        /*決定好儲存格格式後，就是正式寫入儲存格(Cell)，有Formula、Label、Number三種*/
        Label label1 = new Label(0, 1, "This is an apple");                                           //一定要先傳入 x軸、y軸(從0開始算)、文字，再加個format)
        label1.setCellFormat(arial10format);

        /*將儲存格存到分頁中*/
        excelWriteSheet.addCell(label1);     //最後用addCell寫入該sheet的該儲存格 WritableCell 有各種類型

        /*正式寫入檔案*/
        excelWritebook.write();
        excelWritebook.close();
    }
}
