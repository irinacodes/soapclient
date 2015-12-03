This is an example of to digitally sign and verify SOAP messages using Apache Axis2 and Rampart with policy based configuration. 
You should have a solid understanding of the principles of public-key cryptography, public key infrastructure (PKI), and digital signature (read this, for example). 

#Generating key pair and certificates
To get an official digital certificate signed by a recognized CA, you need to generate a public-private key pair and use the public key to create a certificate request. You then send that certificate request to your preferred authority and pay it. The authority in turn verifies your identity and issues the certificate with its signature.
For testing or internal use, you can instead generate your own self-signed certificates. The example code uses such self-signed certificates, one for the client and one for the server. To generate a key pair (private + public key),  you can use Java's keytool program. You can find a detailed tutorial on creating certificate authority (CA), server and client keypairs here.
