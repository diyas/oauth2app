package com.auth.app.repository;

import com.auth.app.domain.OAuth2CountAccess;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OAuth2CountAccessRepository extends PagingAndSortingRepository<OAuth2CountAccess, String> {

    OAuth2CountAccess findByClientId(String clientId);
}
