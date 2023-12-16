package oncall.controller;

import oncall.domain.Date;
import oncall.domain.Worker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class WorkControllerTest {

    private WorkController workController = new WorkController();

    @Test
    void calculateWorkerOrder() {
        Date date = new Date(5,"월");
        List<String> a = Arrays.asList("준팍", "도밥", "고니", "수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리");
        List<String> b = Arrays.asList("수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리", "준팍", "도밥", "고니");
        List<Worker> weekdayWorkers = a.stream()
                .map(Worker::new)
                .collect(Collectors.toList());
        List<Worker> holidayWorkers = b.stream()
                .map(Worker::new)
                .collect(Collectors.toList());

        List<Worker> workers = workController.calculateWorkerOrder(date, weekdayWorkers, holidayWorkers);
        for (Worker worker : workers) {
            System.out.println(worker.getName());
        }

    }
}