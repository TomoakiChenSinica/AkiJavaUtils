/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* 2015/02/05
 * 此為一個配合CreateExcel寫出的類別，目的是儲存一個table的資料，
 * 之後直接丟進CreateExcel，讓他解析即可
 * 目前有的功能
 * 1.利用建構式傳入row col 製造 table
 * 2.insertCell()：造順序插入儲存格資料
 * 
 * 2015/02/06
 * 3.新增由第一列(及title)來計算行數(col)功能
 * 目前預計會新增隨著插入一列(row)，使table增大功能
 */
package tw.dev.tomoaki.excel.entity;

//import tw.dev.tomoaki.function.DateTimeProvider;
/**
 *
 * @author tomoaki
 */
public class ExcelTable {

    
    protected Integer tableRow = 1;  //default為1
    protected Integer tableCol = 1;
    protected String[][] dataTable;

    protected Integer nowRow;
    protected Integer nowCol;

    protected Integer totalData;
    protected Integer maxData;

    protected ExcelTable() {
        this.tableRow = 1;
        this.tableCol = 1;
    }

    protected ExcelTable(Integer row, Integer col) {
        this.tableRow = row;
        this.tableCol = col;
    }

    public static class Factory {

        public static ExcelTable create() {
            ExcelTable excelTable = new ExcelTable();
            excelTable.initTable();
            return excelTable;
        }

        public static ExcelTable create(Integer row, Integer col) {
            ExcelTable excelTable = new ExcelTable(row, col);
            excelTable.initTable();
            return excelTable;
        }
    }

//<editor-fold defaultstate="collapsed" desc="設定/初始化變數的 methods">
      
    protected void doInitDataTable() {
        dataTable = new String[tableRow][tableCol];
        //System.out.println("init table data size is done");        
    }

    protected void doSetupEmptyDataTable() {
        for (int i = 0; i < tableRow; i++) {
            for (int j = 0; j < tableCol; j++) {
                dataTable[i][j] = "";
            }
        }
    }
    
    protected void doInitVariable() {
        maxData = tableRow * tableCol;
        totalData = 0;
        nowRow = 0;
        nowCol = 0;
    }

    protected void initTable() {
        this.doInitDataTable();
        this.doSetupEmptyDataTable();
        this.doInitVariable();
        // System.out.println("init table finish");
        // System.out.println("maxData = " + maxData + " totalData = " + totalData);
    }
    
//</editor-fold>
    
    
    
//<editor-fold defaultstate="collapsed" desc="取得變數的 methods">
    public void countNowPosition() {
        nowRow = totalData / tableCol;
        nowCol = totalData % tableCol;
    }

    /**
     * 取得table共有幾列(Row)
     *
     */
    public int getTableRow() {
        return tableRow;
    }

    /**
     * 設定此table共有幾列(row)
     *
     * @param row:列數
     *
     */
    public void setTableRow(int row) {
        this.tableRow = row;
    }

    /**
     * 取得此table共有幾行(Col)
     *
     */
    public int getTableCol() {
        return tableCol;
    }

    /**
     * 當是使用insertCell()，沒有指定要插入哪一個儲存格時， 會紀錄目前寫到table的哪一格，即nowRow、nowCol，
     * 此為取得nowRow
     */
    public int getNowRow() {
        return nowRow;
    }

    /**
     * 當是使用insertCell()，沒有指定要插入哪一個儲存格時， 會紀錄目前寫到table的哪一格，即nowRow、nowCol，
     * 此為取得nowCol
     */
    public int getNowCol() {
        return nowCol;
    }

    public String getData(Integer theRow, Integer theCol) {
        return dataTable[theRow][theCol];
    }
    
//</editor-fold>



    /**
     * 照順序的將Cell一個一個加進去，加到哪一Row、Col由 nowRow、nowCol紀錄
     *
     * @param data : String型別的資訊
     */
    public void insertCell(String data) {
        //System.out.println("nowRow = " + nowRow + "nowCol = " + nowCol);
        if (this.checkInsert(nowRow, nowCol)) {//檢查是否超過範圍，合法(未超過範圍)為true，不合法(超過範圍)為false
            //System.out.println("寫入 nowRow = " + nowRow + " nowCol = " + nowCol);
            dataTable[nowRow][nowCol] = data;
            totalData++;
            this.countNowPosition();
        } else {
            throw new ExcelTableOperationException("超出table大小, data= %s 無法寫入", data);
        }
    }

    /**
     * 照順序的將Cell一個一個加進去，加到哪一Row、Col由 nowRow、nowCol紀錄
     *
     * @param data : int型別的資訊
     */
    public void insertCell(Integer data) {
        String strData = Integer.toString(data);
        insertCell(strData);
    }

    /**
     * 照順序的將Cell一個一個加進去，加到哪一Row、Col由 nowRow、nowCol紀錄
     *
     * @param data : Date 型別的資訊
     */
    public void insertCell(java.util.Date data) {
//        String strData = DateTimeProvider.DateToString(DateTimeProvider.ConvertDate(data));
        throw new IllegalArgumentException("需解決 Date 轉成 String 問題");
//        insertCell(strData);
    }

    /**
     * 將資料放到指定的儲存格中 theRow = y軸 theCol = x軸
     *
     * @param data 資料，格式為 String
     * @param theRow y軸資料
     * @param theCol x軸資料
     */
    public void insertCell(String data, int theRow, Integer theCol) {
        if (this.checkInsert(theRow, theCol)) {
            dataTable[theRow][theCol] = data;
        } else {
            throw new ExcelTableOperationException("超出table大小");
        }
    }

    /**
     *
     *
     * @param titleData : 標題列有哪些欄位，存在字串中
     *
     *
     *
     *
     */
    public void insertTitleRow(String[] titleData, Integer row) {
        this.tableCol = titleData.length;
        this.tableRow = row;
        this.initTable();
        for (int colIndex = 0; colIndex < tableCol; colIndex++) {
            this.insertCell(titleData[colIndex]);
        }
    }

    
    
    
    
    
    public boolean checkInsert(Integer theRow, Integer theCol) {
        if (theRow >= this.tableRow || theCol >= this.tableCol) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkInsert() {
        if (this.totalData >= this.maxData) {//大於等於就錯(好像怪怪的
            return false;
        } else {
            return true;
        }
    }

    public void printTable() {/*
        for(int i = 0 ; i<tableRow ; i++)
        {
            for(int j = 0 ; j<tableCol ; j++)
                System.out.print(Data[i][j] + "  ");
            System.out.print("\n");
        }
         */
    }
}
