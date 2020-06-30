package com.incognitoMyth.repositories;

import com.incognitoMyth.model.entites.BrowserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowserTabRepository extends JpaRepository<BrowserTabEntity,String> {

}
