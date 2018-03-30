package fr.istic.m2il.idm.videogenapp.web.rest.errors;

public class EmailAlreadyUsedException extends BadRequestAlertException {

    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email address already input use", "userManagement", "emailexists");
    }
}
