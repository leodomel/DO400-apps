package com.redhat.training;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.training.operation.Add;
import com.redhat.training.operation.Identity;
import com.redhat.training.operation.Multiply;
import com.redhat.training.operation.Operation;
import com.redhat.training.operation.Substract;

@ApplicationScoped
public class OperationRegistry {
    @Inject
    Multiply multiply;

    @Inject
    Add add;

    @Inject
    Substract substract;

    @Inject
    Identity identity;

    @PostConstruct
    protected void buildOperationList() {
        setOperations(List.of(substract, add, multiply, identity));
    }

    private List<Operation> operations;
    public List<Operation> getOperations() {
        return this.operations;
    }

    private void setOperations(final List<Operation> operationsParam) {
        this.operations = operationsParam;
    }
}
