package win.i02.service.impl;

import com.google.gson.Gson;
import win.i02.bean.ResultBean;
import win.i02.bean.TranslationBean;
import win.i02.service.ITranslateService;
import win.i02.util.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zyq
 * @date 2020/4/14 9:30
 */
public class YoudaoTranslateServiceImpl implements ITranslateService {
    public static final String appKey = "2b80d4bbd8eaa871";
    public static final String appSec = "IPUSCS4lWbvM3nCdcKkXwV3sKBAFDfMU";

    @Override
    public ResultBean translate(String q) {
        String salt = String.valueOf(System.currentTimeMillis());
        String from = "EN";
        String to = "zh-CHS";
        String sign = md5(appKey + q + salt + appSec);
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", q);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKey);
        ResultBean bean = new ResultBean();
        try {
            String result = HttpUtils.requestForHttp("http://openapi.youdao.com/api", params);
            if (result == null) {
                bean.setSuccess(false);
                bean.setErrorMsg("请求失败");
            } else {
                TranslationBean translationBean = new Gson().fromJson(result, TranslationBean.class);
                if(translationBean.errorCode==0){
                    bean.setSuccess(true);
                    bean.setResult(translationBean.getTranslationResult());
                }else{
                    bean.setSuccess(false);
                    bean.setErrorMsg(translationBean.getErrorMessage());
                }

            }
        } catch (Exception e) {
            bean.setSuccess(false);
            bean.setErrorMsg(e.getMessage());
        }
        return bean;
    }


    public static String genarator(String q) {
        int slat = new Random().nextInt();
        String sign = md5(appKey + q + slat + appSec);
        return "&appKey=" + appKey + "&salt=" + slat + "&sign=" + sign + "&q";
    }

    /**
     * 生成32位MD5摘要
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = string.getBytes("utf-8");
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }
}
