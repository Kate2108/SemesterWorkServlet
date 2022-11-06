package services.validators;

import services.MessageForUser;

public class ValidatorImplSchedule{
    public MessageForUser validateSchedule(int hs, int ms, int he, int me) {
        if(he < hs){
            return new MessageForUser(false,"End hour cannot be less than start hour");
        }
        if(he == hs && me < ms){
            return new MessageForUser(false,"End minutes cannot be less than start minutes");
        }
        if(he == hs && me == ms){
            return new MessageForUser(false,"Training cannot last 0 minutes");
        }
        return new MessageForUser(true);
    }
}
