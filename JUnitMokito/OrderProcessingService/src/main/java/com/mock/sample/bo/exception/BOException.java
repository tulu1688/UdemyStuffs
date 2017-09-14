package com.mock.sample.bo.exception;

import java.sql.SQLException;

/**
 * Created by trankhai on 9/14/17.
 */
public class BOException extends Exception {

    public BOException(SQLException e) {
        super(e);
    }
}
