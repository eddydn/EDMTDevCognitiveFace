package edmt.dev.edmtdevcognitiveface.Rest;

import com.google.gson.Gson;

public class ServiceException  extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Gson errorObject) {
        super(errorObject.toString());
    }
}
