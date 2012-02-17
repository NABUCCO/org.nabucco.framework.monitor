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
package org.nabucco.framework.monitor.facade.datatype.manager.statistic.comparator;

import org.nabucco.framework.monitor.facade.datatype.manager.statistic.ServiceOperationStatistic;

/**
 * ServiceOperationStatisticComparator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ServiceOperationStatisticComparator extends
        StatisticComparator<ServiceOperationStatistic> {

    /**
     * Singleton instance.
     */
    private static ServiceOperationStatisticComparator instance = new ServiceOperationStatisticComparator();

    /**
     * Private constructor.
     */
    private ServiceOperationStatisticComparator() {
    }

    /**
     * Singleton access.
     * 
     * @return the ServiceOperationStatisticComparator instance.
     */
    public static ServiceOperationStatisticComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(ServiceOperationStatistic statistic1, ServiceOperationStatistic statistic2) {
        if (statistic1 == null || statistic2 == null) {
            throw new IllegalArgumentException("Cannot compare ServiceStatistic [null].");
        }

        return -super.compare(statistic1.getDurationMean(), statistic2.getDurationMean());
    }

}