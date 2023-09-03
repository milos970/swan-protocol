package org.spring.web.vaii.entities.worker;

import org.spring.web.vaii.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService implements UserDetailsService
{
    WorkerRepository workerRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.workerRepository = workerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(Worker worker)
    {
        worker.setRole(Role.USER);
        worker.setPassword(this.bCryptPasswordEncoder.encode(worker.getPassword()));
        this.workerRepository.save(worker);
    }


    public void update(long id, Worker worker)
    {
        Worker updatingWorker = this.workerRepository.findById(id).get();

        if (!worker.getPassword().equals("")) {
            updatingWorker.setPassword(worker.getPassword());

        }

        if (!worker.getUsername().equals("")) {
            updatingWorker.setUsername(worker.getUsername());
        }

        if (!worker.getEmail().equals("")) {
            updatingWorker.setEmail(worker.getEmail());
        }

        this.workerRepository.save(updatingWorker);
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

