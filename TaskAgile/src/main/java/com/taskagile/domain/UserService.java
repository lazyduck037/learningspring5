package com.taskagile.domain;

import com.taskagile.domain.commands.RegistrationCommand;

public interface UserService {
    void register(RegistrationCommand registrationCommand);
}
