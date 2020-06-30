//package com.incognitoMyth;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Component
//public class BootStrap {
//    private static Logger logger = Logger.getLogger(BootStrap.class.getName());
//    private final SocketIOServer socketIOServer;
//
//    @Autowired
//    public BootStrap(SocketIOServer socketIOServer) {
//        this.socketIOServer = socketIOServer;
//    }
//
//    @PostConstruct
//    public void startSocketServer(){
//        logger.log(Level.SEVERE,"Server Started:::"+System.currentTimeMillis());
//        socketIOServer.start();
//    }
//
//    @PreDestroy
//    public void stopSocketServer(){
//        logger.log(Level.SEVERE,"Server Stopped:::"+System.currentTimeMillis());
//        socketIOServer.stop();
//    }
//}
