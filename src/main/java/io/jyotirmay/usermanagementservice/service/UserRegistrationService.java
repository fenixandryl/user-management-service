package io.jyotirmay.usermanagementservice.service;

import io.jyotirmay.usermanagementservice.entity.AddressEntity;
import io.jyotirmay.usermanagementservice.entity.UserEntity;
import io.jyotirmay.usermanagementservice.model.AddressModel;
import io.jyotirmay.usermanagementservice.model.UserModel;
import io.jyotirmay.usermanagementservice.repository.UserRegistrationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserRegistrationService {

    private static final Logger LOGGER = LogManager.getLogger(UserRegistrationService.class);

    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public void setUserRegistrationRepository(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Transactional
    public UserEntity register(UserModel userModel) {

        Timestamp timestamp = new Timestamp(new Date().getTime());
        UserEntity userEntity = new UserEntity(userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getGender());
        userEntity.setCreatedOn(timestamp);

        userEntity = registerUser(userEntity);

        AddressModel addressModel = userModel.getAddress();
        AddressEntity addressEntity = new AddressEntity(addressModel.getBuilding(),
                addressModel.getStreet(),
                addressModel.getCity(),
                addressModel.getState(),
                addressModel.getCountry(),
                addressModel.getPincode());
        addressEntity.setUserId(userEntity.getUserId());
        addressEntity.setCreatedOn(timestamp);

        addressEntity = registerAddress(addressEntity);

        return userEntity;
    }

    private UserEntity registerUser(UserEntity userEntity) {
        LOGGER.info("Registering the user {} {}.", userEntity.getFirstName(), userEntity.getLastName());
        userEntity.setCreatedOn(new Timestamp(new Date().getTime()));
        userEntity.setActive(true);
        userEntity = userRegistrationRepository.insertUser(userEntity);
        LOGGER.info("User {} {} registered with id {}.", userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUserId());
        return userEntity;
    }

    private AddressEntity registerAddress(AddressEntity addressEntity) {
        LOGGER.info("Registering address for user with id {}.", addressEntity.getUserId());
        addressEntity.setCreatedOn(new Timestamp(new Date().getTime()));
        addressEntity.setActive(true);

        addressEntity = userRegistrationRepository.insertAddress(addressEntity);
        LOGGER.info("Address registered for user {} with id {}.", addressEntity.getUserId(), addressEntity.getAddressId());
        return addressEntity;
    }

    public UserModel getUserDetailsById(Long userId) {
        List<UserModel> userModels = userRegistrationRepository.getUserDetailsById(userId);
        if (null != userModels && !userModels.isEmpty()) {
            LOGGER.info("The user details retrieved is {}", userModels.get(0));
            return userModels.get(0);
        }

        LOGGER.info("Details for user with id {} not found.", userId);
        return null;
    }
}
