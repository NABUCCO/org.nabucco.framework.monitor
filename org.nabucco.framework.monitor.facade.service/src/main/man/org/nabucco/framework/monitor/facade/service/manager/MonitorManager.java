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
package org.nabucco.framework.monitor.facade.service.manager;

import org.nabucco.framework.monitor.facade.service.manager.statistic.StatisticAggregator;

/**
 * MonitorManager
 * <p/>
 * Manager for monitoring purpose. Is responsible for starting/stopping the monitoring.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public interface MonitorManager {

    /**
     * Start the collection of monitoring information.
     */
    void startMonitoring();

    /**
     * Stopt the collection of monitoring information.
     */
    void stopMonitoring();

    /**
     * Enables the logging of each monitoring entry.
     */
    void enableLogging();

    /**
     * Disables the logging of each monitoring entry.
     */
    void disableLogging();

    /**
     * Getter for the monitor aggregator.
     * 
     * @return the aggregator holding the aggregated monitoring entries
     */
    StatisticAggregator getStatisticAggregator();

}
