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

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.monitor.ExceptionMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.MonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.PersistenceMonitorEntry;
import org.nabucco.framework.base.facade.datatype.monitor.ServiceMonitorEntry;
import org.nabucco.framework.base.facade.exception.service.MonitorException;
import org.nabucco.framework.base.impl.service.monitor.MonitorProcessor;

/**
 * MonitorCollector
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MonitorLogger implements MonitorProcessor {

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            MonitorLogger.class);

    /**
     * Creates a new {@link MonitorLogger} instance.
     */
    MonitorLogger() {
    }

    @Override
    public void process(MonitorEntry entry) throws MonitorException {

        String serviceName = entry.getServiceName().getValue();
        String operationName = entry.getOperationName().getValue();

        switch (entry.getType()) {

        case SERVICE_OPERATION: {

            long duration = ((ServiceMonitorEntry) entry).getDuration().getValue();

            logger.info("Execution of Service Operation ", serviceName, ".", operationName,
                    "() took ", String.valueOf(duration / 100.0), " seconds.");

            break;
        }

        case PERSISTENCE_OPERATION: {

            long duration = ((PersistenceMonitorEntry) entry).getDuration().getValue();

            logger.info("Execution of Persistence Operation ", serviceName, ".", operationName,
                    "() took ", String.valueOf(duration / 100.0), " seconds.");

            break;
        }

        case EXCEPTION: {

            String exception = ((ExceptionMonitorEntry) entry).getExceptionName().getValue();

            logger.info("Execution of Service Operation ", serviceName, ".", operationName,
                    "() caused a ", exception, ".");

            break;
        }

        }

    }
}
