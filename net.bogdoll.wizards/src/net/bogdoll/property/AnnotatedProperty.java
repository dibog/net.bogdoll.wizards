package net.bogdoll.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.EnumSet;

import net.bogdoll.swing.Listener;
import net.bogdoll.swing.Listener.Type;

public class AnnotatedProperty<T> implements PropertyChangeListener
{
	private static final EnumSet<Type> PROPERTY_CHANGE = EnumSet.of(Type.Unspecified, Type.PropertyChange);

	private final Object mInstance;
	private final Method mMethod;
	private boolean mWithArgs;
	private Property<T> mProperty;
	
	public static <T> AnnotatedProperty<T> connect(Object aInstance, Property<T> aProperty, String aId) {
		AnnotatedProperty<T> ap = new AnnotatedProperty<T>(aInstance, aProperty, aId);
		ap.pulse();
		return ap;
	}
	
	public AnnotatedProperty(Object aInstance, Property<T> aProperty, String aId) {
		mInstance = aInstance;
		mProperty = aProperty;

		Method method = null;
		boolean withArgs = false;
		for(Method m : aInstance.getClass().getDeclaredMethods()) {
			Listener propMethod = m.getAnnotation(Listener.class);
			if(propMethod!=null && PROPERTY_CHANGE.contains(propMethod.type())) {
				if(propMethod.value().equals(aId)) {
					Class<?>[] params = m.getParameterTypes();
					if(params.length==0) {
						method = m;
						withArgs = false;
						break;
					}
					else if (params.length==2) {
						method = m;
						withArgs = true;
						break;
					}
				}
			}
		}
		if(method!=null) {
			method.setAccessible(true);
			mMethod = method;
			mWithArgs = withArgs;
			aProperty.addPropertyChangeListener(this);
		}
		else {
			throw new IllegalArgumentException("Can't find any method with annotation for property '"+aId+"' in instance '"+mInstance+"'");
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			if(mWithArgs) {
				mMethod.invoke(mInstance, evt.getOldValue(), evt.getNewValue());
			}
			else {
				mMethod.invoke(mInstance);
			}
		} catch (Exception ex) {
		}
	}

	public void disconnect() {
		mProperty.removePropertyChangeListener(this);
	}

	public void pulse() {
		propertyChange(new PropertyChangeEvent(mProperty,  "value", null, mProperty.get()));
	}
}
