package oncall.controller;

import oncall.domain.Date;
import oncall.domain.Worker;

import java.util.List;

public class WorkController {

    public void calculateWorkerOrder(Date date, List<Worker> weekdayWorkers, List<Worker> weekendWorkers) {
        int endDay = calculateMonthEndDay(date.getMonth());
    }

    private int calculateMonthEndDay(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 28;
    }

}
