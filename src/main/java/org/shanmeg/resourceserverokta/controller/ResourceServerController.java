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

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String helloWorld(@AuthenticationPrincipal Jwt jwt) {
        log.info("got request including jwt {}", jwt);

        String holderName = jwt.getClaimAsString("sub");

        return "Hello " + (holderName != null ? holderName : "anonymous");
    }

    @RequestMapping(path = "/helloOidcUser", method = RequestMethod.GET)
    public String helloWorldOidcUser(@AuthenticationPrincipal OidcUser oidcUser) {
        log.info("got request including oidcUser {}", oidcUser);

        String holderName = oidcUser.getClaimAsString("sub");
        String name = oidcUser.getName();

        return "Hello " + (name != null ? name : "anonymous");
    }

    @RequestMapping(path = "/helloOidcUserNoAnnotation", method = RequestMethod.GET)
    public String helloWorldOidcUserNoAnnotation(OidcUser oidcUser) {
        log.info("got request including oidcUser {}", oidcUser);

        String holderName = oidcUser.getClaimAsString("sub");
        String name = oidcUser.getName();

        return "Hello " + (name != null ? name : "anonymous");
    }

    @RequestMapping(path = "/helloPrincipal", method = RequestMethod.GET)
    public String helloWorld(Principal principal) {
        log.info("got request including principal {}", principal);

        return "Hello " + principal.getName();
    }

    @RequestMapping(path = "/helloAuthenticationPrincipal", method = RequestMethod.GET)
    public String helloAuthenticationPrincipal(@AuthenticationPrincipal Principal principal) {
        log.info("got request including AuthenticationPrincipal {}", principal);

        return "Hello " + principal.getName();
    }
}
