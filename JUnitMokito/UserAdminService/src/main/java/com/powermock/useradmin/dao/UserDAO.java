package com.powermock.useradmin.dao;

import com.powermock.useradmin.com.powermock.useradmin.dto.User;
import com.powermock.useradmin.util.IDGenerator;

/**
 * Created by trankhai on 9/18/17.
 */
public class UserDAO {
    public int create(User user){
        int id = IDGenerator.generateID();
        user.setId(id);
        return id;
    }
}
