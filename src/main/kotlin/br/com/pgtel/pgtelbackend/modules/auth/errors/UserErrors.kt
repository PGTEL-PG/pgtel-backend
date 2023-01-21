package br.com.pgtel.pgtelbackend.modules.auth.errors

import br.com.pgtel.pgtelbackend.core.exceptions.Error

object UserErrors {

    val PASSWORD_IS_BLANK = Error("USER-001", "Password is blank")
    val NAME_IS_BLANK = Error("USER-001", "Name is blank")
    val EMAIL_IS_BLANK = Error("USER-002", "Email is blank")
}
