/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 *
 */

package com.sap.hybris.medical.webservices.controllers;

import com.sap.hybris.medical.commons.dto.MedicalCaseDTO;
import com.sap.hybris.medical.commons.dto.MedicalCasesDTO;
import com.sap.hybris.medical.commons.dto.MedicalKitDTO;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import com.sap.hybris.medical.webservices.dto.CaseKitDTO;
import com.sap.hybris.medical.webservices.dto.CaseKitsDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import javax.annotation.Resource;
import java.net.URI;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/cases")
public class MedicalCasesController extends BaseMedicalController {
    @Resource(name = "medicalFacade")
    private MedicalFacade medicalFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalCasesDTO getCases() {
        return medicalFacade.getAllCases();
    }

    @RequestMapping(value = "/{caseCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalCaseDTO getCase(final @PathVariable("caseCode") String caseCode) {
        return medicalFacade.getCase(caseCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createCase(final @RequestBody MedicalCaseDTO medicalCaseDTO) throws CommerceCartModificationException,InvalidCartException{
        final String caseCode = medicalFacade.createCase(medicalCaseDTO);        
        medicalFacade.updateCaseKits(caseCode, medicalCaseDTO.getKits().stream().map(MedicalKitDTO::getCode).collect(Collectors.toList()));
        //medicalFacade.createOrderFromCase(caseCode);
        return ResponseEntity.created(URI.create(caseCode)).build();
    }

    @RequestMapping(value = "/${caseCode}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateCase(final @PathVariable("caseCode") String caseCode, final @RequestBody MedicalCaseDTO medicalCaseDTO) {
        medicalFacade.updateCase(caseCode,medicalCaseDTO);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{caseCode}/kits", method = RequestMethod.POST)
    public ResponseEntity<Void> updateKitsForCase(@PathVariable("caseCode") final String caseCode, @RequestBody final CaseKitsDTO caseKitsDTO)
    		throws CommerceCartModificationException,InvalidCartException {
        medicalFacade.updateCaseKits(caseCode, caseKitsDTO.getKits().stream().map(CaseKitDTO::getKitCode).collect(Collectors.toList()));
        //medicalFacade.createOrderFromCase(caseCode);
        return ResponseEntity.accepted().build();
    }
    
    
   @RequestMapping(value = "/createOrder/{caseCode}", method = {RequestMethod.POST,RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalCaseDTO createOrderCase(final @PathVariable("caseCode") String caseCode) throws CommerceCartModificationException,InvalidCartException {
       String orderID =medicalFacade.createOrderFromCase(caseCode);
       MedicalCaseDTO caseDTO= new MedicalCaseDTO();
       caseDTO.setOrderID(orderID);
       caseDTO.setCode(caseCode);
        return caseDTO;
    }
}
