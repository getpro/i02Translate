package win.i02.service.impl;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import win.i02.bean.GoogleTranslateBean;
import win.i02.bean.ResultBean;
import win.i02.bean.Sentences;
import win.i02.service.ITranslateService;
import win.i02.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zyq
 * @date 2020/4/14 9:29
 */
public class GoogleTranslateServiceImpl implements ITranslateService {
    @Override
    public ResultBean translate(String q) {
        Map<String, String> params = new HashMap<>();
        params.put("client", "gtx");
        params.put("dt", "t");
        params.put("dj", "1");
        params.put("ie", "UTF-8");
        params.put("sl", "auto");
        params.put("tl", "zh");
        params.put("q", q);
        ResultBean resultBean = new ResultBean();
        try {
            String result = HttpUtils.requestForHttp("http://translate.google.cn/translate_a/single", params);
            if (result != null) {
                GoogleTranslateBean bean = new Gson().fromJson(result, GoogleTranslateBean.class);
                if(!CollectionUtils.isEmpty(bean.getSentences())){
                    bean.getSentences().forEach(new Consumer<Sentences>() {
                        @Override
                        public void accept(Sentences sentences) {
                            resultBean.setResult(resultBean.getResult()!=null?resultBean.getResult()+sentences.getTrans():sentences.getTrans());
                        }
                    });
                    resultBean.setSuccess(true);
                }else{
                    resultBean.setErrorMsg("翻译失败");
                    resultBean.setSuccess(false);
                }
            } else {
                resultBean.setErrorMsg("请求失败");
                resultBean.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setErrorMsg(e.getMessage());
            resultBean.setSuccess(false);
        }

        return resultBean;
    }
}
