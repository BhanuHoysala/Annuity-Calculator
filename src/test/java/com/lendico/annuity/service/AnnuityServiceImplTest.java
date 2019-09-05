package com.lendico.annuity.service;

import com.lendico.annuity.model.AnnuityCalcInput;
import com.lendico.annuity.model.EMI;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AnnuityServiceImplTest {

    @Autowired
    private AnnuityService annuityService;

    @Test
    public void generatePlan() {

        AnnuityCalcInput input = new AnnuityCalcInput();
        input.setLoanAmount(5000);
        input.setNominalRate(5.0);
        input.setDuration(24);
        input.setStartDate("2018-01-01T00:00:00Z");

        List<EMI> emis = annuityService.generatePlan(input);
        Assert.assertNotEquals(null, emis);
        Assert.assertEquals(24,emis.size());
    }

}