package oncall.controller;

import oncall.domain.Date;
import oncall.domain.Worker;
import oncall.util.Util;

import java.util.ArrayList;
import java.util.List;

import static oncall.domain.Constants.*;
import static oncall.domain.Holiday.isHoliday;
import static oncall.util.Util.*;

public class WorkController {

    private static final List<String> DAY_ORDER = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);


    public List<Worker> calculateWorkerOrder(Date date, List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        int endDay = calculateMonthEndDay(date.getMonth());
        String day = date.getDay();
        List<Worker> changedWeekdayWorkers = new ArrayList<>();
        List<Worker> changedHolidayWorkers = new ArrayList<>();
        List<Worker> workOrder = new ArrayList<>();
        int dayIndex = DAY_ORDER.indexOf(day);
        int weekdayWorkerIndex = 0;
        int holidayWorkerIndex = 0;

        for (int dayNumber = 1; dayNumber <= endDay; dayNumber++) {
            //오늘이 평일이면
            if (!isMatchHoliday(date.getMonth(), dayNumber, DAY_ORDER.get(dayIndex))) {
                if (changedWeekdayWorkers.isEmpty()) {
                    //마지막에 추가된 근무자가 오늘 근무자와 같으면
                    if (!workOrder.isEmpty() && workOrder.get(workOrder.size() - 1).getName().equals(weekdayWorkers.get(weekdayWorkerIndex).getName())) {
                        changedWeekdayWorkers.add(weekdayWorkers.get(weekdayWorkerIndex));
                        weekdayWorkerIndex++;
                        weekdayWorkerIndex %= weekdayWorkers.size();
                    }
                    workOrder.add(weekdayWorkers.get(weekdayWorkerIndex));
                    weekdayWorkerIndex++;
                    weekdayWorkerIndex %= weekdayWorkers.size();
                    dayIndex++;
                    dayIndex %= 7;
                    continue;
                }
                if (!changedWeekdayWorkers.isEmpty()) {
                    workOrder.add(changedWeekdayWorkers.get(0));
                    changedWeekdayWorkers.remove(0);
                    dayIndex++;
                    dayIndex %= 7;
                }
            }
            //오늘이 휴일이면
            if (isMatchHoliday(date.getMonth(), dayNumber, DAY_ORDER.get(dayIndex))) {
                if (changedHolidayWorkers.isEmpty()) {
                    //마지막에 추가된 근무자가 오늘 근무자와 같으면
                    if (!workOrder.isEmpty() && workOrder.get(workOrder.size() - 1).getName().equals(holidayWorkers.get(holidayWorkerIndex).getName())) {
                        changedHolidayWorkers.add(holidayWorkers.get(holidayWorkerIndex));
                        holidayWorkerIndex++;
                        holidayWorkerIndex %= holidayWorkers.size();
                    }
                    workOrder.add(holidayWorkers.get(holidayWorkerIndex));
                    holidayWorkerIndex++;
                    holidayWorkerIndex %= holidayWorkers.size();
                    dayIndex++;
                    dayIndex %= 7;
                    continue;
                }
                if (!changedHolidayWorkers.isEmpty()) {
                    workOrder.add(changedHolidayWorkers.get(0));
                    changedHolidayWorkers.remove(0);
                    dayIndex++;
                    dayIndex %= 7;
                }
            }

        }

        return workOrder;
    }

    private boolean isMatchHoliday(int month, int day, String dayName) {
        if (dayName.equals(SATURDAY) || dayName.equals(SUNDAY)) {
            return true;
        }
        if (isHoliday(month, day)) {
            return true;
        }
        return false;
    }


}
