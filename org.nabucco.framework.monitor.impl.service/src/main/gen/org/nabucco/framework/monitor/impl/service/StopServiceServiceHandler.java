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
package org.nabucco.framework.monitor.impl.service;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.MonitorException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandlerSupport;

/**
 * StopServiceServiceHandler<p/>Responsible for starting/stopping the monitoring service.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-01-18
 */
public abstract class StopServiceServiceHandler extends PersistenceServiceHandlerSupport implements ServiceHandler,
        PersistenceServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.monitor.impl.service.StopServiceServiceHandler";

    /** Constructs a new StopServiceServiceHandler instance. */
    public StopServiceServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<EmptyServiceMessage>.
     * @throws MonitorException
     */
    protected ServiceResponse<EmptyServiceMessage> invoke(ServiceRequest<EmptyServiceMessage> rq)
            throws MonitorException {
        ServiceResponse<EmptyServiceMessage> rs;
        EmptyServiceMessage msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.stopService(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<EmptyServiceMessage>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (MonitorException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            MonitorException wrappedException = new MonitorException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new MonitorException("Error during service invocation.", e);
        }
    }

    /**
     * Missing description at method stopService.
     *
     * @param msg the EmptyServiceMessage.
     * @return the EmptyServiceMessage.
     * @throws MonitorException
     */
    protected abstract EmptyServiceMessage stopService(EmptyServiceMessage msg) throws MonitorException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
