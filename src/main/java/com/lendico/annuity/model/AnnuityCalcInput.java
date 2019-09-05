package com.lendico.annuity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnuityCalcInput {

    private Integer loanAmount;

    private Double nominalRate;

    private Integer duration;

    private String startDate;
}
