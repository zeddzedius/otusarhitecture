package org.zedd.otus.homework.impl.command;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.action.Rotable;
import org.zedd.otus.homework.exception.RotateDataCannotGiveException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * команда вращения
 */
public class RotateCommendTest
{
	private static final String POSITION_CANNOT_MOVE = "Position move exception";

	private Rotable rotable;


	@DataProvider(name = "rotateTestData")
	public Object[][] provideData()
	{
		return new Object[][]
			{
				{ 1, 3 , 7, 4 },
				{ 6, 3, 7 , 2 },
				{ 0, 4, 4, 0 }
			};
	}

	@DataProvider(name = "rotateTestException")
	public Object[][] provideDataException()
	{
		return new Object[][]
			{
				{ null, 3 , 7 },
				{ 6, null, 7 },
				{ 0, 4, null }
			};
	}

	@BeforeTest
	private void initEveryoneTest()
	{
		rotable = Mockito.mock(Rotable.class);
	}

	@Test(dataProvider = "rotateTestData")
	public void testExecute
		(
			final Integer direction
			, final Integer velocity
			, final Integer maxVelocity
			, final Integer estimatedDirection
		)
	{
		Mockito.when(rotable.getDirection()).thenReturn(direction);
		Mockito.when(rotable.getAngularVelocity()).thenReturn(velocity);
		Mockito.when(rotable.getMaxDirection()).thenReturn(maxVelocity);
		final var command = new RotateCommend(rotable);
		command.execute();
		verify(rotable, atLeastOnce()).setDirection(ArgumentMatchers.eq(estimatedDirection));
	}

	@Test
		(
			expectedExceptions = RotateDataCannotGiveException.class
			, dataProvider = "rotateTestException"
		)
	public void testExecuteUndefinedDirection
		(
			final Integer direction
			, final Integer velocity
			, final Integer maxVelocity
		)
	{
		Mockito.when(rotable.getDirection()).thenReturn(direction);
		Mockito.when(rotable.getAngularVelocity()).thenReturn(velocity);
		Mockito.when(rotable.getMaxDirection()).thenReturn(maxVelocity);
		final var command = new RotateCommend(rotable);
		command.execute();
	}

	@Test(
		expectedExceptions = RuntimeException.class
		, expectedExceptionsMessageRegExp = POSITION_CANNOT_MOVE
	)
	public void testExecuteCannotMove()
	{
		Mockito.doThrow(new RuntimeException(POSITION_CANNOT_MOVE)).when(rotable).setDirection(Mockito.any(Integer.class));
		Mockito.when(rotable.getDirection()).thenReturn(1);
		Mockito.when(rotable.getAngularVelocity()).thenReturn(1);
		Mockito.when(rotable.getMaxDirection()).thenReturn(2);
		final var command = new RotateCommend(rotable);
		command.execute();
	}

}