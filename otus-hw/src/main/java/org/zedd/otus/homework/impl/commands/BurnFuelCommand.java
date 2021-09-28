package org.zedd.otus.homework.impl.commands;

import lombok.AllArgsConstructor;
import org.zedd.otus.homework.api.actions.Flueble;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;

@AllArgsConstructor
public class BurnFuelCommand implements Command
{
	@Nonnull
	private final Flueble flue;

	@Override
	public void execute()
	{
		final Integer flueCurrent = flue.getFlue();
		flue.setFlue(flue.getFlue()-flue.getBurnVelocity());
	}
}
