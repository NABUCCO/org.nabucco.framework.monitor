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
package org.nabucco.framework.monitor.facade.service;

import org.nabucco.framework.base.facade.exception.service.MonitorException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;

/**
 * MonitorService<p/>Responsible for starting/stopping the monitoring service.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-01-18
 */
public interface MonitorService extends Service {

    /**
     * Missing description at method startService.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<EmptyServiceMessage>.
     * @throws MonitorException
     */
    ServiceResponse<EmptyServiceMessage> startService(ServiceRequest<EmptyServiceMessage> rq) throws MonitorException;

    /**
     * Missing description at method stopService.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<EmptyServiceMessage>.
     * @throws MonitorException
     */
    ServiceResponse<EmptyServiceMessage> stopService(ServiceRequest<EmptyServiceMessage> rq) throws MonitorException;
}
