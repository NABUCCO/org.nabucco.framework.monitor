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
package org.nabucco.framework.monitor.impl.component;

/**
 * MonitorComponentJndiNames<p/>Component for monitoring and management operations.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-01-18
 */
public interface MonitorComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.component.QueryFilterService/remote";

    final String MONITOR_SERVICE_LOCAL = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.service.MonitorService/local";

    final String MONITOR_SERVICE_REMOTE = "nabucco/org.nabucco.framework.monitor/org.nabucco.framework.monitor.facade.service.MonitorService/remote";
}
