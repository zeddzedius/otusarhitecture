package org.zedd.otus.homework.impl.commands;

import org.zedd.otus.homework.api.command.Command;
import org.zedd.otus.homework.impl.SoftStopCommandWorkerException;

public class SoftStopCommand implements Command
{
	@Override
	public void execute()
	{
		throw new SoftStopCommandWorkerException();
	}
}
