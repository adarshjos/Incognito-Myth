package com.incognitoMyth.service;

import com.incognitoMyth.controller.IncognitoController;
import com.incognitoMyth.model.DTO.BrowserTabDTO;
import com.incognitoMyth.model.entites.BrowserTabEntity;
import com.incognitoMyth.repositories.BrowserTabRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class IncognitoService {
    private static final Logger log=Logger.getLogger(IncognitoService.class.getName());
    private final BrowserTabRepository browserTabRepository;
    @Autowired
    public IncognitoService(BrowserTabRepository browserTabRepository) {
        this.browserTabRepository = browserTabRepository;
    }


    public BrowserTabDTO bootStrap(JSONObject dataObj) throws Exception {
        if(!dataObj.has("fingerprint")){
            throw new Exception("JSON key missing");
        }
        BrowserTabDTO browserTabDTO=new BrowserTabDTO();
        String fp=dataObj.getString("fingerprint");
        try{
            List<BrowserTabEntity> browserTabEntities=browserTabRepository.getBrowserTabEntitiesByFingerprintEquals(fp);
            if(!browserTabEntities.isEmpty()){
                List<String>ret=new LinkedList<>();
                browserTabEntities.stream().map(BrowserTabEntity::getName).forEach(ret::add);
                browserTabDTO.setQueries(ret);
                browserTabDTO.setStatus("FOUND");
            }else{
                browserTabDTO.setStatus("NOT FOUND");
            }
            }catch(Exception e){
                browserTabDTO.setStatus("NOT FOUND");
                browserTabDTO.setFingerprint(fp);
                log.log(Level.SEVERE,"Exception while DB fetching::");
            }
        return browserTabDTO;
    }


    @Transactional
    public void populateDB(JSONObject dbObj) throws Exception {
        if(dbObj.has("fingerprint")&&dbObj.has("name")){
            BrowserTabEntity browserTabEntity=new BrowserTabEntity(dbObj.getString("fingerprint"),dbObj.getString("name"));
            try{
                browserTabRepository.save(browserTabEntity);
            }catch(Exception e){
                log.log(Level.SEVERE,"Exception Message ::"+e);
            }
        }
    }

    @Transactional
    public void deleteEntry(JSONObject dbObj) {
        if(dbObj.has("fingerprint")){
            try{
                browserTabRepository.deleteBrowserTabEntityByFingerprintEquals(dbObj.getString("fingerprint"));
            }catch(Exception e){
                log.log(Level.SEVERE,"Exception Message ::"+e);
            }
        }else{
            log.log(Level.SEVERE,":::FingerPrint missing:::");
        }
    }
}
