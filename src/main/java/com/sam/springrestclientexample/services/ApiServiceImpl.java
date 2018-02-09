package com.sam.springrestclientexample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.sam.api.domain.User;
import com.sam.api.domain.UserData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApiServiceImpl implements ApiService {
	
	private RestTemplate restTemplate;
		
	private final String api_url;

	public ApiServiceImpl(org.springframework.web.client.RestTemplate restTemplate, @Value("${api.url}") String api_url) {
		this.restTemplate = restTemplate;
		this.api_url = api_url;
		
	}

	
	

	@Override
	public List<User> getUsers(Integer limit) {
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(api_url)
				.queryParam("limit", limit);
		
		
		
		UserData userData = restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);
		return userData.getData();
	}




	@Override
	public Flux<User> getUsers(Mono<Integer> limit) {
		
		return WebClient
				.create(api_url)
				.get()
				.uri(uRIBuilder -> uRIBuilder.queryParam("limit", limit.block()).build())
				.exchange()
				.flatMap(resp -> resp.bodyToMono(UserData.class))
				.flatMapIterable(UserData::getData);
	}

}
