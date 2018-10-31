package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

public class Accessory {
    /**
     * Accessory types
     */
    public enum AccessoryType {
        @SerializedName("headwear")
        Headwear,
        @SerializedName("glasses")
        Glasses,
        @SerializedName("mask")
        Mask
    }

    /**
     * Indicating the accessory type
     */
    public AccessoryType type;

    /**
     * Indicating the confidence for accessory type
     */
    public double confidence;
}
