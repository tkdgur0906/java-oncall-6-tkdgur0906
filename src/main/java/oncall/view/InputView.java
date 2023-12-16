package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.Date;
import oncall.util.Util;

import java.util.List;

public class InputView {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = OutputView.getOutputView();

    public static InputView getInputView() {
        return inputView;
    }

    private InputView() {
    }

    public String readMainOption() {
        System.out.println(Message.INPUT_WORK_START_DATE.message);
        String input = Console.readLine();
        return input;
    }


    private enum Message {
        INPUT_WORK_START_DATE("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}