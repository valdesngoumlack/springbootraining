package org.isnov.training.app.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.isnov.training.app.dto.UserGroupDTO;
import org.isnov.training.app.dto.UserPropertyResponseDTO;
import org.isnov.training.app.dto.UserResponseDTO;
import org.isnov.training.app.entities.User;
import org.isnov.training.app.entities.UserGroup;
import org.isnov.training.app.models.UserProperty;
import org.isnov.training.app.repositories.UserGroupRepository;
import org.isnov.training.app.repositories.UserPropertyRepository;
import org.isnov.training.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserGroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPropertyRepository userPropertyRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostConstruct
    private void sayYes(){
        log.warn("say Yes");
    }

    public UserResponseDTO getUserByIdWithDto(Long userId) {
        User user = getUserById(userId);
        List<UserProperty> userProperties = getUserPropertyByUser(userId);

        List<UserPropertyResponseDTO> userPropertyResponseDTOS = new ArrayList<>();
        userProperties.forEach(
                userProperty -> userPropertyResponseDTOS.add(
                UserPropertyResponseDTO
                        .builder()
                        .id(userProperty.getUserProperTyId())
                        .name(userProperty.getKey())
                        .value(userProperty.getValue())
                        .build()
        ));

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(user.getUserId())
                .resume(user.getDescription())
                .name(user.getName())
                .lName(user.getLastName())
                .group(
                        UserGroupDTO.builder()
                                .id(user.getUserGroup().getUserGroupId())
                                .name(user.getUserGroup().getName())
                                .build()
                )
                .properties(userPropertyResponseDTOS)
                .build();

        taskService.numberOfUserProperty(userProperties.size());
        messagingTemplate.convertAndSend("/topic/admin", "On recuperer les infos de "+user.getName());

        return userResponseDTO;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User createUser(long groupId, String name, String lastName, String description) throws Exception {
        if (!groupRepository.existsById(groupId))
            throw new Exception("user group not exist");

        User user = new User();
        user.setUserGroup(
                groupRepository.findById(groupId).get()
        );
        user.setName(name);
        user.setLastName(lastName);
        user.setDescription(description);
        userRepository.save(user);

        return user;
    }

    public UserProperty createUserProperty(long userId, String key, String value) throws Exception {
        if (!userRepository.existsById(userId))
            throw new Exception("user not exist");

        UserProperty userProperty = new UserProperty();
        userProperty.setUserId(userId);
        userProperty.setKey(key);
        userProperty.setValue(value);

        return userPropertyRepository.insertUserProperty(userProperty);
    }

    public List<UserProperty> getUserPropertyByUser(long userId) {
        return userPropertyRepository.getUserPropertiesByUser(userId);
    }

    public UserGroup createGroup(String name, String description) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(name);
        userGroup.setDescription(description);
        groupRepository.save(userGroup);

        return userGroup;
    }

    public UserGroup updateGroup(Long userGroupId, String name, String description) throws Exception {
        if (!groupRepository.existsById(userGroupId))
            throw new Exception("user group not exist");

        UserGroup userGroup = groupRepository.getReferenceById(userGroupId);
        userGroup.setName(name);
        userGroup.setDescription(description);
        groupRepository.save(userGroup);

        return userGroup;
    }

    public List<UserGroup> getAllGroup() {
        return groupRepository.findAll();
    }

    public List<UserGroup> getGroupByPage(Integer page, Integer size) throws Exception {
        if (page <= 0)
            throw new Exception("provide page");
        if (size <= 0)
            throw new Exception("provide size");

        return groupRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent();
    }

    public LinkedHashMap getRandomUser(){
        HttpEntity<LinkedHashMap<?, ?>> entity = new HttpEntity<>(new HttpHeaders());

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange("https://randomuser.me/api/", HttpMethod.GET, entity, LinkedHashMap.class).getBody();
    }
}
