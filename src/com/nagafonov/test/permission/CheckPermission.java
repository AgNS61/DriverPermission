package com.nagafonov.test.permission;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:42 PM
 */
public class CheckPermission {
    private String autoNumber;
    private String autoCity;
    private int numberPermission;

    public CheckPermission(String autoNumber, String autoCity) {
        this.autoNumber = autoNumber;
        this.autoCity = autoCity;
    }

    public CheckPermission(int numberPermission) {
        this.numberPermission = numberPermission;
    }

    // true if permission exists
    private boolean checkPermission(String contentGet) {
        return contentGet.contains("Разрешение на перевозку пассажиров и багажа легковым такси действительно.");
    }


}
