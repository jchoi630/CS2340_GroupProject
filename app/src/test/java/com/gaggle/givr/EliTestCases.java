package com.gaggle.givr;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import com.gaggle.givr.LoginActivity

public class EliTestCases {
    public static final int TIMEOUT = 200;
    public LoginActivity testPiece;
    public static final LoginActivity.LoginState LOGIN = LoginActivity.LoginState.LOGIN;

    @Before
    public void setup() { testPiece = new LoginActivity();}

    @Test (timeout = TIMEOUT)
    public void testGetSubmitButtonText() {
        int i = testPiece.getSubmitButtonText(LOGIN);
        assertEquals(i, R.string.login_button);
    }




}
