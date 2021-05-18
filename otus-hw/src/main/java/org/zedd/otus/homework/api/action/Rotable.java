package org.zedd.otus.homework.api.action;

public interface Rotable
{
	Integer getDirection();
	void setDirection(Integer direction);
	Integer getAngularVelocity();
	Integer getMaxDirection();
}
