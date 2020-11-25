/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 10, 2020, 8:02:51 AM                    ---
 * ----------------------------------------------------------------
 */
package org.sappiredemocore.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.sapphiredemocore.constants.SapphiredemocoreConstants;

/**
 * Generated class for type {@link org.sappiredemocore.jalo.BookAnAppointment BookAnAppointment}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedBookAnAppointment extends GenericItem
{
	/** Qualifier of the <code>BookAnAppointment.modelNumber</code> attribute **/
	public static final String MODELNUMBER = "modelNumber";
	/** Qualifier of the <code>BookAnAppointment.typeOfService</code> attribute **/
	public static final String TYPEOFSERVICE = "typeOfService";
	/** Qualifier of the <code>BookAnAppointment.time</code> attribute **/
	public static final String TIME = "time";
	/** Qualifier of the <code>BookAnAppointment.customerDetails</code> attribute **/
	public static final String CUSTOMERDETAILS = "customerDetails";
	/** Qualifier of the <code>BookAnAppointment.orderNo</code> attribute **/
	public static final String ORDERNO = "orderNo";
	/** Qualifier of the <code>BookAnAppointment.customerComments</code> attribute **/
	public static final String CUSTOMERCOMMENTS = "customerComments";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MODELNUMBER, AttributeMode.INITIAL);
		tmp.put(TYPEOFSERVICE, AttributeMode.INITIAL);
		tmp.put(TIME, AttributeMode.INITIAL);
		tmp.put(CUSTOMERDETAILS, AttributeMode.INITIAL);
		tmp.put(ORDERNO, AttributeMode.INITIAL);
		tmp.put(CUSTOMERCOMMENTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.customerComments</code> attribute.
	 * @return the customerComments - Comments of Customer
	 */
	public String getCustomerComments(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERCOMMENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.customerComments</code> attribute.
	 * @return the customerComments - Comments of Customer
	 */
	public String getCustomerComments()
	{
		return getCustomerComments( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.customerComments</code> attribute. 
	 * @param value the customerComments - Comments of Customer
	 */
	public void setCustomerComments(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERCOMMENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.customerComments</code> attribute. 
	 * @param value the customerComments - Comments of Customer
	 */
	public void setCustomerComments(final String value)
	{
		setCustomerComments( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.customerDetails</code> attribute.
	 * @return the customerDetails - Customer details
	 */
	public String getCustomerDetails(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERDETAILS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.customerDetails</code> attribute.
	 * @return the customerDetails - Customer details
	 */
	public String getCustomerDetails()
	{
		return getCustomerDetails( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.customerDetails</code> attribute. 
	 * @param value the customerDetails - Customer details
	 */
	public void setCustomerDetails(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERDETAILS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.customerDetails</code> attribute. 
	 * @param value the customerDetails - Customer details
	 */
	public void setCustomerDetails(final String value)
	{
		setCustomerDetails( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.modelNumber</code> attribute.
	 * @return the modelNumber - Model Number
	 */
	public String getModelNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MODELNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.modelNumber</code> attribute.
	 * @return the modelNumber - Model Number
	 */
	public String getModelNumber()
	{
		return getModelNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.modelNumber</code> attribute. 
	 * @param value the modelNumber - Model Number
	 */
	public void setModelNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MODELNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.modelNumber</code> attribute. 
	 * @param value the modelNumber - Model Number
	 */
	public void setModelNumber(final String value)
	{
		setModelNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.orderNo</code> attribute.
	 * @return the orderNo - Type of service required
	 */
	public String getOrderNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERNO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.orderNo</code> attribute.
	 * @return the orderNo - Type of service required
	 */
	public String getOrderNo()
	{
		return getOrderNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.orderNo</code> attribute. 
	 * @param value the orderNo - Type of service required
	 */
	public void setOrderNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERNO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.orderNo</code> attribute. 
	 * @param value the orderNo - Type of service required
	 */
	public void setOrderNo(final String value)
	{
		setOrderNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.time</code> attribute.
	 * @return the time
	 */
	public String getTime(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.time</code> attribute.
	 * @return the time
	 */
	public String getTime()
	{
		return getTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.time</code> attribute. 
	 * @param value the time
	 */
	public void setTime(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.time</code> attribute. 
	 * @param value the time
	 */
	public void setTime(final String value)
	{
		setTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.typeOfService</code> attribute.
	 * @return the typeOfService - Type of service required
	 */
	public String getTypeOfService(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TYPEOFSERVICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BookAnAppointment.typeOfService</code> attribute.
	 * @return the typeOfService - Type of service required
	 */
	public String getTypeOfService()
	{
		return getTypeOfService( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.typeOfService</code> attribute. 
	 * @param value the typeOfService - Type of service required
	 */
	public void setTypeOfService(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TYPEOFSERVICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BookAnAppointment.typeOfService</code> attribute. 
	 * @param value the typeOfService - Type of service required
	 */
	public void setTypeOfService(final String value)
	{
		setTypeOfService( getSession().getSessionContext(), value );
	}
	
}
