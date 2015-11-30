package com.icoderman.app.controller;

import com.icoderman.app.model.Shout;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MarcoController {

  @MessageMapping("/marco")
  public Shout handleShout(Shout incoming) {
    System.out.println("Received message: " + incoming.getMessage());

    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    
    Shout outgoing = new Shout();
    outgoing.setMessage("Polo!");
    
    return outgoing;
  }

}
