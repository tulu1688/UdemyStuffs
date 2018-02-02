package com.tulu.java.design.patterns.behavioral.chainofresponsibility;

public class AddNumbers implements Chain {
    private Chain nextInChain;

    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    public void calculate(Numbers request) {
        if (request.getCalculationWanted() == "add") {
            System.out.println(request.getNumber1() + " + " + request.getNumber2() + " = " + (request.getNumber1() + request.getNumber2()));
        } else {
            nextInChain.calculate(request);
        }
    }
}
