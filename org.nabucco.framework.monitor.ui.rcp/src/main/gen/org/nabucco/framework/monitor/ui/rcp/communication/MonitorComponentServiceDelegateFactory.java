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

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.monitor.facade.component.MonitorComponent;
import org.nabucco.framework.monitor.facade.component.MonitorComponentLocator;
import org.nabucco.framework.monitor.ui.rcp.communication.MonitorServiceDelegate;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateFactorySupport;

/**
 * ServiceDelegateFactoryTemplate<p/>Component for monitoring and management operations.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-01-18
 */
public class MonitorComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<MonitorComponent> {

    private static MonitorComponentServiceDelegateFactory instance = new MonitorComponentServiceDelegateFactory();

    private MonitorServiceDelegate monitorServiceDelegate;

    /** Constructs a new MonitorComponentServiceDelegateFactory instance. */
    private MonitorComponentServiceDelegateFactory() {
        super(MonitorComponentLocator.getInstance());
    }

    /**
     * Getter for the MonitorService.
     *
     * @return the MonitorServiceDelegate.
     * @throws ClientException
     */
    public MonitorServiceDelegate getMonitorService() throws ClientException {
        try {
            if ((this.monitorServiceDelegate == null)) {
                this.monitorServiceDelegate = new MonitorServiceDelegate(this.getComponent().getMonitorService());
            }
            return this.monitorServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: MonitorComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: MonitorService", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the MonitorComponentServiceDelegateFactory.
     */
    public static MonitorComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
