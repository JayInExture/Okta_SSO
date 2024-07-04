package com.sso.okta.demo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @GetMapping("/admin")
    @ResponseBody
    public String adminPage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        return "Welcome Admin!";
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        String emailAddress = principal.getFirstAttribute("email");
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("userAttributes", principal.getAttributes());
        return "index";
    }

    @GetMapping("/roles")
    @ResponseBody
    public String roles(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
      return "Roles:-"+principal.getAttributes();
    }
}
