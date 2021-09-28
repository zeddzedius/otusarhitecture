package org.zedd.otus.homework.impl.commands;

import org.zedd.otus.homework.api.command.Command;
import org.zedd.otus.homework.impl.HardStopCommandWorkerException;

public class HardStopCommand implements Command
{
	@Override
	public void execute()
	{
		throw new HardStopCommandWorkerException();
	}
}
