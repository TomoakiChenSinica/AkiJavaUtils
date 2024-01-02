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
public class AkiLogger_Bad extends LogHelper {
//
////        static {
////        EXCLUDE_CLASS_NAME_EXIST_MAP = new DataExistMap();
////        EXCLUDE_CLASS_NAME_EXIST_MAP.add(SELF_CLASS_NAME);
////        EXCLUDE_CLASS_NAME_EXIST_MAP.add(THREAD_CLASS_NAME);
////    }
//    private Caller caller;
//
//    private static final String MSG_FMT_CALLER_NAME_AND_METHOD_PREFIX = "[%s] %s(): %s";
////    private static final String MSG_FMT_CALLER_NAME_PREFIX = "[%s]";
//
//    private String msgCallerNamePrefix;
//
//    protected AkiLogger_Bad() {
//
////        this.caller = AkiLogger.getCaller();
//    }
//
//    public static class Factory {
//
//        public static AkiLogger_Bad create() {
//            addExcluded(AkiLogger_Bad.Factory.class);
//            AkiLogger_Bad logger = new AkiLogger_Bad();
//            logger.doSetupCaller();
////            logger.doPrintCaller();
//            logger.doSetupMsgCallerNamePrefix();
//            return logger;
//        }
//    }
//
//    protected final void doSetupCaller() {
//        addExcluded(this);
//        this.caller = LogHelper.getCaller();
//    }
//
//    protected final void doSetupMsgCallerNamePrefix() {
//        this.msgCallerNamePrefix = String.format(MSG_FMT_CALLER_NAME_AND_METHOD_PREFIX, this.caller.getSimpleClassName(), "%s", "%s");
//    }
//
//    protected void doPrintCaller() {
//        System.out.println(this.caller);
//    }
//
//    public void printLog(String msg) {
//        String log = String.format("%s %s",this.msgCallerNameAndMethodPrefix, msg);
//        System.out.println(log);
//    }
}
