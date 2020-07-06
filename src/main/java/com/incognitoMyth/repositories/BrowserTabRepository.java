package com.incognitoMyth.repositories;

import com.incognitoMyth.model.entites.BrowserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrowserTabRepository extends JpaRepository<BrowserTabEntity,String> {
    List<BrowserTabEntity> getBrowserTabEntitiesByFingerprintEquals(String fingerPrint);
    void deleteBrowserTabEntityByFingerprintEquals(String fingerprint);

}
