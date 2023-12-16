package oncall.view;

public class OutputView {

    private static final OutputView outputView = new OutputView();

    public static OutputView getOutputView() {
        return outputView;
    }

    private OutputView() {
    }

    public void printStart() {
        System.out.println(Message.OUTPUT_START_MESSAGE.message);
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    private enum Message {
        OUTPUT_START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
