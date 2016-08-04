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

import net.sf.cindy.impl.AbstractPacketMessage;
import net.sf.cindy.util.ByteBufferUtils;
import net.sf.cindy.util.CharsetUtils;

/**
 * @author Roger Chen
 */
public final class StringMessage extends AbstractPacketMessage {

    private static final ByteBuffer SPLIT = ByteBuffer.wrap("\r\n".getBytes());

    private String content;

    public StringMessage() {
    }

    public StringMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean readFromBuffer(ByteBuffer buffer) {
        int index = ByteBufferUtils.indexOf(buffer, SPLIT);
        if (index >= 0) {
            int limit = buffer.limit();
            buffer.limit(index);
            content = CharsetUtils.UTF8.decode(buffer).toString();
            buffer.limit(limit);
            buffer.position(index + SPLIT.remaining());
            return true;
        }
        return false;
    }

    public ByteBuffer[] toByteBuffer() {
        return new ByteBuffer[] { CharsetUtils.UTF8.encode(content),
                SPLIT.slice() };
    }

    public String toString() {
        return content;
    }

}