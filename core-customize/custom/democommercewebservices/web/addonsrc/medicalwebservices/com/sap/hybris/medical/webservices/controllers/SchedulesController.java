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

import com.sap.hybris.medical.commons.dto.CountryDTO;
import com.sap.hybris.medical.commons.dto.ScheduleDTO;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/schedules")
public class SchedulesController extends BaseMedicalController {
    @Resource(name = "medicalFacade")
    private MedicalFacade medicalFacade;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ScheduleDTO getSchedules() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date today = new Date();
        return getSchedules(dateFormat.format(today));
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    @ResponseBody
    public ScheduleDTO getSchedules(@PathVariable(value = "date") final String date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpScheduleDate;
        try {
            tmpScheduleDate = dateFormat.parse(date);
        } catch (final ParseException e) {
            tmpScheduleDate = new Date();
        }

        final Date scheduleDate = tmpScheduleDate;

        final UserModel currentUser = userService.getCurrentUser();
        final List<CountryDTO> countries = currentUser.getGroups().stream()//
                .filter(gr -> gr instanceof B2BUnitModel)//
                .map(gr -> (B2BUnitModel) gr)//
                .map(facility -> medicalFacade.getFacilityScheduleAtDate(facility, scheduleDate))//
                .flatMap(Collection::stream)//
                .collect(Collectors.toList());
        final ScheduleDTO schedule = new ScheduleDTO();
        schedule.setNations(countries);
        return schedule;
    }
}
