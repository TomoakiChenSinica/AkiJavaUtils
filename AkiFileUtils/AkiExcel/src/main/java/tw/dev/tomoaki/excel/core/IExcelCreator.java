/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.excel.core;

import java.io.IOException;
import tw.dev.tomoaki.excel.*;
import tw.dev.tomoaki.excel.entity.ExcelTable;

/**
 *
 * @author Tomoaki Chen
 */
public interface IExcelCreator {

    public void writeTable(ExcelTable dataTable, String sheetName);

}
