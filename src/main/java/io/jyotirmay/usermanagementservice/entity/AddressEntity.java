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
public class AddressEntity implements Serializable {

    private Long addressId;

    private Long userId;

    private String building;

    private String street;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Timestamp createdOn;

    private Timestamp updatedOn;

    private Boolean active;

    public AddressEntity(String building, String street, String city, String state, String country, String pincode){
        this.building = building;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.active = true;
    }

}
