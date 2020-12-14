package lt.idomus.takas.services;

import lt.idomus.takas.doa.RoleRepository;
import lt.idomus.takas.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role createRole(Role role) {
        return repository.save(role);
    }
}