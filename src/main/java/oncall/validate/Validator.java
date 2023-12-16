package oncall.validate;

import oncall.message.ErrorMessage;

import static oncall.domain.Constants.*;

public class Validator {

    private static final String NUMBER_REGEX = "^[0-9]*$";

    public static void validateMonth(String input) {
        validateNumeric(input);
        validateMonthRange(input);
    }

    private static void validateNumeric(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private static void validateMonthRange(String input) {
        int month = Integer.parseInt(input);
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public static void validateDay(String input) {
        if (!input.equals(MONDAY) && !input.equals(TUESDAY) && !input.equals(WEDNESDAY) && !input.equals(THURSDAY)
                && !input.equals(FRIDAY) && !input.equals(SATURDAY) && !input.equals(SUNDAY)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }


}
