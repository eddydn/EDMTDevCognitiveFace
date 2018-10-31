package edmt.dev.edmtdevcognitiveface.Contract;

import com.google.gson.annotations.SerializedName;

public class Hair {
    /**
     * Indicating the confidence of a bald head
     */
    public double bald;

    /**
     * Indicating whether hair is occluded or not
     */
    public boolean invisible;

    /**
     * Hair color details
     */
    public static class HairColor{
        /**
         * Hair color type
         */
        public enum HairColorType {
            @SerializedName("unknown")
            Unknown,
            @SerializedName("white")
            White,
            @SerializedName("gray")
            Gray,
            @SerializedName("blond")
            Blond,
            @SerializedName("brown")
            Brown,
            @SerializedName("red")
            Red,
            @SerializedName("black")
            Black,
            @SerializedName("other")
            Other
        }

        /**
         * Indicating the hair color type
         */
        public HairColorType color;

        /**
         * Indicating the confidence for hair color type
         */
        public double confidence;
    };

    /**
     * Indicating all possible hair colors with confidences
     */
    public HairColor[] hairColor;
}
