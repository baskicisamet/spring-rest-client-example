package com.sam.springrestclientexample.services;

import java.util.List;

import com.sam.api.domain.User;

public interface ApiService {
	
	List<User> getUsers(Integer limit);

}
