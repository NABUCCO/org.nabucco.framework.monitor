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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nabucco.framework.base.facade.datatype.monitor.ExceptionMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.MonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.PersistenceMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.ServiceMonitorEntry;
import org.nabucco.framework.base.facade.exception.service.MonitorException;
import org.nabucco.framework.base.impl.service.monitor.MonitorProcessor;
import org.nabucco.framework.monitor.impl.service.manager.filter.MonitorFilter;
import org.nabucco.framework.monitor.impl.service.manager.filter.MonitorFilterFactory;

/**
 * MonitorCollector
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MonitorCollector implements MonitorProcessor {

    private static final int UPDATE_SIZE = 10;

    /** The collected service monitor entries. */
    private List<ServiceMonitorEntry> serviceEntries;

    /** The collected persistence monitor entries. */
    private List<PersistenceMonitorEntry> persistenceEntries;

    /** The collected exception monitor entries. */
    private List<ExceptionMonitorEntry> exceptionEntries;

    /** The registerd listeners. */
    private Set<MonitorCollectorListener> listeners;

    /** The registered filter. */
    private MonitorFilter filter;

    /**
     * Creates a new {@link MonitorCollector} instance.
     */
    MonitorCollector() {
        this.serviceEntries = new ArrayList<ServiceMonitorEntry>();
        this.persistenceEntries = new ArrayList<PersistenceMonitorEntry>();
        this.exceptionEntries = new ArrayList<ExceptionMonitorEntry>();

        this.listeners = new HashSet<MonitorCollectorListener>();

        this.filter = MonitorFilterFactory.getInstance().loadFilter();
    }

    /**
     * Adds a listener to the collector that listens to monitor collection state change.
     * 
     * @param listener
     *            the listener to add
     */
    void addListener(MonitorCollectorListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void process(MonitorEntry entry) throws MonitorException {

        switch (entry.getType()) {

        case SERVICE_OPERATION: {
            this.monitorServiceOperation((ServiceMonitorEntry) entry);
            break;
        }
        case PERSISTENCE_OPERATION: {
            this.monitorPersistenceOperation((PersistenceMonitorEntry) entry);
            break;
        }
        case EXCEPTION: {
            this.monitorException((ExceptionMonitorEntry) entry);
            break;
        }
        }

        int size = this.serviceEntries.size()
                + this.persistenceEntries.size() + this.exceptionEntries.size();

        if (size > UPDATE_SIZE) {
            for (MonitorCollectorListener listener : this.listeners) {
                listener.update();
            }
        }
    }

    /**
     * Monitors a service operation monitor entry.
     * 
     * @param entry
     *            the entry to monitor
     */
    private void monitorServiceOperation(ServiceMonitorEntry entry) {
        this.serviceEntries.add(entry);
    }

    /**
     * Monitors a persistence operation monitor entry.
     * 
     * @param entry
     *            the entry to monitor
     */
    private void monitorPersistenceOperation(PersistenceMonitorEntry entry) {
        this.persistenceEntries.add(entry);
    }

    /**
     * Monitors a persistence operation monitor entry.
     * 
     * @param entry
     *            the entry to monitor
     */
    private void monitorException(ExceptionMonitorEntry entry) {
        this.exceptionEntries.add(entry);
    }

    /**
     * Getter for all monitored service operation entries.
     * 
     * @return the list of collected service operation entries.
     */
    public List<ServiceMonitorEntry> getServiceOperationEntries() {
        List<ServiceMonitorEntry> entries = new ArrayList<ServiceMonitorEntry>(this.serviceEntries);
        this.filter.filterServiceOperations(entries);
        return entries;
    }

    /**
     * Getter for all monitored persistence operation entries.
     * 
     * @return the list of collected persistence operation entries.
     */
    public List<PersistenceMonitorEntry> getPersistenceOperationEntries() {
        List<PersistenceMonitorEntry> entries = new ArrayList<PersistenceMonitorEntry>(
                this.persistenceEntries);
        this.filter.filterPersistenceOperations(entries);
        return entries;
    }

    /**
     * Getter for all monitored service operation entries.
     * 
     * @return the list of collected service operation entries.
     */
    public List<ExceptionMonitorEntry> getExceptionEntries() {
        List<ExceptionMonitorEntry> entries = new ArrayList<ExceptionMonitorEntry>(
                this.exceptionEntries);
        this.filter.filterExceptions(entries);
        return entries;
    }

    /**
     * Removes all collected monitoring entries from the in-memory storage.
     */
    public void clear() {
        this.serviceEntries.clear();
        this.persistenceEntries.clear();
        this.exceptionEntries.clear();
    }
}
