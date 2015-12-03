#How to sign and verify SOAP messages using Apache Axis2 and Rampart with policy based configuration  

You should have a solid understanding of the principles of public-key cryptography, public key infrastructure (PKI), and digital signature (read [this] (http://blogs.msdn.com/b/plankytronixx/archive/2010/10/23/crypto-primer-understanding-encryption-public-private-key-signatures-and-certificates.aspx), or [this](http://wso2.com/library/1891/)). 

##How to generate key pair and certificates
To get an official digital certificate signed by a recognized certificate authority (CA), you need to generate a public-private key pair and use the public key to create a certificate request. You then send that certificate request to your preferred authority and pay for it. The authority in turn verifies your identity and issues the certificate with its signature.
For testing or internal use, you can instead generate your own self-signed certificates. In this example client code such self-signed certificate is used, one for the client and one for the server. To generate a key pair (private + public key),  you can use Java's keytool program. You can find a detailed tutorial on creating CA, server and client keypairs [here](http://wso2.com/library/174/).  

Certificates are not used in Java code directly; instead, they are imported into a keystore. A single keystore can contain multiple certificates and private keys, distinguished by an alias. The client.jks keystore used in this example contains the client's private key and certificate, along with the server certificate (which must be stored on the client so that it can be used to verify the signed message coming from the service).  
You can inspect the content of the keystore by using Java's keytool:  
```
keytool -list -v -keystore path/to/client.jks -storepass clientPW
```

##Rampart
[Rampart](http://axis.apache.org/axis2/java/rampart/) is deployed as Axis2 module. To use it in the SOAP client, you need archive rampart.mar and the appropriate libraries (see [here](http://axis.apache.org/axis2/java/rampart/download.html)). Rampart libraries (rampart-core, rampart-policy, rampart-trust, and rampart) must be copied to the same lib folder where the other axis2 libraries are located.  

Next, [Rampart security policy] (https://github.com/probablyirina/soapclient/blob/master/client-repo/modules/rampart-config.xml) is defined according to WS-Security Policy Language and based on signed SOAP service policy. This policy states security requirements of Web services in a standard, interoperable manner, and has two main parts. Asymmetric binding defines what keys will be used, and a few additional properties such as which algorithms to be used in cryptographic operations, layout of the security header, etc. Signed parts assertion defines what parts of the message should be signed (here we will be signing the SOAP body of the message). You can find detailed tutorial on WS security policy [here](http://wso2.com/library/3132/).  

When a security policy is applied to a Web service, the WSDL will be annotated with that particular security policy so the client can secure the SOAP messages according to the policy defined in the WSDL. Code generators that generate stubs to access the Web service can make use of these security polices defined in the WSDL.  

You can find a detailed tutorial on using Rampart to create digitally signed service/client [here](http://wso2.com/library/3415/).

