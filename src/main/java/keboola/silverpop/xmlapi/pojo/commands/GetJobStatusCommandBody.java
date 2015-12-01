/*
 */
package keboola.silverpop.xmlapi.pojo.commands;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * NOTE: Variables of type Object represent optional parameters:
 * parameter is set: if set to empty object (new Object())
 * parameter is not set: if set to NULL
 */
import com.thoughtworks.xstream.annotations.XStreamAlias;
import keboola.silverpop.xmlapi.pojo.results.GetJobStatusResult;

@XStreamAlias("Body")
public class GetJobStatusCommandBody implements SPCommandBody {

    @XStreamAlias("GetJobStatus")
    private Command command;

    public GetJobStatusCommandBody(String jobId) {
        command = new Command(jobId);
    }

    public Class getResultType() {
        return GetJobStatusResult.class;
    }

    static class Command {

        public Command(String jobId) {
            this.jobId = jobId;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        @XStreamAlias("JOB_ID")
        private String jobId;

    }

}
