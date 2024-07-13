package server.impl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import shared.MyService;

/**
 * 服务
 *
 * @author cxq
 * @date 2024-07-07 12:26
 */
public class MyServiceImpl extends RemoteServiceServlet implements MyService {

    @Override
    public String processText(String text) {
        return "输入的文本内容：" + text;
    }
}
