package org.zedd.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zedd.otus.homework.exceptions.ObjectVectorDimensionsException;

import java.util.List;
import javax.annotation.Nonnull;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ObjectVector
{
	@Nonnull
	private final List<Integer> body;

	@Nonnull
	public Integer get(final int i)
	{
		return body.get(i);
	}

	@Nonnull
	public ObjectVector plus(@Nonnull final ObjectVector vector)
	{
		validateDimensions(vector);
		for(int i=0; i < body.size() ; i++)
		{
			body.set(i, body.get(i) + vector.get(i));
		}
		return this;
	}

	private void validateDimensions(@Nonnull final ObjectVector vector)
	{
		if (vector.body.size() != this.body.size())
		{
			throw new ObjectVectorDimensionsException();
		}
	}
}
