package com.powermock.useradmin.util;

/**
 * Created by trankhai on 9/18/17.
 */
public class IDGenerator {
    static int i;

    public static final int generateID(){
        return i++;
    }
}
