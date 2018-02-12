package by.yakovchik.task2_2;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 *  Класс в котормо парсится строка и производятся расчеты
 */
public class Calculator {

    /**
     * @param c
     * @return  true если с является пробелом
     */
    static boolean isDelim(char c) {
        return c == ' ';
    }

    /**
     * @param c
     * @return  true если с явлется оператором или скобкой
     */
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     *  Нужен для получения приоритета оператора
     * @param op
     * @return
     */
    static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Производит расчеты исходя из полученого оператора
     * @param st
     * @param op  оператор
     */
    static void processOperator(LinkedList<BigDecimal> st, char op){
        BigDecimal r = st.removeLast();
        BigDecimal l = st.removeLast();

            switch (op) {
                case '+':
                    st.add(l.add(r)); //Складывает значения
                    break;
                case '-':
                    st.add(l.subtract(r)); //Вычитает r из l
                    break;
                case '*':
                    st.add(l.multiply(r)); //Умножает значения
                    break;
                case '/':   //Lелит левый операнд на правый операнд
                            // Если r = 0 бросает Exception
                    if (r.equals(new BigDecimal("0"))) throw new ArithmeticException("ERROR: деление на 0");
                    st.add(l.divide(r));
                    break;
                case '^':   // Возведение в степень
                    if (r.compareTo(new BigDecimal("0")) == -1){ // Если степень отрицательная
                        r = r.abs();
                        st.add(new BigDecimal("1").divide(l.pow(r.intValueExact())));
                    } else  st.add(l.pow(r.intValueExact()));
                    break;
            }
    }
    public static String eval(String s) {
        LinkedList<BigDecimal> st = new LinkedList<>();
        LinkedList<Character> op = new LinkedList<>();

        try {
            char old =' ';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDelim(c))
                    continue;
                if (c == '(')
                    op.add('(');
                else if (c == ')') {
                    while (op.getLast() != '(')
                        processOperator(st, op.removeLast());
                    op.removeLast();
                } else if (isOperator(c) && old != '^') {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                } else {
                    String operand = "";
                    while ((i < s.length() && Character.isDigit(s.charAt(i)))
                            || (i < s.length() && s.charAt(i) == '.')
                            || (i < s.length() && s.charAt(i) == '-' && operand.equals("")))
                        operand += s.charAt(i++);
                    --i;
                    st.add(new BigDecimal(operand));
                }
                old = s.charAt(i);
            }
            while (!op.isEmpty())
                processOperator(st, op.removeLast());
            return st.get(0).toString();
        }catch (ArithmeticException ae) {
            System.out.println(ae.getMessage());
            return "null";
        }
    }
}
