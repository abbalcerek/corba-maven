package pl.abalcerek.client;

import EchoApp.Echo;
import EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Client {

    public static void main(String[] args) {

        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            // lookup name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // 'cast' NameService to the concrete class
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // lookup echo service and "cast it" to concrete echo class
            Echo href = EchoHelper.narrow(ncRef.resolve_str("ECHO-SERVER"));
            // invoke method on remove object
            String hello = href.echoString();
            System.out.println(hello);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName
                | CannotProceed | InvalidName | NotFound e) {
            e.printStackTrace();
        }
    }

}
