package net.bogdoll.swing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listener 
{
	public enum Type { Unspecified, Action, PropertyChange };
	String value();
	Type type() default Type.Unspecified;
}
