package org.zedd.otus.homework.impl.command;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.action.Movable;
import org.zedd.otus.homework.api.command.Command;
import org.zedd.otus.homework.exception.PositionCannotGiveException;
import org.zedd.otus.homework.exception.VelocityCannotGiveException;

import javax.annotation.Nonnull;

@AllArgsConstructor
public class MoveCommand implements Command
{
	@Nonnull
	private final Movable movable;

	@Override
	public void execute()
	{
		validateMovable();
		movable.getPosition().plus(movable.getVelocity());
	}

	private void validateMovable()
	{
		if (movable.getPosition() == null)
		{
			throw new PositionCannotGiveException();
		}
		if (movable.getVelocity() == null)
		{
			throw new VelocityCannotGiveException();
		}
	}

}
