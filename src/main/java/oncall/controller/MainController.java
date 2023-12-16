package oncall.controller;

import oncall.domain.Date;
import oncall.domain.WorkOrder;
import oncall.domain.Worker;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class MainController {

    private final OutputView outputView;
    private final InputView inputView;
    private final WorkController workController;

    public MainController() {
        outputView = OutputView.getOutputView();
        inputView = InputView.getInputView();
        workController = new WorkController();
    }

    public void run() {
        Date date = inputView.readWorkStartDate();
        WorkOrder workOrder = inputView.readWorkers();
        List<Worker> weekdayWorkers = workOrder.getWeekdayWorkerOrder();
        List<Worker> holidayWorkers = workOrder.getHolidayWorkerOrder();

    }
}
