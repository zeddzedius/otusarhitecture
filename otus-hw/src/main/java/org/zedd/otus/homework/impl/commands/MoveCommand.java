package org.zedd.otus.homework.impl.commands;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Movable;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;

@AllArgsConstructor
public class MoveCommand implements Command
{
	@Nonnull
	private final Movable movable;

	@Override
	public void execute()
	{
		movable.setPosition(movable.getPosition().plus(movable.getVelocity()));
	}
}
