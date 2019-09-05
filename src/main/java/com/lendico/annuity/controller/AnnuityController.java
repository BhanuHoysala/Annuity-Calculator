package com.lendico.annuity.controller;

import com.lendico.annuity.model.AnnuityCalcInput;
import com.lendico.annuity.model.EMI;
import com.lendico.annuity.service.AnnuityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Annuity Calculator", tags = {"Annuity REST Controller"})
@Slf4j
@RestController
public class AnnuityController {

    @Autowired
    private AnnuityService annuityService;

    /**
     * Generates Equated Monthly Installments
     *
     * @param annuityCalcInput
     * @return
     */
    @ApiOperation(value = "Generates Equated Monthly Installments", response = ResponseEntity.class)
    @PostMapping("/generate-plan")
    public ResponseEntity<?> generatePlan(@RequestBody AnnuityCalcInput annuityCalcInput) {

        log.info("Input Parameters received for calculation {}", annuityCalcInput);
        // TODO - Input validation needs to be validation
        try {
            List<EMI> emis = annuityService.generatePlan(annuityCalcInput);
            return new ResponseEntity<>(emis, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid Query ", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal Server Exception", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Application Heart Beat test REST end point
     *
     * @return
     */
    @ApiOperation(value = "Service heart beat check, HTTP status 200 OK", response = ResponseEntity.class)
    @GetMapping("/welcome")
    public ResponseEntity<String> getHeartBeat() {

        return new ResponseEntity<>(HttpStatus.OK.toString(), HttpStatus.OK);
    }

}
