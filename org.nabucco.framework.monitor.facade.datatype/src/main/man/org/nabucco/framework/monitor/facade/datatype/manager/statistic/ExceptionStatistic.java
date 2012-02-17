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
 * ExceptionStatistic
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExceptionStatistic implements MonitorStatistic {

    /** Default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Name of the exception */
    private String exceptionName;

    /** First raised exception. */
    private long first = -1l;

    /** First raised exception. */
    private long last = 0l;

    /** Count of all raised exceptions */
    private int count = 0;

    /**
     * Creates a new {@link ExceptionStatistic} instance.
     * 
     * @param exceptionName
     *            name of the exception
     */
    public ExceptionStatistic(String exceptionName) {
        if (exceptionName == null) {
            throw new IllegalArgumentException(
                    "Cannot create exception statistics for name [null].");
        }

        this.exceptionName = exceptionName;
    }

    /**
     * Update the exception count.
     * 
     * @param timestamp
     *            the exception timestamp
     */
    public void update(long timestamp) {
        this.count++;

        if (this.first < 0) {
            this.first = timestamp;
        }

        if (this.last < timestamp) {
            this.last = timestamp;
        }
    }

    /**
     * Getter for the exceptionName.
     * 
     * @return Returns the exceptionName.
     */
    public String getExceptionName() {
        return this.exceptionName;
    }

    /**
     * Getter for the count.
     * 
     * @return Returns the count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Getter for the first.
     * 
     * @return Returns the first.
     */
    public long getFirst() {
        return this.first;
    }

    /**
     * Getter for the last.
     * 
     * @return Returns the last.
     */
    public long getLast() {
        return this.last;
    }

}
