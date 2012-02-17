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
package org.nabucco.framework.monitor.facade.datatype.manager.statistic;

import org.nabucco.framework.base.facade.datatype.monitor.PersistenceOperationType;

/**
 * PersistenceOperationStatistic
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class PersistenceOperationStatistic extends OperationStatistic implements MonitorStatistic {

    /** Default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The persistence operation type for the minimal duration */
    private PersistenceOperationType minType;

    /** The persistence operation type for the maximal duration */
    private PersistenceOperationType maxType;

    /** The executed query for the minimal duration */
    private String minQuery;

    /** The executed query for the maximal duration */
    private String maxQuery;

    /**
     * Creates a new {@link PersistenceOperationStatistic} instance.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     */
    public PersistenceOperationStatistic(String serviceName, String operationName) {
        super(serviceName, operationName);
    }

    /**
     * Add a duration of a persistence operation to the statistics.
     * 
     * @param duration
     *            the duration of the operation
     * @param timestamp
     *            the timestamp of the operation
     * @param minType
     *            the persistence operation type
     * @param minQuery
     *            the executed minQuery
     */
    public void update(long duration, long timestamp, PersistenceOperationType persistenceType,
            String query) {

        if (duration < super.getMinDuration()) {
            this.minQuery = query;
            this.minType = persistenceType;
        }

        if (duration > super.getMaxDuration()) {
            this.maxQuery = query;
            this.maxType = persistenceType;
        }

        super.update(duration, timestamp);
    }

    /**
     * Getter for the minType.
     * 
     * @return Returns the minType.
     */
    public PersistenceOperationType getMinType() {
        return this.minType;
    }

    /**
     * Getter for the maxType.
     * 
     * @return Returns the maxType.
     */
    public PersistenceOperationType getMaxType() {
        return this.maxType;
    }

    /**
     * Getter for the minQuery.
     * 
     * @return Returns the minQuery.
     */
    public String getMinQuery() {
        return this.minQuery;
    }

    /**
     * Getter for the maxQuery.
     * 
     * @return Returns the maxQuery.
     */
    public String getMaxQuery() {
        return this.maxQuery;
    }

}
