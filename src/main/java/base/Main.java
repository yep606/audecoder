package base;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Dotenv dotenv = Dotenv.load();

        final String key = dotenv.get("API_KEY");

        final String fileName = dotenv.get("FILE_LINK");

        String POST = "https://transcribe.api.cloud.yandex.net/speech/stt/v2/longRunningRecognize";

        JSONObject language = new JSONObject();
        language.put("languageCode", "ru-RU");
        JSONObject specification = new JSONObject();
        specification.put("specification", language);
        JSONObject uri = new JSONObject();
        uri.put("uri", fileName);

        JSONObject main = new JSONObject();
        main.put("audio", uri);
        main.put("config", specification);

        /*
         *
         *
         *
         *
         *
         */

        HttpPost httpPost = new HttpPost(POST);
        httpPost.setEntity(new StringEntity(main.toString()));
        httpPost.addHeader("Authorization", String.format("Api-Key %s", key));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));

        String id = object.getString("id");

        HttpGet httpGet = new HttpGet(String.format("https://operation.api.cloud.yandex.net/operations/%s", id ));
        httpGet.addHeader("Authorization", String.format("Api-Key %s", key));

        System.out.println("Starting decoding: ");
        JSONObject getResponse;

        while(true) {

            Thread.sleep(1000);

            CloseableHttpResponse httpGetResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpGetResponse.getEntity();

            getResponse = new JSONObject(EntityUtils.toString(entity));

            System.out.println(getResponse);

            if(getResponse.getBoolean("done"))
                break;

        }

        String result = getResponse
                .getJSONObject("response")
                .getJSONArray("chunks") //1
                .getJSONObject(0)
                .getJSONArray("alternatives")
                .getJSONObject(0)
                .getString("text");

        System.out.println("Result: - > " + result);
    }

}
