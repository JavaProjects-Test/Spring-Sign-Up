package com.signup.service;

import com.signup.dao.ISignUpDao;
import com.signup.vo.User;

public class SignUpServiceImpl implements ISignUpService {
	ISignUpDao signUpDao;

	public boolean addNewUser(User object) {

		return signUpDao.addNewUser(object);
	}

	public void setSignUpDao(ISignUpDao signUpDao) {
		this.signUpDao = signUpDao;
	}

	public boolean updateUser(User objectUpdateUser) {

		return signUpDao.updateUser(objectUpdateUser);
	}

	public User checkValidation(User objectCheckValidation) {

		return signUpDao.checkValidation(objectCheckValidation);
	}

}
