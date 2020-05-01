package com.auth.app.user;

import com.auth.app.global.Oauth2Properties;
import com.auth.app.global.SettingEnum;
import com.auth.app.payload.RequestAuth;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthCtl {

    @Autowired
    private Oauth2Properties oauth2Properties;

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody RequestAuth request) {
        String credentials = oauth2Properties.getCredentials();
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);
        headers.setBasicAuth(oauth2Properties.getClientId(), oauth2Properties.getClientSecret());
        HttpEntity<String> req = new HttpEntity<String>(headers);

        String accessTokenUrl = oauth2Properties.getUrl();
        accessTokenUrl += "?username=" + request.getLogin().getUsername();
        accessTokenUrl += "&password=" + request.getLogin().getPassword();
        accessTokenUrl += "&grant_type=" + SettingEnum.PASSWORD.value;
        ResponseEntity<String> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, req, String.class);
        return response;
    }

    public void refreshToken() {

    }

}
