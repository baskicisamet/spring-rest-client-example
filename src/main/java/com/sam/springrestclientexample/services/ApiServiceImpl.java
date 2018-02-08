package com.sam.springrestclientexample.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sam.api.domain.User;
import com.sam.api.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {
	
	private RestTemplate RestTemplate;
		

	public ApiServiceImpl(org.springframework.web.client.RestTemplate restTemplate) {
		super();
		RestTemplate = restTemplate;
	}

	
	

	@Override
	public List<User> getUsers(Integer limit) {
		
		UserData userData = RestTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);
		return userData.getData();
	}

}
