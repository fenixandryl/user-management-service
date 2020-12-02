package io.jyotirmay.usermanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    private Long userId;

    private String firstName;

    private String lastName;

    private String gender;

    private Timestamp createdOn;

    private Timestamp updatedOn;

    private Boolean active;

    public UserEntity(String firstName, String lastName, String gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.active = true;
    }

}
