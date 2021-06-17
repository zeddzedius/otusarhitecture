package org.zedd.otus.homework.api.actions;

import org.zedd.otus.homework.model.ObjectVector;

public interface Movable
{
	ObjectVector getPosition();

	void setPosition(ObjectVector position);

	ObjectVector getVelocity();

}
