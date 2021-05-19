package org.zedd.otus.homework.api.actions;

public interface Rotable
{
	Integer getDirection();
	void setDirection(Integer direction);
	Integer getAngularVelocity();
	Integer getMaxDirection();
}
