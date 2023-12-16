package oncall.util;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class Util {
    private Util() {
    }

    public static String removeSpace(String input) {
        return replace(input, Regex.SPACE, Regex.NO_SPACE);
    }

    public static String removeDelimiters(String input) {
        return replace(replace(input, Regex.SQUARE_BRACKETS_START, Regex.NO_SPACE), Regex.SQUARE_BRACKETS_END, Regex.NO_SPACE);
    }

    private static String replace(String input, Regex target, Regex replacement) {
        return input.replace(target.regex, replacement.regex);
    }

    public static List<String> splitByComma(String input) {
        return Arrays.asList(removeSpace(input).split(Regex.COMMA.regex));
    }

    public static List<String> formatProductInfo(String input) {
        return splitByComma(removeDelimiters(removeSpace(input)));
    }

    private String formatPrice(int price) {
        return NumberFormat.getInstance().format(price);
    }

    private enum Regex {
        SPACE(" "), NO_SPACE(""),
        SQUARE_BRACKETS_START("["), SQUARE_BRACKETS_END("]"),
        COMMA(",");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }
}
