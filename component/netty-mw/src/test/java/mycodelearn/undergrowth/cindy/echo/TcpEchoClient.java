/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mycodelearn.undergrowth.cindy.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import mycodelearn.undergrowth.cindy.StringMessage;
import mycodelearn.undergrowth.cindy.StringMessageRecognizer;
import net.sf.cindy.Message;
import net.sf.cindy.Session;
import net.sf.cindy.SessionAdapter;
import net.sf.cindy.impl.SocketSession;
import net.sf.cindy.util.Utils;

/**
 * @author Roger Chen
 */
public class TcpEchoClient {

    private final SocketSession ss = new SocketSession();

    public TcpEchoClient() {
        ss.setLogException(true);
        ss.setMessageRecognizer(new StringMessageRecognizer());
        ss.addSessionListener(new SessionAdapter() {

            public void sessionEstablished(Session session) {
                System.out
                        .println("Type \"quit\" to quit, \"restart\" to restart");
            }

            public void messageReceived(Session session, Message message) {
                System.out.println(message);
            }
        });
    }

    public void start(SocketAddress address) {
        ss.setSocketAddress(address);
        ss.start(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = br.readLine();
                if (line == null)
                    return;
                if (line.equalsIgnoreCase("quit")) {
                    ss.close(false);
                    return;
                }
                if (line.equalsIgnoreCase("restart")) {
                    ss.close(true);
                    ss.start(false);
                    continue;
                }

                StringMessage message = new StringMessage();
                message.setContent(line);
                ss.write(message);
            } catch (IOException e) {
                ss.dispatchException(e);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: "
                    + Utils.getClassSimpleName(TcpEchoClient.class)
                    + " host port");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        TcpEchoClient client = new TcpEchoClient();
        client.start(new InetSocketAddress(host, port));
    }
}