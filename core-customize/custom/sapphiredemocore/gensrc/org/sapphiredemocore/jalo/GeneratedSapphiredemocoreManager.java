/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 10, 2020, 8:02:51 AM                    ---
 * ----------------------------------------------------------------
 */
package org.sapphiredemocore.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.sapphiredemocore.constants.SapphiredemocoreConstants;
import org.sappiredemocore.jalo.BookAnAppointment;
import sapphire.hcl.com.jalo.actions.OrderCancellableAction;

/**
 * Generated class for type <code>SapphiredemocoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedSapphiredemocoreManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("parentEquipment", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrderEntry", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public BookAnAppointment createBookAnAppointment(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( SapphiredemocoreConstants.TC.BOOKANAPPOINTMENT );
			return (BookAnAppointment)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating BookAnAppointment : "+e.getMessage(), 0 );
		}
	}
	
	public BookAnAppointment createBookAnAppointment(final Map attributeValues)
	{
		return createBookAnAppointment( getSession().getSessionContext(), attributeValues );
	}
	
	public OrderCancellableAction createOrderCancellableAction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( SapphiredemocoreConstants.TC.ORDERCANCELLABLEACTION );
			return (OrderCancellableAction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating OrderCancellableAction : "+e.getMessage(), 0 );
		}
	}
	
	public OrderCancellableAction createOrderCancellableAction(final Map attributeValues)
	{
		return createOrderCancellableAction( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return SapphiredemocoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.parentEquipment</code> attribute.
	 * @return the parentEquipment
	 */
	public String getParentEquipment(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, SapphiredemocoreConstants.Attributes.AbstractOrderEntry.PARENTEQUIPMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.parentEquipment</code> attribute.
	 * @return the parentEquipment
	 */
	public String getParentEquipment(final AbstractOrderEntry item)
	{
		return getParentEquipment( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.parentEquipment</code> attribute. 
	 * @param value the parentEquipment
	 */
	public void setParentEquipment(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, SapphiredemocoreConstants.Attributes.AbstractOrderEntry.PARENTEQUIPMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.parentEquipment</code> attribute. 
	 * @param value the parentEquipment
	 */
	public void setParentEquipment(final AbstractOrderEntry item, final String value)
	{
		setParentEquipment( getSession().getSessionContext(), item, value );
	}
	
}
