/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.enumoption;

import tw.dev.tomoaki.enumoption.Introduction;
import tw.dev.tomoaki.enumoption.helper.EnumHelper;

/**
 *
 * @author tomoaki
 */
public enum HistoryOperationStatus implements Introduction {

    INSERT("INSERT", "新增", "資料新增"),
    UPDATE("UPDATE", "修改", "資料修改"),
    DELETE("DELETE", "刪除", "資料刪除");

    private String code;
    private String summaryTW;
    private String summaryEN;
    private String detailTW;
    private String detailEN;

    private HistoryOperationStatus(String code, String summaryTW, String detailTW) {
        this(code, summaryTW, null, detailTW, null);
    }

    private HistoryOperationStatus(String code, String summaryTW, String summaryEN, String detailTW, String detailEN) {
        this.code = code;
        this.summaryTW = summaryTW;
        this.summaryEN = summaryEN;
        this.detailTW = detailTW;
        this.detailEN = detailEN;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getSummaryTW() {
        return summaryTW;
    }

    @Override
    public void setSummaryTW(String summaryTW) {
        this.summaryTW = summaryTW;
    }

    @Override
    public String getSummaryEN() {
        return summaryEN;
    }

    @Override
    public void setSummaryEN(String summaryEN) {
        this.summaryEN = summaryEN;
    }

    @Override
    public String getDetailTW() {
        return detailTW;
    }

    @Override
    public void setDetailTW(String detailTW) {
        this.detailTW = detailTW;
    }

    @Override
    public String getDetailEN() {
        return detailEN;
    }

    @Override
    public void setDetailEN(String detailEN) {
        this.detailEN = detailEN;
    }
    
    
    public static HistoryOperationStatus codeOf(String desigCode) {
        return EnumHelper.codeOf(desigCode, HistoryOperationStatus.class);
    }

}
