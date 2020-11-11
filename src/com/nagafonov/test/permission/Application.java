package com.nagafonov.test.permission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author Agafonov Nikita agns61.1@gmail.com
 * @create 11/9/2020 7:29 PM
 */
public class Application {

    public static void main(String[] args) {
//  if you want to do check in system console
        checkByConsole();



// You can use it if you accepts values from another method

/*
//        choose one of them
//    CheckPermission checkPermission = checkByPermNun(31412);
//    CheckPermission checkPermission = checkByAuto("У578НС", 111116);
    Driver driver;
    try {
        driver = checkPermission.getDriver();
        System.out.println(driver.print() + "\n");
        System.out.println(driver.getGSON());
    } catch (NullPointerException n) {
//        n.printStackTrace();
    }*/

    }


//    search by permission's number
    public static CheckPermission checkByPermNun(int numberPermission) {
        CheckPermission checkPermission = new CheckPermission(numberPermission);
        checkPermission.search();
        return checkPermission;

// search by car's number and city's code
    }

    public static CheckPermission checkByAuto(String autoNumber, int autoCity) {
        CheckPermission checkPermission = new CheckPermission(autoNumber, autoCity);
        checkPermission.search();
        return checkPermission;
    }

// search in system console
// this method print result in console
    public static void checkByConsole() {
        CheckPermission checkPermission;

        String typeOfCheck = "0"; // field for choose type of search or exit
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!typeOfCheck.equals("3")) {      // 3 - exit

            System.out.println("Если Вы хотите проверить водителя по номеру ТС - нажмите 1, по номеру разрешения - нажмите 2");
            try {
                typeOfCheck = bufferedReader.readLine();
                switch (typeOfCheck) {

                    case "1":
                        System.out.println("Введите номер ТС (в формате С000СС или СС000):");
                        String autoNumber = bufferedReader.readLine();

                        System.out.println("Введите регионт ТС (например, 16, 116 или 716):");
                        try {
                            int autoCity = Integer.parseInt(bufferedReader.readLine());
                            checkPermission = new CheckPermission(autoNumber, autoCity); // create our search object
                            checkPermission.search();

                            if (checkPermission.getDriver() != null) {
                                Driver driver = checkPermission.getDriver();
                                System.out.println("\n" + driver.print());

//                            if you want to print json object
//                            System.out.println(driver.getGSON());
                            }
                            System.out.println("\nЧтобы выйти, введите \"3\" \n");
                            break;
                        } catch (NumberFormatException s) {
                        System.out.println("Неверный номер региона!");
//                            s.printStackTrace();
                        break;
                    }



                    case "2":
                        System.out.println("Введите номер разрешения:");
                        int numberPermission = 0;
                        try {
                            numberPermission= Integer.parseInt(bufferedReader.readLine());
                            if (numberPermission !=0) {
                                checkPermission = new CheckPermission(numberPermission);
                                checkPermission.search();
                                if (checkPermission.getDriver() != null) {
                                    Driver driver = checkPermission.getDriver();
                                    System.out.println("\n" + driver.print());

 //                                   if you want to print json object
//                                    System.out.println(driver.getGSON());
                                }
                                System.out.println("\nЧтобы выйти, введите \"3\" \n");
                                break;
                            }  else {
                                System.out.println("Неверный ввод!");
                                break;
                            }
                        } catch (NumberFormatException s) {
                            System.out.println("Неверный номер разрешения!");
//                            s.printStackTrace();
                            break;
                        }

                    case "3": break;
                    default:
                        System.out.println("Неверный ввод!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}