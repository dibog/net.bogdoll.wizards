package net.bogdoll.swing;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.EnumSet;

import javax.swing.AbstractAction;

import net.bogdoll.swing.Listener.Type;

public class AnnotatedAction extends AbstractAction 
{
	private static final long serialVersionUID = 1L;
	
	private static final EnumSet<Type> ACTION = EnumSet.of(Type.Unspecified, Type.Action);
	
	private final Object mInstance;
	private final Method mMethod;
	private final boolean mWithArg;
	
	public AnnotatedAction(Object aInstance, String aCommand) {
		mInstance = aInstance;
		Method method = null;
		boolean withArg = false;
		for(Method m : aInstance.getClass().getDeclaredMethods()) {
			Listener action = m.getAnnotation(Listener.class);
			if(action!=null && ACTION.contains(action.type())) {
				if(action.value().equals(aCommand)) {
					Class<?>[] params = m.getParameterTypes();
					if(params.length==0) {
						method = m;
						withArg = false;
						break;
					}
					else if (params.length==1 && ActionEvent.class.isAssignableFrom(params[0])) {
						method = m;
						withArg = true;
						break;
					}
				}
			}
		}
		if(method!=null) {
			method.setAccessible(true);
			mMethod = method;
			mWithArg = withArg;
		}
		else {
			throw new IllegalArgumentException("Can't find any method with annotation for action command '"+aCommand+"'");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(mWithArg) {
				mMethod.invoke(mInstance, e);
			}
			else {
				mMethod.invoke(mInstance);
			}
		} catch (Exception ex) {
		}
	}
}
