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

        // 定义组件
        final TextBox rpcBox = new TextBox();
        final Button rpcButton = new Button("RPC Button");
        final Button rbButton = new Button("RequestBuilder Button");
        final Label correspondLabel = new Label();
        final Label welcomeLabel = new Label();
        welcomeLabel.setText(setLabelContent(History.getToken()));
        // 将组件定义到根面板中
        RootPanel.get("myElement").add(rpcBox);
        RootPanel.get("myElement").add(rpcButton);
        RootPanel.get("myElement").add(rbButton);
        RootPanel.get("myElement").add(correspondLabel);
        RootPanel.get("myContent").add(welcomeLabel);
        // 添加页面导航面板到root面板
        RootPanel.get("navPanel").add(createNavPanel());
        // 客户端和服务端的通信
        rpcButton.addClickHandler(event -> {
            // 进行异步调用，避免阻塞UI线程
            myServiceAsync.accumulate(Integer.parseInt(rpcBox.getText()), new AsyncCallback<Integer>() {
                @Override
                public void onFailure(Throwable caught) {
                    correspondLabel.setText("Error: " + caught.getMessage());
                }

                @Override
                public void onSuccess(Integer result) {
                    correspondLabel.setText(result.toString());
                }
            });
        });
        // 给按钮2定义回调事件，使用RequestBuilder请求SpringBoot程序
        rbButton.addClickHandler(clickEvent -> {
            ApiService.fetchDataOfSayHello(new AsyncCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    correspondLabel.setText(result);
                }

                @Override
                public void onFailure(Throwable caught) {
                    correspondLabel.setText(caught.getMessage());
                }
            });
        });
        // 定义监听历史记录的状态变化，及其回调函数
        History.addValueChangeHandler(event -> {
            welcomeLabel.setText(setLabelContent(event.getValue()));
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
