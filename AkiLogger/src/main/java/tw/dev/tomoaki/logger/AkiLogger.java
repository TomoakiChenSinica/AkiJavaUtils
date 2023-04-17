package tw.dev.tomoaki.logger;

import static tw.dev.tomoaki.logger.LogHelper.addExcluded;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author arche
 */
public class AkiLogger extends LogHelper {

//        static {
//        EXCLUDE_CLASS_NAME_EXIST_MAP = new DataExistMap();
//        EXCLUDE_CLASS_NAME_EXIST_MAP.add(SELF_CLASS_NAME);
//        EXCLUDE_CLASS_NAME_EXIST_MAP.add(THREAD_CLASS_NAME);
//    }
    private Caller caller;

    private static final String MSG_FMT_CALLER_PREFIX = "[%s] %s(): ";
    
    private String msgCallerPrefix;

    protected AkiLogger() {

//        this.caller = AkiLogger.getCaller();
    }

    public static class Factory {

        public static AkiLogger create() {
            addExcluded(AkiLogger.Factory.class);
            AkiLogger logger = new AkiLogger();
            logger.doSetupCaller();
//            logger.doPrintCaller();
            logger.doSetupMsgCallerPrefix();
            return logger;
        }
    }

    protected final void doSetupCaller() {
        addExcluded(this);
        this.caller = LogHelper.getCaller();
    }
    
    protected final void doSetupMsgCallerPrefix() {
        this.msgCallerPrefix = String.format(MSG_FMT_CALLER_PREFIX, this.caller.getSimpleClassName(), this.caller.getMethodName());
        System.out.println("msgCallerPrefix= " + msgCallerPrefix);
    }

    protected void doPrintCaller() {
        System.out.println(this.caller);
    }
    
}
