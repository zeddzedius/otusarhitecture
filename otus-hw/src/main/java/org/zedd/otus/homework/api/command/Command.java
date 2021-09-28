package org.zedd.otus.homework.api.command;

import org.zedd.otus.homework.impl.commands.CommandException;

public interface Command
{
	void execute() throws CommandException;
}
