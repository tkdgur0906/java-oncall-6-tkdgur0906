package oncall.domain;

import java.util.List;

public class WorkOrder {
    private List<Worker> weekdayWorkerOrder;
    private List<Worker> holidayWorkerOrder;

    public WorkOrder(List<Worker> weekdayWorkerOrder, List<Worker> holidayWorkerOrder) {
        this.weekdayWorkerOrder = weekdayWorkerOrder;
        this.holidayWorkerOrder = holidayWorkerOrder;
    }

    public static WorkOrder of(List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        return new WorkOrder(weekdayWorkers, holidayWorkers);
    }

    public List<Worker> getWeekdayWorkerOrder() {
        return weekdayWorkerOrder;
    }

    public List<Worker> getHolidayWorkerOrder() {
        return holidayWorkerOrder;
    }
}
