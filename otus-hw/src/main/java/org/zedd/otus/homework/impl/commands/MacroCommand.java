package org.zedd.otus.homework.impl.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class MacroCommand implements Command
{

	@Nonnull
	private final List<Command> commands;

	private int current = 0;

	@Override
	public void execute() throws CommandException
	{
		RuntimeException ex = null;
		try
		{
			for (int i = current; i < commands.size(); i++)
			{
				current = i;
				commands.get(i).execute();
			}
			current = 0;
		}
		catch (final RuntimeException e)
		{
			log.error(e.getMessage(),e);
			ex = e;
		}
		if (ex != null)
		{
			throw new CommandException(ex.getMessage());
		}
	}
}
