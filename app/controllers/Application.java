package controllers;

import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.FormClient;
import org.pac4j.play.Config;
import org.pac4j.play.java.JavaController;
import org.pac4j.play.java.RequiresAuthentication;

import play.mvc.Result;

public class Application extends JavaController {
    
    public static Result index() throws TechnicalException {
        // profile (maybe null if not authenticated)
        final CommonProfile profile = getUserProfile();
        final String urlFacebook = getRedirectionUrl("FacebookClient", "/?0");
        final String urlTwitter = getRedirectionUrl("TwitterClient", "/?1");
        final String urlForm = getRedirectionUrl("FormClient", "/?2");
        final String urlBasicAuth = getRedirectionUrl("BasicAuthClient", "/?3");
        final String urlCas = getRedirectionUrl("CasClient", "/?4");
        final String urlMyopenid = getRedirectionUrl("MyOpenIdClient", "/?5");
        return ok(views.html.index.render(profile, urlFacebook, urlTwitter, urlForm, urlBasicAuth, urlCas, urlMyopenid));
    }
    
    private static Result protectedIndex() {
        // profile
        final CommonProfile profile = getUserProfile();
        return ok(views.html.protectedIndex.render(profile));
    }
    
    @RequiresAuthentication(clientName = "FacebookClient")
    public static Result facebookIndex() {
        return protectedIndex();
    }
    
    @RequiresAuthentication(clientName = "TwitterClient")
    public static Result twitterIndex() {
        return protectedIndex();
    }
    
    @RequiresAuthentication(clientName = "FormClient")
    public static Result formIndex() {
        return protectedIndex();
    }
    
    @RequiresAuthentication(clientName = "BasicAuthClient")
    public static Result basicauthIndex() {
        return protectedIndex();
    }
    
    @RequiresAuthentication(clientName = "CasClient")
    public static Result casIndex() {
        return protectedIndex();
    }
    
    @RequiresAuthentication(clientName = "MyOpenIdClient")
    public static Result myopenidIndex() {
        return protectedIndex();
    }
    
    public static Result theForm() throws TechnicalException {
        final FormClient formClient = (FormClient) Config.getClients().findClient("FormClient");
        return ok(views.html.theForm.render(formClient.getCallbackUrl()));
    }
}
