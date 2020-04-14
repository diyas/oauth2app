package com.auth.app.payload;

import com.auth.app.payload.model.Credential;
import com.auth.app.payload.model.Login;
import lombok.Data;

@Data
public class RequestAuth {
    private Credential credential;
    private Login login;
}
