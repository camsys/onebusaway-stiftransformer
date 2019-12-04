package org.onebusaway.onebusaway_stif_transformer.service;


import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * encapsulate the various forms of communication needed for the Deployer
 */
public class NotificationService {
    private final Logger _log = LoggerFactory.getLogger(NotificationService.class);
    private SNSServices _sns = new SNSServices();
    private String _version = null;

    public NotificationService(String version) {
        _version = version;
    }

    public boolean sendSuccess(String message) {
        _log.debug(message);
        boolean status = false;
        try {
            status = _sns.publish("SUCCESS(" + _version + "):  " + message);
        } catch (Exception any) {
            // don't let sns issues interrupt process
            _log.error("sendSuccess issue: {}", any, any);
        }

        return status;
    }

    public boolean sendWarning(String message) {
        _log.debug(message);
        boolean status = false;
        message = message.substring(0, Math.min(message.length(), 12500));
        try {
            status = _sns.publish("WARNING(" + _version + "):  " + message);
        } catch (Exception any) {
            // don't let sns issues interrupt process
            _log.error("sendWarning issue: {}", any, any);
        }
        return status;
    }

    public boolean sendFailure(String message) {
        _log.debug(message);
        boolean status = false;
        message = message.substring(0, Math.min(message.length(), 12500));
        try {
            status = _sns.publish("FAILURE(" + _version + "):  " + message);
        } catch (Exception any) {
            // don't let sns issues interrupt process
            _log.error("sendFailure issue: {}", any, any);
        }
        return status;
    }

    public boolean sendFailure(Exception cause) {
        _log.debug(cause.toString());
        StringWriter errors = new StringWriter();
        cause.printStackTrace(new PrintWriter(errors));
        boolean status = false;
        try {
            status = _sns.publish("FAILURE(" + _version + "):  " + errors.toString());
        } catch (Exception any) {
            // don't let sns issues interrupt process
            _log.error("sendFailure issue: {}", any, any);
        }
        return status;
    }


}

