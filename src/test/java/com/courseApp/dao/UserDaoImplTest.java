package com.courseApp.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoImplTest {

    @Autowired
    UserDaoImpl udi;

    @BeforeEach
    void setUp() {
        udi.setUserName("t1");
        udi.setPassword("t1");
    }


    @Test
    void queryUserRole() {
        assertEquals("regularUser", udi.queryUserRole());
    }

    @Test
    void queryUserInDB() {
        assertTrue(udi.queryUserInDB());
    }

    @Test
    void queryCourseList() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CSC207F");
        expected.add("CSC209S");
        assertEquals(expected,udi.queryCourseList());
    }

    @Test
    void updateScheduleList() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CSC207F");
        expected.add("CSC209S");
        assertEquals(expected,udi.queryCourseList());
    }

}