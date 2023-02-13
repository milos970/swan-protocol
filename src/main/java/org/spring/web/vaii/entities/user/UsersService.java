package org.spring.web.vaii.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    public void save(Worker worker)
{
    this.userRepository.save(worker);
}

    public Worker getUser(final long id)
    {
        return this.userRepository.findById(id).get();
    }

    public List<Worker> getAll()
    {
        return (List<Worker>) this.userRepository.findAll();
    }

    public void delete(final long id)
    {
        this.userRepository.deleteById(id);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Worker worker = this.userRepository.findByUsername(username);

        if (worker == null) {
            //throw new UsernameNotFoundException("not found");
            return null;
        }
        return new MyUserDetails(worker);
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Worker worker = this.userRepository.findByEmail(email);

        if (worker == null) {
            return null;
        }
        return new MyUserDetails(worker);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
}

