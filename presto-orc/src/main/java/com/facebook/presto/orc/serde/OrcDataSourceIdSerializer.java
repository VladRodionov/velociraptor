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
package com.facebook.presto.orc.serde;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.Serializer;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.facebook.presto.orc.OrcDataSourceId;

public class OrcDataSourceIdSerializer extends Serializer<OrcDataSourceId>{

  public OrcDataSourceIdSerializer() {
    
  }
  @Override
  public void write(Kryo kryo, Output output, OrcDataSourceId object) {
    // TODO: it seems that we use this class only on a write path as a key
    // In this case we can reduce space by hashing string
    byte[] digest = Utils.hashCrypto(object.toString());
    output.writeBytes(digest);
  }

  @Override
  public OrcDataSourceId read(Kryo kryo, Input input,
      Class<? extends OrcDataSourceId> type) {
    // Should never happen
    return null;
  }
}