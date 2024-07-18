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
        final Label label = new Label();

        /**
         * 客户端和服务端的通信
         */
        // 定义按钮1的回调事件，RPC通信
        button1.addClickHandler(event -> {
            // 进行异步调用，避免阻塞UI线程
            myServiceAsync.accumulate(Integer.parseInt(textBox.getText()), new AsyncCallback<Integer>() {
                @Override
                public void onFailure(Throwable caught) {
                    label.setText("Error: " + caught.getMessage());
                }

                @Override
                public void onSuccess(Integer result) {
                    label.setText(result.toString());
                }
            });
        });
        // 定义按钮2的回调事件，使用RequestBuilder请求SpringBoot程序
        button2.addClickHandler(clickEvent -> {
            ApiService.fetchDataOfSayHello(new AsyncCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    label.setText(result);
                }

                @Override
                public void onFailure(Throwable caught) {
                    label.setText(caught.getMessage());
                }
            });
        });
        /**
         * 注册历史记录变化处理程序
         */
        History.addValueChangeHandler(event -> {
            String historyToken = event.getValue();
            updateContent(historyToken);
        });
        // 添加页面导航
        RootPanel.get().add(createNavPanel());
        // 检查初始的历史记录条目
        if (History.getToken().isEmpty()) {
            History.newItem("home");
        } else {
            // 更新
            updateContent(History.getToken());
        }
        /**
         * 将组件定义到根面板中
         */
        RootPanel.get("myElement").add(textBox);
        RootPanel.get("myElement").add(button1);
        RootPanel.get("myElement").add(button2);
        RootPanel.get("myElement").add(label);
    }

    private Panel createNavPanel() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(new Anchor("Home", "#home"));
        panel.add(new Anchor("Page 1", "#page1"));
        panel.add(new Anchor("Page 2", "#page2"));
        return panel;
    }

    private void updateContent(String token) {
        Label content = new Label();
        switch (token) {
            case "home":
                content.setText("Welcome to the Home page!");
                break;
            case "page1":
                content.setText("This is Page 1.");
                break;
            case "page2":
                content.setText("This is Page 2.");
                break;
            default:
                content.setText("Page not found.");
                break;
        }
        RootPanel.get("myContent").clear();
        RootPanel.get("myContent").add(content);
    }
}
