package shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author cxq
 * @date 2024/7/7 11:29
 */
public interface MyServiceAsync {

    void processText(String text, AsyncCallback<String> async);
}
