package com.gzmpc.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public abstract class AbstractUserDetailsService implements UserDetailsService {
	
	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
