package io.jyotirmay.usermanagementservice.controller;

import io.jyotirmay.usermanagementservice.entity.UserEntity;
import io.jyotirmay.usermanagementservice.model.UserModel;
import io.jyotirmay.usermanagementservice.service.UserRegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ecom/v1")
public class UserRegistrationController {

    private static final Logger LOGGER = LogManager.getLogger(UserRegistrationController.class);

    private UserRegistrationService userRegistrationService;

    @Autowired
    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping(path = "/register/user")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserModel userModel) {
        LOGGER.info("Request received to register user {} {}", userModel.getFirstName(), userModel.getLastName());

        UserEntity userEntity = userRegistrationService.register(userModel);

        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserModel> getUserDetails(@PathVariable("id") Long userId) {
        LOGGER.info("Request received to get details of user with id {}", userId);

        UserModel userModel = userRegistrationService.getUserDetailsById(userId);
        if (null == userModel) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

}
