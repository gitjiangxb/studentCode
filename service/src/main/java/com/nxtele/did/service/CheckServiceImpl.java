package com.nxtele.did.service;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.nxtele.did.dto.TransferNumberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CheckServiceImpl implements CheckService {

    private static Logger logger = LoggerFactory.getLogger(CheckServiceImpl.class);

    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    @Override
    public TransferNumberDto getTransferNumberByNumber(String callNumber) {
        long countryId = 0L;
        if (!callNumber.contains("+")){
            callNumber = "+" + callNumber;
        }

        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(callNumber, "");
            boolean validNumber = phoneNumberUtil.isValidNumber(number);
            if (validNumber){
                String descriptionForNumber = geocoder.getDescriptionForNumber(number, Locale.US,"");
                if (countryId != 0){
                    return new TransferNumberDto(countryId,number.getNationalNumber()+"");
                } else {
                    logger.error("解析电话号码：{} 得到的国家名称：{}，在本地库里不存在/不正确，国家代码为{}",
                            callNumber,descriptionForNumber,number.getCountryCode());
                    return null;
                }
            }
            logger.error("电话号码：{} 在所属国家代码:{} 中不正确",callNumber,number.getCountryCode());
            return null;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("根据电话号码：{} 得到国码时发生错误",callNumber);
            return null;
        }
    }

    @Override
    public boolean checkNumber(String transferNumber) {
        if (!transferNumber.contains("+")){
            transferNumber = "+" + transferNumber;
        }

        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(transferNumber, "");
            boolean validNumber = phoneNumberUtil.isValidNumber(number);
            if (validNumber){
                return true;
            }
            logger.error("电话号码：{} 在所属国家代码:{} 中不正确",transferNumber,number.getCountryCode());
            return false;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("根据电话号码：{} 校验号码准确性时发生错误",transferNumber);
            return false;
        }
    }
}
