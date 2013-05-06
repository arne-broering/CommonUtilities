package org.n52.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * @author <a href="mailto:broering@52north.org">Arne Broering</a>
 */
public class HttpUtils {

    static Logger LOGGER = Logger.getLogger(HttpUtils.class.getName());
    
    public static InputStream sendGetMessage(String serviceURL, List<NameValuePair> parameters) throws IOException {
        InputStream is = null;

        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(serviceURL);

        NameValuePair[] paramArray = new NameValuePair[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            paramArray[i] = parameters.get(i);
        }
        method.setQueryString(paramArray);

        httpClient.executeMethod(method);

        LOGGER.info("GET-method sent to: " + method.getURI());

        is = method.getResponseBodyAsStream();

        return is;
    }
    
    public static InputStream sendGetMessage(String serviceURL, String queryString) throws IOException {
        InputStream is = null;

        HttpClient httpClient = new HttpClient();
        httpClient.setHostConfiguration(getHostConfiguration(new URL(serviceURL)));
        GetMethod method = new GetMethod(serviceURL);

        method.setQueryString(queryString);

        httpClient.executeMethod(method);

        LOGGER.info("GET-method sent to: " + method.getURI());

        is = method.getResponseBodyAsStream();

        return is;
    }

    /**
     * sends a POST-request using org.apache.commons.httpclient.HttpClient.
     * 
     * @param serviceURL
     * @param request
     * @return
     */
    public static InputStream sendPostMessage(String serviceURL, String request) throws IOException {

        InputStream is = null;

        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(serviceURL);

        method.setRequestEntity(new StringRequestEntity(request, "text/xml", "UTF-8"));
      
        HostConfiguration hostConfig = getHostConfiguration(new URL(serviceURL));
        httpClient.setHostConfiguration(hostConfig);
        httpClient.executeMethod(method);

        LOGGER.info("POST-request sent to: " + method.getURI());
        LOGGER.info("Sent request was: " + request);
        
        is = method.getResponseBodyAsStream();

        return is;
    }
    
    protected static HostConfiguration getHostConfiguration(URL serviceURL) {
        HostConfiguration hostConfig = new HostConfiguration();
        
        // apply proxy settings:
        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");
        String nonProxyHosts = System.getProperty("http.nonProxyHosts");
        
        // check if service url is among the non-proxy-hosts:
        boolean serviceIsNonProxyHost = false;
        if (nonProxyHosts != null && nonProxyHosts.length() > 0)
        {   
            String[] nonProxyHostsArray = nonProxyHosts.split("\\|");
            String serviceHost = serviceURL.getHost();
            
            for (String nonProxyHost : nonProxyHostsArray) {
                if ( nonProxyHost.equals(serviceHost)) {
                    serviceIsNonProxyHost = true;
                    break;
                }
            }
        }
        // set proxy:
        if ( serviceIsNonProxyHost == false
          && host != null && host.length() > 0
          && port != null && port.length() > 0)
        {
            int portNumber = Integer.parseInt(port);
            hostConfig.setProxy(host, portNumber);
            LOGGER.info("Using proxy: " + host + " on port: " + portNumber);
        }
        
        return hostConfig;
    }
}
