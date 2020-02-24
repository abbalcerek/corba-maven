package pl.abalcerek.server;

import EchoApp.Echo;
import EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Server {

    public static void main(String[] args) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant
            EchoServer server = new EchoServer();

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(server);
            Echo href = EchoHelper.narrow(ref);

            // get nameserver
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // register object in nameserver
            NameComponent[] path = ncRef.to_name("ECHO-SERVER");
            ncRef.rebind(path, href);

            System.out.println("pl.abalcerek.server.Server ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        } catch (ServantNotActive | WrongPolicy | InvalidName | org.omg.CORBA.ORBPackage.InvalidName |
                CannotProceed | NotFound | AdapterInactive servantNotActive) {
            servantNotActive.printStackTrace();
        }

        System.out.println("Exiting ...");

    }
}
