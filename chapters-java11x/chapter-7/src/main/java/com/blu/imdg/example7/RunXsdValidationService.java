package com.blu.imdg.example7;

import com.blu.imdg.common.CommonConstants;
import com.blu.imdg.common.TestDataGenerator;
import com.blu.imdg.example3.ValidateMessage;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

import java.io.IOException;

/**
 *
 */
public class RunXsdValidationService {
    private static final String VALIDATING_SERVICE = "validatingService";
//    private static final String VALIDATING_SERVICE = "validatingService-cluster-singleton";

    public static void main(String[] args) throws IOException{
        try (Ignite ignite = Ignition.start(CommonConstants.CLIENT_CONFIG)) {

            String sample1 = TestDataGenerator.getSample1();
            String sample2 = TestDataGenerator.getSample2();
            byte[] vaidateSchema = TestDataGenerator.getValidateSchema();
            String validateScript = TestDataGenerator.getValidateScript();

            System.out.println("Invoke remote service!!");

            XsdValidatingService xsdValidatingService = ignite.services().serviceProxy(VALIDATING_SERVICE, XsdValidatingService.class, /*not-sticky*/false);

            System.out.println("result=" + xsdValidatingService.isOk(new ValidateMessage("1", sample1, vaidateSchema, validateScript)));
            System.out.println("result2=" + xsdValidatingService.isOk(new ValidateMessage("2", sample2, vaidateSchema, validateScript)));

        }

    }

}
