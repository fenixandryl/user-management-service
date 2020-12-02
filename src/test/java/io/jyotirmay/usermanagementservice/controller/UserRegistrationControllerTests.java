package io.jyotirmay.usermanagementservice.controller;

import io.jyotirmay.usermanagementservice.UserManagementServiceApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureMockMvc
@SpringBootTest(classes = {UserRegistrationController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "classpath:application.yml")
@ContextConfiguration(classes = UserManagementServiceApplication.class)
public class UserRegistrationControllerTests {

    private UserRegistrationController userRegistrationController;

    void setUserRegistrationController(UserRegistrationController userRegistrationController){
        this.userRegistrationController = userRegistrationController;
    }

    void testRegisterUser(){

    }

}
