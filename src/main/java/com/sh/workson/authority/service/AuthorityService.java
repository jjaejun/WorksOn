package com.sh.workson.authority.service;

import com.sh.workson.authority.entity.Authority;
import com.sh.workson.authority.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
