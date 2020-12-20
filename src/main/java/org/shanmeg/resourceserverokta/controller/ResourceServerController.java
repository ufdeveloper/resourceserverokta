package org.shanmeg.resourceserverokta.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class ResourceServerController {

    /**
     * This is the right @AuthenticationPrincipal to use when using JWT authorization.
     */
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String helloWorld(@AuthenticationPrincipal Jwt jwt) {
        log.info("got request including jwt {}", jwt);

        String holderName = jwt.getClaimAsString("sub");

        return "Hello " + (holderName != null ? holderName : "anonymous");
    }

    /**
     * The principal will not be resolved to an OidcUser object.
     * See {@link org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver#resolveArgument} for how the annotation works.
     * In short, when using Jwt authorization, the {@link org.springframework.security.core.Authentication} object in the {@link org.springframework.security.core.context.SecurityContextHolder}
     * is a {@link org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken}. The principal in this case is a {@link Jwt} object.
     * This object is not assignable to {@link OidcUser}, and the resolver returns null.
     */
    @RequestMapping(path = "/helloOidcUser", method = RequestMethod.GET)
    public String helloWorldOidcUser(@AuthenticationPrincipal OidcUser oidcUser) {
        log.info("got request including oidcUser {}", oidcUser);

        String holderName = oidcUser.getClaimAsString("sub");
        String name = oidcUser.getName();

        return "Hello " + (name != null ? name : "anonymous");
    }

    /**
     * The principal will not be resolved to an OidcUser object.
     */
    @RequestMapping(path = "/helloOidcUserNoAnnotation", method = RequestMethod.GET)
    public String helloWorldOidcUserNoAnnotation(OidcUser oidcUser) {
        log.info("got request including oidcUser {}", oidcUser);

        String holderName = oidcUser.getClaimAsString("sub");
        String name = oidcUser.getName();

        return "Hello " + (name != null ? name : "anonymous");
    }

    /**
     * Since {@link Principal} is the superclass, this works. The {@link org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken}
     * class will be the actual principal subclass used here.
     */
    @RequestMapping(path = "/helloPrincipal", method = RequestMethod.GET)
    public String helloWorld(Principal principal) {
        log.info("got request including principal {}", principal);

        return "Hello " + principal.getName();
    }

    /**
     *  This does not work. Similar to {@link #helloWorldOidcUser(OidcUser)} above, the {@link Jwt} object is not assignable to {@link Principal}.
     */
    @RequestMapping(path = "/helloAuthenticationPrincipal", method = RequestMethod.GET)
    public String helloAuthenticationPrincipal(@AuthenticationPrincipal Principal principal) {
        log.info("got request including AuthenticationPrincipal {}", principal);

        return "Hello " + principal.getName();
    }
}
