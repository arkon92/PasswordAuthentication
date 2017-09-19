package com.arkon_learning.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by arkon92 on 17/09/2017.
 */

@Component
public class ApplicationConfiguration {

    @Value("${application.html.index.file:/static/index.html}")
    private String indexFile;

    @Value("${application.html.register.file:/static/register.html}")
    private String registerFile;

    @Value("${application.html.signIn.file:/static/signIn.html}")
    private String signInFile;


    public String getIndexFile() {
        return indexFile;
    }

    public String getRegisterFile() {
        return registerFile;
    }

    public String getSignInFile() {
        return signInFile;
    }

}
