package win.i02.bean;

/**
 * @author zyq
 * @date 2020/4/14 9:15
 */
public class ResultBean {
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 结果
     */
    private String result;
    /**
     * 状态
     */
    private Boolean success;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
