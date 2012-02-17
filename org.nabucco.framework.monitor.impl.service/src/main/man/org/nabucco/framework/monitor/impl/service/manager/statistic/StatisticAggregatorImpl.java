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
package org.nabucco.framework.monitor.impl.service.manager.statistic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nabucco.framework.base.facade.datatype.monitor.ExceptionMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.PersistenceMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.PersistenceOperationType;
import org.nabucco.framework.base.facade.datatype.monitor.ServiceMonitorEntry;
import org.nabucco.framework.monitor.facade.datatype.manager.statistic.ExceptionStatistic;
import org.nabucco.framework.monitor.facade.datatype.manager.statistic.PersistenceOperationStatistic;
import org.nabucco.framework.monitor.facade.datatype.manager.statistic.ServiceOperationStatistic;
import org.nabucco.framework.monitor.facade.service.manager.statistic.StatisticAggregator;
import org.nabucco.framework.monitor.impl.service.manager.MonitorCollector;
import org.nabucco.framework.monitor.impl.service.manager.MonitorCollectorListener;

/**
 * StatisticAggregatorImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class StatisticAggregatorImpl implements StatisticAggregator, MonitorCollectorListener {

    /** The monitoring entry collector. */
    private MonitorCollector collector;

    /** Service Operation Statistic */
    private Map<MonitorKey, ServiceOperationStatistic> serviceStatistics;

    /** Persistence Operation Statistic */
    private Map<MonitorKey, PersistenceOperationStatistic> persistenceStatistics;

    /** Exception Statistic */
    private Map<String, ExceptionStatistic> exceptionStatistics;

    /**
     * Creates a new {@link StatisticAggregatorImpl} instance.
     * 
     * @param collector
     *            the monitoring entry collector
     */
    public StatisticAggregatorImpl(MonitorCollector collector) {
        if (collector == null) {
            throw new IllegalArgumentException(
                    "Cannot create StatisticAggregator for MonitorCollector [null].");
        }

        this.collector = collector;

        this.serviceStatistics = new HashMap<MonitorKey, ServiceOperationStatistic>();
        this.persistenceStatistics = new HashMap<MonitorKey, PersistenceOperationStatistic>();
        this.exceptionStatistics = new HashMap<String, ExceptionStatistic>();
    }

    @Override
    public void update() {

        // Service Operation Monitor Entries
        List<ServiceMonitorEntry> serviceEntries = this.collector.getServiceOperationEntries();

        for (ServiceMonitorEntry serviceEntry : serviceEntries) {

            String serviceName = serviceEntry.getServiceName().getValue();
            String operationName = serviceEntry.getOperationName().getValue();
            long duration = serviceEntry.getDuration().getValue();
            long timestamp = serviceEntry.getTimestamp().getValue();

            String exceptionName = (serviceEntry.getExceptionName() != null) ? serviceEntry
                    .getExceptionName().getValue() : null;

            ServiceOperationStatistic statistic = this.getServiceStatistic(serviceName,
                    operationName);

            statistic.update(duration, timestamp, exceptionName);
        }

        // Persistence Operation Monitor Entries
        List<PersistenceMonitorEntry> persistenceEntries = this.collector
                .getPersistenceOperationEntries();

        for (PersistenceMonitorEntry entry : persistenceEntries) {

            String serviceName = entry.getServiceName().getValue();
            String operationName = entry.getOperationName().getValue();
            long duration = entry.getDuration().getValue();
            long timestamp = entry.getTimestamp().getValue();

            PersistenceOperationType type = entry.getOperationType();
            String query = (entry.getQuery() != null) ? entry.getQuery().getValue() : null;

            PersistenceOperationStatistic statistic = this.getPersistenceStatistic(serviceName,
                    operationName);

            statistic.update(duration, timestamp, type, query);
        }

        // Exception Monitor Entries
        List<ExceptionMonitorEntry> exceptionEntries = this.collector.getExceptionEntries();

        for (ExceptionMonitorEntry entry : exceptionEntries) {

            String exceptionName = entry.getExceptionName().getValue();
            long timestamp = entry.getTimestamp().getValue();

            ExceptionStatistic statistic = this.getExceptionStatistic(exceptionName);

            statistic.update(timestamp);
        }

        this.collector.clear();
    }

    @Override
    public ServiceOperationStatistic getServiceStatistic(String serviceName, String operationName) {

        MonitorKey key = MonitorKey.valueOf(serviceName, operationName);
        ServiceOperationStatistic statistic = this.serviceStatistics.get(key);

        if (statistic == null) {
            statistic = new ServiceOperationStatistic(serviceName, operationName);
            this.serviceStatistics.put(key, statistic);
        }

        return statistic;
    }

    @Override
    public PersistenceOperationStatistic getPersistenceStatistic(String serviceName,
            String operationName) {

        MonitorKey key = MonitorKey.valueOf(serviceName, operationName);
        PersistenceOperationStatistic statistic = this.persistenceStatistics.get(key);

        if (statistic == null) {
            statistic = new PersistenceOperationStatistic(serviceName, operationName);
            this.persistenceStatistics.put(key, statistic);
        }

        return statistic;
    }

    @Override
    public ExceptionStatistic getExceptionStatistic(String exceptionName) {

        ExceptionStatistic statistic = this.exceptionStatistics.get(exceptionName);

        if (statistic == null) {
            statistic = new ExceptionStatistic(exceptionName);
            this.exceptionStatistics.put(exceptionName, statistic);
        }

        return statistic;
    }

    @Override
    public Set<ServiceOperationStatistic> getAllServiceStatistics() {
        Set<ServiceOperationStatistic> statistics = new HashSet<ServiceOperationStatistic>();
        for (ServiceOperationStatistic statistic : this.serviceStatistics.values()) {
            statistics.add(statistic);
        }
        return statistics;
    }

    @Override
    public Set<PersistenceOperationStatistic> getAllPersistenceStatistics() {
        Set<PersistenceOperationStatistic> statistics = new HashSet<PersistenceOperationStatistic>();
        for (PersistenceOperationStatistic statistic : this.persistenceStatistics.values()) {
            statistics.add(statistic);
        }
        return statistics;
    }

    @Override
    public Set<ExceptionStatistic> getAllExceptionStatistics() {
        Set<ExceptionStatistic> statistics = new HashSet<ExceptionStatistic>();
        for (ExceptionStatistic statistic : this.exceptionStatistics.values()) {
            statistics.add(statistic);
        }
        return statistics;
    }

}
