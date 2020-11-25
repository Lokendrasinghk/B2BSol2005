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

import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sap.hybris.medical.commons.dto.MedicalKitDTO;
import com.sap.hybris.medical.commons.enums.MedicalKitStatus;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import com.sap.hybris.medical.webservices.dto.MaterialStatusesDTO;
import com.sap.hybris.medical.webservices.dto.MedicalKitStatusDTO;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/kits")
public class KitsController extends BaseMedicalController {

    @Resource(name = "medicalFacade")
    private MedicalFacade medicalFacade;

    @RequestMapping(value = "/{kitCode}", method = RequestMethod.GET)
    @ResponseBody
	public MedicalKitDTO getKitDetails(@PathVariable("kitCode") final String kitCode,
			@RequestParam("date") final Optional<String> dateTxt)
	{
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final String dateToCheck = dateTxt.orElse(formatter.format(LocalDate.now()));
		final MedicalKitDTO kitDetails = medicalFacade.getKitDetails(kitCode, LocalDate.parse(dateToCheck));
        if (kitDetails == null) {
            throw new ModelNotFoundException("There is no kit for code " + kitCode);
        }
        return kitDetails;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createKit(@RequestBody final MedicalKitDTO kitDTO) {
        final String kitCode = medicalFacade.createKit(kitDTO);
        return ResponseEntity.created(URI.create(kitCode)).build();
    }

    @RequestMapping(value = "/{kitCode}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void>  updateKitDetails(@PathVariable("kitCode") final String kitCode,@RequestBody final MedicalKitDTO kitDTO) {
        medicalFacade.updateKit(kitCode,kitDTO);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{kitCode}/status", method = RequestMethod.GET)
    @ResponseBody
	public MedicalKitStatusDTO getKitStatus(@PathVariable("kitCode") final String kitCode,
			@RequestParam("date") final Optional<String> dateTxt)
	{
        final MedicalKitStatusDTO status = new MedicalKitStatusDTO();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final String dateToCheck = dateTxt.orElse(formatter.format(LocalDate.now()));
		status.setStatus(medicalFacade.getKitDetails(kitCode, LocalDate.parse(dateToCheck)).getStatus());
        return status;
    }

    @RequestMapping(value = "/{kitCode}/status/{newStatus}", method = RequestMethod.POST)
    @ResponseBody
	public MedicalKitStatusDTO setKitStatus(@PathVariable("kitCode") final String kitCode,
			@PathVariable("newStatus") MedicalKitStatus newStatus, @RequestParam("date") final Optional<String> dateTxt)
	{
        medicalFacade.updateKitStatus(kitCode, newStatus);
		return getKitStatus(kitCode, dateTxt);
    }

    @RequestMapping(value = "/{kitCode}/materials", method = RequestMethod.POST)
    @ResponseBody
	public MedicalKitStatusDTO updateKitMaterialsStatuses(@PathVariable("kitCode") final String kitCode,
			@RequestParam("date") final Optional<String> dateTxt, @RequestBody final MaterialStatusesDTO materialStatuses)
	{
        medicalFacade.updateKitMaterials(kitCode, materialStatuses.getMaterials());
		return getKitStatus(kitCode, dateTxt);
    }
}
