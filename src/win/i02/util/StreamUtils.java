package win.i02.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by irskj on 2017/11/20.
 */
public class StreamUtils {
    /**
     * 将输入流转换成字符串
     * @param ins
     * @return
     */
    public static String getStringFromStream(InputStream ins) throws IOException {
        // 内层流读取数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        // 写入数据到输出流
        while ((len = ins.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        // 返回字符串
        return new String(outputStream.toByteArray(),"UTF-8");
    }
}
