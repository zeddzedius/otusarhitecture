package org.zedd.otus.homework.impl;

import lombok.extern.slf4j.Slf4j;
import org.zedd.otus.homework.api.command.Command;

import javax.annotation.Nonnull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CommandQueue
{
	private final BlockingQueue<Command> queue;
	private final ReentrantLock locker;  // блокировка
	private final Condition condition;  // условие блокировки

	{
//		queue = new ConcurrentLinkedQueue<Command>();
		queue = new LinkedBlockingDeque<Command>();
		locker = new ReentrantLock();
		condition = locker.newCondition();
	}

	public void addCommand(@Nonnull final Command command) throws InterruptedException
	{
			queue.put(command);
//		final boolean result = queue.add(command);
//		if (result)
//		{
//			locker.lock();
//			try
//			{
//				condition.signalAll();
//			}
//			finally
//			{
//				locker.unlock();
//			}
//		}
//		return result;
	}

	/**
	 * получение верзнего элемента из очереди нотификаций
	 * @return команда для выполнения
	 */
	public Command loadCommand() throws InterruptedException
	{
//		return queue.poll();
			return queue.take();
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
