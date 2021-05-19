package org.zedd.otus.homework.adapters;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Movable;
import org.zedd.otus.homework.exceptions.PositionCannotGiveException;
import org.zedd.otus.homework.exceptions.VelocityCannotGiveException;
import org.zedd.otus.homework.model.BusinessObject;
import org.zedd.otus.homework.model.ObjectVector;

@AllArgsConstructor
public class MovableAdapter implements Movable
{
	private static final String PARAMETER_NAME_POSITION = "Position";
	private static final String PARAMETER_NAME_VELOCITY = "Velocity";

	private final BusinessObject object;

	@Override
	public ObjectVector getPosition()
	{
		if (!(object.getParameter(PARAMETER_NAME_POSITION) instanceof ObjectVector param))
		{
			throw new PositionCannotGiveException();
		}
		return param;
	}

	@Override
	public ObjectVector getVelocity()
	{
		if (!(object.getParameter(PARAMETER_NAME_VELOCITY) instanceof ObjectVector param))
		{
			throw new VelocityCannotGiveException();
		}
		return param;
	}
}
