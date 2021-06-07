package org.zedd.otus.homework.impl;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
@ToString
@Slf4j
public class CommandWorker implements Runnable
{
	private final CommandQueue commandQueue;
	private boolean softStopStarted = false;

	@Override
	public void run()
	{
		log.info("+++++>>> thread begin <<<<<++++");
		boolean exit = false;
		try
		{
			do
			{
				final Command command = commandQueue.loadCommand();
				exit = command == null && softStopStarted || ((command == null)
					? awaitQueue()
					: commandExecute(command));
			}
			while (!exit);
			log.info("+++++>>> thread end <<<<<++++");
		}
		catch (InterruptedException e)
		{
			log.error("interrupt thread command worker", e);
		}
	}

	private boolean awaitQueue()
	{
		commandQueue.getLocker().lock();
		try
		{
			log.info("Queue is empty, thread await.");
			commandQueue.emptyAwait();
			log.info("Signal all , thread continue.");

			return false;
		}
		catch (final InterruptedException e)
		{
			log.error("thread interrupted", e);
			return true;
		}
		finally
		{
			commandQueue.getLocker().unlock();
		}
	}

	private boolean commandExecute(@Nonnull final Command command)
	{
		try
		{
			log.info("command execute");
			command.execute();
		}
		catch (final HardStopCommandWorkerException e)
		{
			log.info("hard stop ", e);
			return true;
		}
		catch (final SoftStopCommandWorkerException e)
		{
			log.info("soft stop signal.", e);
			softStopStarted = true;
		}
		catch (final Exception e)
		{
			log.error("Error of execute command", e);
		}
		return false;
	}


}
