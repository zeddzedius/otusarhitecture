package org.zedd.otus.homework.exceptions;

import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;

@Getter
@ToString
public class ObjectParameterCannotGiveException extends RuntimeException
{
	private final String parameterName;

	public ObjectParameterCannotGiveException(@Nonnull final String parameterName)
	{
		super("Error give parameter:" + parameterName);
		this.parameterName = parameterName;
	}
}
