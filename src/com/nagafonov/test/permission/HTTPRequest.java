package com.nagafonov.test.permission;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/9/2020 7:22 PM
 */
public abstract class HTTPRequest {
    private URL url;
    private String urlAddressGet;
    private String urlAddressPost;
    private String dataPost;  // send in post-request
    private String cookie;
    private String contentGet;


    //do first request to get cookie from server and define it for other connections
    private void defineCookie() {
        try {
            url = new URL(urlAddressPost);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            String cookieHeader = httpURLConnection.getHeaderField("Set-Cookie");
            List<HttpCookie> cookies = HttpCookie.parse(cookieHeader);
            for (HttpCookie httpCookie : cookies) {
                cookie += httpCookie;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPostRequest() {
        try {
            url = new URL(urlAddressPost);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("authority", "uslugi.tatarstan.ru");
            httpURLConnection.setRequestProperty("method", "POST");
            httpURLConnection.setRequestProperty("path", "/taxi_license/check/");
            httpURLConnection.setRequestProperty("scheme", "https");
            httpURLConnection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpURLConnection.setRequestProperty("cookie", cookie + ";");
            httpURLConnection.setRequestProperty("accept-language", "ru-RU,ru;q=0.9");
            httpURLConnection.setRequestProperty("cache-control", "max-age=0");
            httpURLConnection.setRequestProperty("content-length", "624");
            httpURLConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("origin", "1");
            httpURLConnection.setRequestProperty("dnt", "1");
            httpURLConnection.setRequestProperty("referer", "https://uslugi.tatarstan.ru/taxi_license/check");
            httpURLConnection.setRequestProperty("sec-fetch-dest", "document");
            httpURLConnection.setRequestProperty("sec-fetch-mode", "navigate");
            httpURLConnection.setRequestProperty("sec-fetch-site", "same-origin");
            httpURLConnection.setRequestProperty("sec-fetch-user", "?1");
            httpURLConnection.setRequestProperty("upgrade-insecure-requests", "1");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
