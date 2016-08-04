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
package mycodelearn.undergrowth.cindy;

import java.nio.ByteBuffer;

import net.sf.cindy.Message;
import net.sf.cindy.MessageRecognizer;
import net.sf.cindy.Session;

/**
 * @author Roger Chen
 */
public class StringMessageRecognizer implements MessageRecognizer {

    public Message recognize(Session session, ByteBuffer buffer) {
        if (buffer.hasRemaining())
            return new StringMessage();
        return null;
    }

}