package com.betware.rampart.client;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import org.apache.ws.security.WSPasswordCallback;

public class RampartClientPasswordHandler implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {

        for (Callback callback : callbacks) {

            // To use the private key to sign messages, we need to provide
            // the private key password
            WSPasswordCallback pwcb = (WSPasswordCallback) callback;

            if(pwcb.getIdentifer().equals("client") ) {
                pwcb.setPassword("clientPW");
                return;
            }

        }
    }

}
