package io.jyotirmay.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

    private Long userId;

    private String firstName;

    private String lastName;

    private String gender;

    private AddressModel address;

    private Timestamp createdOn;

    private Timestamp updatedOn;
}
