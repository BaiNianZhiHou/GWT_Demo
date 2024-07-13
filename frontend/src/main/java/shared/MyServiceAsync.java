package shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Administrator
 */
public interface MyServiceAsync {

    void processText(String text, AsyncCallback<String> async);
}
