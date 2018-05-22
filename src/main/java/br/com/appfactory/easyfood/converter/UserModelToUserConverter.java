package br.com.appfactory.easyfood.converter;

import javax.ejb.Stateless;

import br.com.appfactory.easyfood.entity.User;
import br.com.appfactory.easyfood.model.UserModel;

@Stateless
public class UserModelToUserConverter extends AbstractConverter<UserModel, User>{

	@Override
	public User update(UserModel from, User to) {
		
		to.setLogin(from.getLogin());
		to.setPassword(from.getPass());
		
		return to;
	}

	@Override
	public User createEntity() {
		return new User();
	}

}
