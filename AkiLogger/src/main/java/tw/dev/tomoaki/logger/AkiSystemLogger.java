/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.logger;

import tw.dev.tomoaki.logger.abst.AbstSystemOutputStream;

/**
 *
 * @author arche
 */
public class AkiSystemLogger<T extends AbstSystemOutputStream> extends AkiLogger {

//    public AkiSystemLogger() throws InstantiationException, IllegalAccessException {
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) t;
//        Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
//        T os = clazz.newInstance();
//        System.setOut(os);
//        this.doSetupCaller();
//        this.doSetupMsgPrefix();
//    }
    public AkiSystemLogger(Class<T> osClazz) throws InstantiationException, IllegalAccessException {
        T os = osClazz.newInstance();
        System.setOut(os);
        this.doSetupCaller();
        this.doSetupMsgPrefix();

    }
}
