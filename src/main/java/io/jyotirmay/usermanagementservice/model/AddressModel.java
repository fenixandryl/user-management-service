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
public class AddressModel implements Serializable {

    private Long addressId;

    private String building;

    private String street;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Timestamp createdOn;

    private Timestamp updatedOn;
}
