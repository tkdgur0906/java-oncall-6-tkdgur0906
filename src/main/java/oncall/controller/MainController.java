package oncall.controller;

import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {

    private final OutputView outputView;
    private final InputView inputView;

    public MainController() {
        outputView = OutputView.getOutputView();
        inputView = InputView.getInputView();
    }

    public void run() {
        inputView.readWorkStartDate();
        inputView.readWeekdayWorker();
    }
}
