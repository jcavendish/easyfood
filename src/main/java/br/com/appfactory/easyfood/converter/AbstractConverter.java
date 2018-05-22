package br.com.appfactory.easyfood.converter;

public abstract class AbstractConverter<T, U> {

	public abstract U update(T from, U to);
	
	public abstract U createEntity();
	
	public U create(T from) {
		U to = createEntity();
		if (to == null) {
			return null;
		}
		return update(from, to);
	}
}
