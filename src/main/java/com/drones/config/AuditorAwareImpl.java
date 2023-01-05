package com.drones.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// will pass static user instead of using spring security
		return Optional.of("test user");
	}

}
