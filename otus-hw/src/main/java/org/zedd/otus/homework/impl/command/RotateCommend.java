package org.zedd.otus.homework.impl.command;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Rotable;
import org.zedd.otus.homework.api.command.Command;
import org.zedd.otus.homework.exceptions.RotateDataCannotGiveException;

import javax.annotation.Nonnull;
import java.util.Objects;

@AllArgsConstructor
public class RotateCommend implements Command
{
	@Nonnull
	private final Rotable rotable;

	@Override
	public void execute()
	{
		validateRotable();
		rotable.setDirection((rotable.getDirection() + rotable.getAngularVelocity())%rotable.getMaxDirection());
	}

	private void validateRotable()
	{
		if (Objects.isNull(rotable.getDirection())
			|| Objects.isNull(rotable.getAngularVelocity())
			|| Objects.isNull(rotable.getMaxDirection())
		)
		{
			throw new RotateDataCannotGiveException();
		}
	}
}
