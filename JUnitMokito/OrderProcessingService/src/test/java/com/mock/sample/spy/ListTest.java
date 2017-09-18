package com.mock.sample.spy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trankhai on 9/18/17.
 */
public class ListTest {
    @Spy
    List<String> myList = new ArrayList<String>();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){
        myList.add("Tulu");
        myList.add("Kai");

        Mockito.doReturn(3).when(myList).size();
        Mockito.when(myList.get(0)).thenReturn("Khai");
        Assert.assertSame(3, myList.size());
        Assert.assertEquals("Khai", myList.get(0));
    }
}
