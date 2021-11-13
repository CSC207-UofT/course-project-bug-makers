package com.courseApp;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.dao.UserDAO;
import com.courseApp.dao.UserDaoImpl;
import com.courseApp.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserDaoImpl.class)
public class SpringBootApp  implements CommandLineRunner {

    @Autowired
    UserDaoImpl udi;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
    }
}