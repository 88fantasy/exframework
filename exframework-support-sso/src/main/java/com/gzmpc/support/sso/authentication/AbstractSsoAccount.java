package com.gzmpc.support.sso.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public abstract class AbstractSsoAccount implements UserDetails {

	
	@Override
	public abstract String getUsername() ;

	@Override
	public abstract String getPassword() ;

	@Override
	public abstract boolean isEnabled() ;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");  
        auths.add(authority);  
        return auths;
	}

}
