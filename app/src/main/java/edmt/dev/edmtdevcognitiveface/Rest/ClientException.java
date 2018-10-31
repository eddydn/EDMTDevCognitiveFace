package edmt.dev.edmtdevcognitiveface.Rest;

import edmt.dev.edmtdevcognitiveface.Common.ClientError;

public class ClientException extends Exception {

    public ClientError error = new ClientError();

    public ClientException(ClientError clientError) {
        super(clientError.message);

        error.code = clientError.code;
        error.message = clientError.message;
    }

    public ClientException(String message, int statusCode) {
        super(message);
        Integer code = statusCode;
        error.code = code.toString();
        error.message = message;
    }

    public ClientException(String message) {
        super(message);
    }
}

