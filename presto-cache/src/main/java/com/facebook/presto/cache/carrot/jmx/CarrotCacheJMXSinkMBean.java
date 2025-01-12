/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.cache.carrot.jmx;

public interface CarrotCacheJMXSinkMBean {

  String getremote_fs_uri();
  
  int getdata_page_size();
  
  int getdata_prefetch_buffer_size();
      
  long gettotal_bytes_read();
  
  long gettotal_bytes_read_remote_fs();
  
  long gettotal_bytes_read_data_cache();
    
  long gettotal_bytes_read_prefetch();
  
  long gettotal_read_requests();
  
  long gettotal_read_requests_remote_fs();
  
  long gettotal_read_requests_data_cache();
  
  long gettotal_read_requests_prefetch();
  
  long gettotal_scans_detected();
    
  long getio_data_cache_read_avg_time();
  
  long getio_data_cache_read_avg_size();
    
  long getio_remote_fs_read_avg_time();
  
  long getio_remote_fs_read_avg_size();
  
}
