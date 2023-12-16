package oncall.controller;

import oncall.domain.Date;
import oncall.domain.Worker;

import java.util.ArrayList;
import java.util.List;

import static oncall.domain.Constants.*;
import static oncall.domain.Holiday.isHoliday;
import static oncall.util.Util.calculateMonthEndDay;

public class WorkController {

    private final List<Worker> changedWeekdayWorkers = new ArrayList<>();
    private final List<Worker> changedHolidayWorkers = new ArrayList<>();
    private final List<Worker> workOrder = new ArrayList<>();
    private static int weekdayWorkerIndex;
    private static int holidayWorkerIndex;
    private static final List<String> DAY_ORDER = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);

    public void init() {
        changedWeekdayWorkers.clear();
        changedHolidayWorkers.clear();
        workOrder.clear();
        weekdayWorkerIndex = 0;
        holidayWorkerIndex = 0;
    }

    public List<Worker> calculateWorkerOrder(Date date, List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        init();
        int dayIndex = getDayIndex(date);
        for (int dayNumber = 1; dayNumber <= calculateMonthEndDay(date.getMonth()); dayNumber++) {
            String currentDay = DAY_ORDER.get(dayIndex);
            boolean isHoliday = isMatchHoliday(date.getMonth(), dayNumber, currentDay);
            if (isHoliday) {
                holidayWorkerIndex = processDay(holidayWorkers, holidayWorkerIndex, workOrder, changedHolidayWorkers, dayIndex);
            }
            if (!isHoliday) {
                weekdayWorkerIndex = processDay(weekdayWorkers, weekdayWorkerIndex, workOrder, changedWeekdayWorkers, dayIndex);
            }
            dayIndex = increaseDayIndex(dayIndex);
        }
        return workOrder;
    }

    private int processDay(List<Worker> workers, int workerIndex, List<Worker> workOrder, List<Worker> changedWorkers, int dayIndex) {
        if (changedWorkers.isEmpty()) {
            return assignWorker(workers, workerIndex, workOrder, changedWorkers);
        }
        return assignDeferredWorker(changedWorkers, workOrder, dayIndex);
    }


    private static int getDayIndex(Date date) {
        int dayIndex = DAY_ORDER.indexOf(date.getDay());
        return dayIndex;
    }


    private static int assignWorker(List<Worker> workers, int workerIndex, List<Worker> workOrder, List<Worker> changedWorkers) {
        workerIndex = handleSequentialWork(workers, workOrder, workerIndex, changedWorkers);
        workOrder.add(workers.get(workerIndex));
        workerIndex = increaseWorkerIndex(workers, workerIndex);
        return workerIndex;
    }

    private static int assignDeferredWorker(List<Worker> changedWorkers, List<Worker> workOrder, int dayIndex) {
        if (!changedWorkers.isEmpty()) {
            workOrder.add(changedWorkers.get(0));
            changedWorkers.remove(0);
            dayIndex = increaseDayIndex(dayIndex);
        }
        return dayIndex;
    }

    private static int handleSequentialWork(List<Worker> workers, List<Worker> workOrder, int workerIndex, List<Worker> changedWorkers) {
        if (isSameWorkerWithBefore(workers, workOrder, workerIndex)) {
            changedWorkers.add(workers.get(workerIndex));
            workerIndex = increaseWorkerIndex(workers, workerIndex);
        }
        return workerIndex;
    }

    private static boolean isSameWorkerWithBefore(List<Worker> workers, List<Worker> workOrder, int workerIndex) {
        return !workOrder.isEmpty() && workOrder.get(workOrder.size() - 1).getName().equals(workers.get(workerIndex).getName());
    }

    private static int increaseWorkerIndex(List<Worker> workers, int workerIndex) {
        workerIndex++;
        workerIndex %= workers.size();
        return workerIndex;
    }

    private static int increaseDayIndex(int dayIndex) {
        dayIndex++;
        dayIndex %= 7;
        return dayIndex;
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
