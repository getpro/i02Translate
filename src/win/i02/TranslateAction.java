package win.i02;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;
import win.i02.bean.ResultBean;
import win.i02.service.impl.TraslateFactory;

import java.awt.*;

/**
 * Created by irskj on 2017/11/20.
 */
public class TranslateAction extends AnAction{
    private Editor mEditor;
    private long latestClickTime;  // 上一次的点击时间

    @Override
    public void actionPerformed(AnActionEvent e) {

        if (!isFastClick(1000)) {
            performTranslation(e);
        }
    }

    /** 执行翻译 */
    private void performTranslation(AnActionEvent e) {

        /**
         * 第一步 --> 选中单词
         */
        // 获取动作编辑器
        mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (mEditor == null) {
            return;
        }

        // 获取选择模式对象
        SelectionModel model = mEditor.getSelectionModel();

        if(model.getSelectedText()==null) {
            return;
        }

        // 选中文字
        String selectedText = model.getSelectedText().replaceAll("\\*","");
        selectedText = selectedText.replaceAll("<(\\\\|/)*[a-zA-Z]+>","");
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }

        ResultBean bean = TraslateFactory.translate(selectedText);
        if(bean.isSuccess()){
            showPopupWindow(bean.getResult());
        }else{
            showPopupWindow(bean.getErrorMsg());
        }
    }


    /**
     * 第三步 --> 弹出对话框
     *
     * @param result
     */
    private void showPopupWindow(final String result) {
        ApplicationManager.getApplication().invokeLater(() -> {
            JBPopupFactory factory = JBPopupFactory.getInstance();
            factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                    .setFadeoutTime(5000)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(mEditor), Balloon.Position.below);
        });
    }


    /** 屏蔽多次选中 */
    public boolean isFastClick(long timeMillis) {
        long time = System.currentTimeMillis();
        long timeD = time - latestClickTime;
        if (0 < timeD && timeD < timeMillis) {
            return true;
        }
        latestClickTime = time;
        return false;
    }
}
