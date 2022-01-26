/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.joinerylab.main;

import java.io.IOException;
import joinery.DataFrame;

/**
 *
 * @author arche
 */
public class TestMain1 {

    public static void main(String[] args) throws IOException {
//        String url = "https://www.cryptodatadownload.com/cdd/Binance_BTCUSDT_d.csv";
//        DataFrame<Object> datas = DataFrame.readCsv(url);
        String path = "D:\\Temp\\Binance_BTCUSDT_d_manual.csv";
        DataFrame<Object> datas = DataFrame.readCsv(path);
        System.out.println(datas.col("close"));
//        joinery.DataFrame.
    }
}
