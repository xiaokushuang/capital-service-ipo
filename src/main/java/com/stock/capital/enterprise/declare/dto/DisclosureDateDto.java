package com.stock.capital.enterprise.declare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by XueHui on 2016/11/28 0028.
 */
public class DisclosureDateDto implements Serializable{

    private static final long serialVersionUID = 2911941248592049386L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date disclosureDate;

    private String userName;

    public Date getDisclosureDate() {
        return disclosureDate;
    }

    public void setDisclosureDate(Date disclosureDate) {
        this.disclosureDate = disclosureDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
