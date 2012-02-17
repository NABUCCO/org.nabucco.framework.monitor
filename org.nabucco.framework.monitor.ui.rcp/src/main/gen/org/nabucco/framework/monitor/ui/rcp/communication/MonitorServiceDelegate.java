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
package org.nabucco.framework.monitor.ui.rcp.communication;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.monitor.facade.service.MonitorService;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * MonitorServiceDelegate<p/>Responsible for starting/stopping the monitoring service.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-01-18
 */
public class MonitorServiceDelegate extends ServiceDelegateSupport {

    private MonitorService service;

    /**
     * Constructs a new MonitorServiceDelegate instance.
     *
     * @param service the MonitorService.
     */
    public MonitorServiceDelegate(MonitorService service) {
        super();
        this.service = service;
    }

    /**
     * StartService.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the EmptyServiceMessage.
     * @throws ClientException
     */
    public EmptyServiceMessage startService(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<EmptyServiceMessage> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.startService(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MonitorService.class, "startService", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MonitorService.startService");
    }

    /**
     * StopService.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the EmptyServiceMessage.
     * @throws ClientException
     */
    public EmptyServiceMessage stopService(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<EmptyServiceMessage> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.stopService(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MonitorService.class, "stopService", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MonitorService.stopService");
    }
}
