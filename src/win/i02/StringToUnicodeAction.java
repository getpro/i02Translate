package win.i02;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.http.util.TextUtils;

/**
 * Created by irskj on 2018/9/27.
 */
public class StringToUnicodeAction extends AnAction {
    private Editor mEditor;
    private long latestClickTime;  // 上一次的点击时间

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (!isFastClick(1000)) {
            stringToUnicode(e);
        }
    }

    public void stringToUnicode(AnActionEvent e) {
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

        if (model.getSelectedText() == null) {
            return;
        }

        // 选中文字
        String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }
        WriteCommandAction.runWriteCommandAction(e.getProject(), new Runnable() {
            @Override
            public void run() {
                String content = mEditor.getDocument().getText();
                String start = content.substring(0,model.getSelectionStart());
                String end = content.substring(model.getSelectionEnd());
                mEditor.getDocument().setText(start+ChinaToUnicode(selectedText)+end);
            }
        });
    }

    /*      * from Chinese to Unicode      */
    public static String ChinaToUnicode(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int chr = str.charAt(i);
            result.append("\\u").append(Integer.toHexString(chr));
        }
        return result.toString();
    }


    /**
     * 屏蔽多次选中
     */
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
