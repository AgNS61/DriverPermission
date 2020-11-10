package com.nagafonov.test.permission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/9/2020 7:22 PM
 */
public class HTTPRequest {

    private URL url;
    private final String urlAddressGet = "https://uslugi.tatarstan.ru/taxi_license/check/complete" ;
    private final String urlAddressPost = "https://uslugi.tatarstan.ru/taxi_license/check";
    private String dataPost;  // send in post-request, expect from user
    private String cookie = "";
    private String contentGet;
    private String contentPost;
    private int responseCodeGet;    //response code from server
    private int responseCodePost;
    private Map<String, String> propertiesForRequests = new HashMap<>();



    //do first request to get cookie from server and define it for other connections
    private void defineCookie() {
        try {
            url = new URL(urlAddressPost);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            String cookieHeader = httpURLConnection.getHeaderField("Set-Cookie");
            StringBuilder sb = new StringBuilder();
            List<HttpCookie> cookies = HttpCookie.parse(cookieHeader);
            for (HttpCookie httpCookie : cookies) {
                sb.append(httpCookie);
            }
            cookie = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //first send post
    private void sendPostRequest() {
        try {
            url = new URL(urlAddressPost);
            insertMap();

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            System.out.println(propertiesForRequests.get("cookie"));
            for (Map.Entry<String, String> entry : propertiesForRequests.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            httpURLConnection.setRequestProperty("method", "POST");
            httpURLConnection.setRequestProperty("path", "/taxi_license/check/");
            httpURLConnection.setRequestProperty("content-length", "624");
            httpURLConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("origin", "1");

//            httpURLConnection.setRequestProperty("authority", "uslugi.tatarstan.ru");
//            httpURLConnection.setRequestProperty("scheme", "https");
//            httpURLConnection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            httpURLConnection.setRequestProperty("cookie", cookie + ";");
//            httpURLConnection.setRequestProperty("accept-language", "ru-RU,ru;q=0.9");
//            httpURLConnection.setRequestProperty("cache-control", "max-age=0");
//            httpURLConnection.setRequestProperty("dnt", "1");
//            httpURLConnection.setRequestProperty("referer", "https://uslugi.tatarstan.ru/taxi_license/check");
//            httpURLConnection.setRequestProperty("sec-fetch-dest", "document");
//            httpURLConnection.setRequestProperty("sec-fetch-mode", "navigate");
//            httpURLConnection.setRequestProperty("sec-fetch-site", "same-origin");
//            httpURLConnection.setRequestProperty("sec-fetch-user", "?1");
//            httpURLConnection.setRequestProperty("upgrade-insecure-requests", "1");
//            httpURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");


            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(dataPost.getBytes());
            outputStream.flush();
            outputStream.close();
            responseCodePost = httpURLConnection.getResponseCode();


            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            bufferedReader.close();
            contentPost = response.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //second send get
    private void sendGetRequest() {
        try {
            url = new URL(urlAddressGet);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("authority", "uslugi.tatarstan.ru");
            httpURLConnection.setRequestProperty("method", "GET");
            httpURLConnection.setRequestProperty("path", "/taxi_license/check/complete/");
            httpURLConnection.setRequestProperty("scheme", "https");
            httpURLConnection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpURLConnection.setRequestProperty("cookie", cookie + ";");
            httpURLConnection.setRequestProperty("accept-language", "ru-RU,ru;q=0.9");
            httpURLConnection.setRequestProperty("cache-control", "max-age=0");
            httpURLConnection.setRequestProperty("dnt", "1");
            httpURLConnection.setRequestProperty("referer", "https://uslugi.tatarstan.ru/taxi_license/check");
            httpURLConnection.setRequestProperty("sec-fetch-dest", "document");
            httpURLConnection.setRequestProperty("sec-fetch-mode", "navigate");
            httpURLConnection.setRequestProperty("sec-fetch-site", "same-origin");
            httpURLConnection.setRequestProperty("sec-fetch-user", "?1");
            httpURLConnection.setRequestProperty("upgrade-insecure-requests", "1");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");

            responseCodeGet = httpURLConnection.getResponseCode();

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            bufferedReader.close();
            contentGet = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // if both requests are succesfully -> continue
    public String getContentGet() {
        if (responseCodePost == 200 && responseCodeGet == 200) return contentGet;
            else {
            System.out.println("Response from POST: " + responseCodePost);
            System.out.println("Response from GET: " + responseCodeGet);
            System.out.println("Error executing!");
            return null;
        }
    }

    public String getContentPost() {
        return contentPost;
    }

    public void setDataPost(String autoNumber, int autoCity) {
        if (autoNumber != null && autoCity != 0) {
            dataPost = "taxi_license_check_model[type]=0&taxi_license_check_model[auto_number]=" + autoNumber + "&taxi_license_check_model[auto_region]=" + autoCity;
        }
    }

    public void setDataPost(int permNum) {
        if (permNum != 0) dataPost = "taxi_license_check_model[type]=1&taxi_license_check_model[permission_number]=" + permNum;
    }

    public void doConnect() {
        defineCookie();
        sendPostRequest();
        sendGetRequest();
    }


    private void insertMap(){
        propertiesForRequests.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        propertiesForRequests.put("authority", "uslugi.tatarstan.ru");
        propertiesForRequests.put("scheme", "https");
        propertiesForRequests.put("cookie", cookie + ";");
        propertiesForRequests.put("accept-language", "ru-RU,ru;q=0.9");
        propertiesForRequests.put("cache-control", "max-age=0");
        propertiesForRequests.put("dnt", "1");
        propertiesForRequests.put("referer", "https://uslugi.tatarstan.ru/taxi_license/check");
        propertiesForRequests.put("sec-fetch-dest", "document");
        propertiesForRequests.put("sec-fetch-mode", "navigate");
        propertiesForRequests.put("sec-fetch-site", "same-origin");
        propertiesForRequests.put("sec-fetch-user", "?1");
        propertiesForRequests.put("upgrade-insecure-requests", "1");
        propertiesForRequests.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");

    }
}

