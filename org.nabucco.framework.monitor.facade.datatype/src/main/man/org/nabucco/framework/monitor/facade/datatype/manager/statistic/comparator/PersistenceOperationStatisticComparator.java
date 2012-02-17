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

import org.nabucco.framework.monitor.facade.datatype.manager.statistic.PersistenceOperationStatistic;

/**
 * ServiceOperationStatisticComparator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class PersistenceOperationStatisticComparator extends
        StatisticComparator<PersistenceOperationStatistic> {

    /**
     * Singleton instance.
     */
    private static PersistenceOperationStatisticComparator instance = new PersistenceOperationStatisticComparator();

    /**
     * Private constructor.
     */
    private PersistenceOperationStatisticComparator() {
    }

    /**
     * Singleton access.
     * 
     * @return the PersistenceOperationStatisticComparator instance.
     */
    public static PersistenceOperationStatisticComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(PersistenceOperationStatistic s1, PersistenceOperationStatistic s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Cannot compare PersistenceStatistic [null].");
        }

        return -super.compare(s1.getDurationMean(), s2.getDurationMean());
    }

}
