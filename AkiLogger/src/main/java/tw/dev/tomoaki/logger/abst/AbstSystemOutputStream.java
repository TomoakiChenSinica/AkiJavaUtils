/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.logger.abst;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author arche
 */
public abstract class AbstSystemOutputStream extends PrintStream {

    private static final String MSG_FMT_SYS_LOG = "[%s]%s";
    private final String msgSysLog = obtainMsgSysmLog();
    
    
    //StackOverFlow
    public AbstSystemOutputStream() {
        super(System.out);
    }

    @Override
    public void println(String mainMsg) {
        String msg = String.format(msgSysLog, mainMsg);
        super.println(msg); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    protected String obtainMsgSysmLog() {
        return String.format(MSG_FMT_SYS_LOG, this.getSystemName(), "%s");
    }

    protected abstract String getSystemName();
}
