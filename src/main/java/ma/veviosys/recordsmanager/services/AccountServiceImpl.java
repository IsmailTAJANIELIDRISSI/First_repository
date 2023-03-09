package ma.veviosys.recordsmanager.services;

import ma.veviosys.recordsmanager.entities.AppRole;
import ma.veviosys.recordsmanager.entities.AppUser;
import ma.veviosys.recordsmanager.repositry.AppRoleRepository;
import ma.veviosys.recordsmanager.repositry.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
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
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=appUserRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
