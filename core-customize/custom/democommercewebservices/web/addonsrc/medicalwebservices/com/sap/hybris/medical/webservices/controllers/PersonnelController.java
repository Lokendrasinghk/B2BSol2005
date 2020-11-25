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
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sap.hybris.medical.commons.dto.DoctorsDTO;
import com.sap.hybris.medical.commons.dto.FacilitiesDTO;
import com.sap.hybris.medical.commons.dto.MedicalKitsDTO;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import com.sap.hybris.medical.webservices.dto.OrganizationHierarchyDTO;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/personnel")
public class PersonnelController extends BaseMedicalController {

    @Resource(name = "medicalFacade")
    private MedicalFacade medicalFacade;

    @RequestMapping(value = "/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorsDTO getAllDoctors() {
        return medicalFacade.getDoctors();
    }

    @RequestMapping(value = "/doctors/{doctorCode}/facilities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FacilitiesDTO getDoctors(@PathVariable("doctorCode") final String doctorCode) {
        return medicalFacade.getFacilitiesOfDoctor(doctorCode);
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @ResponseBody
    public OrganizationHierarchyDTO getManagerHierarchy() {
        return new OrganizationHierarchyDTO();
        //medicalFacade.getOrganizationHierarchyForCurrentUser();
    }

    @RequestMapping(value = "/manager/kits", method = RequestMethod.GET)
    @ResponseBody
	public MedicalKitsDTO getKitsForManager(@RequestParam("date") final Optional<String> dateTxt)
	{
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final String dateToCheck = dateTxt.orElse(formatter.format(LocalDate.now()));
        final MedicalKitsDTO medicalKitsDTO = new MedicalKitsDTO();
		medicalKitsDTO.setKits(medicalFacade.getAvailableKitsForManager(LocalDate.parse(dateToCheck)));
        return medicalKitsDTO;
    }

    @RequestMapping(value = "/manager/facilities", method = RequestMethod.GET)
    @ResponseBody
    public FacilitiesDTO getFacilitiesForManager() {
        final FacilitiesDTO facilitiesDTO = new FacilitiesDTO();
        facilitiesDTO.setFacilities(medicalFacade.getFacilitiesForManager());
        return facilitiesDTO;
    }
}
