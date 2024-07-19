package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import server.ApiService;
import shared.MyService;
import shared.MyServiceAsync;

/**
 * GWT服务的入口
 *
 * @author cxq
 * @date 2024/7/7 11:29
 */
public class MyApp implements EntryPoint {

    /**
     * 创建异步服务代理
     */
    private final MyServiceAsync myServiceAsync = GWT.create(MyService.class);

    @Override
    public void onModuleLoad() {

        /**
         * 定义组件
         */
        final TextBox textBox = new TextBox();
        final Button button1 = new Button("RPC Button");
        final Button button2 = new Button("RequestBuilder Button");
        final Label counterLabel = new Label();
        final Label welcomeLabel = new Label();
        welcomeLabel.setText(setLabelContent(History.getToken()));
        /**
         * 将组件定义到根面板中
         */
        RootPanel.get("myElement").add(textBox);
        RootPanel.get("myElement").add(button1);
        RootPanel.get("myElement").add(button2);
        RootPanel.get("myElement").add(counterLabel);
        RootPanel.get("myContent").add(welcomeLabel);
        // 添加页面导航面板到root面板
        RootPanel.get("navPanel").add(createNavPanel());
        /**
         * 客户端和服务端的通信
         */
        // 给按钮1定义回调事件，RPC通信
        button1.addClickHandler(event -> {
            // 进行异步调用，避免阻塞UI线程
            myServiceAsync.accumulate(Integer.parseInt(textBox.getText()), new AsyncCallback<Integer>() {
                @Override
                public void onFailure(Throwable caught) {
                    counterLabel.setText("Error: " + caught.getMessage());
                }

                @Override
                public void onSuccess(Integer result) {
                    counterLabel.setText(result.toString());
                }
            });
        });
        // 给按钮2定义回调事件，使用RequestBuilder请求SpringBoot程序
        button2.addClickHandler(clickEvent -> {
            ApiService.fetchDataOfSayHello(new AsyncCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    counterLabel.setText(result);
                }

                @Override
                public void onFailure(Throwable caught) {
                    counterLabel.setText(caught.getMessage());
                }
            });
        });
        /**
         * 定义监听历史记录的状态变化，及其回调函数
         */
        History.addValueChangeHandler(event -> {
            String historyToken = event.getValue();
            welcomeLabel.setText(setLabelContent(History.getToken()));
        });
    }

    /**
     * 创建导航面板
     *
     * @return 面板
     */
    private Panel createNavPanel() {
        // 创建垂直排列的组件
        VerticalPanel panel = new VerticalPanel();
        // 将超链接放入到该组件中
        panel.add(new Anchor("主页", "#home"));
        panel.add(new Anchor("页面1", "#page1"));
        panel.add(new Anchor("页面2", "#page2"));
        return panel;
    }

    /**
     * 更新组件内容
     *
     * @param token 浏览器最新的历史记录
     */
    private String setLabelContent(String token) {

        if (token == null || token.isEmpty() || "home".equals(token)) {
            return "欢迎来到主页面";
        }
        if ("page1".equals(token)) {
            return "欢迎来到页面1";
        }
        if ("page2".equals(token)) {
            return "欢迎来到页面2";
        }
        return "没有找到页面";
    }
}
