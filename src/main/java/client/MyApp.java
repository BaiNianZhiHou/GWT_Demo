package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import shared.MyService;
import shared.MyServiceAsync;

/**
 * ${DESC}
 *
 * @author cxq
 * @date 2024/7/7 11:29
 */
public class MyApp implements EntryPoint {

    @Override
    public void onModuleLoad() {
        // 定义组件
        final TextBox textBox = new TextBox();
        final Button button = new Button("Submit");
        final Label label = new Label();
        // 定义服务
        MyServiceAsync myService = GWT.create(MyService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) myService;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "myservice");
        // 定义按钮的点击事件
        button.addClickHandler(event -> {
            String text = textBox.getText();
            myService.processText(text, new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable caught) {
                    label.setText("Error: " + caught.getMessage());
                }
                @Override
                public void onSuccess(String result) {
                    label.setText(result);
                }
            });
        });
        RootPanel.get().add(textBox);
        RootPanel.get().add(button);
        RootPanel.get().add(label);
    }
}
