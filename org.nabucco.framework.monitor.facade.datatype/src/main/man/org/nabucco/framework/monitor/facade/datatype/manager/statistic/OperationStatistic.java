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
package org.nabucco.framework.monitor.facade.datatype.manager.statistic;

/**
 * OperationStatistic
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public abstract class OperationStatistic implements MonitorStatistic {

    /** Default serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Name of the service */
    private String serviceName;

    /** Name of the service operation */
    private String operationName;

    /** Count of all Service Operation */
    private int count = 0;

    /** Minimum Duration of a Service Operation */
    private long minDuration = Long.MAX_VALUE;

    /** Timestamp of the minimum Duration */
    private long minTimestamp = 0l;

    /** Maximum Duration of a Service Operation */
    private long maxDuration = 0l;

    /** Timestamp of the maximum Duration */
    private long maxTimestamp = 0l;

    /** Mean of all Service Operation Durations */
    private double durationMean = 0l;

    /** Sum of all Service Operation Durations */
    private long durationSum = 0l;

    /** Sum of all squared Service Operation Durations */
    private long durationSquareSum = 0l;

    /** Dispersion from the average */
    private double standardDeviation = 0.0d;

    /** The variance */
    private double variance = 0.0d;

    /**
     * Creates a new {@link OperationStatistic} instance.
     * 
     * @param serviceName
     *            name of the service
     * @param operationName
     *            name of the service operation
     */
    public OperationStatistic(String serviceName, String operationName) {
        this.serviceName = serviceName;
        this.operationName = operationName;
    }

    /**
     * Add a duration of a service operation to the aggregation;
     * 
     * @param duration
     *            the duration of the operation
     * @param timestamp
     *            the timestamp of the operation
     */
    protected void update(long duration, long timestamp) {
        if (duration < this.minDuration) {
            this.newMinimum(duration, timestamp);
        }
        if (duration >= this.maxDuration) {
            this.newMaximum(duration, timestamp);
        }

        this.count++;
        this.durationMean += (duration - this.durationMean) / this.count;

        this.durationSum += duration;
        this.durationSquareSum += (duration * duration);

        this.standardDeviation = this.calculateStandardDeviation();

        this.variance = this.standardDeviation * this.standardDeviation;
    }

    /**
     * New statistical minimum.
     * 
     * @param duration
     *            the new duration
     * @param timestamp
     *            the timestamp
     */
    private void newMinimum(long duration, long timestamp) {
        this.minDuration = duration;
        this.minTimestamp = timestamp;
    }

    /**
     * New statistical maximum.
     * 
     * @param duration
     *            the new duration
     * @param timestamp
     *            the timestamp
     */
    private void newMaximum(long duration, long timestamp) {
        this.maxDuration = duration;
        this.maxTimestamp = timestamp;
    }

    /**
     * Calculates the standard deviation by the given values.
     * <p/>
     * 
     * sqrt( (1 / (n - 1)) * (psum - ((1 / n) * sum�)) )
     * <p/>
     * -->
     * <p/>
     * sqrt( ((n * psum) - sum�) / n * (n-1) )
     * 
     * @return the standard deviation
     */
    private double calculateStandardDeviation() {
        long dividend = this.count * this.durationSquareSum - this.durationSum * this.durationSum;
        int divisor = this.count * (this.count - 1);

        if (divisor <= 0) {
            return 0.0d;
        }

        long quotient = dividend / divisor;

        double standardDeviation = Math.sqrt(quotient);

        return standardDeviation;
    }

    /**
     * Getter for the serviceName.
     * 
     * @return Returns the serviceName.
     */
    public String getServiceName() {
        return this.serviceName;
    }

    /**
     * Getter for the operationName.
     * 
     * @return Returns the operationName.
     */
    public String getServiceOperationName() {
        return this.operationName;
    }

    /**
     * Getter for the minDuration.
     * 
     * @return Returns the minDuration.
     */
    public long getMinDuration() {
        return this.minDuration;
    }

    /**
     * Getter for the minTimestamp.
     * 
     * @return Returns the minTimestamp.
     */
    public long getMinTimestamp() {
        return this.minTimestamp;
    }

    /**
     * Getter for the maxDuration.
     * 
     * @return Returns the maxDuration.
     */
    public long getMaxDuration() {
        return this.maxDuration;
    }

    /**
     * Getter for the maxTimestamp.
     * 
     * @return Returns the maxTimestamp.
     */
    public long getMaxTimestamp() {
        return this.maxTimestamp;
    }

    /**
     * Getter for the count.
     * 
     * @return Returns the count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Getter for the durationMean.
     * 
     * @return Returns the durationMean.
     */
    public double getDurationMean() {
        return this.durationMean;
    }

    /**
     * Getter for the durationSum.
     * 
     * @return Returns the durationSum.
     */
    public long getDurationSum() {
        return this.durationSum;
    }

    /**
     * Getter for the durationSquareSum.
     * 
     * @return Returns the durationSquareSum.
     */
    public long getDurationSquareSum() {
        return this.durationSquareSum;
    }

    /**
     * Getter for the standardDeviation.
     * 
     * @return Returns the standardDeviation.
     */
    public double getStandardDeviation() {
        return this.standardDeviation;
    }

    /**
     * Getter for the variance.
     * 
     * @return Returns the variance.
     */
    public double getVariance() {
        return this.variance;
    }

    /**
     * Reset all collected values.
     */
    public void reset() {
        this.minDuration = 0l;
        this.minTimestamp = 0l;
        this.maxDuration = 0l;
        this.maxTimestamp = 0l;
        this.count = 0;
        this.durationMean = 0l;
        this.durationSum = 0l;
        this.durationSquareSum = 0l;
        this.standardDeviation = 0.0d;
        this.variance = 0.0d;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.serviceName == null) ? 0 : this.serviceName.hashCode());
        result = prime
                * result + ((this.operationName == null) ? 0 : this.operationName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OperationStatistic other = (OperationStatistic) obj;
        if (this.serviceName == null) {
            if (other.serviceName != null) {
                return false;
            }
        } else if (!this.serviceName.equals(other.serviceName)) {
            return false;
        }
        if (this.operationName == null) {
            if (other.operationName != null) {
                return false;
            }
        } else if (!this.operationName.equals(other.operationName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.serviceName);
        result.append('.');
        result.append(this.operationName);
        result.append("\n\t-Count: ");
        result.append(this.count);
        result.append("\n\t-Min: ");
        result.append(this.minDuration);
        result.append("\n\t-Max : ");
        result.append(this.maxDuration);
        result.append("\n\t-Average: ");
        result.append(this.durationMean);
        result.append("\n\t-Sum: ");
        result.append(this.durationSum);
        result.append("\n\t-Standard Deviation: ");
        result.append(this.standardDeviation);
        result.append("\n\t-Variance: ");
        result.append(this.variance);

        return result.toString();
    }

}
