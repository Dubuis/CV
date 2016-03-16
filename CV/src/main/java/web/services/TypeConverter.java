package web.services;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import business.model.Activity.ActivityType;

/**
 * @author DUBUIS Michael
 *
 */
@ManagedBean(name="typeConverter")
public class TypeConverter implements Converter {

	/**
	 * Constructor
	 */
	public TypeConverter() {
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		for(ActivityType type : ActivityType.values()) {
			if(type.toString().equals(value)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((ActivityType) value).toString();
	}

}
