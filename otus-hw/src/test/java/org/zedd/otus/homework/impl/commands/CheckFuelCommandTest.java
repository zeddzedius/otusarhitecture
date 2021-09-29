package org.zedd.otus.homework.impl.commands;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.actions.Flueble;

import javax.annotation.Nonnull;

public class CheckFuelCommandTest
{
	@Nonnull
	private Flueble flue;

	@DataProvider(name = "burnTestData")
	public Object[][] provideData()
	{
		return new Object[][]
			{
				{ 5, 1},
				{ 6, 3},
				{ 9, 2}
			};
	}

	@BeforeTest
	private void initEveryoneTest()
	{
		flue = Mockito.mock(Flueble.class);
	}

	@Test(dataProvider = "burnTestData")
	public void testExecute
		(
			final Integer startFlue
			, final Integer burnVelocity
		)
	{
		Mockito.when(flue.getFlue()).thenReturn(startFlue);
		Mockito.when(flue.getBurnVelocity()).thenReturn(burnVelocity);
		final var command = new CheckFuelCommand(flue);
		command.execute();
	}

	@Test(expectedExceptions = {org.zedd.otus.homework.impl.commands.CommandException.class})
	public void testExecuteException() throws CommandException
	{
		Mockito.when(flue.getFlue()).thenReturn(1);
		Mockito.when(flue.getBurnVelocity()).thenReturn(2);
		final var command = new CheckFuelCommand(flue);
		command.execute();
//		throw new CommandException("test");
	}
}