package oncall.message;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = String.format("[ERROR] %s", message);
    }

    public String getMessage() {
        return message;
    }
}
