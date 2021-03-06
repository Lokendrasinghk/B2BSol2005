/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.democustomerticketingc4cb2bintegration.converters.populators;


import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingc4cintegration.SitePropsHolder;
import de.hybris.platform.customerticketingc4cintegration.constants.Customerticketingc4cintegrationConstants;
import de.hybris.platform.customerticketingc4cintegration.data.Note;
import de.hybris.platform.customerticketingfacades.data.StatusData;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.democustomerticketingc4cb2bintegration.data.Result;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 *
 * Populator for C4C new requirements
 *
 */
public class NewC4C2YTicketPopulator<SOURCE extends Result, TARGET extends TicketData> implements Populator<SOURCE, TARGET>
{
	private final static Logger LOGGER = Logger.getLogger(NewC4C2YTicketPopulator.class);

	@Resource
	private SitePropsHolder sitePropsHolder;
	@Resource
	private Map<StatusData, List<StatusData>> validTransitions;
	@Resource
	private Map<String, StatusData> statusMapping;

	@Resource
	private CustomerFacade customerFacade;

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@Override
	public void populate(final Result source, final TicketData target) throws ConversionException
	{
		target.setSubject(source.getName().getContent());

		target.setId(source.getID());
		target.setObjectID(source.getObjectID());
		target.setAssignedToName(source.getAssignedToName().getContent());
		target.setServiceTechnicianTeam(source.getServiceTechnicianTeam());
		target.setServicePriorityCode(source.getServicePriorityCodeText());
		target.setCompletionDueDate(parseDate(source.getCompletionDueDate()));

		target.setCreationDate(parseDate(source.getCreationDateTime()));
		target.setLastModificationDate(parseDate(source.getLastChangeDateTime()));

		/*
		 * if (sitePropsHolder.isB2C()) { target.setCustomerId(source.getExternalCustomerID()); } else {
		 * target.setCustomerId(source.getExternalContactID()); }
		 * 
		 * if (CollectionUtils.isNotEmpty(source.getRelatedTransactions())) {
		 * target.setCartId(source.getRelatedTransactions().get(0).getID()); }
		 * 
		 * final List<TicketEventData> ticketEvents = new ArrayList<TicketEventData>(); if
		 * (CollectionUtils.isNotEmpty(source.getNotes())) { final DateFormat formatter = new
		 * SimpleDateFormat("dd-MM-yy HH:mm"); final List<TicketEventData> collected = source .getNotes() .stream()
		 * .filter(n -> n.getParentObjectID() != null) .sorted(new NotesComparator()) .map(note -> { final TicketEventData
		 * ticketEventData = new TicketEventData(); formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		 * ticketEventData.setStartDateTime(parseDate(note.getCreatedOn())); ticketEventData.setText(note.getText());
		 * ticketEventData.setAuthor(getCreatedBy(note));
		 * 
		 * final StringBuilder textBuilder = new StringBuilder(); textBuilder.append(getCreatedBy(note));
		 * textBuilder.append(" ").append(Localization.getLocalizedString("text.supporttickets.history.on")).append(" ")
		 * .append(note.getCreatedOn()).append("\n").append(note.getText());
		 * ticketEventData.setDisplayText(textBuilder.toString());
		 * 
		 * ticketEvents.add(ticketEventData);
		 * 
		 * return ticketEventData; }).collect(Collectors.toList()); target.setTicketEvents(collected); }
		 * 
		 */ target.setStatus(statusMapping.get(source.getServiceRequestUserLifeCycleStatusCode()));
		final List<StatusData> transitionStatuuses = validTransitions.get(target.getStatus());
		target.setAvailableStatusTransitions(transitionStatuuses == null ? Collections.EMPTY_LIST : transitionStatuuses);
		populateProduct(target, source);
	}

	/**
	 *
	 */
	private void populateProduct(final TicketData target, final Result source)
	{
		final String productCode = source.getProductID();
		final List<ProductOption> extraOptions = Arrays.asList(ProductOption.VARIANT_MATRIX_BASE, ProductOption.VARIANT_MATRIX_URL,
				ProductOption.VARIANT_MATRIX_MEDIA);
		try
		{
			if (StringUtils.isNotEmpty(productCode))
			{
				target.setProductId(productCode);
				final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, extraOptions);
				target.setProduct(productData);
			}
		}
		catch (final Exception e)
		{
			String paddedString = productCode;
			while (paddedString.length() < 18)
			{
				paddedString = "0" + paddedString;
			}
			final ProductData productData = productFacade.getProductForCodeAndOptions(paddedString, extraOptions);
			target.setProductId(paddedString);
			target.setProduct(productData);
			LOGGER.warn(" ProductId: " + productCode + "does not exist. Exception details: " + e.getMessage());
			//Ignore the exception
		}
	}

	public Date parseDate(final String date)
	{
		return date != null && date.contains("(") && date.contains(")")
				? new Date(Long.valueOf(date.substring(date.indexOf("(") + 1, date.indexOf(")"))).longValue())
				: null;
	}

	protected String getCreatedBy(final Note note)
	{
		return Config.getParameter("customerticketingc4cintegration.displayname").equalsIgnoreCase(note.getCreatedBy())
				? (customerFacade.getCurrentCustomer().getFirstName() + " " + customerFacade.getCurrentCustomer().getLastName())
				: Customerticketingc4cintegrationConstants.SUPPORT_TICKET_AGENT_NAME;
	}

}