package com.nagafonov.test.permission;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:42 PM
 */
public class CheckPermission {
    private String autoNumber;
    private int autoCity;
    private int numberPermission;
    private HTTPRequest httpRequest = new HTTPRequest();


    //constructor for search by auto number and region
    public CheckPermission(String autoNumber, int autoCity) {
        this.autoNumber = autoNumber;
        this.autoCity = autoCity;
        httpRequest.setDataPost(0, autoNumber, autoCity);
    }

    //constructor for search by number permission
    public CheckPermission(int numberPermission) {
        this.numberPermission = numberPermission;
        httpRequest.setDataPost(numberPermission, null, 0);
    }

    // true if permission exists
    private boolean checkPermission(String contentGet) {
        return contentGet.contains("Разрешение на перевозку пассажиров и багажа легковым такси действительно.");
    }


}
