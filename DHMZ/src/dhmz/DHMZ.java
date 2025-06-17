package dhmz;

import dhmz.service.WeatherServiceImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class DHMZ {
    public static void main(String[] args) {
        try {
            System.out.println("Pokrecem servis...");
            WebServer server = new WebServer(7070);
            
            XmlRpcServer xmlServer = server.getXmlRpcServer();
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("weather", WeatherServiceImpl.class); 
            xmlServer.setHandlerMapping(phm);
            
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlServer.getConfig();
            serverConfig.setEnabledForExtensions(true);
            serverConfig.setContentLengthOptional(false);
            
            server.start();
            System.out.println("Uspjesno pokrenut servis.");
            System.out.println("Cekam zahtjeve...");
        } catch (XmlRpcException | IOException ex) {
            Logger.getLogger(DHMZ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
