package com.jagex;

import com.jagex.launcher.GameLaunch;

/**
 * Created by Chris on 3/12/2017.
 */
public class LoginResponses {

    public static RSString alreadyLoggedIn = RSString.createString("Your account is already logged in)3");
    public static RSString nonCreatedAccount = RSString.createString("Please register via the website.");
    public static RSString nonValidatedAccount = RSString.createString("This account has yet to be validated.");
    public static RSString websiteError = RSString.createString("A connection could not be made to the website.");
    public static RSString worldError = RSString.createString("Please try using a different world)3");
    public static RSString worldError2 = RSString.createString("Please use a different world)3");
    public static RSString invalidUsernamePassString = RSString.createString("Invalid username or password)3");
    public static RSString accountDisabled = RSString.createString("Your account has been disabled)3");
    public static RSString timeOut = RSString.createString("Connection timed out)3");
    public static RSString noResponse = RSString.createString("No response from server)3");;
    public static RSString tryAgain = RSString.createString("Try again in 60 secs)3)3)3");
    public static RSString reloadPage = RSString.createString("Please reload this page)3");
    public static RSString gameUpdated = RSString.createString(GameLaunch.getSetting().getName() +" has been updated(Q");
    public static RSString worldFull = RSString.createString("This world is full)3");
    public static RSString serverOffline = RSString.createString("Login server offline)3");
    public static RSString cantConnect = RSString.createString("Unable to connect)3");
    public static RSString tooManyConnection = RSString.createString("Too many connections from your address)3");
    public static RSString loginLimit = RSString.createString("Login limit exceeded)3");
    public static RSString badSession = RSString.createString("Bad session id)3");
    public static RSString subscribe = RSString.createString("Please subscribe)1 or use a different world)3");
    public static RSString memberWorldOnly = RSString.createString("You need a members account to login to this world)3");
    public static RSString cantComplete = RSString.createString("Could not complete login)3");
    public static RSString notInFreeArea = RSString.createString("To play on this world move to a free area first)3");
    public static RSString inMemberAreaString = RSString.createString("You are standing in a members)2only area)3");
    public static RSString inviteOnly = RSString.createString("Sorry invited players only)3");
    public static RSString closedBeta = RSString.createString("This world is running a closed Beta)3");
    public static RSString waitOneMinute = RSString.createString("Please wait 1 minute and try again)3");
    public static RSString noReply = RSString.createString("No reply from loginserver)3");
    public static RSString contactSupport = RSString.createString("Please contact customer support)3");
    public static RSString profileError = RSString.createString("Error loading your profile)3");
    public static RSString unexpectedLoginResponse = RSString.createString("Unexpected loginserver response)3");
    public static RSString ruleBreak = RSString.createString("as it was used to break our rules)3");
    public static RSString addressBlocked = RSString.createString("This computers address has been blocked");
    public static RSString serviceUnavailable = RSString.createString("Service unavailable)3");
    public static RSString unexpectedServerResponse = RSString.createString("Unexpected server response)3");
    public static RSString pleaseTryAgain = RSString.createString("Please try again)3");
    public static RSString malformedPacket = RSString.createString("Malformed login packet)3");
    public static RSString invalidServerRequest = RSString.createString("Invalid loginserver requested)3");
    public static RSString lockedAccount = RSString.createString("Press (Wrecover a locked account(W on front page)3");
    public static RSString stolenAccount = RSString.createString("Account locked as we suspect it has been stolen)3");
    public static RSString waitFiveMinutes = RSString.createString("Please wait 5 minutes before trying again)3");
    public static RSString tooManyLogins = RSString.createString("Too many incorrect logins from your address)3");
    public static RSString serverUpdating = RSString.createString("The server is being updated)3");
    public static RSString changePassword = RSString.createString("Press (Wchange your password(W on front page)3");
    public static RSString stolenPassword = RSString.createString("We suspect someone knows your password)3");
    public static RSString checkMessages = RSString.createString("Please check your message)2centre for details)3");
    public static RSString errorConnecting = RSString.createString("Error connecting to server)3");

}
