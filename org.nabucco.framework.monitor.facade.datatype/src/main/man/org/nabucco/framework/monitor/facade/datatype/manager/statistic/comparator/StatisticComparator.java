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

import java.util.Comparator;

/**
 * StatisticComparator
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
abstract class StatisticComparator<T> implements Comparator<T> {

    /**
     * Compares two <code>ints</code> numerically.
     * 
     * @param int1
     *            the first int
     * @param int2
     *            the second int
     * 
     * @return the value <code>0</code> if the first <code>int</code> is equal to the second
     *         <code>int</code>; a value less than <code>0</code> if the first <code>int</code> is
     *         numerically less than the second <code>int</code>; and a value greater than
     *         <code>0</code> if the first <code>int</code> is numerically greater than the second
     *         <code>int</code> (signed comparison).
     */
    protected int compare(int int1, int int2) {
        return (int1 < int2 ? -1 : (int1 == int2 ? 0 : 1));
    }

    /**
     * Compares two <code>longs</code> numerically.
     * 
     * @param long1
     *            the first long
     * @param long2
     *            the second long
     * 
     * @return the value <code>0</code> if the first <code>long</code> is equal to the second
     *         <code>long</code>; a value less than <code>0</code> if the first <code>long</code> is
     *         numerically less than the second <code>long</code>; and a value greater than
     *         <code>0</code> if the first <code>long</code> is numerically greater than the second
     *         <code>long</code> (signed comparison).
     */
    protected int compare(long long1, long long2) {
        return (long1 < long2 ? -1 : (long1 == long2 ? 0 : 1));
    }

    /**
     * Compares the two specified <code>double</code> values. The sign of the integer value returned
     * is the same as that of the integer that would be returned by the call:
     * 
     * <pre>
     * new Double(d1).compareTo(new Double(d2))
     * </pre>
     * 
     * @param d1
     *            the first <code>double</code> to compare
     * @param d2
     *            the second <code>double</code> to compare
     * @return the value <code>0</code> if <code>d1</code> is numerically equal to <code>d2</code>;
     *         a value less than <code>0</code> if <code>d1</code> is numerically less than
     *         <code>d2</code>; and a value greater than <code>0</code> if <code>d1</code> is
     *         numerically greater than <code>d2</code>.
     * @since 1.4
     */
    public static int compare(double d1, double d2) {
        return Double.compare(d1, d2);
    }
}
