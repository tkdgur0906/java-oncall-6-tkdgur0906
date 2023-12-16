package oncall.domain;

import java.util.List;

public class WorkOrder {
    private List<Worker> workOrder;

    public WorkOrder(List<Worker> workOrder) {
        this.workOrder = workOrder;
    }

    public List<Worker> getWorkOrder() {
        return workOrder;
    }
}
