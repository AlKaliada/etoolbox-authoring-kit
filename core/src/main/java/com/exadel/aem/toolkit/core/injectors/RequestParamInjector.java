package com.exadel.aem.toolkit.core.injectors;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.spi.DisposalCallbackRegistry;
import org.apache.sling.models.spi.Injector;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.aem.toolkit.api.annotations.injectors.RequestParam;

/**
 * Injects into a Sling model the value of a HTTP request parameter (multiple parameters) obtained
 * via a {@code SlingHttpServletRequest} object
 * @see RequestParam
 * @see Injector
 */
@Component(service = Injector.class,
    property = Constants.SERVICE_RANKING + ":Integer=" + InjectorConstants.SERVICE_RANKING
)
public class RequestParamInjector implements Injector {

    private static final Logger LOG = LoggerFactory.getLogger(RequestParamInjector.class);

    public static final String NAME = "eak-request-parameter-injector";

    /**
     * Retrieves the name of the current instance
     * @return String value
     * @see Injector
     */
    @Nonnull
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Attempts to inject a value into the given adaptable
     * @param adaptable        A {@link SlingHttpServletRequest} or a {@link Resource} instance
     * @param name             Name of the Java class member to inject the value into
     * @param type             Type of receiving Java class member
     * @param element          {@link AnnotatedElement} instance that facades the Java class member allowing to retrieve
     *                         annotation objects
     * @param callbackRegistry {@link DisposalCallbackRegistry} object
     * @return The value to inject, or null in case injection is not possible
     * @see Injector
     */
    @Override
    public Object getValue(
        @Nonnull Object adaptable,
        String name,
        @Nonnull Type type,
        @Nonnull AnnotatedElement element,
        @Nonnull DisposalCallbackRegistry callbackRegistry) {

        RequestParam annotation = element.getDeclaredAnnotation(RequestParam.class);
        if (annotation == null) {
            return null;
        }

        SlingHttpServletRequest request = InjectorUtils.getRequest(adaptable);
        if (request == null) {
            return null;
        }

        String paramName = annotation.name().isEmpty() ? name : annotation.name();

        if (InjectorUtils.isValidObjectType(type, String.class)) {
            return request.getParameter(paramName);

        } else if (InjectorUtils.isValidObjectType(type, RequestParameter.class)) {
            return request.getRequestParameter(paramName);

        } else if (InjectorUtils.isValidCollection(type, RequestParameter.class)) {
            return request.getRequestParameterList();

        } else if (InjectorUtils.isValidArray(type, RequestParameter.class)) {
            return request.getRequestParameters(paramName);

        } else if (InjectorUtils.isValidObjectType(type, RequestParameterMap.class)) {
            return request.getRequestParameterMap();
        }

        LOG.debug(InjectorConstants.EXCEPTION_UNSUPPORTED_TYPE, type);
        return null;
    }
}
