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
import com.facebook.presto.orc.metadata.DwrfStripeCacheData;
import com.facebook.presto.orc.metadata.DwrfStripeCacheMode;

import io.airlift.slice.Slice;

public class DwrfStripeCacheDataSerializer extends Serializer<DwrfStripeCacheData>{

  @Override
  public void write(Kryo kryo, Output output, DwrfStripeCacheData object) {
    
    Slice slice = object.getDwrfStripeCacheSlice();
    int cacheSliceSize = object.getDwrfStripeCacheSize();
    DwrfStripeCacheMode mode = object.getDwrfStripeCacheMode();
    int origOffset = object.getOriginalOffset();
    kryo.writeObject(output,  slice);
    output.writeInt(cacheSliceSize);
    output.writeByte( (byte) mode.ordinal());
    output.writeInt(origOffset);
    
  }

  @Override
  public DwrfStripeCacheData read(Kryo kryo, Input input,
      Class<? extends DwrfStripeCacheData> type) {
    
    Slice slice = kryo.readObject(input,  Slice.class);
    int cacheSliceSize = input.readInt();;
    DwrfStripeCacheMode mode = DwrfStripeCacheMode.values()[input.readByte()];
    int origOffset = input.readInt();
    return new  DwrfStripeCacheData(slice, cacheSliceSize, mode, origOffset);
  }
}
