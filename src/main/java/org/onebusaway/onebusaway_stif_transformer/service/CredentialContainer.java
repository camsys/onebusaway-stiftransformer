package org.onebusaway.onebusaway_stif_transformer.service;



public class CredentialContainer {
    private String profile;

    public CredentialContainer(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public static CredentialContainer getDefault() {
        return new CredentialContainer("default");
    }
}
