package org.zedd.otus.homework.impl.commands;

import java.io.Serial;

public class CommandException extends RuntimeException
{
	public CommandException(final String  message)
	{
		super(message);
	}
	@Serial
	private static final long serialVersionUID = -2101303083515542377L;
}
