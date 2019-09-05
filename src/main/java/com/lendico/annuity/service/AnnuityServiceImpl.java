package com.lendico.annuity.service;

import com.lendico.annuity.model.AnnuityCalcInput;
import com.lendico.annuity.model.EMI;
import com.lendico.annuity.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AnnuityServiceImpl implements AnnuityService {

    /**
     * Calculates the EMI of a month
     *
     * @param principal
     * @param rate
     * @param duration
     * @return
     */
    private double calculateEMI(double principal, double rate, int duration) {

        // Convert rate rate into a decimal. eg. 5% = 0.05
        double monthlyRate = rate / 100;

        // calculating monthly rate from yearly rate, divided by 12
        monthlyRate /= 12;

        double monthlyPayment = (principal * monthlyRate * (Math.pow((1 + monthlyRate), duration))
                / ((Math.pow((1 + monthlyRate), duration)) - 1));
        return monthlyPayment;
    }

    @Override
    public List<EMI> generatePlan(AnnuityCalcInput annuityCalcInput) {

        double principal = annuityCalcInput.getLoanAmount();
        double rate = annuityCalcInput.getNominalRate();
        int duration = annuityCalcInput.getDuration();

        // converting rate to monthly in decimal form
        double interestRate = (rate / 12) / 100;

        double monthlyPayment = calculateEMI(principal, rate, duration);
        double intPerMonth;
        List<EMI> emiList = new ArrayList<>();

        for (int i = 0; i < duration; i++) {

            EMI emi = new EMI();
            intPerMonth = (principal * interestRate);
            principal = ((principal) - ((monthlyPayment) - (intPerMonth)));

            emi.setDate(DateTimeUtils.addMonthsToGivenLocaleDateTime
                            (annuityCalcInput.getStartDate(), i, DateTimeUtils.DATE_FORMAT));
            log.info("Calculating EMI for the month : {}", emi.getDate());

            emi.setInterest(roundup(intPerMonth));
            emi.setPrincipal(roundup((monthlyPayment) - intPerMonth));
            double principlePerMonth = monthlyPayment - intPerMonth;
            double borrowerPayment = ((monthlyPayment) - intPerMonth) + intPerMonth;
            emi.setBorrowerPaymentAmount(roundup(borrowerPayment));
            emi.setRemainingOutstandingPrincipal(roundup(principal));
            emi.setInitialOutstandingPrincipal(roundup(principal + principlePerMonth));
            emiList.add(emi);
        }
        return emiList;
    }


    /**
     * Method to roundup to two decimal places
     *
     * @param number
     * @return
     */
    private double roundup(double number) {

        number = Math.round(number * 100);
        return number /= 100;
    }

}
