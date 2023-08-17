package org.spring.web.vaii.entities.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService implements UserDetailsService
{
    @Autowired
    WorkerRepository workerRepository;

    public void save(Worker worker)
{
    this.workerRepository.save(worker);
}

    public Worker getUser(final long id)
    {
        return this.workerRepository.findById(id).get();
    }

    public List<Worker> getAll()
    {
        return (List<Worker>) this.workerRepository.findAll();
    }

    public void delete(final long id)
    {
        this.workerRepository.deleteById(id);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Worker worker = this.workerRepository.findByUsername(username);

        if (worker == null) {
            //throw new UsernameNotFoundException("not found");
            return null;
        }
        return new MyWorkerDetails(worker);
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Worker worker = this.workerRepository.findByEmail(email);

        if (worker == null) {
            return null;
        }
        return new MyWorkerDetails(worker);
    }


    public WorkerRepository getUserRepository() {
        return workerRepository;
    }
}

