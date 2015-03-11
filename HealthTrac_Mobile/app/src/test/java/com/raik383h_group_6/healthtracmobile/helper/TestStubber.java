package com.raik383h_group_6.healthtracmobile.helper;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ACCOUNT_NOT_MADE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.EMPTY_FIELD_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ERROR_FIND_PROFILE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FEMALE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_PREF_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_DATE_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_FIELD_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.MALE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SECRET_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SIGNIN_ERR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TEAM_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TWITTER;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATED_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATE_ERR;
import static org.mockito.Mockito.when;

public class TestStubber {
    private TestStubber() {
        //unused
    }

    public static void stubViewForResources(BaseView view) {
        when(view.getResource(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        when(view.getResource(R.string.EXTRA_USER)).thenReturn(USER_KEY);
        when(view.getResource(R.string.invalid_field_message)).thenReturn(INVALID_FIELD_MSG);
        when(view.getResource(R.string.user_updated_message)).thenReturn(USER_UPDATED_MSG);
        when(view.getResource(R.string.update_user_error)).thenReturn(USER_UPDATE_ERR);
        when(view.getResource(R.string.pref_access_grant)).thenReturn(GRANT_PREF_KEY);
        when(view.getResource(R.string.error_find_profile)).thenReturn(ERROR_FIND_PROFILE);
        when(view.getResource(R.string.PROVIDER_FACEBOOK)).thenReturn(FACEBOOK);
        when(view.getResource(R.string.PROVIDER_TWITTER)).thenReturn(TWITTER);
        when(view.getResource(R.string.EXTRA_ACCESS_TOKEN)).thenReturn(TOKEN_KEY);
        when(view.getResource(R.string.EXTRA_ACCESS_SECRET)).thenReturn(SECRET_KEY);
        when(view.getResource(R.string.EXTRA_PROVIDER)).thenReturn(PROVIDER_KEY);
        when(view.getResource(R.string.male_label)).thenReturn(MALE);
        when(view.getResource(R.string.empty_field_error)).thenReturn(EMPTY_FIELD_ERROR);
        when(view.getResource(R.string.invalid_date_error)).thenReturn(INVALID_DATE_ERROR);
        when(view.getResource(R.string.EXTRA_TEAM)).thenReturn(TEAM_KEY);
        when(view.getResource(R.string.label_female)).thenReturn(FEMALE);
        when(view.getResource(R.string.account_not_made)).thenReturn(ACCOUNT_NOT_MADE);
        when(view.getResource(R.string.sign_in_error)).thenReturn(SIGNIN_ERR);
        when(view.getResource(R.string.facebook_male_label)).thenReturn(TestConstants.MALE);
    }

    public static void stubUserViewGetters(UserValidationView view, User user) {
        when(view.getBirthDate()).thenReturn(FormatUtils.format(user.getBirthDate()));
        when(view.getEmail()).thenReturn(user.getEmail());
        when(view.getFirstName()).thenReturn(user.getFirstName());
        when(view.getHeight()).thenReturn(FormatUtils.format(user.getHeight()));
        when(view.getLastName()).thenReturn(user.getLastName());
        when(view.getLocation()).thenReturn(user.getLocation());
        when(view.getPreferredName()).thenReturn(user.getPreferredName());
        when(view.getSex()).thenReturn(user.getSex().name());
        when(view.getUsername()).thenReturn(user.getUserName());
        when(view.getWeight()).thenReturn(FormatUtils.format(user.getWeight()));
    }

}
