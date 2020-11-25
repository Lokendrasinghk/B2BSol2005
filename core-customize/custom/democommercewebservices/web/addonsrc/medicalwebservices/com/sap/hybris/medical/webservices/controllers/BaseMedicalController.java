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

import com.google.common.collect.Lists;
import com.sap.hybris.medical.commons.exceptions.CaseCreationException;
import com.sap.hybris.medical.commons.exceptions.KitCreationException;
import com.sap.hybris.medical.webservices.dto.CaseCreationErrorDTO;
import com.sap.hybris.medical.webservices.dto.KitCreationErrorDTO;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.PaginationWsDTO;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.webservicescommons.dto.error.ErrorListWsDTO;
import de.hybris.platform.webservicescommons.dto.error.ErrorWsDTO;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.mapping.DataMapper;
import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;
import de.hybris.platform.webservicescommons.util.YSanitizer;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


/**
 * Base Controller. It defines the exception handler to be used by all controllers. Extending controllers can add or
 * overwrite the exception handler if needed.
 */
@Controller
public class BaseMedicalController {
    protected static final String DEFAULT_PAGE_SIZE = "20";
    protected static final String DEFAULT_CURRENT_PAGE = "0";
    protected static final String DEFAULT_FIELD_SET = FieldSetLevelHelper.DEFAULT_LEVEL;
    private static final String HEADER_TOTAL_COUNT = "X-Total-Count";
    private static final Logger LOG = Logger.getLogger(BaseMedicalController.class);
    @Resource(name = "dataMapper")
    private DataMapper dataMapper;

    protected static String logParam(final String paramName, final long paramValue) {
        return paramName + " = " + paramValue;
    }

    protected static String logParam(final String paramName, final Long paramValue) {
        return paramName + " = " + paramValue;
    }

    protected static String logParam(final String paramName, final String paramValue) {
        return paramName + " = " + logValue(paramValue);
    }

    private static String logValue(final String paramValue) {
        return "'" + sanitize(paramValue) + "'";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorListWsDTO handleModelNotFoundException(final Exception ex) {
        LOG.info("Handling Exception for this request - " + ex.getClass().getSimpleName() + " - " + sanitize(ex.getMessage()));
        if (LOG.isDebugEnabled()) {
            LOG.debug(ex);
        }
        return handleErrorInternal(UnknownIdentifierException.class.getSimpleName(), ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(CaseCreationException.class)
    public CaseCreationErrorDTO handleCaseCreationError(final Exception ex) {
        final CaseCreationErrorDTO errorInfo = new CaseCreationErrorDTO();
        errorInfo.setInfo("Cant create case!");
        errorInfo.setMessage(ex.getMessage());
        return errorInfo;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(KitCreationException.class)
    public KitCreationErrorDTO handleKitCreationError(final Exception ex) {
        final KitCreationErrorDTO errorInfo = new KitCreationErrorDTO();
        errorInfo.setInfo("Cant create kit!");
        errorInfo.setMessage(ex.getMessage());
        return errorInfo;
    }

    private ErrorListWsDTO handleErrorInternal(final String type, final String message) {
        final ErrorListWsDTO errorListDto = new ErrorListWsDTO();
        final ErrorWsDTO error = new ErrorWsDTO();
        error.setType(type.replace("Exception", "Error"));
        error.setMessage(sanitize(message));
        errorListDto.setErrors(Lists.newArrayList(error));
        return errorListDto;
    }

    protected void validate(final Object object, final String objectName, final Validator validator) {
        final Errors errors = new BeanPropertyBindingResult(object, objectName);
        validator.validate(object, errors);
        if (errors.hasErrors()) {
            throw new WebserviceValidationException(errors);
        }
    }


    protected void setTotalCountHeader(final HttpServletResponse response, final PaginationWsDTO paginationDto) {
        if (paginationDto != null && paginationDto.getTotalResults() != null) {
            response.setHeader(HEADER_TOTAL_COUNT, String.valueOf(paginationDto.getTotalResults()));
        }
    }

    protected void setTotalCountHeader(final HttpServletResponse response, final PaginationData paginationDto) {
        if (paginationDto != null) {
            response.setHeader(HEADER_TOTAL_COUNT, String.valueOf(paginationDto.getTotalNumberOfResults()));
        }
    }

    private static String sanitize(final String input) {
        return YSanitizer.sanitize(input);
    }

    protected DataMapper getDataMapper() {
        return dataMapper;
    }

    protected void setDataMapper(final DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }
}
