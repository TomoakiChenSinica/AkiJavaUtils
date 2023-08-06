/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.logger.abst.AbstSystemOutputStream;

/**
 *
 * @author arche
 */
public class AkiLoggerMainOutput extends AbstSystemOutputStream{

    @Override
    protected String getSystemName() {
       return "AkiLoggerMain";
    }
    
}
