package win.i02.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import win.i02.bean.TranslationBean;
import win.i02.bean.TranslationGoogleBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by irskj on 2017/11/20.
 */
public class HttpUtils {
    //    private static final String BASE_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
    public static final String appKey = "2b80d4bbd8eaa871";
    public static final String appSec = "IPUSCS4lWbvM3nCdcKkXwV3sKBAFDfMU";
    private static final String BASE_URL = "http://openapi.youdao.com/api?from=EN&to=zh-CHS";

//    private static final String BASE_GOOGLE_URL="https://translate.google.com/translate_a/single?client=t&sl=en&tl=zh-CN&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&source=btn&ssel=3&tsel=0&kc=0&tk=955354.586851&q=";


//    public static void requestNetDataGoogle(String q,TranslateCallBack callBack){
//        try {
//            URL url = new URL(BASE_URL + q);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setConnectTimeout(5000);
//            conn.setReadTimeout(5000);
//            conn.setRequestMethod(conn.getRequestMethod());
//
//            // 连接成功
//            if (conn.getResponseCode() == 200) {
//                InputStream ins = conn.getInputStream();
//
//                // 获取到Json字符串
//                String content = StreamUtils.getStringFromStream(ins);
//                if (content != null) {
//                    callBack.onSuccess(new Gson().fromJson(content, callBack.mType));
//                } else {
//                    callBack.onFailure(TranslationBean.EMPTY);
//                }
//            } else {
//                callBack.onFailure(conn.getResponseMessage());
//            }
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//            callBack.onFailure(e.getMessage());
//        }
//    }

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

    /**
     * 请求网络数据
     */
    public static void requestNetData(String queryWord, TranslateCallBack callBack) {
        // TODO 读取本地缓存

        String urlString = BASE_URL + genarator(queryWord);

        try {
            URL url = new URL(urlString + queryWord);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod(conn.getRequestMethod());

            // 连接成功
            if (conn.getResponseCode() == 200) {
                InputStream ins = conn.getInputStream();

                // 获取到Json字符串
                String content = StreamUtils.getStringFromStream(ins);
                System.out.println("返回值：" + content);
                if (content != null) {
                    callBack.onSuccess(new Gson().fromJson(content, callBack.mType));
                } else {
                    callBack.onFailure(TranslationBean.EMPTY);
                }
            } else {
                callBack.onFailure(conn.getResponseMessage());
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            callBack.onFailure(e.getMessage());
        }
    }

    public static void requestData(String q, TranslateCallBack callBack) {
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
        try {
            String result = requestForHttp("http://openapi.youdao.com/api", params);
            if (result == null) {
                callBack.onFailure("请求失败");
            } else {
                callBack.onSuccess(new Gson().fromJson(result, callBack.mType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestGoogle(String q, TranslateCallBack<TranslationGoogleBean> callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("q", q);
        try {
            String result = requestForHttp("http://qr.i02.win/translate", params);
            if (result != null) {
                callBack.onSuccess(new Gson().fromJson(result, callBack.mType));
            } else {
                callBack.onFailure("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onFailure(e.getMessage());
        }
    }

    public static String requestForHttp(String url, Map requestParams) throws Exception {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
        List params = new ArrayList();
        Iterator<Map.Entry<String, String>> it = requestParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
