package server.impl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.concurrent.atomic.AtomicInteger;
import shared.MyService;

/**
 * 服务
 *
 * @author cxq
 * @date 2024-07-07 12:26
 */
public class MyServiceImpl extends RemoteServiceServlet implements MyService {

    /**
     * 数值累加
     */
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int accumulate(int num) {
        // 使用原子性自增操作
        return counter.addAndGet(num);
    }
}
