package com.nagafonov.test.permission;

import com.google.gson.Gson;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:30 PM
 */
public class Driver {
    private final int numberPermission;
    private final String statusPermission;
    private final String companyName;
    private final String dateExpire;
    private final String numberAuto;
    private final String numberModel;

    public Driver(int numberPermission, String companyName, String dateExpire, String numberAuto, String numberModel, String status) {
        this.numberPermission = numberPermission;
        this.companyName = companyName;
        this.dateExpire = dateExpire;
        this.numberAuto = numberAuto;
        this.numberModel = numberModel;
        this.statusPermission = status;
    }

    public String print() {
        return  "\n" + statusPermission
                + "\nНомер разрешения: " + numberPermission
                + "\nНаименование юридического лица: " + companyName
                + "\nСрок действия разрешения: " + dateExpire
                + "\nНомер транспортного средства: " + numberAuto
                + "\nМарка и модель ТС: " + numberModel;
    }

    public String getGSON() {
        Gson gson = new Gson();
        String gs = gson.toJson(this);
        return gs;
    }
}



