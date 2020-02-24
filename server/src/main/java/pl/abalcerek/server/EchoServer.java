package pl.abalcerek.server;

import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public String echoString() {

        String message = "Hello World!!!!!!!";
        System.out.println("Returning message to the abalcerek.client: " + message);

        return message;
    }
}
