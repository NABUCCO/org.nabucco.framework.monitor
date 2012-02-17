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
package org.nabucco.framework.monitor.facade.service.manager.statistic;

import java.util.Set;

import org.nabucco.framework.monitor.facade.datatype.manager.statistic.ExceptionStatistic;
import org.nabucco.framework.monitor.facade.datatype.manager.statistic.PersistenceOperationStatistic;
import org.nabucco.framework.monitor.facade.datatype.manager.statistic.ServiceOperationStatistic;

/**
 * StatisticAggregator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public interface StatisticAggregator {

    /**
     * Updates the collected monitoring entries into the aggregator and calculates the appropriate
     * statistics like sum, standard deviation, variance, etc. .
     */
    void update();

    /**
     * Getter for the service statistic of a given service operation.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     * 
     * @return the service statistic
     */
    ServiceOperationStatistic getServiceStatistic(String serviceName, String operationName);

    /**
     * Getter for the persistence statistic of a given service operation.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     * 
     * @return the persistence statistic
     */
    PersistenceOperationStatistic getPersistenceStatistic(String serviceName, String operationName);

    /**
     * Getter for the statistic of a given exception.
     * 
     * @param exceptionName
     *            name of the exception
     * 
     * @return the exception statistic
     */
    ExceptionStatistic getExceptionStatistic(String exceptionName);

    /**
     * Getter for all service statistic.
     * 
     * @return the set of service operation statistic
     */
    Set<ServiceOperationStatistic> getAllServiceStatistics();

    /**
     * Getter for all persistence statistic.
     * 
     * @return the set of persistence operation statistic
     */
    Set<PersistenceOperationStatistic> getAllPersistenceStatistics();

    /**
     * Getter for all exception statistic.
     * 
     * @return the set of exception operation statistic
     */
    Set<ExceptionStatistic> getAllExceptionStatistics();

}
