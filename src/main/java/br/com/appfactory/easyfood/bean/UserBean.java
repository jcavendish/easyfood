package br.com.appfactory.easyfood.bean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.appfactory.easyfood.converter.UserModelToUserConverter;
import br.com.appfactory.easyfood.dao.UserDao;
import br.com.appfactory.easyfood.entity.User;
import br.com.appfactory.easyfood.exception.EasyFoodException;
import br.com.appfactory.easyfood.model.UserModel;

@Stateless
@WebService
public class UserBean {

	@Inject
	private UserDao userDao;

	@Inject
	UserModelToUserConverter converter;

	@WebMethod
	@WebResult(name = "User")
	public User create(@WebParam(name = "login") String login, @WebParam(name = "password") String pass)
			throws EasyFoodException {
		UserModel userModel = new UserModel(login, pass);

		validateModel(userModel);
		if (userDao.findByLogin(login).isPresent()) {
			throw new EasyFoodException("User already registered. Please try another user name.");
		}

		User user = converter.create(userModel);
		userDao.save(user);
		return user;
	}

	@WebMethod
	@WebResult(name = "User")
	public User consult(@WebParam(name = "id") Long id) {
		return userDao.findById(id);
	}

	@WebMethod
	@WebResult(name = "UserList")
	public List<User> listAllUsers() {
		return userDao.findAll();
	}

	private void validateModel(Object model) {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Object>> errors = validator.validate(model);
		if (!errors.isEmpty()) {
			throw new IllegalArgumentException(
					errors.stream().map(o -> o.getMessage()).collect(Collectors.joining(", ")));

		}
	}
}
