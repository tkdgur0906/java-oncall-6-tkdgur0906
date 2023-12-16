package oncall.view;

import oncall.domain.Date;
import oncall.domain.Holiday;
import oncall.domain.Worker;

import java.util.List;

import static oncall.domain.Constants.*;
import static oncall.util.Util.calculateMonthEndDay;

public class OutputView {

    private static final OutputView outputView = new OutputView();
    private static final List<String> DAY_ORDER = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);

    public static OutputView getOutputView() {
        return outputView;
    }

    private OutputView() {
    }

    public void printWorkOrders(List<Worker> workOrders, Date date) {
        System.out.println();
        int dayIndex = DAY_ORDER.indexOf(date.getDay());
        for (int day = 1; day <= calculateMonthEndDay(date.getMonth()); day++) {
            System.out.print(date.getMonth() + Message.OUTPUT_MONTH.message + day + Message.OUTPUT_DAY.message);
            if (Holiday.isHoliday(date.getMonth(), day)) {
                System.out.print(Message.OUTPUT_HOLIDAY.message);
            }
            System.out.println(DAY_ORDER.get(dayIndex) + " " + workOrders.get(day - 1).getName());
            dayIndex++;
            dayIndex %= 7;
        }
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    private enum Message {
        OUTPUT_MONTH("월 "),
        OUTPUT_DAY("일 "),
        OUTPUT_HOLIDAY("(휴일) ");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
