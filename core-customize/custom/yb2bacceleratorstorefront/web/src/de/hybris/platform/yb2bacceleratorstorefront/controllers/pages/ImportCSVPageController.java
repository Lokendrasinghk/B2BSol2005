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

import de.hybris.platform.acceleratorfacades.cartfileupload.impl.DefaultSavedCartFileUploadFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ImportCSVSavedCartFormValidator;
import de.hybris.platform.b2bcommercefacades.company.B2BCostCenterFacade;
import de.hybris.platform.b2bcommercefacades.company.data.B2BCostCenterData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CommerceSaveCartParameterData;
import de.hybris.platform.commerceservices.order.CommerceSaveCartException;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.yb2bacceleratorstorefront.controllers.ControllerConstants;
import de.hybris.platform.yb2bacceleratorstorefront.forms.misc.BulkOrderFormValidator;
import de.hybris.platform.yb2bacceleratorstorefront.forms.misc.ImportCSVSavedCartForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sapphire.hcl.com.bulkorderupload.facade.impl.DefaultBulkOrderFileUploadFacade;
import sapphire.hcl.com.order.facade.impl.DefaultBulkOrderCheckoutFacade;



/**
 * Controller for importing CSV file(s)
 */
@Controller
@RequestMapping("/import/csv")
public class ImportCSVPageController extends AbstractPageController
{
	private static final String SAVED_CART_PATH_SEGMENT = "/saved-cart";
	private static final String BULK_UPLOAD_PATH_SEGMENT = "/saved-cart/bulk";
	private static final String IMPORT_CSV_FILE_MAX_SIZE_BYTES_KEY = "import.csv.file.max.size.bytes";
	private static final String IMPORT_CSV_SAVED_CART_CMS_PAGE = "importCSVSavedCartPage";
	private static final String IMPORT_CSV_BULK_ORDER_CMS_PAGE = "importCSVBulkOrderPage";




	private static final Logger LOG = Logger.getLogger(ImportCSVPageController.class);

	@Resource(name = "simpleBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

	@Resource(name = "importCSVSavedCartFormValidator")
	private ImportCSVSavedCartFormValidator importCSVSavedCartFormValidator;

	@Resource(name = "bulkOrderFormValidator")
	private BulkOrderFormValidator bulkOrderFormValidator;

	@Resource(name = "savedCartFileUploadFacade")
	private DefaultSavedCartFileUploadFacade savedCartFileUploadFacade;

	@Resource(name = "bulkOrderFileUploadFacade")
	private DefaultBulkOrderFileUploadFacade bulkOrderFileUploadFacade;

	@Resource(name = "bulkOrderCheckoutFacade")
	private DefaultBulkOrderCheckoutFacade bulkOrderCheckoutFacade;

	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	@Resource(name = "costCenterFacade")
	private B2BCostCenterFacade costCenterFacade;

	@ModelAttribute("costCenters")
	public List<? extends B2BCostCenterData> getVisibleActiveCostCenters()
	{
		final List<? extends B2BCostCenterData> costCenterData = costCenterFacade.getActiveCostCenters();
		return costCenterData == null ? Collections.<B2BCostCenterData> emptyList() : costCenterData;
	}

	@RequestMapping(value = SAVED_CART_PATH_SEGMENT, method = RequestMethod.GET)
	@RequireHardLogIn
	public String savedCartImport(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("csvFileMaxSize", getSiteConfigService().getLong(IMPORT_CSV_FILE_MAX_SIZE_BYTES_KEY, 0));
		//final CartData cartData = getCheckoutFacade().getCheckoutCart();
		//model.addAttribute("cartData", cartData);
		model.addAttribute("importCSVSavedCartForm", preparePaymentTypeForm());
		//prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(IMPORT_CSV_SAVED_CART_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(IMPORT_CSV_SAVED_CART_CMS_PAGE));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs("import.csv.savedCart.title"));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

		return ControllerConstants.Views.Pages.CSV.ImportCSVSavedCartPage;
	}

	protected ImportCSVSavedCartForm preparePaymentTypeForm()
	{
		final ImportCSVSavedCartForm importCSVSavedCartForm = new ImportCSVSavedCartForm();

		// set cost center
		if (!CollectionUtils.isEmpty(getVisibleActiveCostCenters()) && getVisibleActiveCostCenters().size() == 1)
		{
			importCSVSavedCartForm.setCostCenterId(getVisibleActiveCostCenters().get(0).getCode());
		}

		// set purchase order number
		importCSVSavedCartForm.setPurchaseOrderNumber(null);
		importCSVSavedCartForm.setOrderId(null);
		return importCSVSavedCartForm;
	}

	@ResponseBody
	@RequestMapping(value = SAVED_CART_PATH_SEGMENT, method = RequestMethod.POST)
	@RequireHardLogIn
	public ResponseEntity<String> handleBulkOrderImport(
			@ModelAttribute("importCSVSavedCartForm") final ImportCSVSavedCartForm importCSVSavedCartForm,
			final BindingResult bindingResult) throws IOException, InvalidCartException, CommerceSaveCartException
	{
		bulkOrderFormValidator.validate(importCSVSavedCartForm, bindingResult);
		if (bindingResult.hasErrors())
		{
			final String errorMessage = getMessageSource().getMessage(bindingResult.getAllErrors().get(0).getCode(), null,
					getI18nService().getCurrentLocale());
			return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		else
		{
			try (final InputStream inputStream = importCSVSavedCartForm.getCsvFile().getInputStream())
			{
				//System.out.println("Current time is -------------" + System.currentTimeMillis());
				final CartModel uploadedCart = bulkOrderFileUploadFacade.createBulkCartFromFileUpload(inputStream,
						importCSVSavedCartForm.getCsvFile().getOriginalFilename(),
						importCSVSavedCartForm.getCsvFile().getContentType());
				//System.out.println("Value of cartcode is -------------" + uploadedCart.getCode());

				/*
				 * System.out.println("Current status is -------------" + uploadedCart.getImportStatus()); final CartModel
				 * cartModel = cartFileUploadProcessModel.getSavedCart();
				 *
				 * do { try { Thread.sleep(1000); } catch (final InterruptedException e) { LOG.error("Sleep Interuppted",
				 * e); } } while (uploadedCart.getImportStatus() != ImportStatus.COMPLETED);
				 * System.out.println("Current time is -------------" + System.currentTimeMillis());
				 */
				//System.out.println("Value of cost center is -------------" + importCSVSavedCartForm.getCostCenterId());
				//uploadedCart.setDeliveryAddress(uploadedCart.getUser().getDefaultShipmentAddress());
				//uploadedCart.setDeliveryMode(uploadedCart.);

				//uploadedCart.setPaymentAddress(uploadedCart.getUser().getDefaultPaymentAddress());

				final CommerceSaveCartParameterData commerceSaveCartParameterData = new CommerceSaveCartParameterData();
				commerceSaveCartParameterData.setCartId(uploadedCart.getCode());
				commerceSaveCartParameterData.setEnableHooks(true);
				try
				{
					saveCartFacade.restoreSavedCart(commerceSaveCartParameterData);
				}
				catch (final CommerceSaveCartException e)
				{
					throw new CommerceSaveCartException(e.getMessage(), e);
				}

				try
				{
					final OrderModel orderData = bulkOrderCheckoutFacade.placeOrder(uploadedCart,
							importCSVSavedCartForm.getCostCenterId(), importCSVSavedCartForm.getPurchaseOrderNumber());
					System.out.println("Value of order id is -------------" + orderData.getCode());
				}
				catch (final InvalidCartException ex)
				{
					LOG.error("Error while creating order for cartId " + ex);
				}

				return new ResponseEntity<String>(HttpStatus.OK);
			}
			catch (final IOException e)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug(e.getMessage(), e);
				}

				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	}

}
