package org.zedd.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zedd.otus.homework.exceptions.ObjectVectorDimensionsException;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

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
		List<Integer> pl = new ArrayList<>();
		for(int i=0; i < body.size() ; i++)
		{
			pl.add (i, body.get(i) + vector.get(i));
		}
		return new ObjectVector(pl);
	}

	private void validateDimensions(@Nonnull final ObjectVector vector)
	{
		if (vector.body.size() != this.body.size())
		{
			throw new ObjectVectorDimensionsException();
		}
	}
}
