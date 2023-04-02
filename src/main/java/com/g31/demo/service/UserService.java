package com.g31.demo.service;

import com.g31.demo.model.AuditUser;
import com.g31.demo.web.RegisterRequest;
import com.g31.demo.web.UpdateRequest;

/**
 * @Description:
 */
public interface UserService {
    void save(RegisterRequest request);
    void update(UpdateRequest request);
    void checkUserPresent(AuditUser auditUser);

}
