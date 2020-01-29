package com.bib.service;

import static org.junit.Assert.*;

import com.bib.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class HazelcastServiceTest extends BaseTest {

    @Autowired
    private HazelcastService hazelcastService;

    @Test
    public void hazel00() throws InterruptedException {
        hazelcastService.useBucket();

    }
}