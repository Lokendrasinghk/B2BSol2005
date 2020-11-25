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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sap.hybris.medical.commons.data.MedicalKitTemplateData;
import com.sap.hybris.medical.commons.dto.*;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import com.sap.hybris.medical.webservices.dto.MedicalKitProcedureAvailableKitDTO;
import com.sap.hybris.medical.webservices.dto.MedicalKitProcedureAvailableKitsDTO;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/facilities")
public class FacilitiesController extends BaseMedicalController {

    @Resource
    private MedicalFacade medicalFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public FacilitiesDTO getFacilities() {
        return medicalFacade.getFacilities();
    }

    @RequestMapping(value = "/{facilityCode}", method = RequestMethod.GET)
    @ResponseBody
    public FacilityDTO getFacility(@PathVariable("facilityCode") final String facilityCode) {
        return medicalFacade.getFacility(facilityCode);
    }

    @RequestMapping(value = "/{facilityCode}/kits", method = RequestMethod.GET)
    @ResponseBody
    public MedicalKitsDTO getAvailableKits(@PathVariable("facilityCode") final String facilityCode) {
        return medicalFacade.getAvailableKits(facilityCode);
    }

    @RequestMapping(value = "/{facilityCode}/procedures", method = RequestMethod.GET)
    @ResponseBody
    public MedicalProceduresDTO getAvailableProcedures(@PathVariable("facilityCode") final String facilityCode) {
        List<MedicalProcedureDTO> availableProcedures = medicalFacade.getAvailableProcedures(facilityCode);
        MedicalProceduresDTO proceduresDTO = new MedicalProceduresDTO();
        proceduresDTO.setProcedures(availableProcedures);
        return proceduresDTO;

    }

    @RequestMapping(value = "/{facilityCode}/kittemplates/{kitTemplateCode}/kits", method = RequestMethod.GET)
    @ResponseBody
    public MedicalKitsDTO getAvailableKits(@PathVariable("facilityCode") final String facilityCode,
			@PathVariable("kitTemplateCode") final String kitTemplateCode, @RequestParam("date") final Optional<String> dateTxt)
	{
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final String dateToCheck = dateTxt.orElse(formatter.format(LocalDate.now()));
		final List<MedicalKitDTO> availableKits = medicalFacade.getAvailableKitsForFacilityAndTemplate(facilityCode,
				kitTemplateCode, LocalDate.parse(dateToCheck));
        MedicalKitsDTO medicalKitsDTO = new MedicalKitsDTO();
        medicalKitsDTO.setKits(availableKits);
        return medicalKitsDTO;
    }

    @RequestMapping(value = "/{facilityCode}/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorsDTO getDoctors(@PathVariable("facilityCode") final String facilityCode) {
        return medicalFacade.getDoctorsForFacility(facilityCode);
    }

    @RequestMapping(value = "/{facilityCode}/procedures/{procedureCode}/kits")
    @ResponseBody
	public MedicalKitProcedureAvailableKitsDTO getAvailableKitsForProcedure(@PathVariable final String procedureCode,
			@PathVariable final String facilityCode, @RequestParam("date") final Optional<String> dateTxt)
	{
        final MedicalKitProcedureAvailableKitsDTO kitsForProcedure = new MedicalKitProcedureAvailableKitsDTO();
        final MedicalKitTemplatesDTO procedureKits = medicalFacade.getKitTemplatesForProcedure(procedureCode);
        final List<String> firstChoiceKits = new ArrayList<>();
        final Map<String, MedicalKitProcedureAvailableKitDTO> templatesMap = new HashMap<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final String dateToCheck = dateTxt.orElse(formatter.format(LocalDate.now()));
        procedureKits.getKits().forEach(procedureKit -> {
            final MedicalKitProcedureAvailableKitDTO kitTemplate = new MedicalKitProcedureAvailableKitDTO();
            templatesMap.put(kitTemplate.getCode(), kitTemplate);
            kitTemplate.setCode(procedureKit.getCode());
            kitTemplate.setName(procedureKit.getName());
            kitsForProcedure.getTemplates().add(kitTemplate);
			medicalFacade.getAvailableKitsForFacilityAndTemplate(facilityCode, kitTemplate.getCode(), LocalDate.parse(dateToCheck))//
                    .forEach(kit -> {
                        if (!firstChoiceKits.contains(kit.getCode())) {
                            firstChoiceKits.add(kit.getCode());
                        }
                        kitTemplate.getKits().add(kit);
                    });

        });

        medicalFacade.getAvailableKitsForFacility(facilityCode, LocalDate.parse(dateToCheck)).stream()//
                .filter(kit -> !firstChoiceKits.contains(kit.getCode()))//
                .forEach(kit -> {
                    if (!templatesMap.containsKey(kit.getKitTemplateCode())) {
                        final MedicalKitProcedureAvailableKitDTO kitTemplate = new MedicalKitProcedureAvailableKitDTO();
                        final MedicalKitTemplateData kitTemplateData = medicalFacade.getKitTemplate(kit.getKitTemplateCode());
                        kitTemplate.setCode(kitTemplateData.getCode());
                        kitTemplate.setName(kitTemplateData.getName());
                        templatesMap.put(kit.getKitTemplateCode(), kitTemplate);
                        kitsForProcedure.getTemplates().add(kitTemplate);
                    }
                    final MedicalKitProcedureAvailableKitDTO kitTemplate = templatesMap.get(kit.getKitTemplateCode());
                    kitTemplate.getKits().add(kit);
                });
        return kitsForProcedure;
    }
}
