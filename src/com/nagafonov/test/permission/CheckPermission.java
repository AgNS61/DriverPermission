package com.nagafonov.test.permission;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:42 PM
 */
public class CheckPermission {
    private final HTTPRequest httpRequest = new HTTPRequest();
    private Driver driver;


    //constructor for search by auto number and region
    public CheckPermission(String autoNumber, int autoCity) {
        httpRequest.setDataPost(autoNumber, autoCity);
    }

    //constructor for search by number permission
    public CheckPermission(int numberPermission) {
        httpRequest.setDataPost(numberPermission);
    }

    public void search() {
        httpRequest.doConnect();
        String contentGet = httpRequest.getContentGet();
        if (checkPermission(contentGet)) {
            Document html = Jsoup.parse(contentGet);
            Elements p = html.select("p");
            String status = p.get(4).text();
            int permNum = Integer.parseInt(p.get(5).text());
            String oranization = p.get(6).text();
            String expireDate = p.get(7).text();
            String autoNumber = p.get(8).text();
            String autoModel = p.get(9).text();
            driver = new Driver(permNum, oranization, expireDate, autoNumber, autoModel, status);
        }
            else System.out.println("По введенным вами данным разрешение не найдено");
    }

    // true if permission exists
    private boolean checkPermission(String contentGet) {
        return !contentGet.contains("По введенным вами данным разрешение не найдено");
    }

    public Driver getDriver() {
        if (driver != null) return driver;
        else return null;
    }
}
