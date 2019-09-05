package com.lendico.annuity.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Model to represent Equated monthly installments (EMI)
 */
@Data
public class EMI {

    private double borrowerPaymentAmount;
    private LocalDateTime date;
    private double initialOutstandingPrincipal;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;
}
