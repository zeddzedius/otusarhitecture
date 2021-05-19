package org.zedd.otus.homework.model;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class BusinessObject
{
	private final ConcurrentHashMap<String, Object> parameters;

	public Object getParameter(final String name)
	{
		return parameters.get(name);
	}

	public void setParameter(final String name, final Object value)
	{
		parameters.put(name, value);
	}
}
