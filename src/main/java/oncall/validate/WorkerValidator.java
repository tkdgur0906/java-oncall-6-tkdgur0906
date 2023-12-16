package oncall.validate;

import oncall.domain.Worker;
import oncall.message.ErrorMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;

public class WorkerValidator {

    public static void validateWorker(List<String> workers) {
        validateWorkerSize(workers);
        validateWorkerName(workers);
    }

    public static void validateWorkerEqual(List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        List<String> weekdayWorkersName = weekdayWorkers.stream()
                .map(Worker::getName)
                .collect(toList());
        List<String> holidayWorkersName = holidayWorkers.stream()
                .map(Worker::getName)
                .collect(toList());
        if (!weekdayWorkersName.containsAll(holidayWorkersName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }


    private static void validateWorkerSize(List<String> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private static void validateWorkerName(List<String> workers) {
        validateWorkerNameDuplicate(workers);
        validateWorkerNameSize(workers);
    }

    private static void validateWorkerNameDuplicate(List<String> workers) {
        List<String> existWorkers = new ArrayList<>();
        for (int index = 0; index < workers.size(); index++) {
            if (existWorkers.contains(workers.get(index))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
            existWorkers.add(workers.get(index));
        }
    }

    private static void validateWorkerNameSize(List<String> workers) {
        for (int index = 0; index < workers.size(); index++) {
            if (workers.get(index).length() > 5) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
        }
    }
}
