package shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author cxq
 * @date 2024/7/7 12:26
 */
@RemoteServiceRelativePath("myService")
public interface MyService extends RemoteService {

    /**
     * 累加
     *
     * @param num 带累加数值
     * @return 结果
     */
    int accumulate(int num);
}
