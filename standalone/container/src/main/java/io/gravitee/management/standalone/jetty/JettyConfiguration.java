/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.management.standalone.jetty;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author David BRASSELY (brasseld at gmail.com)
 */
public class JettyConfiguration {

    @Value("${jetty.port:8082}")
    private int httpPort;

    @Value("${jetty.idleTimeout:30000}")
    private int idleTimeout;

    @Value("${jetty.acceptors:-1}")
    private int acceptors;

    @Value("${jetty.selectors:-1}")
    private int selectors;

    @Value("${jetty.pool.minThreads:10}")
    private int poolMinThreads;

    @Value("${jetty.pool.maxThreads:200}")
    private int poolMaxThreads;

    @Value("${jetty.pool.idleTimeout:60000}")
    private int poolIdleTimeout;

    @Value("${jetty.pool.queueSize:6000}")
    private int poolQueueSize;

    @Value("${jetty.jmx:false}")
    private boolean jmxEnabled;

    @Value("${jetty.statistics:false}")
    private boolean statisticsEnabled;

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getAcceptors() {
        return acceptors;
    }

    public void setAcceptors(int acceptors) {
        this.acceptors = acceptors;
    }

    public int getSelectors() {
        return selectors;
    }

    public void setSelectors(int selectors) {
        this.selectors = selectors;
    }

    public int getPoolMinThreads() {
        return poolMinThreads;
    }

    public void setPoolMinThreads(int poolMinThreads) {
        this.poolMinThreads = poolMinThreads;
    }

    public int getPoolMaxThreads() {
        return poolMaxThreads;
    }

    public void setPoolMaxThreads(int poolMaxThreads) {
        this.poolMaxThreads = poolMaxThreads;
    }

    public boolean isJmxEnabled() {
        return jmxEnabled;
    }

    public void setJmxEnabled(boolean jmxEnabled) {
        this.jmxEnabled = jmxEnabled;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public boolean isStatisticsEnabled() {
        return statisticsEnabled;
    }

    public void setStatisticsEnabled(boolean statisticsEnabled) {
        this.statisticsEnabled = statisticsEnabled;
    }

    public int getPoolIdleTimeout() {
        return poolIdleTimeout;
    }

    public void setPoolIdleTimeout(int poolIdleTimeout) {
        this.poolIdleTimeout = poolIdleTimeout;
    }

    public int getPoolQueueSize() {
        return poolQueueSize;
    }

    public void setPoolQueueSize(int poolQueueSize) {
        this.poolQueueSize = poolQueueSize;
    }
}