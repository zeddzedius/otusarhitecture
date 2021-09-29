package org.zedd.otus.homework.impl.commands;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Rotable;
import org.zedd.otus.homework.api.command.Command;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;

import javax.annotation.Nonnull;
import java.util.Objects;

import static org.zedd.otus.homework.Config.PARAMETER_NAME_ANGULARVELOCITY;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_DIRECTION;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_MAXDIRECTION;

@AllArgsConstructor
public class RotateCommand implements Command
{
	@Nonnull
	private final Rotable rotable;

	@Override
	public void execute()
	{
		validateRotatable();
		rotable.setDirection((rotable.getDirection() + rotable.getAngularVelocity())%rotable.getMaxDirection());
	}

	private void validateRotatable()
	{
		if (Objects.isNull(rotable.getDirection()))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_DIRECTION);
		}
		if (Objects.isNull(rotable.getAngularVelocity()))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_ANGULARVELOCITY);
		}
		if (Objects.isNull(rotable.getMaxDirection()))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_MAXDIRECTION);
		}
	}
}
