package shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author cxq
 * @date 2024/7/7 12:26
 */
@RemoteServiceRelativePath("myservice")
public interface MyService extends RemoteService {
    String processText(String text);
}
