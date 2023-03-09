package com.authe.authe.controller;

import com.authe.authe.entities.AppRole;
import com.authe.authe.entities.AppUser;
import com.authe.authe.services.AccountServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    //@PostAuthorize("hasAuthority('ADMIN')")
    public List<AppUser> GetUsers(){
        return accountService.listUsers();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add/{roleName}")
    //@PostAuthorize("hasAuthority('ADMIN')")
    public void saveUser(@RequestBody AppUser appUser,@PathVariable String roleName){
        System.out.println(appUser);
        System.out.println(roleName);
        accountService.addNewUser(appUser);
        accountService.addRoleToUser(appUser.getUsername(),"ADMIN");
         //accountService.addRoleToUser(userForm.appUser.getUsername(), userForm.getRoleName());
         //RoleToUserForm ru=new RoleToUserForm(userForm.appUser.getUsername(), userForm.roleName);
         //this.saveRoleUser(ru);

    }
    @PostMapping("/addRole")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addRoleToUser")
    public void saveRoleUser(@RequestBody RoleToUserForm roleToUserForm){
        accountService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("users/{id}")
    public  AppUser getUser(@PathVariable Long id){
        return accountService.getUserById(id);
    }

    /*
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken=request.getHeader("Authorization");
        if(authToken!=null && authToken.startsWith("Bearer ")){
            try {
                String refreshToken=authToken.substring(7);
                Algorithm algorithm=Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT=jwtVerifier.verify(refreshToken);
                String username=decodedJWT.getSubject();
                AppUser appUser=accountService.loadUserByUsername(username);
                String jwtAccessToken= JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",appUser.getAppRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken=new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",authToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){
                response.setHeader("error-message",e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else {
            throw new RuntimeException("Refresh Token Required");
        }
    }*/
    @Data
    class RoleToUserForm{
        private String username;
        private String roleName;

        public RoleToUserForm(String username, String roleName) {
            this.username = username;
            this.roleName = roleName;
        }
    }
    @Data
    public class UserForm {
        private AppUser appUser;
        private String roleName;

        public UserForm(Object userForm) {}

        public UserForm(AppUser appUser, String roleName) {
            this.appUser = appUser;
            this.roleName = roleName;
        }

        // getters and setters
    }
}
