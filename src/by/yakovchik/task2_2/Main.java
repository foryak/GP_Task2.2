package by.yakovchik.task2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        String expression = readExpression();

        if (Validation.check(expression)) System.out.println("result:" + Calculator.eval(expression));

    }

    /**
     * Метод читает строку из консоли и возвращет ее
     */
    private static String readExpression(){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Ввод выражения:");
            String str = reader.readLine().replace("," , ".");
            return str;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
