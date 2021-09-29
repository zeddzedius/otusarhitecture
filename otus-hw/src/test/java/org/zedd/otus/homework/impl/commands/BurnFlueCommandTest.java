package org.zedd.otus.homework.impl.commands;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.actions.Flueble;

import javax.annotation.Nonnull;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class BurnFlueCommandTest
{
	@Nonnull
	private Flueble flue;

	@DataProvider(name = "burnTestData")
	public Object[][] provideData()
	{
		return new Object[][]
			{
				{ 5, 1, 4},
				{ 6, 3, 3},
				{ 9, 2, 7}
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
			, final Integer estimatedFlue
		)
	{
		Mockito.when(flue.getFlue()).thenReturn(startFlue);
		Mockito.when(flue.getBurnVelocity()).thenReturn(burnVelocity);
		final var command = new BurnFlueCommand(flue);
		command.execute();
		verify(flue, atLeastOnce()).setFlue(ArgumentMatchers.eq(estimatedFlue));
	}

}