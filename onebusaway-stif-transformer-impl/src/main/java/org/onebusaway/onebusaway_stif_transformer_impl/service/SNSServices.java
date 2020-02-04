package org.onebusaway.onebusaway_stif_transformer_impl.service;


import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Convenience methods for using AWS SNS.  Looks for keys
 * populated in environment by default.
 */
public class SNSServices {

    private AmazonSNS _sns;
    private Boolean _initialized = null;
    private final Logger _log = LoggerFactory.getLogger(SNSServices.class);

    public boolean publish(String message) {
        AmazonSNS sns = getSns();
        if (sns == null) return false;
        PublishRequest pr = new PublishRequest(getTopicArn(), message);
        PublishResult result = null;
        try {
            result = _sns.publish(pr);
            return false;
        } catch (Exception any) {
            // don't let a failure here prevent the build from continuing
            _log.error("exception publishing:", any);
        }
        return result.getMessageId() != null;
    }

    private String getTopicArn() {
        return System.getProperty("sns.topic");
    }

    public AmazonSNS getSns() {
        if (_initialized == null) {
            try {
                // if not configured simply exit
                if (getTopicArn() == null) return null;
                _sns = AmazonSNSClientBuilder.defaultClient();
                _initialized = true;
            } catch (Exception any) {
                _initialized = false;
            }
        }
        if (!_initialized) return null;
        return _sns;
    }
}
