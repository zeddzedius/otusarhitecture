package org.zedd.otus.homework.adapters;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Flueble;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;
import org.zedd.otus.homework.model.BusinessObject;

import static org.zedd.otus.homework.Config.PARAMETER_NAME_BURN_VELOCITY;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_FLUE;

@AllArgsConstructor
public class FluebleAdapter implements Flueble
{
	private final BusinessObject object;

	@Override
	public Integer getFlue()
	{
		if (!(object.getParameter(PARAMETER_NAME_FLUE) instanceof Integer param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_FLUE);
		}
		return param;
	}

	@Override
	public void setFlue(final Integer flue)
	{
		object.setParameter(PARAMETER_NAME_FLUE, flue);
	}

	@Override
	public Integer getBurnVelocity()
	{
		if (!(object.getParameter(PARAMETER_NAME_FLUE) instanceof Integer param))
		{
			throw new ObjectParameterCannotGiveException(PARAMETER_NAME_BURN_VELOCITY);
		}
		return param;
	}
}
