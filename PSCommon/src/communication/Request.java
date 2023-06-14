/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import enumeration.Operation;
import java.io.Serializable;

/**
 *
 * @author Somika
 */
public class Request implements Serializable {

    private Operation operation;
    private Object data;
    private Object data2;

    public Request() {
    }

    public Request(Operation operation, Object data) {
        this.operation = operation;
        this.data = data;
    }

    public Request(Operation operation) {
        this.operation = operation;
    }

    public Request(Operation operation, Object data, Object data2) {
        this.operation = operation;
        this.data = data;
        this.data2 = data2;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData2() {
        return data2;
    }
}
