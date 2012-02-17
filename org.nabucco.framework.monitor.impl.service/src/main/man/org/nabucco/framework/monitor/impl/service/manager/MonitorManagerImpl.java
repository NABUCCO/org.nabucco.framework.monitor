/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.monitor.impl.service.manager;

import org.nabucco.framework.base.impl.service.monitor.MonitorFacade;
import org.nabucco.framework.base.impl.service.monitor.MonitorProcessor;
import org.nabucco.framework.monitor.facade.service.manager.MonitorManager;
import org.nabucco.framework.monitor.facade.service.manager.statistic.StatisticAggregator;
import org.nabucco.framework.monitor.impl.service.manager.statistic.StatisticAggregatorImpl;

/**
 * MonitorManagerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MonitorManagerImpl implements MonitorManager {

    /** The aggregator of statistical monitoring information. */
    private StatisticAggregator aggregator;

    /** The collector of all monitoring entries. */
    private MonitorProcessor collector;

    /** The logger for all monitoring entries. */
    private MonitorProcessor logger;

    /**
     * Singleton instance.
     */
    private static MonitorManagerImpl instance;

    /**
     * Private constructor.
     */
    private MonitorManagerImpl() {
        MonitorLogger logger = new MonitorLogger();
        MonitorCollector collector = new MonitorCollector();
        StatisticAggregatorImpl aggregator = new StatisticAggregatorImpl(collector);

        // collector.addListener(aggregator);

        this.logger = logger;
        this.collector = collector;
        this.aggregator = aggregator;
    }

    /**
     * Singleton access.
     * 
     * @return the MonitorManagerImpl instance.
     */
    public static synchronized MonitorManagerImpl getInstance() {
        if (instance == null) {
            instance = new MonitorManagerImpl();
        }
        return instance;
    }

    @Override
    public synchronized void startMonitoring() {
        MonitorFacade.getInstance().registerProcessor(this.collector);
    }

    @Override
    public synchronized void stopMonitoring() {
        MonitorFacade.getInstance().unregisterProcessor(this.collector);
    }

    @Override
    public synchronized void enableLogging() {
        MonitorFacade.getInstance().registerProcessor(this.logger);
    }

    @Override
    public synchronized void disableLogging() {
        MonitorFacade.getInstance().unregisterProcessor(this.logger);
    }

    @Override
    public StatisticAggregator getStatisticAggregator() {
        return this.aggregator;
    }
}
