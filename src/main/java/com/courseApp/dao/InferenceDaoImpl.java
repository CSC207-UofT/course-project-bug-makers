package com.courseApp.dao;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.courseApp.constants.Constants.*;
import static com.courseApp.constants.Exceptions.INFERENCE_SERVER_ERROR;

/**
 * Implemented inferenceDAO for getting model inference result.
 *
 */
public class InferenceDaoImpl implements InferenceDAO{
    /**
     * Take in a review string and send it to the inference server and return the result.
     *
     * @param reviewString review string for user
     * @return inference result of review string (convert to double)
     */
    @Override
    public double modelInference(String reviewString) throws Exception {
        String res = queryResult(sentRequest(reviewString));
        return Double.parseDouble(res);
    }


    /**
     * Get the inference result from the inference server.
     *
     * @param queryLocation url for getting the inference result
     * @return String of classification result
     * @throws Exception Server exception
     */
    private String queryResult(String queryLocation) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(queryLocation);
        httpGet.setHeader(SUBSCRIPTION_KEY, SUBSCRIPTION_VALUE);
        httpGet.setHeader(CONTENT_TYPE, JSON_TYPE);
        // Add authentication ket to the request header

        int count = 0;
        int maxTries = 10; // try ten times
        final Pattern pattern = Pattern.compile(REGEX_CLASSIFICATION, Pattern.CASE_INSENSITIVE); //use regex to match the result


        while(true){
            try (CloseableHttpResponse response2 = httpclient.execute(httpGet)) {
                HttpEntity httpEntity = response2.getEntity();
                String s = EntityUtils.toString(httpEntity);
                final Matcher matcher = pattern.matcher(s);
                if (matcher.find())
                {
                    return matcher.group(0).substring(matcher.group(0).length() - 1); //get last char, which is the classification
                }
                else{
                    TimeUnit.SECONDS.sleep(2);
                    if (++count == maxTries){ // time out
                        throw new Exception(INFERENCE_SERVER_ERROR);
                    }
                }
            }
        }
    }

    /**
     * Sent post request to our ML inference server.
     *
     * @param reviewString string of user review
     * @return String of inference query url
     * @throws IOException exception
     */
    private String sentRequest(String reviewString) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(PREDICTION_URL);
        String JSON_STRING= JSON_BODY.replaceFirst(DOC_IDENTIFIER, reviewString);
        httpPost.setHeader(SUBSCRIPTION_KEY, SUBSCRIPTION_VALUE);
        httpPost.setHeader(CONTENT_TYPE, JSON_TYPE);
        // Add authentication ket to the request header
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        try (CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
            Header[] headers = response2.getHeaders(OPERATION_LOCATION);
            return headers[0].getValue();
        }
    }
}
