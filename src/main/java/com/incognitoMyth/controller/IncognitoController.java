package com.incognitoMyth.controller;


import com.incognitoMyth.model.DTO.BrowserTabDTO;
import com.incognitoMyth.model.entites.BrowserTabEntity;
import com.incognitoMyth.repositories.BrowserTabRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IncognitoController {
    private static final Logger log=Logger.getLogger(IncognitoController.class.getName());

    private final BrowserTabRepository browserTabRepository;

    @Autowired
    public IncognitoController(BrowserTabRepository browserTabRepository) {
        this.browserTabRepository = browserTabRepository;
    }

    @SubscribeMapping("/fp/initFP/{fingerPrint}")
    public BrowserTabDTO initFP(@DestinationVariable("fingerPrint") String transferData) throws Exception {
        log.log(Level.INFO,"FingerPrint Received':::"+transferData.toString());
        BrowserTabDTO browserTabDTO=new BrowserTabDTO();
        JSONObject dataObj=new JSONObject(transferData);
        if(!dataObj.has("fingerprint")){
            throw new Exception("JSON key missing");
        }
        String fp=dataObj.getString("fingerprint");
        try{
            boolean fpExists=browserTabRepository.existsById(fp);
            if(fpExists){
                BrowserTabEntity browserTabEntity=browserTabRepository.findById(fp).get();
                browserTabDTO.setFingerprint(browserTabEntity.getFingerprint());
                browserTabDTO.setName(browserTabEntity.getName());
                browserTabDTO.setStatus("FOUND");
            }else{
                browserTabDTO.setStatus("NOT FOUND");
                browserTabDTO.setFingerprint(fp);
            }

        }catch(Exception e){
            browserTabDTO.setStatus("NOT FOUND");
            browserTabDTO.setFingerprint(fp);
            log.log(Level.SEVERE,"Exception while DB fetching::");
        }
        return browserTabDTO;
    }

    @Transactional
    @MessageMapping("/insertFP")
    //@SendTo("/topic/greetings")
    public void dbEntry(String data) throws Exception {
        log.log(Level.SEVERE,"Message:::"+data);
        JSONObject dbObj=new JSONObject(data);
        if(dbObj.has("fingerprint")&&dbObj.has("name")){
            BrowserTabEntity browserTabEntity=new BrowserTabEntity();
            browserTabEntity.setName(dbObj.getString("name"));
            browserTabEntity.setFingerprint(dbObj.getString("fingerprint"));
            try{
                browserTabRepository.save(browserTabEntity);
            }catch(Exception e){
                log.log(Level.SEVERE,"Exception Message ::"+e);
            }

        }
    }
    @Transactional
    @MessageMapping("/forgetFP")
    public void forgetFP(String data) throws Exception {
        log.log(Level.INFO,"Forgetting FP:::"+data);
        JSONObject dbObj=new JSONObject(data);
        if(dbObj.has("fingerprint")){
            try{
                browserTabRepository.deleteById(dbObj.getString("fingerprint"));
            }catch(Exception e){
                log.log(Level.SEVERE,"Exception Message ::"+e);
            }
        }
    }
}
