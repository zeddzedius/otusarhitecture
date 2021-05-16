package org.zedd.otus.homework.impl.command;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.action.Rotable;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;

@AllArgsConstructor
public class RotateCommend implements Command
{
	@Nonnull
	private final Rotable rotable;

	@Override
	public void execute()
	{

	}
}
