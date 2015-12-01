/*
 */
package keboola.silverpop.xmlapi.pojo.results;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("RESULT")
public class GetJobStatusResult implements SPResult {

    @XStreamAlias("SUCCESS")
    private boolean success;
    @XStreamAlias("JOB_ID")
    private String sessionId;
    @XStreamAlias("JOB_STATUS")
    private String jobStatus;
    @XStreamAlias("JOB_DESCRIPTION")
    private String jobDescription;
    @XStreamAlias("PARAMETERS")
    private List<Parameter> parameters;

    @XStreamAlias("PARAMETER")
    static class Parameter {

        @XStreamAlias("NAME")
        private String name;
        @XStreamAlias("VALUE")
        private String value;

        public Parameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public boolean isSuccess() {
        return success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Map<String, String> getParameters() {
        Map<String, String> pars = new HashMap<String, String>();
        for (Parameter par : parameters) {
            pars.put(par.name, par.value);
        }
        return pars;
    }

}
