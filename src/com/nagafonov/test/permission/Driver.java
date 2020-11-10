package com.nagafonov.test.permission;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/10/2020 5:30 PM
 */
public class Driver {
    private int numberPermission;
    private String companyName;
    private String dateExpire;
    private String numberAuto;
    private String numberModel;

    public Driver(int numberPermission, String companyName, String dateExpire, String numberAuto, String numberModel) {
        this.numberPermission = numberPermission;
        this.companyName = companyName;
        this.dateExpire = dateExpire;
        this.numberAuto = numberAuto;
        this.numberModel = numberModel;
    }

    @Override
    public String toString() {
        return  "\nРазрешение на перевозку пассажиров и багажа легковым такси действительно."
                + "\n\nНомер разрешения: " + numberPermission
                + "\nНаименование юридического лица: " + companyName
                + "\nСрок действия разрешения: " + dateExpire
                + "\nНомер транспортного средства: " + numberAuto
                + "\nМарка и модель ТС: " + numberModel;
    }
}



