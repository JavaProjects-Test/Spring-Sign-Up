package com.signup.dao;

import com.signup.vo.User;

public interface ISignUpDao {

	public boolean addNewUser(User object);

	public boolean updateUser(User objectUpdateUser);

	public User checkValidation(User objectCheckValidation);
}
