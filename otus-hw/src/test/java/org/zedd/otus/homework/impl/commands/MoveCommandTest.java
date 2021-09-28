package org.zedd.otus.homework.impl.commands;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.actions.Movable;
import org.zedd.otus.homework.exceptions.ObjectParameterCannotGiveException;
import org.zedd.otus.homework.model.ObjectVector;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_POSITION;
import static org.zedd.otus.homework.Config.PARAMETER_NAME_VELOCITY;

/**
 * команда перемещения
 */
public class MoveCommandTest
{
	private static final String POSITION_CANNOT_MOVE = "Position move exception";

	private Movable movable;
	private ObjectVector positionActual;
	private ObjectVector positionExcepted;
	private ObjectVector velocity;


	@BeforeTest
	private void initEveryoneTest()
	{
		movable = Mockito.mock(Movable.class);
		positionActual = new ObjectVector(Arrays.asList(12, 5));
		positionExcepted = new ObjectVector(Arrays.asList(5,8));
		velocity = new ObjectVector(Arrays.asList(-7, 3));
	}

	@Test
	public void testExecuteMoveSuccess()
	{
		when(movable.getPosition()).thenReturn(positionActual);
		when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
		verify(movable, times(1)).setPosition(any(ObjectVector.class));
	}

	@Test(expectedExceptions = ObjectParameterCannotGiveException.class
	, expectedExceptionsMessageRegExp = ".*"+PARAMETER_NAME_POSITION+".*")
	public void testExecuteUndefinedPosition()
	{
		doThrow(new ObjectParameterCannotGiveException(PARAMETER_NAME_POSITION)).when(movable).getPosition();
		when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
	}

	@Test(expectedExceptions = ObjectParameterCannotGiveException.class
	, expectedExceptionsMessageRegExp = ".*"+PARAMETER_NAME_VELOCITY+".*")
	public void testExecuteUndefinedVelocity()
	{
		when(movable.getPosition()).thenReturn(positionActual);
		doThrow(new ObjectParameterCannotGiveException(PARAMETER_NAME_VELOCITY)).when(movable).getVelocity();
		final var command = new MoveCommand(movable);
		command.execute();
	}

	@Test(
		expectedExceptions = RuntimeException.class
		, expectedExceptionsMessageRegExp = POSITION_CANNOT_MOVE
	)
	public void testExecuteCannotMove()
	{
		final var positionActualSpy = Mockito.spy(positionActual);
		doThrow(new RuntimeException(POSITION_CANNOT_MOVE)).when(positionActualSpy).plus(Mockito.any(ObjectVector.class));
		when(movable.getPosition()).thenReturn(positionActualSpy);
		when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
	}
}