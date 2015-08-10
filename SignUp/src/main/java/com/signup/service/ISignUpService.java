package com.signup.service;

import com.signup.vo.User;

public interface ISignUpService {

	public boolean addNewUser(User object);

	public boolean updateUser(User objectUpdateUser);

	public User checkValidation(User objectCheckValidation);

}
