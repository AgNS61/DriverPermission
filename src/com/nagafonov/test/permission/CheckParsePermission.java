package com.nagafonov.test.permission;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:42 PM
 */
public class CheckParsePermission {
    private String contentGet;


    // true if permission exists
    private boolean checkPermission(String contentGet) {
        return contentGet.contains("Разрешение на перевозку пассажиров и багажа легковым такси действительно.");
    }


}
