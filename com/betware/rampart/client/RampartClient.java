package com.betware.rampart.client;

import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;

public class RampartClient {

    //url of the signed service
    private static final String soapEndpoint= "https://catalonia.betware.com/api/lacaixabank/services/LaCaixaBank";
    private static final String clientRepo = "client-repo";
    private static final String rampartConfigPath = clientRepo + "/modules/rampart-config.xml";
    private static final String message = "Client request from Rampart to be signed";

    public static void main(String[] args) throws Exception {

        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(clientRepo);
        RampartStub stub = new RampartStub(ctx, soapEndpoint);

        ServiceClient serviceClient = stub._getServiceClient();

        StAXOMBuilder builder = new StAXOMBuilder(rampartConfigPath);
        Policy rampartConfig = PolicyEngine.getPolicy(builder.getDocumentElement());

        stub._getServiceClient().getAxisService().getPolicyInclude().addPolicyElement(PolicyInclude.AXIS_SERVICE_POLICY, rampartConfig);
        serviceClient.engageModule("rampart");

        RampartStub.Echo echo = new RampartStub.Echo();
        echo.setArgs0(message);
        RampartStub.EchoResponse response = stub.echo(echo);

        System.out.println("Response from SOAP server:" + response.get_return());

    }

}
