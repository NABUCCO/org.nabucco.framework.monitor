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

/**
 * PersistenceOperationStatistic
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ServiceOperationStatistic extends OperationStatistic implements MonitorStatistic {

    /** Default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Count of raised exceptions for this service operation. */
    private int exceptionCount;

    /**
     * Creates a new {@link ServiceOperationStatistic} instance.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     */
    public ServiceOperationStatistic(String serviceName, String operationName) {
        super(serviceName, operationName);
    }

    /**
     * Add a duration of a persistence operation to the statistics.
     * 
     * @param duration
     *            the duration of the operation
     * @param timestamp
     *            the timestamp of the operation
     * @param exceptionName
     *            name of a raised exception, or null if none was raised
     */
    public void update(long duration, long timestamp, String exceptionName) {

        if (exceptionName != null) {
            this.exceptionCount++;
        }

        super.update(duration, timestamp);
    }

    /**
     * Getter for the exceptionCount.
     * 
     * @return Returns the exceptionCount.
     */
    public int getExceptionCount() {
        return this.exceptionCount;
    }

}
