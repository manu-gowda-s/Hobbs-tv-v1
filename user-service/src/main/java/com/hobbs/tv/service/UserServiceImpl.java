package com.hobbs.tv.service;

import com.hobbs.tv.dto.*;
import com.hobbs.tv.entity.Country;
import com.hobbs.tv.entity.User;
import com.hobbs.tv.exception.EmailAlreadyRegisteredException;
import com.hobbs.tv.exception.ResourceNotFoundException;
import com.hobbs.tv.repo.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    private UserRepository userRepository;
    private SequenceGeneratorService sequenceGeneratorService;
    private ModelMapper modelMapper;

//    private RestTemplate restTemplate;
    private WebClient webClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Override
    public UserDto regUser(UserDto userDto) throws EmailAlreadyRegisteredException {

        //convert Dto to JPA
        User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyRegisteredException("Email Already Registered Try to LOGIN !!");
        } else {
            user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
            User savedUser = userRepository.save(user);

            // set data to regDTO and producing data to Authentication Service
            UserRegDto regDto = new UserRegDto();
            regDto.setId(savedUser.getId());
            regDto.setName(savedUser.getName());
            regDto.setEmail(savedUser.getEmail());
            regDto.setUserName(savedUser.getUserName());
            regDto.setPassword(savedUser.getPassword());

            rabbitTemplate.convertAndSend(exchange.getName(),"user_routing_key_1", regDto);

            return modelMapper.map(savedUser, UserDto.class);
        }
    }


    // using WEB CLIENT to consume data

//    @CircuitBreaker(
//            name = "${spring.application.name}",
//            fallbackMethod = "getDefaultContents"
//    )
    @Retry(
            name = "${spring.application.name}",
            fallbackMethod = "getDefaultContents"
    )
    @Override
    public UserAndContentsDTO findByUerId(Long id)
    {
        // find the user by id if not present then throw ResourceNotFoundException

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));

        UserDto userDto = modelMapper.map(user, UserDto.class);

        //web Client logic
        ContentDTO[] contentDTOS = webClient
                .get()
                .uri("http://localhost:8082/api/v1/content/" + userDto.getId())
                .retrieve()
                .bodyToMono(ContentDTO[].class)
                .block();

        //Create an object of Dto which u need the response
        UserAndContentsDTO userAndContents = new UserAndContentsDTO();
        userAndContents.setUserDto(userDto);
        userAndContents.setContentDTOS(contentDTOS);

        // finally return the response
        return userAndContents;
    }

    // FALLBACK METHOD IMPLEMENTATION IS DONE HERE

    public UserAndContentsDTO getDefaultContents(Long id, Exception exception)
    {

        LOGGER.info("getDefaultContents() log : ");

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));

        UserDto userDto = modelMapper.map(user, UserDto.class);

        ContentDTO[] contentDTO = new ContentDTO[1];
        contentDTO[0] = new ContentDTO(105L,13L,"Kirck Party",ContentType.MOVIE,Genres.ACTION,Language.KANNADA,
                                        8.9F, 299);

        UserAndContentsDTO userAndContentsDTO = new UserAndContentsDTO();
        userAndContentsDTO.setUserDto(userDto);
        userAndContentsDTO.setContentDTOS(contentDTO);

        return userAndContentsDTO;

    }


    // REST TEMPLATE
//    @Override
//    public UserAndContentsDTO findByUerId(Long id) {
//        // find the user by id if not present then throw ResourceNotFoundException
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "id", id));
//
//        // consume List of contents and storing in Array
//        ResponseEntity<ContentDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/v1/content/" + user.getId(),
//                ContentDTO[].class);
//        ContentDTO[] contentDTO = responseEntity.getBody();
//
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//
//        //after consuming setting into response
//        UserAndContentsDTO userAndContentsDTO = new UserAndContentsDTO();
//        userAndContentsDTO.setUserDto(userDto);
//        userAndContentsDTO.setContentDTOS(contentDTO);
//
//        return userAndContentsDTO;
//    }


    @Override
    public List<User> findAllUsersByCountry(Country country) {
        return userRepository.findByCountry(country);
    }

    @Override
    public User updateUserMobileNum(User user, Long id) {
        // find the User by ID
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", id);
        } else {
            User changeUser = optionalUser.get();
            changeUser.setMobile(user.getMobile());

            return userRepository.save(changeUser);
        }
    }

    @Override
    public User updateUserEmail(User user, Long id)
    {
        // find user id
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", id);
        }else {
            User changeEmail = optionalUser.get();
            changeEmail.setEmail(user.getEmail());

            return userRepository.save(changeEmail);
        }
    }

    @Override
    public void deleteUserById(Long id)
    {
        // find by id to find the user
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
         userRepository.deleteById(id);
    }

    @Override
    public List<User> getUserByCountry()
    {
    return userRepository.findAll()
             .stream()
             .filter(country -> country.getCountry() != null)
             .collect(Collectors.toList());
    }

    @Override
    public Map<Country, List<User>> getGroupUserByCountry()
    {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .filter(country -> country.getCountry() != null)
                .distinct()
                .collect(
                        Collectors.groupingBy(User::getCountry)
                );
    }

}
