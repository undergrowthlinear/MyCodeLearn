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

import mycodelearn.undergrowth.cindy.StringMessageRecognizer;
import net.sf.cindy.Message;
import net.sf.cindy.Session;
import net.sf.cindy.SessionAdapter;
import net.sf.cindy.impl.SimpleServerSocketSession;
import net.sf.cindy.impl.SocketSession;
import net.sf.cindy.util.Utils;

/**
 * @author Roger Chen
 */
public class TcpEchoServer {

    private final SimpleServerSocketSession sss = new SimpleServerSocketSession();

    public TcpEchoServer() {
        sss.addSessionListener(new SessionAdapter() {

            public void sessionEstablished(Session session) {
                System.out.println(sss.getChannel().socket()
                        .getLocalSocketAddress());
            }
        });
        sss.setLogException(true);
        sss.setMessageRecognizer(new StringMessageRecognizer());
        sss.addSocketSessionListener(new SessionAdapter() {

            public void sessionEstablished(Session session) {
                System.out.println(((SocketSession) session).getChannel()
                        .socket().getRemoteSocketAddress()
                        + " connected");
            }

            public void messageReceived(Session session, Message message) {
                System.out.println(message);
                session.write(message);
            }
        });
    }

    public void start(int port) {
        sss.setListenPort(port);
        sss.start(false);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("usage: "
                    + Utils.getClassSimpleName(TcpEchoServer.class) + " port");
            return;
        }
        int port = Integer.parseInt(args[0]);
        TcpEchoServer server = new TcpEchoServer();
        server.start(port);
    }
}