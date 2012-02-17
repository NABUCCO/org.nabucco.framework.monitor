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

/**
 * MonitorFilterFactory
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MonitorFilterFactory {

    /**
     * Singleton instance.
     */
    private static MonitorFilterFactory instance;

    /**
     * Private constructor.
     */
    private MonitorFilterFactory() {
    }

    /**
     * Singleton access.
     * 
     * @return the MonitorFilterFactory instance.
     */
    public synchronized static MonitorFilterFactory getInstance() {
        if (instance == null) {
            instance = new MonitorFilterFactory();
        }
        return instance;
    }

    /**
     * 
     * 
     * @return
     */
    public MonitorFilter loadFilter() {
        return new DefaultMonitorFilter();
    }

}
