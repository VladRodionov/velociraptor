/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.parquet.serde;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.apache.parquet.schema.Type.ID;
import org.testng.annotations.Test;

import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;

public class TestIDSerializer extends TestSerializerBase{

  @Test
  public void testIDSerializer() {
    
    ID id = new ID(10);
    Output out = new Output(1 << 16);
    kryo.writeObject(out, id);
    byte[] buf = out.getBuffer();
    Input in = new Input(buf);
    ID id1 = kryo.readObject(in, ID.class);
    assertEquals(id, id1);
    
    // Test null
    out.reset();
    kryo.writeObjectOrNull(out, null, ID.class);
    buf = out.getBuffer();
    in = new Input(buf);
    id1 = kryo.readObjectOrNull(in, ID.class);
    assertNull(id1);
  }
}