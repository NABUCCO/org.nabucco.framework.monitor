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

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorKey
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class MonitorKey implements Comparable<MonitorKey> {

    private static Set<MonitorKey> keys = new HashSet<MonitorKey>();

    /** Name of the service */
    private String serviceName;

    /** Name of the service operation */
    private String serviceOperationName;

    /**
     * Creates a new {@link MonitorKey} instance.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     */
    private MonitorKey(String serviceName, String operationName) {
        this.serviceName = serviceName;
        this.serviceOperationName = operationName;
    }

    /**
     * Find the key for the given service name and operation name.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     * 
     * @return the appropriate key
     */
    public static MonitorKey valueOf(String serviceName, String operationName) {
        if (serviceName == null) {
            throw new IllegalArgumentException("Cannot create key for service [null].");
        }
        if (operationName == null) {
            throw new IllegalArgumentException("Cannot create key for operation [null].");
        }

        for (MonitorKey key : keys) {
            if (key.getServiceName().equals(serviceName)
                    && key.getServiceOperationName().equals(operationName)) {
                return key;
            }
        }

        MonitorKey newKey = new MonitorKey(serviceName, operationName);
        keys.add(newKey);

        return newKey;
    }

    /**
     * Getter for the serviceName.
     * 
     * @return Returns the serviceName.
     */
    public String getServiceName() {
        return this.serviceName;
    }

    /**
     * Getter for the serviceOperationName.
     * 
     * @return Returns the serviceOperationName.
     */
    public String getServiceOperationName() {
        return this.serviceOperationName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.serviceName == null) ? 0 : this.serviceName.hashCode());
        result = prime
                * result
                + ((this.serviceOperationName == null) ? 0 : this.serviceOperationName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MonitorKey other = (MonitorKey) obj;
        if (this.serviceName == null) {
            if (other.serviceName != null) {
                return false;
            }
        } else if (!this.serviceName.equals(other.serviceName)) {
            return false;
        }
        if (this.serviceOperationName == null) {
            if (other.serviceOperationName != null) {
                return false;
            }
        } else if (!this.serviceOperationName.equals(other.serviceOperationName)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MonitorKey o) {
        if (o == null) {
            return -1;
        }

        int result = this.serviceName.compareTo(o.serviceName);
        if (result != 0) {
            return result;
        }

        return this.serviceOperationName.compareTo(o.serviceOperationName);
    }
    
    @Override
    public String toString() {
        return this.serviceName + "." + this.serviceOperationName;
    }
}
