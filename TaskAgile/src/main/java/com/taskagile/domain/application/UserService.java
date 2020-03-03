package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.RegistrationCommand;

public interface UserService {
    void register(RegistrationCommand registrationCommand) throws Exception;
}
