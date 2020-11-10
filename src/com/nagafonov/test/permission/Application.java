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
        System.out.println("Если Вы хотите проверить водителя по номеру ТС - нажмите 1, по номеру разрешения - нажмите 2");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int typeOfCheck = Integer.parseInt(bufferedReader.readLine());
            switch (typeOfCheck) {
                case 1:
                    System.out.println("Введите номер ТС (в формате С000СС или СС000):");
                    String autoNumber = bufferedReader.readLine();

                    System.out.println("Введите регионт ТС (например, 16, 116 или 716):");
                    String autoCity = bufferedReader.readLine();
                    checkPermission = new CheckPermission(autoNumber, autoCity);
                    break;

                case 2:
                    System.out.println("Введите номер разрешения:");
                    int numberPermission = Integer.parseInt(bufferedReader.readLine());
                    checkPermission = new CheckPermission(numberPermission);
                    break;

                default:
                    System.out.println("Неверный ввод!");
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
