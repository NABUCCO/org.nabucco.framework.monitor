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
package org.nabucco.framework.monitor.impl.service.manager.filter;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.monitor.ExceptionMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.PersistenceMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.ServiceMonitorEntry;

/**
 * DefaultMonitorFilter
 * <p/>
 * Does not perform any monitor entry filtering.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class DefaultMonitorFilter implements MonitorFilter {

    @Override
    public void filterServiceOperations(List<ServiceMonitorEntry> entries) {
        // Nothing to filter!
    }

    @Override
    public void filterPersistenceOperations(List<PersistenceMonitorEntry> entries) {
        // Nothing to filter!
    }

    @Override
    public void filterExceptions(List<ExceptionMonitorEntry> entries) {
        // Nothing to filter!
    }

}
