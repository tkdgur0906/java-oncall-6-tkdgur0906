package oncall.validate;

import oncall.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class WorkerValidator {

    public static void validateWorker(List<String> workers) {
        validateWorkerSize(workers);
        validateWorkerName(workers);
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
        for(int index = 0; index < workers.size(); index++) {
            if(existWorkers.contains(workers.get(index))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
            existWorkers.add(workers.get(index));
        }
    }

    private static void validateWorkerNameSize(List<String> workers) {
        for(int index = 0; index < workers.size(); index++) {
            if(workers.get(index).length() > 5) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
            }
        }
    }
}
