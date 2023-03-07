package com.authe.authe.services;

import com.authe.authe.entities.AppRole;
import com.authe.authe.entities.AppUser;

import java.util.List;


public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
