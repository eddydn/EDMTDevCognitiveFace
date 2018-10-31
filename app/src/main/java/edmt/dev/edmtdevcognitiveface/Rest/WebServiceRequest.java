package edmt.dev.edmtdevcognitiveface.Rest;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

import edmt.dev.edmtdevcognitiveface.Common.RequestMethod;
import edmt.dev.edmtdevcognitiveface.Common.ServiceError;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebServiceRequest {
    private static final String HEADER_KEY = "ocp-apim-subscription-key";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String OCTET_STREAM = "octet-stream";
    private static final String DATA = "data";

    private OkHttpClient mClient = new OkHttpClient();
    private String mSubscriptionKey;
    private Gson mGson = new Gson();

    public WebServiceRequest(String key) {
        this.mSubscriptionKey = key;
    }

    public Object request(String url, RequestMethod method, Map<String, Object> data, String contentType) throws ClientException, IOException {
        switch (method) {
            case GET:
                return get(url);
            case HEAD:
                break;
            case POST:
                return post(url, data, contentType);
            case PATCH:
                return patch(url, data, contentType);
            case DELETE:
                return delete(url, data);
            case PUT:
                return put(url, data);
            case OPTIONS:
                break;
            case TRACE:
                break;
        }

        return null;
    }

    private Object get(String url) throws ClientException, IOException {
        Request request = new Request.Builder()
                .addHeader(HEADER_KEY, this.mSubscriptionKey)
                .url(url)
                .build();


        Response response = mClient.newCall(request).execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            return readInput(response.body().byteStream());
        } else {
            String json = readInput(response.body().byteStream());
            if (json != null) {
                ServiceError error = mGson.fromJson(json, ServiceError.class);
                if (error != null) {
                    throw new ClientException(error.error);
                }
            }

            throw new ClientException("Error executing GET request!", statusCode);
        }
    }


    private Object patch(String url, Map<String, Object> data, String contentType) throws ClientException, IOException {

        MediaType  mediaType =  MediaType.parse("application/json; charset=utf-8");
        String json = this.mGson.toJson(data);
        RequestBody requestBody = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .addHeader(HEADER_KEY, mSubscriptionKey)
                .url(url)
                .patch(requestBody)
                .build();


        Response response = mClient.newCall(request).execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            return readInput(response.body().byteStream());
        } else {
            json = readInput(response.body().byteStream());
            if (json != null) {
                ServiceError error = mGson.fromJson(json, ServiceError.class);
                if (error != null) {
                    throw new ClientException(error.error);
                }
            }

            throw new ClientException("Error executing Patch request!", statusCode);
        }
    }


    private Object post(String url, Map<String, Object> data, String contentType) throws ClientException, IOException {
        MediaType  mediaType =  MediaType.parse("application/json; charset=utf-8");
        String json = this.mGson.toJson(data);
        RequestBody requestBody = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .addHeader(HEADER_KEY, mSubscriptionKey)
                .url(url)
                .post(requestBody)
                .build();


        Response response = mClient.newCall(request).execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            return readInput(response.body().byteStream());
        } else {
            json = readInput(response.body().byteStream());
            if (json != null) {
                ServiceError error = mGson.fromJson(json, ServiceError.class);
                if (error != null) {
                    throw new ClientException(error.error);
                }
            }

            throw new ClientException("Error executing POST request!", statusCode);
        }
    }

    private Object put(String url, Map<String, Object> data) throws ClientException, IOException {
        MediaType  mediaType =  MediaType.parse("application/json; charset=utf-8");
        String json = this.mGson.toJson(data);
        RequestBody requestBody = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .addHeader(HEADER_KEY, mSubscriptionKey)
                .url(url)
                .put(requestBody)
                .build();


        Response response = mClient.newCall(request).execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            return readInput(response.body().byteStream());
        } else {
            json = readInput(response.body().byteStream());
            if (json != null) {
                ServiceError error = mGson.fromJson(json, ServiceError.class);
                if (error != null) {
                    throw new ClientException(error.error);
                }
            }

            throw new ClientException("Error executing PUT request!", statusCode);
        }
    }


    private Object delete(String url, Map<String, Object> data) throws ClientException, IOException {
        Response response;
        if (data == null || data.isEmpty()) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader(HEADER_KEY, this.mSubscriptionKey)
                    .delete()
                    .build();
            response = mClient.newCall(request).execute();
        } else {

            MediaType  mediaType =  MediaType.parse("application/json; charset=utf-8");
            String json = this.mGson.toJson(data);
            RequestBody requestBody = RequestBody.create(mediaType, json);

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader(HEADER_KEY, this.mSubscriptionKey)
                    .addHeader("Content-Type", "application/json")
                    .delete(requestBody)
                    .build();

            response = mClient.newCall(request).execute();
        }

        int statusCode = response.code();
        if (statusCode != 200) {
            String json = readInput(response.body().byteStream());
            if (json != null) {
                ServiceError error = mGson.fromJson(json, ServiceError.class);
                if (error != null) {
                    throw new ClientException(error.error);
                }
            }

            throw new ClientException("Error executing DELETE request!", statusCode);
        }

        return readInput(response.body().byteStream());
    }


    public static String getUrl(String path, Map<String, Object> params) {
        StringBuffer url = new StringBuffer(path);

        boolean start = true;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (start) {
                url.append("?");
                start = false;
            } else {
                url.append("&");
            }

            try {
                url.append(param.getKey());
                url.append("=");
                url.append(URLEncoder.encode(param.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return url.toString();
    }

    private String readInput(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer json = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

        return json.toString();
    }
}
