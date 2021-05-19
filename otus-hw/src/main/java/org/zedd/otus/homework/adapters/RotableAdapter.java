package org.zedd.otus.homework.adapters;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Rotable;
import org.zedd.otus.homework.exceptions.RotateDataCannotGiveException;
import org.zedd.otus.homework.model.BusinessObject;

@AllArgsConstructor
public class RotableAdapter implements Rotable
{
	private static final String PARAMETER_NAME_DIRECTION = "Direction";
	private static final String PARAMETER_NAME_ANGULARVELOCITY = "AngularVelocity";
	private static final String PARAMETER_NAME_MAXDIRECTION = "MaxDirection";

	private final BusinessObject object;

	@Override
	public Integer getDirection()
	{
		if (!(object.getParameter(PARAMETER_NAME_DIRECTION) instanceof Integer param))
		{
			throw new RotateDataCannotGiveException();
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
			throw new RotateDataCannotGiveException();
		}
		return param;
	}

	@Override
	public Integer getMaxDirection()
	{
		if (!(object.getParameter(PARAMETER_NAME_MAXDIRECTION) instanceof Integer param))
		{
			throw new RotateDataCannotGiveException();
		}
		return param;
	}
}
