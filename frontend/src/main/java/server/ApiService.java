package server;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * api调用
 *
 * @author cxq
 * @date 2024/7/15 17:29
 */
public class ApiService {

    private static final String API_URL_PREFIX = "http://localhost:8081/api";

    public static void fetchDataOfSayHello(AsyncCallback<String> callback) {

        String apiSuffix = "/sayHello";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, API_URL_PREFIX + apiSuffix);

        try {
            builder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == Response.SC_OK) {
                        callback.onSuccess(response.getText());
                    } else {
                        callback.onFailure(new Exception("响应异常: " + response.getStatusText()));
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(new Exception("响应失败: " + exception.getMessage()));
                }
            });
        } catch (RequestException e) {
            callback.onFailure(new Exception("请求异常: " + e.getMessage()));
        }
    }
}
