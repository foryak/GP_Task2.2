package by.yakovchik.task2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String expression = readExpression();

        if (Validation.check(expression)) System.out.println("result = " + Calculator.eval(expression));

    }

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
