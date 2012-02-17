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
package org.nabucco.framework.monitor.facade.component;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for MonitorComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class MonitorComponentLocator extends ComponentLocatorSupport<MonitorComponent> implements
        ComponentLocator<MonitorComponent> {

    private static MonitorComponentLocator instance;

    /**
     * Constructs a new MonitorComponentLocator instance.
     *
     * @param component the Class<MonitorComponent>.
     * @param jndiName the String.
     */
    private MonitorComponentLocator(String jndiName, Class<MonitorComponent> component) {
        super(jndiName, component);
    }

    @Override
    public MonitorComponent getComponent() throws ConnectionException {
        MonitorComponent component = super.getComponent();
        if ((component instanceof MonitorComponentLocal)) {
            return new MonitorComponentLocalProxy(((MonitorComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the MonitorComponentLocator.
     */
    public static MonitorComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new MonitorComponentLocator(MonitorComponent.JNDI_NAME, MonitorComponent.class);
        }
        return instance;
    }
}
