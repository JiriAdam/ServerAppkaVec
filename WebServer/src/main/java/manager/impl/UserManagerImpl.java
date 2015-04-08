package manager.impl;

/**
 * Created by Irrielde on 14.12.2014.
 */

import manager.UserRoleManager;
import mybatis.dao.AppUserMapper;
import mybatis.dao.EventFilterMapper;
import mybatis.dao.EventTypeMapper;
import mybatis.model.complex.AppUser;
import mybatis.model.basic.EventFilter;
import mybatis.model.basic.EventType;
import mybatis.model.basic.UserRole;
import mybatis.model.complex.FullProfile;
import mybatis.model.complex.UserEventFilter;
import mybatis.model.enumeration.RoleType;
import org.springframework.beans.factory.annotation.Autowired;

import manager.UserManager;
import org.springframework.stereotype.Service;
import web.model.UserFO;
import webservice.rest.model.RegisterUserRO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserManagerImpl implements UserManager{

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private UserRoleManager userRoleManager;

    @Autowired
    private EventFilterMapper eventFilterMapper;

    @Autowired
    private EventTypeMapper eventTypeMapper;


    private String usedDateFormat = "dd/MM/yyyy";

    @Override
    public Long addUser(RegisterUserRO user){

        AppUser newUser = new AppUser();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword1(user.getPassword());
        newUser.setEnabled(true);

        newUser.setNotifyViaEmail(false);
        newUser.setNotifyViaPush(false);



        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);

        try {
            Date dateFromString = sdf.parse(user.getBirthday());
            newUser.setBirthDate(dateFromString);
        } catch (ParseException e) {
            newUser.setBirthDate(new Date());
            System.err.println(e.toString());
        }



        if( appUserMapper.insert(newUser) > 0) {

            Long insertedID = newUser.getId();

            userRoleManager.addUserRole(insertedID, RoleType.BASIC_USER);


            boolean ok = createDefaultFilter(newUser);

            if(!ok){
                return -2L;
            }

            return insertedID;

        }else{
            return -1L;
        }



    }

    @Override
    public Long addUser(UserFO fo) {

        AppUser newUser = new AppUser();
        newUser.setUsername(fo.getUsername());
        newUser.setEmail(fo.getEmail());
        newUser.setPassword1(fo.getPassword1());
        newUser.setEnabled(true);



        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);

        try {
            Date dateFromString = sdf.parse(fo.getBirthDate());
            newUser.setBirthDate(dateFromString);
        } catch (ParseException e) {
            newUser.setBirthDate(new Date());
            System.err.println(e.toString());
        }



        if( appUserMapper.insert(newUser) > 0) {

            Long insertedID = newUser.getId();

            userRoleManager.addUserRole(insertedID, RoleType.BASIC_USER);


            boolean ok = createDefaultFilter(newUser);

            if(!ok){
                return -2L;
            }

            return insertedID;

        }else{
            return -1L;
        }

    }

    /**
     * Creates default filter, if relation exists he subscribes to the option ...
     * basicly we set everything to "yes"
     * @param newUser
     * @return
     */
    private boolean createDefaultFilter(AppUser newUser) {


       List<EventType> allTypes = eventTypeMapper.selectAllTypes();

        for (EventType eventType : allTypes) {

            EventFilter ef = new EventFilter();
            ef.setIdEventType(eventType.getId());
            ef.setIdUser(newUser.getId());

            if(eventFilterMapper.insert(ef) <= 0){
                return false;
            }

        }


        return true;
    }

    @Override
    public List<UserRole> getUserRolesTest(Long userID) {

        return appUserMapper.selectRolesList(userID);

    }

    @Override
    public AppUser getUserFromUsername(String name) {

        return appUserMapper.selectByUsername(name);
    }

    @Override
    public FullProfile getFullUserInfoFromID(Long userID) {
        return appUserMapper.selectFullProfileByPrimaryKey(userID);
    }

    @Override
    public UserEventFilter getUserEventFilterFromUsername(String name) {
        return appUserMapper.selectByUsernameWithEventFilter(name);
    }

    @Override
    public List<AppUser> getUsersWithRole(RoleType roleType) {

        return appUserMapper.selectUsersWithRole(RoleType.getRoleIdFromDatabase(roleType));
    }

    @Override
    public boolean updateUserWithBlobs(AppUser appUser) {

        int rowsAffected = appUserMapper.updateByPrimaryKeyWithBLOBs(appUser);

        if(rowsAffected > 0){
            return true;
        }

        return false;
    }

    @Override
    public AppUser getProfileFromID(Long user_id) {
        return appUserMapper.selectProfileByPrimaryKey(user_id);
    }

    @Override
    public List<AppUser> getAllUsersForRegion(Long idRegion) {
        return appUserMapper.selectUsersWithRegionByRegionID(idRegion);
    }

    @Override
    public boolean updateUser(AppUser user) {

        int rowsAffected = appUserMapper.updateByPrimaryKey(user);

        if(rowsAffected > 0){
            return true;
        }

        return false;
    }


    @Override
    public boolean updateUserSelective(AppUser user) {

        int rowsAffected = appUserMapper.updateByPrimaryKeySelective(user);

        if(rowsAffected > 0){
            return true;
        }

        return false;


    }


}
