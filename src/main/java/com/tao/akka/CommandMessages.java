package com.tao.akka;

import java.io.Serializable;

//#messages
public interface CommandMessages {

  public static class Command implements Serializable {
    private final String content;

    public Command(String text) {
      this.content = text;
    }

    public String getContent() {
      return content;
    }

    @Override
    public String toString() {
      return "Command(" + content + ")";
    }
  }



  public static final String WORKER_REGISTRATION = "WorkerRegistration";

}
//#messages