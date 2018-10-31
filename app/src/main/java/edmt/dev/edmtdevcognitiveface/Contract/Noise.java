package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

public class Noise {
    /**
     * Definition of noise level
     */
    public enum NoiseLevel {
        /**
         * Low noise level indicating a clear face image
         */
        @SerializedName("low")
        Low,
        /**
         * Medium noise level indicating a slightly noisy face image
         */
        @SerializedName("medium")
        Medium,
        /**
         * High noise level indicating a extremely noisy face image
         */
        @SerializedName("high")
        High
    }

    /**
     * Indicating noise level of face image
     */
    public NoiseLevel noiseLevel;

    /**
     * Noise value is in range [0, 1]. Larger value means the face image is more noisy.
     * [0, 0.3) is low noise level.
     * [0.3, 0.7) is medium noise level.
     * [0.7, 1] is high noise level.
     */
    public double value;
}
