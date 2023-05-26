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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.facebook.presto.orc.metadata.statistics.DateColumnStatistics;
import com.facebook.presto.orc.metadata.statistics.DateStatistics;
import com.facebook.presto.orc.metadata.statistics.HiveBloomFilter;

public class TestDateColumnStatisticsSerializer extends TestSerializerBase{

  @Test
  public void testDateColumnStatisticsSerializer() {
    
    HiveBloomFilter filter = TestUtils.getBloomFilter();
    long numValues = 1000;
    DateStatistics bs = new DateStatistics(1000000, 2000000);
    DateColumnStatistics bcs = new DateColumnStatistics(numValues, filter, bs);
    
    Output out = new Output(1 << 16);
    kryo.writeObject(out, bcs);
    
    Input in = new Input(out.getBuffer());
    int type = in.readByte();
    assertEquals(StatisticsKind.DATE, StatisticsKind.values()[type]);
    DateColumnStatistics read = kryo.readObject(in, DateColumnStatistics.class);
    assertEquals(bcs.getNumberOfValues(), read.getNumberOfValues());
    assertEquals(bcs.getDateStatistics().getMin(), read.getDateStatistics().getMin());
    assertEquals(bcs.getDateStatistics().getMax(), read.getDateStatistics().getMax());

    TestUtils.equals(bcs.getBloomFilter(), read.getBloomFilter());
    
    // filter = null
    out.reset();
    bcs = new DateColumnStatistics(numValues, null, bs);
    kryo.writeObject(out, bcs);
    
    in = new Input(out.getBuffer());
    type = in.readByte();
    assertEquals(StatisticsKind.DATE, StatisticsKind.values()[type]);
    read = kryo.readObject(in, DateColumnStatistics.class);
    assertEquals(bcs.getNumberOfValues(), read.getNumberOfValues());
    assertEquals(bcs.getDateStatistics().getMin(), read.getDateStatistics().getMin());
    assertEquals(bcs.getDateStatistics().getMax(), read.getDateStatistics().getMax());
    TestUtils.equals(bcs.getBloomFilter(), read.getBloomFilter());
  }
}