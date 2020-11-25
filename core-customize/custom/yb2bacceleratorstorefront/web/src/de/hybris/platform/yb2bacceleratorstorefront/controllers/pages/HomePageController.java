/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.yb2bacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.yb2bacceleratorstorefront.checkout.steps.validation.impl.BookAnAppointmentForm;

import java.util.List;

import org.sappiredemocore.model.BookAnAppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController
{
	private static final String LOGOUT = "logout";
	private static final String ACCOUNT_CONFIRMATION_SIGNOUT_TITLE = "account.confirmation.signout.title";
	private static final String ACCOUNT_CONFIRMATION_CLOSE_TITLE = "account.confirmation.close.title";
	private static final String SHOW_BEST_DEALS = "bestDealspage";
	private static final String BOOK_APPOINTMENT_PAGE = "bookAppointmentPage";


	@Autowired
	ModelService modelService;

	@Autowired
	FlexibleSearchService flexibleSearchService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = WebConstants.CLOSE_ACCOUNT, defaultValue = "false") final boolean closeAcc,
			@RequestParam(value = LOGOUT, defaultValue = "false") final boolean logout, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (logout)
		{
			String message = ACCOUNT_CONFIRMATION_SIGNOUT_TITLE;
			if (closeAcc)
			{
				message = ACCOUNT_CONFIRMATION_CLOSE_TITLE;
			}
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, message);
			return REDIRECT_PREFIX + ROOT;
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(null));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
		updatePageTitle(model, getContentPageForLabelOrId(null));

		return getViewForPage(model);
	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}
	
	
	@RequestMapping(value = "/showBestDeals", method = RequestMethod.GET)
	public String showBestDeals(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(SHOW_BEST_DEALS));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SHOW_BEST_DEALS));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/showBookAnAppointmentPage", method = RequestMethod.GET)
	public String openBookAnAppointmentPage(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("bookAnAppointmentForm", new BookAnAppointmentForm());
		showBookedAppointment(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(BOOK_APPOINTMENT_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(BOOK_APPOINTMENT_PAGE));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/bookAppointment", method = RequestMethod.POST)
	public String saveBookAppointment(final Model model, final BookAnAppointmentForm bookAnAppointmentForm)
			throws CMSItemNotFoundException
	{

		final BookAnAppointmentModel bookAnAppointmentModel = new BookAnAppointmentModel();
		bookAnAppointmentModel.setModelNumber(bookAnAppointmentForm.getModelNumber());
		bookAnAppointmentModel.setTypeOfService(bookAnAppointmentForm.getTypeOfService());
		bookAnAppointmentModel.setTime(bookAnAppointmentForm.getTime());
		bookAnAppointmentModel.setCustomerDetails(bookAnAppointmentForm.getCustomerDetails());
		bookAnAppointmentModel.setOrderNo(bookAnAppointmentForm.getOrderNo());
		modelService.save(bookAnAppointmentModel);
		System.out.println("Appointment is booked  Thanks!! ");
		showBookedAppointment(model);
		model.addAttribute("bookAnAppointmentForm", new BookAnAppointmentForm());
		storeCmsPageInModel(model, getContentPageForLabelOrId(BOOK_APPOINTMENT_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(BOOK_APPOINTMENT_PAGE));
		return getViewForPage(model);
	}


	public void showBookedAppointment(final Model model)
	{
		final String queryStr = "select {pk} from {BookAnAppointment} order by {creationtime} desc";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(queryStr);
		final SearchResult<BookAnAppointmentModel> result = flexibleSearchService.search(fsq);
		final List<BookAnAppointmentModel> bookAnAppointmentModelList = result.getResult();
		model.addAttribute("bookAnAppointmentModelList", bookAnAppointmentModelList);
	}
}
