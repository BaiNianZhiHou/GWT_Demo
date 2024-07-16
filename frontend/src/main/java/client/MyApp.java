package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
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
    private final MyServiceAsync myService = GWT.create(MyService.class);

    @Override
    public void onModuleLoad() {

        // 定义组件
        final TextBox textBox = new TextBox();
        final Button button1 = new Button("RPC Button");
        final Button button2 = new Button("RequestBuilder Button");
        final Label label = new Label();

        // 定义按钮1的回调事件，RPC通信
        button1.addClickHandler(event -> {
            // 进行异步调用，避免阻塞UI线程
            myService.accumulate(Integer.parseInt(textBox.getText()), new AsyncCallback<Integer>() {
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
        RootPanel.get("myElement").add(textBox);
        RootPanel.get("myElement").add(button1);
        RootPanel.get("myElement").add(button2);
        RootPanel.get("myElement").add(label);
    }
}
