package by.yakovchik.task2_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для проверки валидности введеной строки
 */
public class Validation {

    public static boolean check(String str) {

        String patternSymbol = "[0-9()\\.\\-\\+\\*\\^\\/ ]*"; //Проверка на допустимые символы
        Pattern patternValidSpace = Pattern.compile("\\d+\\s+\\d+|\\d+\\s*\\(|\\)\\s*\\d+"); // Проверка на пробелы
        Matcher matcherValidSpace = patternValidSpace.matcher(str);

        if (!str.matches(patternSymbol)){
            System.out.println("ERROR: недопустимые символы");
            return false;
        }else
        if (matcherValidSpace.find()) {
            System.out.println("ERROR: пробелы между числами");
            return false;
        }
        return true;
    }
}
