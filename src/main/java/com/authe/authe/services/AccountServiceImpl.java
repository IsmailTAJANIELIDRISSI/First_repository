package com.authe.authe.services;

import com.authe.authe.entities.AppRole;
import com.authe.authe.entities.AppUser;
import com.authe.authe.repositry.AppRoleRepository;
import com.authe.authe.repositry.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {

        return appUserRepository.save(appUser);
    }

    public AppUser getUserById(Long id){
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {

        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser user=appUserRepository.findByUsername(username);

        return user;
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
