/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main.cssliner;

import java.io.IOException;
import tw.dev.tomoaki.cssinliner.AkiCSSInliner;
import tw.dev.tomoaki.util.cast.JavaToJson;

/**
 *
 * @author Tomoaki Chen
 */
public class CCSInlinerTestMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String html = "<style>\n"
                + ".lecture-participant-report {\n"
                + "    font-family: Microsoft JhengHei;\n"
                + "}\n"
                + ".lecture-participant-report .dataTable {\n"
                + "    /*border-spacing: 0px;*/\n"
                + "}\n"
                + ".lecture-participant-report > .dataTable   tr:not(.header-row):nth-child(even) {\n"
                + "    background: #F1F1F1;\n"
                + "}\n"
                + ".lecture-participant-report > .dataTable  tr:not(.header-row):nth-child(odd) {\n"
                + "    background: #E3E3E3;\n"
                + "}\n"
                + ".lecture-participant-report > .dataTable   tr  td {\n"
                + "    padding: 8px;\n"
                + "    text-align: center;\n"
                + "}\n"
                + ".col-wid-3 {\n"
                + "    width: 3%;\n"
                + "}\n"
                + ".col-wid-5 {\n"
                + "    width: 5%;\n"
                + "}\n"
                + ".col-wid-10 {\n"
                + "    width: 10%;\n"
                + "}\n"
                + ".lecture-participant-report > .dataTable  .header-row {\n"
                + "    background: #A3A3A3;\n"
                + "    font-weight: bold;\n"
                + "}\n"
                + ".lecture-participant-report > .lecture-participant-reportTitle {\n"
                + "    font-size: 30px;\n"
                + "    font-weight: black;\n"
                + "    text-align: center;\n"
                + "    margin-top: 10px;\n"
                + "    margin-bottom: 10px;\n"
                + "}\n"
                + ".lecture-participant-report > .lecture-participant-date {\n"
                + "    text-align: right;\n"
                + "    margin-bottom: 5px;    \n"
                + "}\n"
                + "</style>\n"
                + "<div class =\"lecture-participant-report\">\n"
                + "老師您好: <br/>\n"
                + "下列為老師「鐘楷閔老師實驗室」到職三年內的助理(不含上班地點不在資訊所者)聆聽卓越演講的統計，請老師多鼓勵助理參加！<br/><br/>\n"
                + "<table class=\"dataTable\">\n"
                + "	<tr class=\"header-row\">\n"
                + "		<td>\n"
                + "		      姓名		\n"
                + "                  </td>\n"
                + "		 <td>\n"
                + "			  到院日\n"
                + "		 </td>\n"
                + "		 <td>\n"
                + "			  參與情形\n"
                + "		 </td>   <td >\n"
                + "     2023-01-10\n"
                + " </rd></tr><tr>\n"
                + "    <td>\n"
                + "       李吉昌\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2022-07-01\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       蔡秉邕\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2022-07-04\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       黃資翔\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2022-07-01\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       施智偉\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2021-08-16\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       胡筱郁\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2021-03-08\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       洪偉翔\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2020-10-30\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr><tr>\n"
                + "    <td>\n"
                + "       唐爾晨\n"
                + "    </td>\n"
                + "     <td>\n"
                + "         2022-08-11\n"
                + "     </td>\n"
                + "     <td>\n"
                + "         0 / 1\n"
                + "     </td><td> </td></tr></table>\n"
                + "<div>\n"
                + "如認為助理可以不用參與卓越演講，可填寫\n"
                + "<a href=\"https://eform.iis.sinica.edu.tw/surl/iisApply/excludeFromDLS\" target=\"_blank\">卓越演講免計次申請</a>\n"
                + "</div> </div>";

        System.out.println("html= \n" + html);
        AkiCSSInliner inliner = AkiCSSInliner.Factory.create(Boolean.TRUE, Boolean.TRUE);
        ///System.out.println( JavaToJson.getJsonString(inliner.obtainRuleList(html)) );
        String newHtml = inliner.inlineStyle(html);
        System.out.println("========================================================= ");
        System.out.println("newHtml= \n" + newHtml);
    }

}
