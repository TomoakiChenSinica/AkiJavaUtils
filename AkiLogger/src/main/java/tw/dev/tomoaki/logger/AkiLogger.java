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

//    private Caller caller; //因為Caller已經包裝成不只是 Class，還有Method

    private static final String MSG_FMT_PREFIX_WITH_CALLER_NAME_AND_METHOD = "[%s] %s(): %s";

    protected AkiLogger() {
        // this.caller = AkiLogger.getCaller();
    }

    public static class Factory {

        public static AkiLogger create() {
            addExcluded(AkiLogger.Factory.class);
            AkiLogger logger = new AkiLogger();
            return logger;
        }
    }

//    protected final void doSetupCaller() {
//        addExcluded(this);
//        this.caller = LogHelper.getCaller();
//    }
    protected Caller obtainCaller() {
        addExcluded(this);
        return LogHelper.getCaller();
    }

 

    public void printLog(String msg) {
        Caller caller = this.obtainCaller();
        String log = String.format(MSG_FMT_PREFIX_WITH_CALLER_NAME_AND_METHOD, caller.getSimpleClassName(), caller.getMethodName(), msg);
        System.out.println(log);
    }
}
