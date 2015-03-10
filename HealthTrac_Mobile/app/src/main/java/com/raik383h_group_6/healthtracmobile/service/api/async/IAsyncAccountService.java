package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.UserLogin;

public interface IAsyncAccountService {
    void registerAccountAsync(UserLogin userLogin) throws Exception;
}
