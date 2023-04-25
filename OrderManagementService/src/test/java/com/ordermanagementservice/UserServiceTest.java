package com.ordermanagementservice;

import com.ordermanagementservice.entity.User;
import com.ordermanagementservice.repository.UserRepository;
import com.ordermanagementservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void testGetUserById() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hasdasd");
        user.setUserName("hrudesh12");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<User> actualUserById = userServiceImpl.getUserById(1L);
        assertSame(ofResult, actualUserById);
        assertTrue(actualUserById.isPresent());
        verify(userRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(2L);
        user.setOrders(new ArrayList<>());
        user.setPassword("asdas");
        user.setUserName("hrudesh12");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("hrudesh123@gmail.com");
        user2.setId(2L);
        user2.setOrders(new ArrayList<>());
        user2.setPassword("sadasda");
        user2.setUserName("hrudesh123");
        assertSame(user, userServiceImpl.createUser(user2));
        verify(userRepository).save(Mockito.<User>any());
    }


    @Test
    void testUpdateUser() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hefsf");
        user.setUserName("hrudesh12");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("hrudesh123@gmail.com");
        user2.setId(2L);
        user2.setOrders(new ArrayList<>());
        user2.setPassword("sadasdas");
        user2.setUserName("hrudesh123");
        assertSame(user, userServiceImpl.updateUser(user2));
        verify(userRepository).save(Mockito.<User>any());
    }


    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());
        assertEquals("User has been deleted successfully", userServiceImpl.deleteUser(1L));
        verify(userRepository).deleteById(Mockito.<Long>any());
    }
}
