package org.zedd.otus.homework.impl;

import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CommandQueue
{
	private final Queue<Command> queue;
	private final ReentrantLock locker;  // блокировка
	private final Condition condition;  // условие блокировки

	{
		queue = new ConcurrentLinkedQueue<Command>();
		locker = new ReentrantLock();
		condition = locker.newCondition();
	}

	public boolean addCommand(@Nonnull final Command command)
	{
		final boolean result = queue.add(command);
		if (result)
		{
			locker.lock();
			try
			{
				condition.signalAll();
			}
			finally
			{
				locker.unlock();
			}
		}
		return result;
	}

	/**
	 * получение верзнего элемента из очереди нотификаций
	 * @return команда для выполнения
	 */
	public Command loadCommand()
	{
		return queue.poll();
	}

	public void emptyAwait() throws InterruptedException
	{
		condition.await();
	}

	public ReentrantLock getLocker()
	{
		return locker;
	}
}
