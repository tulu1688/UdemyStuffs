package com.tulu.java.design.patterns.behavioral.chainofresponsibility;

public interface Chain {
    public void setNextChain(Chain nextChain);
    public void calculate(Numbers request);
}
