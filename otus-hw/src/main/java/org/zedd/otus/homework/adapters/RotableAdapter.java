package org.zedd.otus.homework.adapters;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Rotable;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;
import org.zedd.otus.homework.model.BusinessObject;

import static org.zedd.otus.homework.Config.PARAMETER_NAME_ANGULARVELOCITY;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_DIRECTION;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_MAXDIRECTION;

@AllArgsConstructor
public class RotableAdapter implements Rotable
{
	private final BusinessObject object;

	@Override
	public Integer getDirection()
	{
		if (!(object.getParameter(PARAMETER_NAME_DIRECTION) instanceof Integer param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_DIRECTION);
		}
		return param;
	}

	@Override
	public void setDirection(final Integer direction)
	{
		object.setParameter(PARAMETER_NAME_DIRECTION, direction);
	}

	@Override
	public Integer getAngularVelocity()
	{
		if (!(object.getParameter(PARAMETER_NAME_ANGULARVELOCITY) instanceof Integer param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_ANGULARVELOCITY);
		}
		return param;
	}

	@Override
	public Integer getMaxDirection()
	{
		if (!(object.getParameter(PARAMETER_NAME_MAXDIRECTION) instanceof Integer param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_MAXDIRECTION);
		}
		return param;
	}
}
