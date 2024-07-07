package server;/**
 * @author cxq
 * @date 2024/7/7 12:26
 */

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import shared.MyService;

/**
 * @program: gwt_demo
 *
 * @description:
 *
 * @author: Mr.Like
 *
 * @create: 2024-07-07 12:26
 */
public class MyServiceImpl extends RemoteServiceServlet implements MyService {

    @Override
    public String processText(String text) {
        return "Processed: " + text;
    }
}
