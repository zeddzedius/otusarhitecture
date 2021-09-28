package org.zedd.otus.homework.adapters;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Movable;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;
import org.zedd.otus.homework.model.BusinessObject;
import org.zedd.otus.homework.model.ObjectVector;

import static org.zedd.otus.homework.Config.PARAMETER_NAME_POSITION;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_VELOCITY;

@AllArgsConstructor
public class MovableAdapter implements Movable
{
	private final BusinessObject object;

	@Override
	public ObjectVector getPosition()
	{
		if ( object.getParameter(PARAMETER_NAME_POSITION) == null
			|| !(object.getParameter(PARAMETER_NAME_POSITION) instanceof ObjectVector param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_POSITION);
		}
		return param;
	}

	@Override
	public void setPosition(ObjectVector position)
	{
		object.setParameter(PARAMETER_NAME_POSITION, position);
	}

	@Override
	public ObjectVector getVelocity()
	{
		if ( object.getParameter(PARAMETER_NAME_VELOCITY) == null
			|| !(object.getParameter(PARAMETER_NAME_VELOCITY) instanceof ObjectVector param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_VELOCITY);
		}
		return param;
	}
}
