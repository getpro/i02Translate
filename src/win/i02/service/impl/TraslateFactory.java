package win.i02.service.impl;

import win.i02.bean.ResultBean;

/**
 * @author zyq
 * @date 2020/4/14 9:33
 */
public class TraslateFactory {

    public static ResultBean translate(String q) {
        ResultBean bean = new GoogleTranslateServiceImpl().translate(q);
        if(bean.isSuccess()){
            return bean;
        }
        bean = new YoudaoTranslateServiceImpl().translate(q);
        return bean;
    }
}
