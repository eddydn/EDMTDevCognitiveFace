package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TrainingStatus {
    public enum Status {
        /**
         * Training is succeeded.
         */
        @SerializedName("succeeded")
        Succeeded,

        /**
         * Training is failed.
         */
        @SerializedName("failed")
        Failed,

        /**
         * Training is in progress.
         */
        @SerializedName("running")
        Running
    }

    /**
     * Training status.
     */
    public Status status;

    /**
     * Creation date time.
     */
    public Date createdDateTime;

    /**
     * Last action date time.
     */
    public Date lastActionDateTime;

    /**
     * Message. Only when failed
     */
    public String message;
}
