package org.zedd.otus.homework.impl.commands;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.actions.Movable;
import org.zedd.otus.homework.exceptions.PositionCannotGiveException;
import org.zedd.otus.homework.exceptions.VelocityCannotGiveException;
import org.zedd.otus.homework.model.ObjectVector;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

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
		Mockito.when(movable.getPosition()).thenReturn(positionActual);
		Mockito.when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
		assertEquals(positionActual, positionExcepted, "Текущее значение позиции не соответствует ожидаемому");
	}

	@Test(expectedExceptions = PositionCannotGiveException.class)
	public void testExecuteUndefinedPosition()
	{
		Mockito.when(movable.getPosition()).thenReturn(null);
		Mockito.when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
	}

	@Test(expectedExceptions = VelocityCannotGiveException.class)
	public void testExecuteUndefinedVelocity()
	{
		Mockito.when(movable.getPosition()).thenReturn(positionActual);
		Mockito.when(movable.getVelocity()).thenReturn(null);
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
		Mockito.doThrow(new RuntimeException(POSITION_CANNOT_MOVE)).when(positionActualSpy).plus(Mockito.any(ObjectVector.class));
		Mockito.when(movable.getPosition()).thenReturn(positionActualSpy);
		Mockito.when(movable.getVelocity()).thenReturn(velocity);
		final var command = new MoveCommand(movable);
		command.execute();
	}
}