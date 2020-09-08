package com.nxtele.did;


import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.nxtele.did.service.CheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;


@SpringBootTest
class ClientTest {

    @Autowired
    private CheckService checkService;

    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    @Test
    public void checkPhone()  {
        this.check("美国","14796898353");
        this.check("美国","16399938704");
        this.check("美国","17692223644");
        this.check("美国","17313257960");
        this.check("美国","19104303075");

//        this.check("英国","447876166133");
//        this.check("英国","447505570083");
//        this.check("英国","447956412247");
//        this.check("英国","447552226742");
//        this.check("英国","447734238587");

    }

    private void check(String countryName,String callNumber){
        try {
            if (!callNumber.contains("+")){
                callNumber = "+" + callNumber;
            }
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(callNumber, "");
            boolean validNumber = phoneNumberUtil.isValidNumber(number);
            if (validNumber) {
                String descriptionForNumber = geocoder.getDescriptionForNumber(number, Locale.US, "");

                System.out.println(countryName  + " —— " +  callNumber  + " —— " + descriptionForNumber);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
