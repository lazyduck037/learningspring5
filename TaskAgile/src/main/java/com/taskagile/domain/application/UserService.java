package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.RegistrationCommand;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegistrationCommand registrationCommand) throws Exception;
}
