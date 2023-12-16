package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.Date;
import oncall.domain.Worker;
import oncall.util.Util;
import oncall.validate.WorkerValidator;

import java.util.List;

import static oncall.message.ErrorMessage.INVALID_INPUT;
import static oncall.util.Util.*;
import static oncall.validate.Validator.validateDay;
import static oncall.validate.Validator.validateMonth;
import static oncall.validate.WorkerValidator.*;

public class InputView {

    private static final InputView inputView = new InputView();
    private static final int DATE_SIZE = 2;

    public static InputView getInputView() {
        return inputView;
    }

    private InputView() {
    }

    public Date readWorkStartDate() {
        try{
            System.out.println(Message.INPUT_WORK_START_DATE.message);
            String input = Console.readLine();
            List<String> date = splitByComma(input);
            validateWorkStartDate(date);
            return Date.of(Integer.parseInt(date.get(0)), date.get(1));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWorkStartDate();
        }

    }

    public List<Worker> readWeekdayWorker() {
        try{
            System.out.println(Message.INPUT_WEEKDAY_WORKER.message);
            List<String> workers = splitByComma(Console.readLine());
            validateWorker(workers);
            return workers.stream()
                    .map(Worker::new)
                    .toList();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWeekdayWorker();
        }
    }

    public List<Worker> readHolidayWorker() {
        try{
            System.out.println(Message.INPUT_HOLIDAY_WORKER.message);
            List<String> workers = splitByComma(Console.readLine());
            validateWorker(workers);
            return workers.stream()
                    .map(Worker::new)
                    .toList();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readHolidayWorker();
        }
    }

    private void validateWorkStartDate(List<String> date) {
        validateDateSize(date);
        validateMonth(date.get(0));
        validateDay(date.get(1));

    }

    private static void validateDateSize(List<String> date) {
        if (date.size() != DATE_SIZE) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }


    private enum Message {
        INPUT_WORK_START_DATE("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
        INPUT_WEEKDAY_WORKER("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
        INPUT_HOLIDAY_WORKER("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}