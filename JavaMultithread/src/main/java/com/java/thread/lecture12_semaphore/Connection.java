package com.java.thread.lecture12_semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by trankhai on 7/12/17.
 */
public class Connection {
    private static Connection instance;

    Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance(){
        if (instance == null)
            instance = new Connection();
        return instance;
    }

    public void connect(){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            sem.release();
        }
    }

    public void doConnect(){
        synchronized (this) {
            connections++;
            System.out.println("Current connection: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }
}
