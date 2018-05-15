package com.meklit.demo;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findByRole(String Approle);
}
