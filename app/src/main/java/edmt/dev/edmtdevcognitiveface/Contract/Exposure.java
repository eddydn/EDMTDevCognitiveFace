package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

public class Exposure {
    /**
     * Definition of exposure level
     */
    public enum ExposureLevel{
        /**
         * Indicating face image is in under exposure
         */
        @SerializedName("underExposure")
        UnderExposure,
        /**
         * Indicating face image is in good exposure
         */
        @SerializedName("goodExposure")
        GoodExposure,
        /**
         * Indicating face image is in over exposure
         */
        @SerializedName("overExposure")
        OverExposure
    }

    /**
     * Indicating exposure level of face image
     */
    public ExposureLevel exposureLevel;

    /**
     * Exposure value is in range [0, 1]. Larger value means the face image is more brighter.
     * [0, 0.25) is under exposure.
     * [0.25, 0.75) is good exposure.
     * [0.75, 1] is over exposure.
     */
    public double value;
}
