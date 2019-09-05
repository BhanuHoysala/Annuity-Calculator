package com.lendico.annuity.service;

import com.lendico.annuity.model.AnnuityCalcInput;
import com.lendico.annuity.model.EMI;

import java.util.List;

public interface AnnuityService {

    List<EMI> generatePlan(AnnuityCalcInput annuityCalcInput);
}
