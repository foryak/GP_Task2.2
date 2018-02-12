package by.yakovchik.task2_2;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Calculator {


    static boolean isDelim(char c) {
        return c == ' ';
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
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
    static void processOperator(LinkedList<BigDecimal> st, char op){
        BigDecimal r = st.removeLast();
        BigDecimal l = st.removeLast();

            switch (op) {
                case '+':
                    st.add(l.add(r));
                    break;
                case '-':
                    st.add(l.subtract(r));
                    break;
                case '*':
                    st.add(l.multiply(r));
                    break;
                case '/':
                    if (r.equals(new BigDecimal("0"))) throw new ArithmeticException("ERROR: деление на 0");
                    st.add(l.divide(r));
                    break;
                case '^':
                    if (r.compareTo(new BigDecimal("0")) == -1){
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
            char old ='0';
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
                            || (i < s.length() && s.charAt(i) == '-' && operand.equals(""))) {
                        operand += s.charAt(i++);

                    }
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
