package org.zedd.otus.homework.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
public class CommandWorkerTest
{

	public static final long MILLIS = 10000L;
	private ReentrantLock locker;
	private Condition condition;


	@Getter
	@Setter
	private class TestDataModel
	{
		private List<Command> commands;
		private int expectedPassedCount;
	}

	@BeforeTest
	private void initEveryoneTest()
	{
		locker = new ReentrantLock();
		condition = locker.newCondition();
	}

	/**
	 * подготовка данных
	 * три набора
	 *  1 - с ошибкой, и ожиданием при пустой очереди
	 *  2 - soft stop потока
	 *  3 - hard sop потока
	 * @return
	 */
	@DataProvider(name = "commandQueueTestData")
	public Object[][] provideDataException()
	{
		TestDataModel testDataMModel1 = new TestDataModel();
		testDataMModel1.setCommands(generateCommand(new RuntimeException()));
		testDataMModel1.setExpectedPassedCount(7);

		TestDataModel testDataMModel2 = new TestDataModel();
		testDataMModel2.setCommands(generateCommand(new SoftStopCommandWorkerException()));
		testDataMModel2.setExpectedPassedCount(7);

		TestDataModel testDataMModel3 = new TestDataModel();
		testDataMModel3.setCommands(generateCommand(new HardStopCommandWorkerException()));
		testDataMModel3.setExpectedPassedCount(4);

		return new Object[][]
			{
				{ testDataMModel1 },
				{ testDataMModel2 },
				{ testDataMModel3 }
			};
	}

	@Nonnull
	private List<Command> generateCommand(@Nonnull final RuntimeException ex)
	{
		List<Command> list = new ArrayList<>();
		list.add(mock(Command.class)); //-- 1
		list.add(mock(Command.class)); //-- 2
		list.add(mock(Command.class)); //-- 3
		Command cmd = mock(Command.class);
		doThrow(ex).when(cmd).execute();
		list.add(cmd); //-- 4
		list.add(mock(Command.class)); //-- 5
		list.add(mock(Command.class)); //-- 6
		Command cmd2 = mock(Command.class);
		doAnswer(invocation -> {condition.signalAll();return null;}).when(cmd2).execute();
		list.add(mock(Command.class)); //-- 7
		return list;
	}

	@Test(dataProvider = "commandQueueTestData")
	public void workerTest (@Nonnull final TestDataModel commandModels)
	{
		log.info("====>>>> begin test <<<<======");
		final CommandQueue queue = new CommandQueue();
		CommandWorker worker = new CommandWorker(queue);
		Thread workerThread = new Thread(worker);
		workerThread.start();

		for (Command command: commandModels.commands)
		{
			queue.addCommand(command);
		}

		try
		{
			//-- TODO: пока незнаю как проверить ожидание потока
			//-- , то что он замёрз для ожидания следующих команд
			workerThread.join(MILLIS);
			if (workerThread.isAlive())
			{
				workerThread.interrupt();
			}
		}
		catch (final InterruptedException e)
		{
			log.error("error join", e);
		}
		int i = 0;
		for(Command command : commandModels.commands)
		{
			log.info("verify {} length {} {}", i, commandModels.commands.size(), commandModels.getExpectedPassedCount());
			if (i < commandModels.getExpectedPassedCount())
			{
				verify(command, times(1)).execute();
			}
			else
			{
				verify(command, never()).execute();
			}
			i++;
		}
		log.info("====>>>> end test <<<<======");
	}
}