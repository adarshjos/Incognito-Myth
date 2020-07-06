package com.incognitoMyth.controller;


import com.incognitoMyth.model.DTO.BrowserTabDTO;
import com.incognitoMyth.service.IncognitoService;
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


    private final IncognitoService incognitoService;
    @Autowired
    public IncognitoController(IncognitoService incognitoService) {
        this.incognitoService=incognitoService;
    }

    @SubscribeMapping("/fp/initFP/{fingerPrint}")
    public BrowserTabDTO initFP(@DestinationVariable("fingerPrint") String transferData) throws Exception {
        log.log(Level.INFO,"FingerPrint Received':::"+transferData.toString());
        JSONObject dataObj=new JSONObject(transferData);
        return incognitoService.bootStrap(dataObj);
    }


    @MessageMapping("/insertFP")
    public void dbEntry(String data) throws Exception {
        log.log(Level.SEVERE,"Message:::"+data);
        JSONObject dbObj=new JSONObject(data);
        incognitoService.populateDB(dbObj);
    }
    @Transactional
    @MessageMapping("/forgetFP")
    public void forgetFP(String data) throws Exception {
        log.log(Level.INFO,"Forgetting FP:::"+data);
        JSONObject dbObj=new JSONObject(data);
        incognitoService.deleteEntry(dbObj);
    }
}
