package com.mock.scrapbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by trankhai on 9/15/17.
 */
public class ATest {
    @Mock
    B b;
    private A a;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }

    @Test
    public void usesVoidMethodShouldCallTheVoidMethod() throws Exception {
        Mockito.doNothing().when(b).voidMethod();
        Assert.assertEquals(1, a.usesVoidMethod());
        Mockito.verify(b).voidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void useVoidMethodShouldThrowRuntimeException() throws Exception{
        Mockito.doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void testConsecutiveCall() throws Exception{
        Mockito.doNothing().doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
        Mockito.verify(b).voidMethod();
        a.usesVoidMethod();
    }
}
