package org.zedd.otus.homework.impl.commands;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.actions.Rotable;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_ANGULARVELOCITY;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_DIRECTION;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_MAXDIRECTION;

/**
 * команда вращения
 */
public class RotateCommandTest
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
		final var command = new RotateCommand(rotable);
		command.execute();
		verify(rotable, atLeastOnce()).setDirection(ArgumentMatchers.eq(estimatedDirection));
	}

	@Test
		(
			expectedExceptions = ObjectParameterCannotGiveException.class
			, expectedExceptionsMessageRegExp =
			".*("+PARAMETER_NAME_DIRECTION+"|"
				+PARAMETER_NAME_ANGULARVELOCITY+"|"
				+PARAMETER_NAME_MAXDIRECTION+").*"
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
		final var command = new RotateCommand(rotable);
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
		final var command = new RotateCommand(rotable);
		command.execute();
	}

}