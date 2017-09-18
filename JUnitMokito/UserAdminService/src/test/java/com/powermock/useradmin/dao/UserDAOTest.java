package com.powermock.useradmin.dao;

import com.powermock.useradmin.com.powermock.useradmin.dto.User;
import com.powermock.useradmin.util.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IDGenerator.class)
public class UserDAOTest {
    @Test
    public void createShouldReturnAUserId(){
        UserDAO dao = new UserDAO();

        mockStatic(IDGenerator.class);
        when(IDGenerator.generateID()).thenReturn(17);

        int result = dao.create(new User());

        assertEquals(17, result);
        verifyStatic();
    }
}
