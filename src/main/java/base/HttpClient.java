package base;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class HttpClient {

    public JSONObject sendPost(String url) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        return new JSONObject(EntityUtils.toString(response.getEntity()));

    }

    public String sendGet(String url) throws IOException {


        return "";
    }


}
