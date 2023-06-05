package com.hobbs.tv.controller;

import com.hobbs.tv.dto.UserAndContentsDTO;
import com.hobbs.tv.dto.UserDto;
import com.hobbs.tv.entity.Country;
import com.hobbs.tv.entity.User;
import com.hobbs.tv.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "REST Apis for USer Service",
        description = "Performing CRUD operation for User service"
)
public class UserController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }

    //USER Registration
    @Operation(
            summary = "Save User REST Api",
            description = "This Api is used to add new User to the Application"
    )
    @PostMapping("/user")
    public ResponseEntity<UserDto> registrationUser(@RequestBody @Validated UserDto userDto )
    {
       LOGGER.info(String.format("User Registered --> %s",userDto));
       UserDto savedUser = userService.regUser(userDto);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Find User by ID
    @Operation(
            summary = "To find User by ID REST Api",
            description = "This Api is used to find the User Details and watch history of the user by its User ID"
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<UserAndContentsDTO> findUserDetailsByID(@PathVariable Long id)
    {
        UserAndContentsDTO userDto = userService.findByUerId(id);
        LOGGER.info(String.format("User Found for this ID --> %s",userDto));
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    //Find All User By their COUNTRY
    @GetMapping("/user/country/{country}")
    public ResponseEntity<List<User>> findUsersByCountry(@PathVariable ("country") Country country)
    {
        List<User> userList = userService.findAllUsersByCountry(country);
        LOGGER.info(String.format("Users List by Country --> %s", userList));

        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    //Change Mobile Number
    @PatchMapping("/user/mobile/{id}")
    public ResponseEntity<User> changeMobileNumber(@RequestBody User user,
                                                     @PathVariable Long id)
    {
        User updateUserMobileNum = userService.updateUserMobileNum(user, id);
        LOGGER.info(String.format("User Mobile Number Changed to --> %s",updateUserMobileNum.getMobile()));

        return new ResponseEntity<>(updateUserMobileNum, HttpStatus.OK);
    }

    // Change email for user
    @PatchMapping("/user/email/{id}")
    public ResponseEntity<User> changeUserEmail(@RequestBody User user, @PathVariable Long id)
    {
        User user1 = userService.updateUserEmail(user,id);
        LOGGER.info(String.format("User Email is Changed to --> %s", user1.getEmail()));

        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    //As user, I need to delete Account permanently
    @DeleteMapping(value = "/user/rmv/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable (value = "id") Long id)
    {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User Account is deleted, we hope you Come back !!");
    }

    // to find all
    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getUserByCountryGroup()
    {
        List<User> users = userService.getUserByCountry();
        return new ResponseEntity<>(users, HttpStatus.OK
        );
    }

    // to find the user based on country
    @GetMapping("/user/group/country")
    public ResponseEntity<Map<Country, List<User>>> groupAllUserByCountry()
    {
        Map<Country, List<User>> countryListMap = userService.getGroupUserByCountry();
        return new ResponseEntity<>(countryListMap, HttpStatus.FOUND);
    }


}
