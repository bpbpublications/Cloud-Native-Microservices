package com.fsm.servicediscovery;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {

	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		return builder.routes().route("broadcastId", r -> r.path("/broadcast/**").uri("lb://enquiry-broadcast"))
				.build();
	}

}
