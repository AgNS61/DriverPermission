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
        CheckPermission checkPermission;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int typeOfCheck = 0;

        while (typeOfCheck != 3) {
            System.out.println("Если Вы хотите проверить водителя по номеру ТС - нажмите 1, по номеру разрешения - нажмите 2");
            try {
                typeOfCheck = Integer.parseInt(bufferedReader.readLine());
                switch (typeOfCheck) {
                    case 1:
                        System.out.println("Введите номер ТС (в формате С000СС или СС000):");
                        String autoNumber = bufferedReader.readLine();

                        System.out.println("Введите регионт ТС (например, 16, 116 или 716):");
                        int autoCity = Integer.parseInt(bufferedReader.readLine());
                        checkPermission = new CheckPermission(autoNumber, autoCity);
                        checkPermission.search();

                        if (checkPermission.getDriver() != null) System.out.println("\n" + checkPermission.getDriver().toString());
                        System.out.println("\nЧтобы выйти, введите \"3\" \n");
                        break;

                    case 2:
                        System.out.println("Введите номер разрешения:");
                        int numberPermission = Integer.parseInt(bufferedReader.readLine());
                        checkPermission = new CheckPermission(numberPermission);
                        checkPermission.search();
                        if (checkPermission.getDriver() != null) System.out.println("\n" + checkPermission.getDriver().toString());
                        System.out.println("\nЧтобы выйти, введите \"3\" \n");
                        break;

                    case 3: break;
                    default:
                        System.out.println("Неверный ввод!");
                        System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
