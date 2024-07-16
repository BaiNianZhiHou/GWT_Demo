package shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author cxq
 * @date 2024/7/7 11:29
 */
public interface MyServiceAsync {

    /**
     * 累加
     *
     * @param num 带累加数值
     */
    void accumulate(int num, AsyncCallback<Integer> async);
}
