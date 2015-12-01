/*
 */
package keboola.silverpop.xmlapi.pojo.results;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("RESULT")
public class ImportListResult implements SPResult {

    @XStreamAlias("SUCCESS")
    private boolean success;
    @XStreamAlias("JOB_ID")
    private String jobId;

    public ImportListResult(boolean success, String jobId) {
        this.success = success;
        this.jobId = jobId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
