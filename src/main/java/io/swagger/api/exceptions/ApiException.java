package io.swagger.api.exceptions;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
public class ApiException extends RuntimeException {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ApiException (int code, String msg, Throwable cause) {
        super(msg,cause);
        this.code = code;
    }

    public int getCode() { return code; }
}
