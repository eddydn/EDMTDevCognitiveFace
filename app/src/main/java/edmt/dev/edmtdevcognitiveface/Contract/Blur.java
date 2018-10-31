package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

public class Blur {
    /**
     * Definition of blur level
     */
    public enum BlurLevel{
        /**
         * Low blur level indicating a clear face image
         */
        @SerializedName("low")
        Low,
        /**
         * Medium blur level indicating a slightly blurry face image
         */
        @SerializedName("medium")
        Medium,
        /**
         * High blur level indicating a extremely blurry face image
         */
        @SerializedName("high")
        High
    }

    /**
     * Indicating the blur level of face image
     */
    public BlurLevel blurLevel;

    /**
     * Blur value is in range [0, 1]. Larger value means the face image is more blurry.
     * [0, 0.25) is low blur level.
     * [0.25, 0.75) is medium blur level.
     * [0.75, 1] is high blur level.
     */
    public double value;
}
